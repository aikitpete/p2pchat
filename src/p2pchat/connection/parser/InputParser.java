/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import p2pchat.connection.AbstractConnection;

/**
 * Class for parsing input stream
 * @author USER
 */
public class InputParser {

    private SAXParserFactory factory;
    private DocumentHandler handler;
    private InputStream input;
    private SAXParser saxParser;

    /**
     * Creates instance of the class
     * @param input input stream used as input
     * @param connection connection instance
     */
    public InputParser(InputStream input) {
        this.input = input;
        handler = new DocumentHandler();
        factory = SAXParserFactory.newInstance();
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException ex) {
            System.err.println("Error: InputParser - Couldn't create SAXParser");
        }
    }

    /**
     * Parse the input
     * @throws ParserConfigurationException parsing error
     * @throws SAXException parsing error
     * @throws IOException parsing error
     * @throws SAXTerminatorException end od document
     */
    public void parse(int index) throws SAXTerminatorException, SAXException, IOException, ParserConfigurationException {

        String ret[] = new String[7];
        
        handler.setReturn(ret);
        
        saxParser.parse(input, handler);

    }
}
