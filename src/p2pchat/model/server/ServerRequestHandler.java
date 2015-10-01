/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.server;

import p2pchat.connection.P2PChatConnection;
import p2pchat.connection.server.IConnectionManagerServer;
import p2pchat.connection.server.TransmissionRequest;
import p2pchat.model.*;
import p2pchat.persistence.server.IPersistenceUser;

/**
 * Handler for requests to server
 *
 * @author USER
 */
public class ServerRequestHandler implements IDataHandler {

    //Variables
    private IConnectionManagerServer connectionManager;
    private ILoginController loginController;
    private IRegistrationController registrationController;
    private IOnlineUserManagerServer onlineUserManager;

    /**
     * Constructor
     *
     * @param connectionManager connection manager instance
     * @param loginController login controller instance
     * @param registration controller instance
     * @param onlineUserManager online user manager instance
     */
    public ServerRequestHandler(IConnectionManagerServer connectionManager,
            IRegistrationController registrationController,
            ILoginController loginController,
            IOnlineUserManagerServer onlineUserManager) {;
        this.connectionManager = connectionManager;
        this.loginController = loginController;
        this.registrationController = registrationController;
        this.onlineUserManager = onlineUserManager;
    }

    /**
     * Handle request
     *
     * @param obj data container
     */
    @Override
    public void handleData(Object obj) {
        TransmissionRequest data = (TransmissionRequest) obj;

        //Handle session termination
        if (data.getData() == null) {
            handleEndSession(data.getSession());
            return;
        }

        //Create message
        Message message = new Message(data.getData());

        switch (message.getType()) {
            case PEER_TO_SERVER_TYPE:
                handlePeerToServerMessage(data.getSession(), data.getAddress(), data.getPort(), message);
                break;
            default:
                System.err.println("Warning: ServerRequestHandler - Unknown message type: " + message.getType());
        }
    }

    /**
     * Handle message sent by peer to server
     *
     * @param session session number
     * @param address peer address
     * @param port peer port
     * @param message message
     */
    private void handlePeerToServerMessage(int session, String address, int port, Message message) {

        switch (message.getSubtype()) {
            case LOGIN_REQUEST_SUBTYPE:
                handleLoginRequestMessage(session, address, port, message);
                break;
            case LOGOUT_REQUEST_SUBTYPE:
                handleLogoutRequestMessage(session, address, port, message);
                break;
            case REGISTRATION_REQUEST_SUBTYPE:
                handleRegistrationRequestMessage(session, address, port, message);
                break;
            case ADD_FRIEND_REQUEST_SUBTYPE:
                handleAddFriendRequestMessage(session, address, port, message);
                break;
            case REMOVE_FRIEND_REQUEST_SUBTYPE:
                handleRemoveFriendRequestMessage(session, address, port, message);
                break;
            default:
                System.err.println("Warning: ServerRequestHandler - Unknown message subtype: " + message.getSubtype());
        }

    }

    /**
     * Handle login request message
     *
     * @param session session number
     * @param address peer addredd
     * @param port peer port
     * @param message message
     */
    private void handleLoginRequestMessage(int session, String address, int port, Message message) {

        //Attempt to login
        User loggedInUser = loginController.login(session, address, port, message.getField1(), message.getField2());
        onlineUserManager.addOnlineUser(loggedInUser);

        Message reply;

        if (loggedInUser != null) {
            //Create accept message
            reply = MessageFactory.createLoginAcceptMessage(message.getField1(), loggedInUser.getAddress(), loggedInUser.getPort());
        } else {
            //Create reject message
            reply = MessageFactory.createLoginRejectMessage(message.getField1(), ErrorType.REMOTE_ACTION_UNSUCCESSFUL);
        }

        TransmissionRequest data = new TransmissionRequest(session, address, port, reply);

        connectionManager.sendToClient(data);
    }

    /**
     * Handle logout request message
     *
     * @param session session number
     * @param address peer address
     * @param port peer port
     * @param message message
     */
    private void handleLogoutRequestMessage(int session, String address, int port, Message message) {
        loginController.logout(message.getField1());

        User onlineUsers[] = onlineUserManager.getOnlineUsers();

        Message update = MessageFactory.createRemoveFriendAcceptMessage(message.getField1());
        TransmissionRequest data;

        for (User u : onlineUsers) {
            if (u != null) {
                data = new TransmissionRequest(u.getSession(), u.getAddress(), u.getPort(), update);

                connectionManager.sendToClient(data);
            }
        }
    }

