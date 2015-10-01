package p2pchat.connection.peer;

import java.io.IOException;
import p2pchat.connection.P2PChatConnection;
import p2pchat.connection.server.TransmissionRequest;

/**
 * Class for handling all peer to peer communication
 */
public class PeerToPeerConnection {

    //Variables
    private P2PChatConnection connectionManager;
    //Peer-to-Peer connections
    private PeerToPeerInput peerToPeerInput;
    private PeerToPeerOutput peerToPeerOutput;

    /**
     * Constructor
     * @param connectionManager connection manager 
     */
    public PeerToPeerConnection(P2PChatConnection connectionManager) {
        this.connectionManager = connectionManager;
    }
    
    /**
     * Initialize connection
     * @param port peer port number for listening
     */
    public void init(int port) {
        peerToPeerInput = new PeerToPeerInput(this, port);
        peerToPeerInput.start();
        peerToPeerOutput = new PeerToPeerOutput(); 
    }
    
    /**
     * Close peer to peer connection
     */
    public void close() {
        peerToPeerInput.close();
        peerToPeerOutput.close(); 
    }
    
    /**
     * Send message to other peer
     * @param request data container
     */
    public void send(TransmissionRequest request) {
        try {
            peerToPeerOutput.connect(request.getAddress(), request.getPort());
            peerToPeerOutput.send(request.getData());
        } catch (IOException ex) {
            System.err.println("Error: PeerToPeerConnection - Coulend't connect to peer: "+request.getAddress()+" "+request.getPort());
        }
    }

    /**
     * Receive message from other peer
     * @param args message arguments
     */
    public void receive(String[] args) {
        connectionManager.receiveFromPeer(args);
    }

}
