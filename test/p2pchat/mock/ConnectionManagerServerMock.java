/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.mock;

import p2pchat.connection.server.IConnectionManagerServer;
import p2pchat.connection.server.TransmissionRequest;

/**
 *
 * @author USER
 */
public class ConnectionManagerServerMock implements IConnectionManagerServer {

    public int sendToClientCounter = 0;
    public TransmissionRequest sendToClientRequests[] = new TransmissionRequest[10];
    
    @Override
    public void sendToClient(TransmissionRequest data) {
        sendToClientRequests[sendToClientCounter] = data;
        sendToClientCounter++;
    }

    @Override
    public void startServer() {
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
    public void receiveFromClient(TransmissionRequest data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
