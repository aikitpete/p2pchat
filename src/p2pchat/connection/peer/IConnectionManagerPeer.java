/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection.peer;

import java.io.IOException;
import p2pchat.connection.server.TransmissionRequest;

/**
 * Connnection interface for peer
 * @author USER
 */
public interface IConnectionManagerPeer {
    //Peer to peer communication

    /**
     * Send message to peer
     * @param data  message arguments
     */
    public void sendToPeer(TransmissionRequest data);

    /**
     * Reset connections, called when logging out
     */
    public void resetConnections();

    /**
     * Receive message fro peer
     * @param args message arguments
     */
    public void receiveFromPeer(String args[]);
    
    //Peer to server communication

    /**
     * Send message to server
     * @param args message arguments
     */
    public void sendToServer(String args[]);

    /**
     * Check server connection
     * @return connection status
     */
    public boolean serverConnected();

    /**
     * Connect to server
     * Peer must connect to the server before initiating communication with other peers
     * @throws IOException io exception 
     */
    public void connectToServer() throws IOException;

    /**
     * Get server port
     * @return server port number
     */
    public int getServerPort();

    /**
     * Get server address
     * @return server address
     */
    public String getServerAddress();

    /**
     * Receive message from server
     * @param args message arguments
     */
    public void receiveFromServer(String args[]);

    /**
     * Set server address
     * @param address address of the server 
     */
    public void setServerAddress(String address);

    /**
     * Set server port
     * @param portNumber port number of the server 
     */
    public void setServerPort(int portNumber);
    
    /**
     * Starts peer connection with other peers
     * 1. Connection is initialized
     * 2. Server starts listening on assigned port
     * @param port 
     */
    public void startPeer(int port);

    /**
     * Close all peer connections
     */
    public void closePeerConnections();
}
