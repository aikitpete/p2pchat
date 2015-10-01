/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection.peer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import p2pchat.connection.AbstractConnection;
import p2pchat.connection.parser.InputParser;
import p2pchat.connection.parser.SAXTerminatorException;

/**
 * Class used for obtaining input from other peers
 *
 * @author USER
 */
public class PeerToPeerInput extends AbstractConnection {

    //Constants
    public final int DEFAULT_PORT = 2010;//TODO remove
    //Connection variables
    private PeerToPeerConnection peerToPeerConnection;
    private String address;
    private int port;
    private ServerSocket serverSocket;
    //Parser
    private InputParser inParser;

    /**
     * Constructor
     *
     * @param peerToPeerConnection class to handle connection data
     * @param port port for listening
     */
    public PeerToPeerInput(PeerToPeerConnection peerToPeerConnection, int port) {
        this.peerToPeerConnection = peerToPeerConnection;
        this.port = port;
        this.connected = true;
    }

    /**
     * Listens for incoming connections while running
     */
    @Override
    public void run() {

        serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            System.err.println("Error: PeerToPeerInput - Can't create server socket " + port);
        }

        System.out.println("Server socket at " + serverSocket.getLocalPort());

        while (connected) {

            try {

                //Listen to connections
                socket = serverSocket.accept();

                //Get input stream
                inParser = new InputParser(socket.getInputStream());

                //Parse message
                inParser.parse(0);

            } catch (SAXTerminatorException xmlMessage) {

                System.out.println("End of message");

                //Obtain message contents
                receive(xmlMessage.getMessageFields());

            } catch (ParserConfigurationException | IOException | SAXException ex) {
                System.out.println("Peer connection closed");
            }
        }
    }

    @Override
    public void connect(String server, int port) throws IOException {
        System.err.println("Warning: PeerToPeerInput - Connect is not supported");
    }

    @Override
    public void send(String[] args) {
        System.err.println("Warning: PeerToPeerInput - Send is not supported");
    }

    /**
     * Receive message arguments
     *
     * @param args message arguments
     */
    @Override
    public void receive(String[] args) {
        peerToPeerConnection.receive(args);
    }

    @Override
    public String getAddress() {
        System.err.println("Warning: PeerToPeerInput - Address is empty");
        return null;
    }

    /**
     * Get port on which the peer is listening
     *
     * @return
     */
    @Override
    public int getPort() {
        return port;
    }

    /**
     * Close peer input connection
     */
    @Override
    public void close() {
        try {
            connected = false;
            if (socket!=null) {
                socket.close();
            }
            if (serverSocket!=null) {
                serverSocket.close();
            }
        } catch (IOException ex) {
            System.err.println("Error: PeerToPeerInput - Error while closing socket");
        }
    }
}
