/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import p2pchat.connection.AbstractConnection;
import p2pchat.model.Message;
import p2pchat.model.MessageType;

/**
 *
 * @author USER
 */
public class OutputParser {

    private OutputStream outStream;
    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;

    public OutputParser(OutputStream outStream, AbstractConnection connection) {
        this.outStream = outStream;
    }

    /**
     * Send XML message
     * @param args message arguments
     * @return parsing result
     */
    public boolean send(String args[]) {
        
        if (args.length!=7) {
            System.err.println("Error: OutputPerser - Can't parse data");
            return false;
        }
        
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();

            // root elements
            org.w3c.dom.Document doc = docBuilder.newDocument();
            org.w3c.dom.Element rootElement = doc.createElement("P2PChat");
            doc.appendChild(rootElement);

            // type element
            org.w3c.dom.Element type = doc.createElement("MessageType");
            type.appendChild(doc.createTextNode(args[0]));
            rootElement.appendChild(type);
            
            // type element
            org.w3c.dom.Element subtype = doc.createElement("MessageSubtype");
            subtype.appendChild(doc.createTextNode(args[1]));
            rootElement.appendChild(subtype);
            
            // type element
            org.w3c.dom.Element result = doc.createElement("MessageResult");
            result.appendChild(doc.createTextNode(args[2]));
            rootElement.appendChild(result);

            // text element
            org.w3c.dom.Element field1 = doc.createElement("Field1");
            field1.appendChild(doc.createTextNode(args[3]));
            rootElement.appendChild(field1);

            // from element
            org.w3c.dom.Element field2 = doc.createElement("Field2");
            field2.appendChild(doc.createTextNode(args[4]));
            rootElement.appendChild(field2);

            // to element
            org.w3c.dom.Element field3 = doc.createElement("Field3");
            field3.appendChild(doc.createTextNode(args[5]));
            rootElement.appendChild(field3);
            
            // time element
            org.w3c.dom.Element time = doc.createElement("Time");
            time.appendChild(doc.createTextNode(args[6]));
            rootElement.appendChild(time);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(outStream);

            // Output to console for testing
            //streamResult = new StreamResult(System.out);
            
            transformer.transform(source, streamResult);

        } catch (TransformerException | ParserConfigurationException ex) {
            Logger.getLogger(OutputParser.class.getName()).log(Level.SEVERE, null, ex);

            return false;

        }

        return true;
    }
}
