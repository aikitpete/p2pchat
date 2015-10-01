/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection.server;

/**
 * Interface used to manage server communication
 * @author USER
 */
public interface IConnectionManagerServer {
    //Server to peer connection related
    /**
     * Send message to client
     * @param data data container
     */
    public void sendToClient(TransmissionRequest data);
    
    /**
     * Start server
     */
    public void startServer();
    
    /**
     * Get server port
     * @return server port
     */
    public int getServerPort();
    
    /**
     * Get server address
     * @return server address
     */
    public String getServerAddress();
    
    /**
     * Receive message from client
     * @param data data container
     */
    public void receiveFromClient(TransmissionRequest data);
}
