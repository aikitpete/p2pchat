/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model;

/**
 * Class used to represent message type
 *
 * @author USER
 */
public enum MessageType {

    SERVER_TO_PEER_TYPE("SERVER_TO_PEER_TYPE"),
    PEER_TO_SERVER_TYPE("PEER_TO_SERVER_TYPE"),
    PEER_TO_PEER_TYPE("PEER_TO_PEER_TYPE");
    private final String text;

    /**
     * Constructor
     *
     * @param text
     */
    private MessageType(final String text) {
        this.text = text;
    }

    /**
     * To string value
     *
     * @return string value
     */
    @Override
    public String toString() {
        return text;
    }

}
