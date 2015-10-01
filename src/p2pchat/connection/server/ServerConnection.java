package p2pchat.connection.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import p2pchat.connection.P2PChatConnection;
import p2pchat.model.Message;

/**
 * Class to manage multiple server-peer connections
 */
public class ServerConnection {

    private final int SESSION_LENGTH = 3;
    private P2PChatConnection connectionManager;
    //Indicates  if the connection is active
    private boolean connected = false;
    //Socket used to listen to new connections
    Socket tempSocket;
    //Array with server-peer sessions
    ServerSession serverSessions[] = new ServerSession[SESSION_LENGTH];

    /**
     * Constructor
     *
     * @param manager connection manager
     */
    public ServerConnection(P2PChatConnection connectionManager) {

        //Set connection manager
        this.connectionManager = connectionManager;

        //Initialize server sessions
        for (int i = 0; i < SESSION_LENGTH; i++) {
            System.out.println("Creating session " + i);
            serverSessions[i] = new ServerSession(this, i);
        }
    }

    /**
     * Start a new server
     *
     * @param port server port
     */
    public final void startServer(int port) {

        System.out.println("Starting server at port " + port);

        // Create an unbound socket
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            System.err.println("Error: ServerConnection - Port already taken");
        }

        while (true) {
            try {
                //Accept connection from client
                tempSocket = serverSocket.accept();

                System.out.println("Connection accepted from " + tempSocket.getPort());

                //Find unused session
                for (int i = 0; i < SESSION_LENGTH; i++) {
                    
                    //Check session availability
                    if (!serverSessions[i].isConnected()) {
                        serverSessions[i] = new ServerSession(this, i);
                        serverSessions[i].init(tempSocket);
                        serverSessions[i].start();
                        break;
                    }
                    
                    //No session available
                    if (i == SESSION_LENGTH-1) {
                        System.err.println("Error: Server Connection - No empty session found");
                    }
                }

            } catch (IOException e) {
                //Connection error - thrown when client disconnected
                System.out.println("Error: Connection error");
            }

        }
    }

    /**
     * Send message
     *
     * @param args message fields
     * @param index session index
     */
    public void send(TransmissionRequest data) {
        serverSessions[data.getSession()].send(data.getData());
    }

    /**
     * Receive message from client
     * @param session session number
     * @param address client address
     * @param port client port
     * @param args message arguments
     */
    void receiveFromClient(int session, String address, int port, String args[]) {
        TransmissionRequest data = new TransmissionRequest(session, address, port, args);
        connectionManager.receiveFromClient(data);
    }

    /**
     * End server session
     * @param session session number
     * @param address client address
     * @param port client port
     */
    void endSession(int session, String address, int port) {
        TransmissionRequest data = new TransmissionRequest(session, address, port);
        connectionManager.receiveFromClient(data);
    }

    /**
     * Close server connection
     */
    public void close() {
        for (int i=0; i<serverSessions.length; i++) {
            serverSessions[i].close();
        }
    }


}
