/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.server;

import p2pchat.connection.ConnectionManager;
import p2pchat.model.P2PChatModel;

/**
 *
 * @author USER
 */
public class P2PServer {

    public static P2PChatModel model;
    public static ConnectionManager connectionManager;
    
    /**
     * 
     */
    public static void start() {
        model = P2PChatModel.getInstance();
        connectionManager = ConnectionManager.getInstance();
        
        connectionManager.startServer();
    }
}
