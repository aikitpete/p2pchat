package p2pchat.connection.peer;

import p2pchat.connection.parser.InputParser;
import p2pchat.connection.parser.OutputParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import p2pchat.connection.P2PChatConnection;
import p2pchat.connection.AbstractConnection;
import p2pchat.connection.parser.SAXTerminatorException;

/**
 * Class representing peer to server connection
 */
public class PeerToServerConnection extends AbstractConnection {

    //Variables
    private P2PChatConnection connectionManager;

    /**
     * Constructor
     *
     * @param connectionManager class for managing connections
     */
    public PeerToServerConnection(P2PChatConnection connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * Connect to server
     *
     * @param address server address
     * @param port server port
     * @throws IOException exception thrown when no connection is available
     */
    @Override
    public void connect(String address, int port) throws IOException {

        System.out.println("Connecting to server: " + port);

// Create a socket with a timeout
        InetAddress addr = InetAddress.getByName(address);
        SocketAddress sockaddr = new InetSocketAddress(addr, port);

        // Create an unbound socket
        socket = new Socket();

        // This method will block no more than timeoutMs.
        // If the timeout occurs, SocketTimeoutException is thrown.
        int timeoutMs = 2000;   // 2 seconds

        socket.connect(sockaddr, timeoutMs);

        setOutputStream(socket.getOutputStream());
        setInputStream(socket.getInputStream());

        connected = true;

        //Initialize parsers
        setOutputParser(new OutputParser(getOutputStream(), this));
        setInputParser(new InputParser(getInputStream()));

    }

    /**
     * Runs the session
     */
    @Override
    public void run() {

        if (!connected) {
            return;
        }

        System.out.println("Server connected");

        try {
            //Read incomming messages
            while (true) {
                try {

                    //Initialize parsers
                    inParser.parse(0);

                } catch (SAXTerminatorException xmlMessage) {

                    System.out.println("End of message");

                    //Obtain message contents
                    receive(xmlMessage.getMessageFields());

                }
            }
        } catch (ParserConfigurationException | SAXException ex) {
            System.out.println("Error: ServerSession - SAX error");
        } catch (IOException ex) {
            System.out.println("Error: ServerSession - Connection error");
        }

        //Server disconnected
        System.out.println("Server disconnected");

        //Unset connected flag
        this.connected = false;
    }

    /**
     * Sends message to server
     * @param args message arguments
     */
    @Override
    public void send(String args[]) {
        getOutputParser().send(args);
    }

    /**
     * Receives message from server
     * @param args message arguments
     */
    @Override
    public void receive(String args[]) {
        connectionManager.receiveFromServer(args);
    }

    /**
     * Get server address
     * @return server address
     */
    @Override
    public String getAddress() {
        return connectionManager.getServerAddress();
    }

    /**
     * Get server port
     * @return server port
     */
    @Override
    public int getPort() {
        return connectionManager.getServerPort();
    }

    /**
     * Close connection with server
     */
    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException ex) {
            System.err.println("Error: PeerToServerConnection - Error while closing socket");
        }
    }
}
