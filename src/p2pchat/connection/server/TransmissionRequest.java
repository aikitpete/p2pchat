/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection.server;

import p2pchat.model.Message;

/**
 * Class used as data container, 
 * every instance contains session id, address, port and message data
 * @author USER
 */
public class TransmissionRequest {

    private int session;
    private final String address;
    private final int port;
    private final String data[];

    /**
     * Constructor with empty data
     * @param session session id
     * @param address address
     * @param port port
     */
    public TransmissionRequest(int session, String address, int port) {
        this.session = session;
        this.address = address;
        this.port = port;
        this.data = null;
    }

    /**
     * Constructor with data passed as object
     * @param session session id
     * @param address address
     * @param port port
     * @param object object
     */
    public TransmissionRequest(int session, String address, int port, Object object) {
        this.session = session;
        this.address = address;
        this.port = port;
        this.data = object.toString().split(",");
    }

    /**
     * Constructor with data passed as String array
     * @param session session id
     * @param address address
     * @param port port
     * @param args array of Strings to store data
     */
    public TransmissionRequest(int session, String address, int port, String[] args) {
        this.session = session;
        this.address = address;
        this.port = port;
        this.data = args;
    }

    /**
     * Constructor with data passed as message
     * @param session session id
     * @param address address
     * @param port port
     * @param message message
     */
    public TransmissionRequest(int session, String address, int port, Message message) {
        this.session = session;
        this.address = address;
        this.port = port;
        this.data = message.toStringArray();
    }
    
    /**
     * Constructor without session id (used in peer-to-peer communication)
     * @param address address
     * @param port port
     * @param args array of strings to store data
     */
    public TransmissionRequest(String address, int port, String args[]) {
        this.address = address;
        this.port = port;
        this.data = args;
    }

    /**
     * Get session number
     * @return session number
     */
    public int getSession() {
        return session;
    }
    
    /**
     * Get address
     * @return address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * Get port
     * @return port number 
     */
    public int getPort() {
        return port;
    }

    /**
     * Get data
     * @return data as string array
     */
    public String[] getData() {
        return data;
    }
}
