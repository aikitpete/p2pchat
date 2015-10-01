/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model;

/**
 * Enumeration containing error types and descriptions
 * @author USER
 */
public enum ErrorType {
    NO_ERROR("NO_ERROR", "", 0),
    MESSAGE_LENGTH_SHORT("MESSAGE_LENGTH_SHORT","Please enter message text", 1),
    MESSAGE_LENGTH_LONG("MESSAGE_LENGTH_LONG","Message exceeds 140 characters.", 2), 
    PASSWORDS_DONT_MATCH("PASSWORDS_DONT_MATCH","Passwords don't match.", 3), 
    BLANK_REGISTRATION_FIELD("BLANK_REGISTRATION_FIELD","Username and password can't be blank.", 4), 
    ALREADY_ONLINE("ALREADY_ONLINE","Already online.", 5), 
    BLANK_FIELD("BLANK_FIELD","Field must be filled before submitting.", 6), 
    REMOTE_ACTION_UNSUCCESSFUL("REMOTE_ACTION_UNSUCCESSFUL","Remote action unsuccessful.", 7),
    FRIEND_LENGTH_SHORT("FRIEND_LENGTH_SHORT","Please enter friend name.", 8), 
    NOT_ONLINE("NOT_ONLINE","You are not online.", 9),
    ALREADY_REGISTERED("ALREADY_REGISTERED","Already registered.", 10),
    FULL_USER_LIST("FULL_USER_LIST","User list is full.", 11),
    FULL_ONLINE_USER_LIST("FULL_ONLINE_USER_LIST","Online user list is full.", 11),
    FRIEND_ALREADY_CHATTING("FRIEND_ALREADY_CHATTING","Already chatting with selected user.", 12),
    FRIEND_INPUT_OWNSELF("FRIEND_INPUT_OWNSELF","Can't input ownself.", 13),
    CHAT_EMPTY("CHAT_EMPTY","Chat is empty.", 14),
    INVALID_CONTENT("INVALID_CONTENT", "Content is invalid", 15),
    FRIEND_NOT_CHATTING("FRIEND_NOT_CHATTING", "Selected friend is not in chat", 16)
    
    ;
    /**
     * Constructor
     * @param text
     */
    private ErrorType(final String text, final String caption, final int number) {
        this.text = text;
        this.caption = caption;
        this.number = number;
    }

    private final String text;
    private final String caption;
    private final int number;

    /**
     * To String value
     */
    @Override
    public String toString() {
        return caption;
    }
    
    /**
     * Get error name
     * @return error name
     */
    public String getName() {
        return text;
    }
    
        /**
     * Get error caption
     * @return error name
     */
    public String getCaption() {
        return caption;
    }
    
    /**
     * Get error number
     * @return error number
     */
    public int getNumber() {
        return number;
    }
}
