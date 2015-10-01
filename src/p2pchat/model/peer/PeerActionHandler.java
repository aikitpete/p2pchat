/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.peer;

import p2pchat.IP2PChat;
import p2pchat.model.User;
import p2pchat.connection.server.TransmissionRequest;
import p2pchat.connection.peer.IConnectionManagerPeer;
import p2pchat.controller.P2PChatController;
import p2pchat.model.*;
import p2pchat.view.IViewManager;
import p2pchat.view.ViewConstants;

/**
 * Class representing peer action handler
 *
 * @author USER
 */
public class PeerActionHandler implements IPeerActionHandler {

    //Constants
    public static final String DEFAULT_INPUT = "";
    //Variables
    IConnectionManagerPeer connectionManager;
    IOnlineUserManagerPeer peerManager;
    IViewManager viewManager;
    IP2PChat p2pChat;

    /**
     * Constructor
     *
     * @param connectionManager connection manager
     * @param peerManager message manager
     */
    public PeerActionHandler(IP2PChat p2pChat, IConnectionManagerPeer connectionManager, IOnlineUserManagerPeer peerManager, IViewManager viewManager) {
        this.connectionManager = connectionManager;
        this.peerManager = peerManager;
        this.viewManager = viewManager;
        this.p2pChat = p2pChat;
    }

    /**
     * Method used to handle status update
     *
     * @param args command arguments
     */
    @Override
    public void handleStatusAction(String args[]) {

        StatusType command = StatusType.valueOf(args[0]);

        //Check for repeating command
        if (command == peerManager.getStatus()) {
            return;
        }

        //Update status
        if (command == StatusType.ONLINE) {
            if (!peerManager.isOnline()) {
                viewManager.displayLogin();
            } else {
                //    System.err.println("Warning: PeerActionHandler - Already online");
            }
        } else if (command == StatusType.OFFLINE) {
            if (peerManager.isOnline()) {

                //Send message to server
                Message message = MessageFactory.createLogoutRequestMessage(peerManager.getCurrentUser());
                connectionManager.sendToServer(message.toStringArray());

                //Close peer connections
                connectionManager.closePeerConnections();

                peerManager.setStatus(StatusType.OFFLINE);
                viewManager.setStatus(StatusType.OFFLINE);
                viewManager.setStatusCaption(StatusType.OFFLINE, null, null);
                viewManager.addMessage(ViewConstants.LOGOUT_MESSAGE);

            } else {
                //    System.err.println("Warning: PeerActionHandler - Already offline");
            }
        }
    }

    /**
     * Handle send action
     */
    @Override
    public void handleSendAction() {

        //Check online status
        if (!peerManager.isOnline()) {
            viewManager.displayError(ErrorType.NOT_ONLINE);
            return;
        }

        //Check if other users are online
        if (peerManager.getOnlineUsersNumber() == 0) {
            viewManager.displayError(ErrorType.CHAT_EMPTY);
            return;
        }

        //Get user input
        String userInput = viewManager.getUserInput();

        //Check short input length
        if (userInput == null || userInput.length() == 0) {
            viewManager.displayError(ErrorType.MESSAGE_LENGTH_SHORT);
            return;
        }

        //Check long input length
        if (userInput.length() > 150) {
            viewManager.displayError(ErrorType.MESSAGE_LENGTH_LONG);
            return;
        }

        //Get username
        String username = peerManager.getCurrentUser();

        //Get online users in chat
        int recipientLength = peerManager.getOnlineUsersNumber();
        User recipients[] = peerManager.getUsers();

        String recipientString = "";

        //Create recipients field
        for (int i = 0; i < recipients.length; i++) {//TODO
            if (recipients[i] != null) {
                recipientString = recipientString + " " + recipients[i].getUsername();
            }
        }
System.out.println("REC_STR: "+recipientString);
        Message message = MessageFactory.createChatMessage(username, recipientString, userInput);
        String messageData[] = message.toStringArray();
        TransmissionRequest request;

        for (int i = 0; i < recipients.length; i++) {//TODO

            if (recipients[i] != null) {

                //Create data to send
                request = new TransmissionRequest(recipients[i].getAddress(), recipients[i].getPort(), messageData);

                //Send the message
                connectionManager.sendToPeer(request);

            }

        }

        viewManager.addMessage("(" + message.getTime() + ") " + message.getField1() + " -> " + message.getField2() + ":\n" + message.getField3());
        viewManager.setUserInput(DEFAULT_INPUT);
    }

