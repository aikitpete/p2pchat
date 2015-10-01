/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection.parser;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import p2pchat.connection.AbstractConnection;
import p2pchat.model.Message;
import p2pchat.model.MessageType;

/**
 * Custom handler for XML parsing
 * @author USER
 */
public class DocumentHandler extends DefaultHandler {

    String ret[];
    boolean bp2p;
    boolean btype;
    boolean bsubtype;
    boolean bresult;
    boolean bfield1;
    boolean bfield2;
    boolean bfield3;
    boolean btime;
    String messageType;
    String messageSubtype;
    String messageResult;
    String messageField1;
    String messageField2;
    String messageField3;
    String messageTime;

    /**
     * Constructor
     */
    public DocumentHandler() {
    }

    /**
     * Startof document event
     */
    @Override
    public void startDocument() {
        //System.out.println("START DOCUMENT");
        System.out.println("Received message:");
    }
    
    /**
     * End of document event
     * @throws SAXException parsing exception called when reaching end of document
     */
    @Override
    public void endDocument() throws SAXException {
        //System.out.println("END DOCUMENT");

        ret[0] = messageType;
        ret[1] = messageSubtype;
        ret[2] = messageResult;
        ret[3] = messageField1;
        ret[4] = messageField2;
        ret[5] = messageField3;
        ret[6] = messageTime;

        throw new SAXTerminatorException(ret);

    }

    /**
     * Start element event
     * @param uri element uri
     * @param localName element local name
     * @param qName qName
     * @param attributes element attributes
     * @throws org.xml.sax.SAXException called when parsing is unexpectedly interrrupted
     */
    @Override
    public void startElement(String uri, String localName, String qName,
            org.xml.sax.Attributes attributes) throws org.xml.sax.SAXException {

        //System.out.println("START ELEMENT :" + qName);

        if (qName.equalsIgnoreCase(XMLTagType.P2P_HEADER.toString())) {
            bp2p = true;

            btype = false;
            bsubtype = false;
            bresult = false;
            bfield1 = false;
            bfield2 = false;
            bfield3 = false;
            btime = false;
            messageType = null;
            messageSubtype = null;
            messageResult = null;
            messageField1 = null;
            messageField2 = null;
            messageField3 = null;
            messageTime = null;
        }

        if (qName.equalsIgnoreCase(XMLTagType.MESSAGE_TYPE.toString())) {
            btype = true;
        }

        if (qName.equalsIgnoreCase(XMLTagType.MESSAGE_SUBTYPE.toString())) {
            bsubtype = true;
        }

        if (qName.equalsIgnoreCase(XMLTagType.MESSAGE_RESULT.toString())) {
            bresult = true;
        }

        if (qName.equalsIgnoreCase(XMLTagType.MESSAGE_FIELD1.toString())) {
            bfield2 = false;
            bfield3 = false;
            bfield1 = true;
        }

        if (qName.equalsIgnoreCase(XMLTagType.MESSAGE_FIELD2.toString())) {
            bfield1 = false;
            bfield3 = false;
            bfield2 = true;
        }

        if (qName.equalsIgnoreCase(XMLTagType.MESSAGE_FIELD3.toString())) {
            bfield1 = false;
            bfield2 = false;
            bfield3 = true;
        }

        if (qName.equalsIgnoreCase(XMLTagType.MESSAGE_TIME.toString())) {
            bfield1 = false;
            bfield2 = false;
            bfield3 = false;
            btime = true;
        }
    }

    /**
     * End element event
     * @param uri element uri
     * @param localName element local name
     * @param qName element qName
     * @throws SAXException called when parsing interrupted unexpectedly
     */
    @Override
    public void endElement(String uri, String localName,
            String qName) throws SAXException {

        //System.out.println("END ELEMENT :" + qName);

        if (qName.equalsIgnoreCase(XMLTagType.P2P_HEADER.toString())) {
            endDocument();
        }

    }

    /**
     * Characters event
     * @param ch character array
     * @param start start index
     * @param length length of array
     * @throws SAXException called when parsing interrupted unexpectedly
     */
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        //System.out.println("CHARACTERS: " + new String(ch, start, length));

        if (bp2p) {
            bp2p = false;
        }

        if (btype) {
            messageType = new String(ch, start, length);
            System.out.println("MessageType: " + messageType);
            btype = false;
        }

        if (bsubtype) {
            messageSubtype = new String(ch, start, length);
            System.out.println("MessageSubtype: " + messageSubtype);
            bsubtype = false;
        }

        if (bresult) {
            messageResult = new String(ch, start, length);
            System.out.println("MessageResult: " + messageResult);
            bresult = false;
        }

        if (bfield1) {
            messageField1 = new String(ch, start, length);
            System.out.println("MessageField1: " + messageField1);
            bfield1 = false;
        }

        if (bfield2) {
            messageField2 = new String(ch, start, length);
            System.out.println("MessageField2: " + messageField2);
            bfield2 = false;
        }

        if (bfield3) {
            messageField3 = new String(ch, start, length);
            System.out.println("MessageField3: " + messageField3);
            bfield3 = false;
        }

        if (btime) {
            messageTime = new String(ch, start, length);
            System.out.println("MessageTime: " + messageTime);
            btime = false;
        }

    }

    /**
     * Set return variable
     * @param ret return variable
     */
    void setReturn(String[] ret) {
        this.ret = ret;
    }
}
