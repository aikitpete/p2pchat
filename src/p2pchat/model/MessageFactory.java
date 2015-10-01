/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model;

import utils.DateUtils;

/**
 * Class for creating messages (Factory pattern)
 * @author USER
 */
public class MessageFactory {

    //LOGIN
    
    /**
     * Create message used by peer to log in
     * @param username username
     * @param password password
     * @return message
     */
    public static Message createLoginRequestMessage(String username, String password) {
        return new Message(
                MessageType.PEER_TO_SERVER_TYPE,
                MessageSubtype.LOGIN_REQUEST_SUBTYPE,
                MessageResult.NONE_RESULT,
                username,
                password,
                "",
                DateUtils.getTime());
    }

    /**
     * Create message used by server to confirm login
     * @param username username
     * @param address address
     * @param session session number
     * @return message
     */
    public static Message createLoginAcceptMessage(String username, String address, int session) {
        return new Message(
                MessageType.SERVER_TO_PEER_TYPE,
                MessageSubtype.LOGIN_RESPONSE_SUBTYPE,
                MessageResult.ACCEPT_RESULT,
                username,
                address,
                String.valueOf(session),
                DateUtils.getTime());
    }

    /**
     * Create message used by server to reject login
     * @param username username
     * @return message
     */
    public static Message createLoginRejectMessage(String username, ErrorType error) {
        return new Message(
                MessageType.SERVER_TO_PEER_TYPE,
                MessageSubtype.LOGIN_RESPONSE_SUBTYPE,
                MessageResult.REJECT_RESULT,
                username,
                error.getName(),
                "",
                DateUtils.getTime());
    }

    //REGISTRATION
    
    /**
     * Create message used by peer to request registration
     * @param username username
     * @param password password
     * @param rePassword re-type password
     * @return message
     */
    public static Message createRegistrationRequestMessage(String username, String password, String rePassword) {
        return new Message(
                MessageType.PEER_TO_SERVER_TYPE,
                MessageSubtype.REGISTRATION_REQUEST_SUBTYPE,
                MessageResult.NONE_RESULT,
                username,
                password,
                rePassword,
                DateUtils.getTime());
    }

    /**
     * Create message used by server to accept registration
     * @param username username
     * @return message
     */
    public static Message createRegistrationAcceptMessage(String username) {
        return new Message(
                MessageType.SERVER_TO_PEER_TYPE,
                MessageSubtype.REGISTRATION_RESPONSE_SUBTYPE,
                MessageResult.ACCEPT_RESULT,
                username,
                "",
                "",
                DateUtils.getTime());
    }

    /**
     * Create message used by server to reject registration
     * @param username username
     * @return message
     */
    public static Message createRegistrationRejectMessage(String username, ErrorType error) {
        return new Message(
                MessageType.SERVER_TO_PEER_TYPE,
                MessageSubtype.REGISTRATION_RESPONSE_SUBTYPE,
                MessageResult.REJECT_RESULT,
                username,
                error.getName(),
                "",
                DateUtils.getTime());
    }

    //FRIEND
    
    /**
     * Create message used by peer to request friend
     * @param requester user who requested adding friend
     * @param acceptor user being added
     * @return message
     */
    public static Message createAddFriendRequestMessage(String requester, String acceptor) {
        return new Message(
                MessageType.PEER_TO_SERVER_TYPE,
                MessageSubtype.ADD_FRIEND_REQUEST_SUBTYPE,
                MessageResult.NONE_RESULT,
                requester,
                acceptor,
                "",
                DateUtils.getTime());
    }

    /**
     * Create message used by server to accept friend request
     * @param username username
     * @param address address
     * @param port port
     * @return message
     */
    public static Message createAddFriendAcceptMessage(String username, String address, int port) {
        return new Message(
                MessageType.SERVER_TO_PEER_TYPE,
                MessageSubtype.ADD_FRIEND_RESPONSE_SUBTYPE,
                MessageResult.ACCEPT_RESULT,
                username,
                address,
                String.valueOf(port),
                DateUtils.getTime());
    }

    /**
     * Create message used by server to reject friend request
     * @param username username
     * @return message
     */
    public static Message createAddFriendRejectMessage(String username, ErrorType error) {
        return new Message(
                MessageType.SERVER_TO_PEER_TYPE,
                MessageSubtype.ADD_FRIEND_RESPONSE_SUBTYPE,
                MessageResult.REJECT_RESULT,
                username,
                error.getName(),
                "",
                DateUtils.getTime());
    }

    //PROFILE
    
    /**
     * Create message used by peer to request profile
     * @param username username
     * @return message
     */
    public static Message createRemoveFriendRequestMessage(String requestor, String username) {
        return new Message(
                MessageType.PEER_TO_SERVER_TYPE,
                MessageSubtype.REMOVE_FRIEND_REQUEST_SUBTYPE,
                MessageResult.NONE_RESULT,
                requestor,
                username,
                "",
                DateUtils.getTime());
    }

    /**
     * Create message used by server to accept profile request
     * @param username username
     * @return message
     */
    public static Message createRemoveFriendAcceptMessage(String username) {
        return new Message(
                MessageType.SERVER_TO_PEER_TYPE,
                MessageSubtype.REMOVE_FRIEND_RESPONSE_SUBTYPE,
                MessageResult.ACCEPT_RESULT,
                username,
                "",
                "",
                DateUtils.getTime());
    }

    /**
     * Create message used by server to reject profile request
     * @param username username
     * @return message
     */
    public static Message createRemoveFriendRejectMessage(String username, ErrorType error) {
        return new Message(
                MessageType.SERVER_TO_PEER_TYPE,
                MessageSubtype.REMOVE_FRIEND_RESPONSE_SUBTYPE,
                MessageResult.REJECT_RESULT,
                username,
                error.getName(),
                "",
                DateUtils.getTime());
    }

    //LOGOUT
    
    /**
     * Create message used by peer to request logout
     * @param username username
     * @return message
     */
    public static Message createLogoutRequestMessage(String username) {
        return new Message(
                MessageType.PEER_TO_SERVER_TYPE,
                MessageSubtype.LOGOUT_REQUEST_SUBTYPE,
                MessageResult.NONE_RESULT,
                username,
                "",
                "",
                DateUtils.getTime());
    }

    /**
     * Create message used by server to accept logout
     * @param username username
     * @return message
     */
    public static Message createLogoutAcceptMessage(String username) {
        return new Message(
                MessageType.SERVER_TO_PEER_TYPE,
                MessageSubtype.LOGOUT_RESPONSE_SUBTYPE,
                MessageResult.ACCEPT_RESULT,
                username,
                "",
                "",
                DateUtils.getTime());
    }

    /**
     * Create message used by server to reject logout
     * @param username username
     * @return message
     */
    public static Message createLogoutRejectMessage(String username, ErrorType error) {
        return new Message(
                MessageType.SERVER_TO_PEER_TYPE,
                MessageSubtype.LOGOUT_RESPONSE_SUBTYPE,
                MessageResult.REJECT_RESULT,
                username,
                error.getName(),
                "",
                DateUtils.getTime());
    }

    //CHAT
    
    /**
     * Create chat message sent between two peers
     * @param username username
     * @param recipientString recipients
     * @param userInput input
     * @return message
     */
    public static Message createChatMessage(String username, String recipientString, String userInput) {
        return new Message(
                MessageType.PEER_TO_PEER_TYPE,
                MessageSubtype.CHAT_SUBTYPE,
                MessageResult.NONE_RESULT,
                username,
                recipientString,
                userInput,
                DateUtils.getTime());
    }
}
