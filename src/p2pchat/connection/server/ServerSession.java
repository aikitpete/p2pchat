/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import p2pchat.connection.P2PChatConnection;
import p2pchat.connection.AbstractConnection;
import p2pchat.connection.parser.InputParser;
import p2pchat.connection.parser.OutputParser;
import p2pchat.connection.parser.SAXTerminatorException;

/**
 * Class representing server session
 * @author USER
 */
public class ServerSession extends AbstractConnection {

    //Connection variables
    private ServerConnection serverConnection;
    private boolean connected;
    private int session;//session index
    private InetAddress address;
    private int port;

    /**
     * Class constructor
     */
    public ServerSession(ServerConnection serverConnection, int session) {
        this.session = session;
        this.connected = false;
        this.serverConnection = serverConnection;
    }

    /**
     * Initializes session bound to a socket
     *
     * @param socket client socket
     */
    public void init(Socket socket) {

        //Set address and port
        this.address = socket.getInetAddress();
        this.port    = socket.getPort();
        
        //Set connected flag
        this.connected = true;

        try {
            //Set input and output streams
            this.input = socket.getInputStream();
            this.output = socket.getOutputStream();
        } catch (IOException ex) {
            System.err.println("Error: ServerSession - Can't get IO");
        }

        //Setup the connection
        setSocket(socket);
        setOutputParser(new OutputParser(output, this));
        setInputParser(new InputParser(input));

    }

    /**
     * Runs the session
     */
    @Override
    public void run() {

        System.out.println("Session "+session+" started");

        try {
            //Read incomming messages
            while (true) {
                try {

                    //Initialize parsers
                    inParser.parse(session);

                } catch (SAXTerminatorException xmlMessage) {

                    System.out.println("End of message");

                    //Obtain message contents
                    receive(xmlMessage.getMessageFields());

                }
            }
        } catch (ParserConfigurationException | SAXException ex) {
            System.err.println("Error: ServerSession - SAX error");
        } catch (IOException ex) {
            System.err.println("Warning: ServerSession - Lost Connection With Peer");
        }
        
        //Close session
        serverConnection.endSession(session, address.toString(), port);
        
        //Server disconnected
        System.out.println("Session "+session+" ended");
        
        //Unset connected flag
        this.connected = false;
        
    }

    /**
     * Send message
     *
     * @param args message fields
     */
    @Override
    public void send(String[] args) {
        getOutputParser().send(args);
        try {
            output.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Message sent");
    }

    /**
     * Receive message from client
     *
     * @param args message fields
     */
    @Override
    public void receive(String[] data) {
        serverConnection.receiveFromClient(session, address.toString(), port, data);
    }

    /**
     * Get server address
     * @return server address
     */
    @Override
    public String getAddress() {
        return this.getAddress();
    }

    /**
     * Get server port number
     * @return port number
     */
    @Override
    public int getPort() {
        return this.getPort();
    }

    /**
     * Check connection status
     * @return server connected
     */
    @Override
    public boolean isConnected() {
        return connected;
    }

    /**
     * Close server sessions
     */
    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException ex) {
            System.err.println("Error: PeerToPeerOutput - Error while closing socket");
        }
    }
}