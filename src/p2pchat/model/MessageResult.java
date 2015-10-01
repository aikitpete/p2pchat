/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model;

/**
 * Enumeration representing the message result
 *
 * @author USER
 */
public enum MessageResult {

    ACCEPT_RESULT("ACCEPT_RESULT"),
    REJECT_RESULT("REJECT_RESULT"),
    ERROR_RESULT("ERROR_RESULT"),
    NONE_RESULT("NONE_RESULT");
    private final String text;

    /**
     * Constructor
     *
     * @param text message result text
     */
    private MessageResult(final String text) {
        this.text = text;
    }

    /**
     * To string value
     *
     * @return
     */
    @Override
    public String toString() {

        return text;
    }
}
