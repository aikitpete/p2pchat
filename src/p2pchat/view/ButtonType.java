/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view;

/**
 * Enumeration containing types of buttons and descriptions
 *
 * @author USER
 */
public enum ButtonType {

    REMOVE_FRIEND("REMOVE_FRIEND", "Remove Friend", "Remove Friend From Chat"),
    ADD_FRIEND("ADD_FRIEND", "Add Friend", "Add Friend to Chat"),
    REGISTER("REGISTER", "Register", "Register New User"),
    PROFILE("PROFILE", "Profile", "Change your profile photo"),
    SETTINGS("SETTINGS", "Settings", "Connection settings"),
    ONLINE("ONLINE", "Online", "Go Online"),
    OFFLINE("OFFLINE", "Offline", "Go Offline"),
    STATUS("STATUS", "Status", "Update your status"),
    INPUT_BOX("INPUT_BOX", "Message Input", "Type your message here"),
    SUBMIT_REGISTER("SUBMIT_REGISTER", "Submit", "Submit Data"),
    SUBMIT_MESSAGE("SUBMIT_MESSAGE", "Submit", "Send Message"),
    SUBMIT_LOGIN("SUBMIT_LOGIN", "Submit", "Submit Data"),
    SUBMIT_ADD_FRIEND("SUBMIT_ADD_FRIEND", "Submit", "Submit Data"),
    SUBMIT_REMOVE_FRIEND("SUBMIT_REMOVE_FRIEND", "Submit", "Submit Data"),
    SUBMIT_SETTINGS("SUBMIT_SETTINGS", "Submit", "Submit Data"),
    SIGN_IN("SIGN_IN", "Sign in", "Log in to Chat"),
    CANCEL("CANCEL", "Cancel", "Cancel"),
    FEATURES("FEATURES", "Features", null),
    FILE("FILE", "File", "File Menu"),
    QUIT("QUIT", "Quit", "Quit P2PChat");
    private final String text;
    private final String caption;
    private final String description;

    /**
     * Constructor
     *
     * @param text text
     * @param caption caption displayed
     * @param description description (tooltips, accessible content, etc.)
     */
    private ButtonType(final String text, final String caption, final String description) {
        this.text = text;
        this.caption = caption;
        this.description = description;
    }

    /**
     * To string value
     * @return string value
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return text;
    }

    /**
     * Get button name as string
     * @return name as string
     */
    public String getText() {
        return text;
    }

    /**
     * Get button caption
     * @return caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Get button description
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
