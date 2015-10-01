/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection;

import p2pchat.connection.parser.InputParser;
import p2pchat.connection.parser.OutputParser;
import java.util.Observable;
import p2pchat.model.Message;

/**
 *
 * @author USER
 */
public class ConnectionManager implements IConnection {

    //Constants
    public static final String DEFAULT_SERVER = "localhost";
    public static final int DEFAULT_PORT = 2000;
    //Variables
    private static ConnectionManager instance;
    private InputParser inParser;
    private OutputParser outParser;
    //Server variables
    private String server = ConnectionManager.DEFAULT_SERVER;
    private int port = ConnectionManager.DEFAULT_PORT;
    boolean serverConnected;
    ServerConnection serverConnection;
    PeerToServerConnection peerConnection;
    //Peer variables
    PeerToServerConnection peerConnections[];

    public ConnectionManager() {
        setServerConnected(false);
        serverConnection = new ServerConnection(this);
        peerConnection = new PeerToServerConnection(this);
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    final void setServerConnected(boolean value) {
        this.serverConnected = value;
    }

    public boolean serverConnected() {
        return serverConnected;
    }

    public void startServer() {
        serverConnection.startServer(server, port);
    }

    public void connectServer() {
        peerConnection.connect(server, port);
    }
    
    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }
    @Override
    public void send(Message message) {
        peerConnection.send(message);
    }

    @Override
    public void connect(String server, int port) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void receive(Message message) {
    }

    @Override
    public boolean isConnected() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setConnected(boolean b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