    /**
     * Handle quit action
     */
    @Override
    public void handleQuitAction() {
        p2pChat.exit();
    }

    /**
     * Handle sessing action
     *
     * @param address server address
     * @param port server port
     */
    @Override
    public void handleSettingsAction(String address, String port) {
        int portNumber;

        //Check null address or port
        if (address == null || port == null) {
            viewManager.displayError(ErrorType.INVALID_CONTENT);
            return;
        }

        //Check address and port length
        if (address.length() == 0 || port.length() == 0) {
            viewManager.displayError(ErrorType.INVALID_CONTENT);
            return;
        }

        //Check number format
        try {
            portNumber = Integer.parseInt(port);
        } catch (NumberFormatException ex) {
            viewManager.displayError(ErrorType.INVALID_CONTENT);
            return;
        }

        //Check if port is valid
        if (portNumber < 0 || portNumber > 65535) {
            viewManager.displayError(ErrorType.INVALID_CONTENT);
            return;
        }

        connectionManager.setServerAddress(address);
        connectionManager.setServerPort(portNumber);
    }

    /**
     * Handle register action
     *
     * @param username username
     * @param password password
     * @param rePassword retype password
     */
    @Override
    public void handleRegisterAction(String username, String password, String rePassword) {

        //Check null
        if (username == null || password == null || rePassword == null) {
            viewManager.displayError(ErrorType.BLANK_FIELD);
            return;
        }

        //Check username length
        if (username.length() == 0) {
            viewManager.displayError(ErrorType.BLANK_FIELD);
            return;
        }

        //Check password length
        if (password.length() == 0) {
            viewManager.displayError(ErrorType.BLANK_FIELD);
            return;
        }

        //Check rePassword length
        if (rePassword.length() == 0) {
            viewManager.displayError(ErrorType.BLANK_FIELD);
            return;
        }

        //Check if passwords match
        if (!password.equals(rePassword)) {
            viewManager.displayError(ErrorType.PASSWORDS_DONT_MATCH);
            return;
        }

        Message message = MessageFactory.createRegistrationRequestMessage(username, password, rePassword);

        //Send the message
        connectionManager.sendToServer(message.toStringArray());
    }

    /**
     * Handle friend action
     *
     * @param friendname friendname
     */
    @Override
    public void handleAddFriendAction(String friendname) {
        //Check friendname length
        if (friendname.length() == 0) {
            viewManager.displayError(ErrorType.BLANK_FIELD);
            return;
        }

        //Check if friend is not user himself
        if (peerManager.getCurrentUser().equals(friendname)) {
            viewManager.displayError(ErrorType.FRIEND_INPUT_OWNSELF);
            return;
        }

        //Check if already chatting with the friend
        if (peerManager.findOnlineUser(friendname) != null) {
            viewManager.displayError(ErrorType.FRIEND_ALREADY_CHATTING);
            return;
        }

        Message message = MessageFactory.createAddFriendRequestMessage(peerManager.getCurrentUser(), friendname);

        //Send the message
        connectionManager.sendToServer(message.toStringArray());
    }

    /**
     * Handle remove friend action
     *
     * @param friendname friendname
     */
    @Override
    public void handleRemoveFriendAction(String friendname) {
        //Check friendname length
        if (friendname == null || friendname.length() == 0) {
            viewManager.displayError(ErrorType.BLANK_FIELD);
            return;
        }

        //Check if friend is not user himself
        if (peerManager.getCurrentUser().equals(friendname)) {
            viewManager.displayError(ErrorType.FRIEND_INPUT_OWNSELF);
            return;
        }

        //Check if already chatting with the friend
        if (peerManager.findOnlineUser(friendname) == null) {
            viewManager.displayError(ErrorType.FRIEND_NOT_CHATTING);
            return;
        }

        Message message = MessageFactory.createRemoveFriendRequestMessage(peerManager.getCurrentUser(), friendname);

        //Send the message
        connectionManager.sendToServer(message.toStringArray());
    }

    /**
     * Handle login action
     *
     * @param username username
     * @param password password
     */
    @Override
    public void handleLoginAction(String username, String password) {
        //Check username length
        if (username == null || username.length() == 0) {
            viewManager.displayError(ErrorType.BLANK_FIELD);
            return;
        }

        //Check password length
        if (password == null || password.length() == 0) {
            viewManager.displayError(ErrorType.BLANK_FIELD);
            return;
        }

        Message message = MessageFactory.createLoginRequestMessage(username, password);

        //Send the message
        connectionManager.sendToServer(message.toStringArray());
    }
}
