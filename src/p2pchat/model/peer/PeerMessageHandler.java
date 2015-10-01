/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.peer;

import p2pchat.connection.peer.IConnectionManagerPeer;
import p2pchat.controller.P2PChatController;
import p2pchat.model.IDataHandler;
import p2pchat.model.Message;
import p2pchat.model.StatusType;
import p2pchat.view.IViewManager;
import p2pchat.view.ViewConstants;

/**
 * Class for handling messages originated by peer
 *
 * @author USER
 */
public class PeerMessageHandler implements IDataHandler {

    IOnlineUserManagerPeer peerManager;
    IConnectionManagerPeer connectionManager;
    IViewManager viewManager;

    /**
     * Constructor
     *
     * @param connectionManager connection manager
     * @param peerManager peer manager
     */
    public PeerMessageHandler(IConnectionManagerPeer connectionManager, IOnlineUserManagerPeer peerManager, IViewManager viewManager) {
        this.peerManager = peerManager;
        this.connectionManager = connectionManager;
        this.viewManager = viewManager;
    }

    /**
     * Handle data fom listener
     *
     * @param obj data
     */
    @Override
    public void handleData(Object obj) {
        String[] data = (String[]) obj;
        Message message = new Message(data);

        switch (message.getType()) {
            case SERVER_TO_PEER_TYPE:
                handleServerToPeerMessage(message);
                break;
            case PEER_TO_PEER_TYPE:
                handlePeerToPeerMessage(message);
                break;
            default:
                System.err.println("Unknown message type: " + message.getType());
        }
    }

    /**
     * Method for handling peer to peer messages
     *
     * @param message message
     */
    private void handlePeerToPeerMessage(Message message) {
        switch (message.getSubtype()) {
            case CHAT_SUBTYPE:
                handleChatMessage(message);
                break;
        }
    }

    /**
     * Method for handling server to peer messages
     *
     * @param message message
     */
    private void handleServerToPeerMessage(Message message) {
        switch (message.getSubtype()) {
            case LOGIN_RESPONSE_SUBTYPE:
                handleLoginMessage(message);
                break;
            case REGISTRATION_RESPONSE_SUBTYPE:
                handleRegistrationMessage(message);
                break;
            case ADD_FRIEND_RESPONSE_SUBTYPE:
                handleAddFriendMessage(message);
                break;
            case REMOVE_FRIEND_RESPONSE_SUBTYPE:
                handleRemoveFriendMessage(message);
                break;

        }
    }

    /**
     * Method for handling login message
     *
     * @param message login message
     */
    private void handleLoginMessage(Message message) {
        switch (message.getResult()) {
            case ACCEPT_RESULT:

                //Initialize connection
                connectionManager.startPeer(Integer.valueOf(message.getField3()));

                //Update model
                peerManager.setStatus(StatusType.ONLINE);
                peerManager.setUser(message.getField1(), null);

                //Display login message
                viewManager.addMessage(ViewConstants.LOGIN_MESSAGE);

                //Update view
                viewManager.setUserImage("profile/"+message.getField1()+".jpg");
                viewManager.setStatusCaption(StatusType.ONLINE, message.getField1(), peerManager.getOnlineUserNames());
                viewManager.setStatus(StatusType.ONLINE);

                //Set main display focus
                viewManager.setFocus();

                break;
            case REJECT_RESULT:
                //Display error
                viewManager.displayError(message.getField2());
                break;
        }
    }

    /**
     * Method for handling registration
     *
     * @param message message received
     */
    private void handleRegisterMessage(Message message) {
        switch (message.getResult()) {
            case ACCEPT_RESULT:
                viewManager.setFocus();
                break;
            case REJECT_RESULT:
                viewManager.displayError(message.getField2());
                break;
        }
    }

    /**
     * Method for handling add friend request
     *
     * @param message message
     */
    private void handleAddFriendMessage(Message message) {
        switch (message.getResult()) {
            case ACCEPT_RESULT:
        
                boolean result = peerManager.addOnlineUser(message.getField1(), message.getField2(), Integer.valueOf(message.getField3()));
 
                if (result) {
                    viewManager.setStatusCaption(peerManager.getStatus(), peerManager.getCurrentUser(), peerManager.getOnlineUserNames());
                    viewManager.setFocus();
                }
                break;
            case REJECT_RESULT:
                
                peerManager.removeOnlineUser(message.getField1());
                viewManager.setStatusCaption(peerManager.getStatus(), peerManager.getCurrentUser(), peerManager.getOnlineUserNames());
                if (message.getField2() != null) {
                    viewManager.displayError(message.getField2());
                }
                
                break;
        }
    }
    
        /**
     * Method for handling friend request
     *
     * @param message message
     */
    private void handleRemoveFriendMessage(Message message) {
        switch (message.getResult()) {
            case ACCEPT_RESULT:
                peerManager.removeOnlineUser(message.getField1());
                viewManager.setStatusCaption(peerManager.getStatus(), peerManager.getCurrentUser(), peerManager.getOnlineUserNames());
                
                //Set main display focus
                viewManager.setFocus();
                
                break;
            case REJECT_RESULT:
                viewManager.setStatusCaption(peerManager.getStatus(), peerManager.getCurrentUser(), peerManager.getOnlineUserNames());
                if (message.getField2() != null) {
                    viewManager.displayError(message.getField2());
                }
                break;
        }
    }

    /**
     * Method for handling chat request
     *
     * @param message message received
     */
    private void handleChatMessage(Message message) {
        viewManager.addMessage("(" + message.getTime() + ") " + message.getField1() + " -> " + message.getField2() + ":\n" + message.getField3());
    }

    /**
     * Method for handling registration request
     *
     * @param message message received
     */
    private void handleRegistrationMessage(Message message) {
        switch (message.getResult()) {
            case ACCEPT_RESULT:
                viewManager.addMessage(message.getField1() + " registered");
                viewManager.setFocus();
                break;
            case REJECT_RESULT:
                viewManager.displayError(message.getField2());
                break;
        }
    }
}
