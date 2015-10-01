/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model;

/**
 * Class representing the message subtype
 * @author USER
 */
public enum MessageSubtype {

    CHAT_SUBTYPE("CHAT_SUBTYPE"),
    REGISTRATION_REQUEST_SUBTYPE("REGISTRATION_REQUEST_SUBTYPE"),
    REGISTRATION_RESPONSE_SUBTYPE("REGISTRATION_RESPONSE_SUBTYPE"),
    LOGIN_REQUEST_SUBTYPE("LOGIN_REQUEST_SUBTYPE"),
    LOGIN_RESPONSE_SUBTYPE("LOGIN_RESPONSE_SUBTYPE"),
    LOGOUT_REQUEST_SUBTYPE("LOGOUT_REQUEST_SUBTYPE"),
    LOGOUT_RESPONSE_SUBTYPE("LOGOUT_RESPONSE_SUBTYPE"),
    ADD_FRIEND_REQUEST_SUBTYPE("ADD_FRIEND_REQUEST_SUBTYPE"),
    ADD_FRIEND_RESPONSE_SUBTYPE("ADD_FRIEND_RESPONSE_SUBTYPE"),
    REMOVE_FRIEND_REQUEST_SUBTYPE("REMOVE_FRIEND_REQUEST_SUBTYPE"),
    REMOVE_FRIEND_RESPONSE_SUBTYPE("REMOVE_FRIEND_RESPONSE_SUBTYPE");
    private final String text;

    /**
     * Constructor
     * @param text message subtype text
     */
    private MessageSubtype(final String text) {
        this.text = text;
    }

    /**
     * To string value
     * @return string value
     */
    @Override
    public String toString() {

        return text;
    }
}
