/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection.parser;

import p2pchat.model.*;

/**
 * Enum representing XML tags in exchanged messages
 * @author USER
 */
public enum XMLTagType {

    P2P_HEADER("P2PChat"),
    MESSAGE_TYPE("MessageType"),
    MESSAGE_SUBTYPE("MessageSubtype"),
    MESSAGE_RESULT("MessageResult"),
    MESSAGE_FIELD1("Field1"),
    MESSAGE_FIELD2("Field2"),
    MESSAGE_FIELD3("Field3"),
    MESSAGE_TIME("Time")
;
    /**
     * Constructor
     * @param text tag text
     */
    private XMLTagType(String text) {
        this.text = text;
    }
    private final String text;

    /*
     * To string value
     */
    @Override
    public String toString() {
        return text;
    }
}
