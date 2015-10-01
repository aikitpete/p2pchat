/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.mock;

import java.io.IOException;
import p2pchat.connection.peer.IConnectionManagerPeer;
import p2pchat.connection.server.TransmissionRequest;

/**
 *
 * @author USER
 */
public class ConnectionManagerPeerMock implements IConnectionManagerPeer {

    public String sendToServer[];
    public boolean closePeerConnections;
    public TransmissionRequest sendToPeer[];
    public int sendToPeerCount = 0;
    public String setServerAddress;
    public int setServerPort;
    public int startPeer;

    @Override
    public void sendToPeer(TransmissionRequest data) {
        sendToPeer[sendToPeerCount] = data;
        sendToPeerCount++;
    }

    @Override
    public void resetConnections() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void receiveFromPeer(String[] args) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sendToServer(String[] args) {
        sendToServer = args;
    }

    @Override
    public boolean serverConnected() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void connectToServer() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getServerPort() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getServerAddress() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void receiveFromServer(String[] args) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setServerAddress(String address) {
        setServerAddress = address;
    }

    @Override
    public void setServerPort(int portNumber) {
        setServerPort = portNumber;
    }

    @Override
    public void startPeer(int port) {
        startPeer = port;
    }

    @Override
    public void closePeerConnections() {
        closePeerConnections = true;
    }
}
