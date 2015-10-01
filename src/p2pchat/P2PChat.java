/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat;

import javax.swing.JFrame;
import p2pchat.connection.P2PChatConnection;
import p2pchat.controller.P2PChatController;
import p2pchat.model.P2PChatModel;
import p2pchat.persistence.P2PChatPersistence;
import p2pchat.view.P2PChatView;

/**
 * Main P2PChat class
 *
 * @author USER
 */
public class P2PChat implements IP2PChat {

    private static P2PChat instance;
    private P2PChatConnection connection;
    private P2PChatPersistence persistence;
    private P2PChatModel model;
    private P2PChatView view;
    private P2PChatController controller;

    /**
     * Returns instance of P2P chat
     *
     * @return instance
     */
    public static P2PChat getInstance() {
        if (instance == null) {
            instance = new P2PChat();
        }
        return instance;
    }

    /**
     * Create model, view, and controller. They are created once here and passed
     * to the parts that need them so there is only one copy of each.
     */
    public void run(P2PChatType type) {

        //Get persistence instance
        persistence = P2PChatPersistence.getInstance();

        //Get view instance
        if (type == P2PChatType.p) {
            view = P2PChatView.getInstance();
        }
        
        //Get model instance
        model = P2PChatModel.getInstance();

        //Get controller instance
        if (type == P2PChatType.p) {
            controller = P2PChatController.getInstance();
        }

        //Get connection instance
        connection = P2PChatConnection.getInstance();

        //Initialize everything
        persistence.init(type);
        
        if (type == P2PChatType.p) {
            view.init();
        }
        
        model.init(this, type, connection, persistence, view);
        
        if (type == P2PChatType.p) {
            controller.init();
        }
        
        connection.init(type, model);
    }

    /**
     * Exit P2PChat
     */
    @Override
    public void exit() {

        //Call close on every module
        //connection.close();//Throws SAX error but working
        persistence.close();
        model.close();
        view.close();
        controller.close();

        //Exit with zero status
        System.exit(0);
    }
}