    /**
     * Handle registration request message
     *
     * @param session session number
     * @param address peer address
     * @param portpeer port
     * @param message message
     */
    private void handleRegistrationRequestMessage(int session, String address, int port, Message message) {
        boolean result = registrationController.register(message.getField1(), message.getField2(), message.getField3(), "");

        Message reply;

        if (result == true) {
            reply = MessageFactory.createRegistrationAcceptMessage(message.getField1());
        } else {
            reply = MessageFactory.createRegistrationRejectMessage(message.getField1(), ErrorType.REMOTE_ACTION_UNSUCCESSFUL);
        }

        //Create data container
        TransmissionRequest data = new TransmissionRequest(session, address, port, reply);

        //Send reply
        connectionManager.sendToClient(data);
    }

    /**
     * Handle add friend request message
     *
     * @param session session number
     * @param address peer address
     * @param port peer port number
     * @param message message
     */
    private void handleAddFriendRequestMessage(int session, String address, int port, Message message) {

        //Obtain requestor and acceptor instances from online users
        User requestor = onlineUserManager.findOnlineUser(message.getField1());
        User acceptor = onlineUserManager.findOnlineUser(message.getField2());

        //Requestor and and acceptor may not be empty or equal
        if (requestor != null && acceptor != null && !requestor.getUsername().equals(acceptor.getUsername())) {

            //Create a copy of the message for both requestor and acceptor
            Message reply1 = MessageFactory.createAddFriendAcceptMessage(requestor.getUsername(), requestor.getAddress(), requestor.getPort());
            Message reply2 = MessageFactory.createAddFriendAcceptMessage(acceptor.getUsername(), acceptor.getAddress(), acceptor.getPort());

            //Create data container for both messages
            TransmissionRequest data1 = new TransmissionRequest(requestor.getSession(), requestor.getAddress(), requestor.getPort(), reply2);
            TransmissionRequest data2 = new TransmissionRequest(acceptor.getSession(), acceptor.getAddress(), acceptor.getPort(), reply1);

            //Send both messages
            connectionManager.sendToClient(data1);
            connectionManager.sendToClient(data2);

        } else {

            Message reply;

            //Create rejection message
            reply = MessageFactory.createAddFriendRejectMessage(message.getField1(), ErrorType.REMOTE_ACTION_UNSUCCESSFUL);

            //Create data container
            TransmissionRequest data = new TransmissionRequest(session, address, port, reply);

            //Send the message
            connectionManager.sendToClient(data);
        }


    }

    /**
     * Handle remove friend request message
     *
     * @param session session number
     * @param address peer address
     * @param port peer port number
     * @param message message
     */
    private void handleRemoveFriendRequestMessage(int session, String address, int port, Message message) {

        //Obtain requestor and acceptor instances from online users
        User requestor = onlineUserManager.findOnlineUser(message.getField1());
        User acceptor = onlineUserManager.findOnlineUser(message.getField2());

        //Requestor and and acceptor may not be empty or equal
        if (requestor != null && acceptor != null && !requestor.getUsername().equals(acceptor.getUsername())) {

            //Create a copy of the message for both requestor and acceptor
            Message reply1 = MessageFactory.createRemoveFriendAcceptMessage(requestor.getUsername());
            Message reply2 = MessageFactory.createRemoveFriendAcceptMessage(acceptor.getUsername());

            //Create data container for both messages
            TransmissionRequest data1 = new TransmissionRequest(requestor.getSession(), requestor.getAddress(), requestor.getPort(), reply2);
            TransmissionRequest data2 = new TransmissionRequest(acceptor.getSession(), acceptor.getAddress(), acceptor.getPort(), reply1);

            //Send both messages
            connectionManager.sendToClient(data1);
            connectionManager.sendToClient(data2);

        } else {

            Message reply;

            //Create rejection message
            reply = MessageFactory.createRemoveFriendRejectMessage(message.getField1(), ErrorType.REMOTE_ACTION_UNSUCCESSFUL);

            //Create data container
            TransmissionRequest data = new TransmissionRequest(session, address, port, reply);

            //Send the message
            connectionManager.sendToClient(data);
        }


    }

    /**
     * Handle end of session (when terminating improperly)
     *
     * @param session session number
     */
    private void handleEndSession(int session) {

        //Terminate the session
        User tmp = onlineUserManager.endSession(session);

        //Check if user was found
        if (tmp == null) {
            System.err.println("Error: ServerRequestHandler - Connection lost");
        }

        //Get all online users
        User onlineUsers[] = onlineUserManager.getOnlineUsers();

        //Create rejection message
        Message update = MessageFactory.createRemoveFriendAcceptMessage(tmp.getUsername());

        //Create data container
        TransmissionRequest data;

        for (User u : onlineUsers) {
            if (u != null) {

                //Create data container for each online user
                data = new TransmissionRequest(u.getSession(), u.getAddress(), u.getPort(), update);

                //Send message to each online user
                connectionManager.sendToClient(data);
            }
        }
    }
}
