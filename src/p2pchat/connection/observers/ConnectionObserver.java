/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.connection.observers;

import java.util.Observable;
import java.util.Observer;
import p2pchat.model.IDataHandler;

/**
 *
 * @author USER
 */
public class ConnectionObserver implements Observer {

    IDataHandler messageHandler;
    
    /**
     * Constructor
     * @param messageHandler message handler for either server or peer
     */
    public ConnectionObserver(IDataHandler messageHandler) {
        this.messageHandler = messageHandler;
    }
    
    /**
     * Triggers observer update
     * @param o observed object
     * @param obj data passed as arguments
     */
    @Override
    public void update(Observable o, Object obj) {
        messageHandler.handleData(obj);
    }
}
