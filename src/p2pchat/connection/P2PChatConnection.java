/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection;

import java.io.IOException;
import p2pchat.connection.server.TransmissionRequest;
import java.util.Observable;
import p2pchat.P2PChatType;
import p2pchat.connection.observers.ConnectionObserver;
import p2pchat.connection.parser.InputParser;
import p2pchat.connection.parser.OutputParser;
import p2pchat.connection.peer.IConnectionManagerPeer;
import p2pchat.connection.peer.PeerToPeerConnection;
import p2pchat.connection.peer.PeerToServerConnection;
import p2pchat.connection.server.IConnectionManagerServer;
import p2pchat.connection.server.ServerConnection;
import p2pchat.model.P2PChatModel;

/**
 * Class for handling connection between server and peers
 * @author USER
 */
public class P2PChatConnection extends Observable implements IConnectionManagerServer, IConnectionManagerPeer {

    //Constants
    public static final int MAX_CONNECTIONS = 10;
    public static final String DEFAULT_SERVER = "localhost";
    public static final int DEFAULT_SERVER_PORT = 2000;
    //Variables
    private static P2PChatConnection instance;
    private static P2PChatModel model;
    private InputParser inParser;
    private OutputParser outParser;
    //Server variables
    private String serverAddress = P2PChatConnection.DEFAULT_SERVER;
    private int serverPort = P2PChatConnection.DEFAULT_SERVER_PORT;
    private ServerConnection serverConnection;
    //Peer variables
    private PeerToServerConnection peerToServerConnection;
    private PeerToPeerConnection peerToPeerConnection;

    /**
     * Constructor
     */
    private P2PChatConnection() {
    }

    /**
     * Get current connection instance
     * @return instance
     */
    public static P2PChatConnection getInstance() {
        if (instance == null) {
            instance = new P2PChatConnection();
        }
        return instance;
    }

    /**
     * Initializes connection objects
     * @param type indicated if the P2PChat instance is server or peer
     * @param model model instance
     */
    public void init(P2PChatType type, P2PChatModel model) {

        P2PChatConnection.model = model;
        ConnectionObserver connectionObserver = null;

        switch (type) {
            case s:
                connectionObserver = new ConnectionObserver(model.getServerMessageHandler());
                addObserver(connectionObserver);
                startServer();
                break;
            case p:
                connectionObserver = new ConnectionObserver(model.getPeerMessageHandler());
                addObserver(connectionObserver);
                peerToPeerConnection = new PeerToPeerConnection(this);
                break;
            default:
                break;
        }
    }

    /**
     * Resets peer connections
     */
    @Override
    public void resetConnections() {
        peerToPeerConnection = new PeerToPeerConnection(this);
    }

    /**
     * Starts server
     */
    @Override
    public final void startServer() {
        System.out.println("Starting server...");
        serverConnection = new ServerConnection(this);
        serverConnection.startServer(serverPort);
    }

    /**
     * Connects to server
     * @throws IOException 
     */
    @Override
    public void connectToServer() throws IOException {
        peerToServerConnection = new PeerToServerConnection(this);
        peerToServerConnection.connect(serverAddress, serverPort);
        peerToServerConnection.start();
    }

    /**
     * Sends reply to peer
     * @param data data container
     */
    @Override
    public void sendToPeer(TransmissionRequest data) {
        peerToPeerConnection.send(data);//TODO
    }

    /**
     * Sends message to server
     * @param args message arguments
     */
    @Override
    public void sendToServer(String args[]) {
        if (!serverConnected()) {
            try {
                connectToServer();
            } catch (IOException ex) {
                System.err.println("Error: P2PChatConnection - Could not connect to server");
            }
        }

        peerToServerConnection.send(args);

    }

    /**
     * Sends message to client
     * @param data data container
     */
    @Override
    public void sendToClient(TransmissionRequest data) {
        //Send to client i
        serverConnection.send(data);
    }

    /**
     * Received message from client
     * @param data data container
     */
    @Override
    public void receiveFromClient(TransmissionRequest data) {
        this.setChanged();
        this.notifyObservers(data);
    }

    /**
     * Receives message from server
     * @param args message parguments
     */
    @Override
    public void receiveFromServer(String args[]) {
        this.setChanged();
        this.notifyObservers(args);
    }

    /**
     * Receives message from peer
     * @param args message arguments
     */
    @Override
    public void receiveFromPeer(String args[]) {
        this.setChanged();
        this.notifyObservers(args);
    }

    /**
     * Checks server connection status
     * @return server connection status
     */
    @Override
    public boolean serverConnected() {
        if (peerToServerConnection == null) {
            return false;
        }

        return peerToServerConnection.isConnected();
    }

    /**
     * Get server port
     * @return server port
     */
    @Override
    public int getServerPort() {
        return serverPort;
    }

    /**
     * Get server address
     * @return server address
     */
    @Override
    public String getServerAddress() {
        return serverAddress;
    }

    /**
     * Set server address
     * @param serverAddress server address
     */
    @Override
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    /**
     * Set server port
     * @param serverPort server port 
     */
    @Override
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * Called when exiting program, closes every connection
     */
    public void close() {
        if (serverConnection!=null) {
            serverConnection.close();
        }
        if (peerToPeerConnection!=null) {
            peerToPeerConnection.close();
        }
        if (peerToServerConnection!=null) {
            peerToServerConnection.close();
        }
    }

    /**
     * Starts peer connection on a specified prot
     * @param port port number
     */
    @Override
    public void startPeer(int port) {
        System.out.println("Starting peer...");
        peerToPeerConnection.init(port);
    }

    /**
     * Closes all peer connections when logging out
     */
    @Override
    public void closePeerConnections() {
        peerToPeerConnection.close();
    }
}
