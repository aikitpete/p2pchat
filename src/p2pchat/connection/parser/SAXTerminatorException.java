/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection.parser;

import org.xml.sax.SAXException;
import p2pchat.model.Message;

/**
 * Class for custom termination of sax parsing
 */
public class SAXTerminatorException extends SAXException {

    String[] messageFields;

    /*
     * Default constructor
     */
    public SAXTerminatorException(String[] messageFields) {
        super();             // call superclass constructor
        this.messageFields = messageFields;
    }

    /**
     * Get message fields
     *
     * @return text message fields
     */
    public String[] getMessageFields() {
        return messageFields;
    }
}
