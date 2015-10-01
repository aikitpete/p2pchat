/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection.peer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import p2pchat.connection.AbstractConnection;
import p2pchat.connection.parser.OutputParser;

/**
 * Class for sending data to other peers
 *
 * @author USER
 */
public class PeerToPeerOutput extends AbstractConnection {

    /**
     * Connects to a socket on specified address and port
     *
     * @param address address of remote socket
     * @param port remote socket port
     * @throws IOException io exception passed when no connection is available
     */
    @Override
    public void connect(String address, int port) throws IOException {
        SocketAddress sockaddr = new InetSocketAddress("localhost", port);

        // Create an unbound socket
        socket = new Socket();

        // This method will block no more than timeoutMs.
        // If the timeout occurs, SocketTimeoutException is thrown.
        int timeoutMs = 2000;   // 2 seconds

        socket.connect(sockaddr, timeoutMs);

        //Set connected flag
        this.connected = true;
    }

    /**
     * Send message to peer
     *
     * @param args message arguments
     */
    @Override
    public void send(String[] args) {
        try {

            OutputParser parser = new OutputParser(socket.getOutputStream(), this);

            parser.send(args);

            socket.close();

        } catch (UnknownHostException ex) {
            Logger.getLogger(PeerToPeerOutput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PeerToPeerOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Close peer output connection
     */
    @Override
    public void close() {
        try {
            this.connected = false;
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ex) {
            System.err.println("Error: PeerToPeerOutput - Error while closing socket");
        }
    }
}
