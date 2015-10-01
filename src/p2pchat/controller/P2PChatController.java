/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.controller;

import p2pchat.P2PChat;
import p2pchat.controller.listeners.DefaultActionListener;
import p2pchat.controller.listeners.DefaultMouseListener;
import p2pchat.controller.listeners.DefaultWindowListener;
import p2pchat.controller.listeners.SubmitActionListener;
import p2pchat.model.P2PChatModel;
import p2pchat.view.P2PChatView;

/**
 * Class representing P2PChat controller of the MVC
 * @author USER
 */
public class P2PChatController {
    //The Controller needs to interact with both the Model and View.

    //Variables
    private static P2PChatController instance;
    private static P2PChatModel model;
    private static P2PChatView view;
    private static ControllerManager controllerManager;

    /**
     * Constructor
     */
    private P2PChatController() {
        P2PChatController.model = P2PChatModel.getInstance();
        P2PChatController.view = P2PChatView.getInstance();
        
    }
    
    /**
     * Initialize controller
     */
    public void init() {
        controllerManager = new ControllerManager(view.getViewManager(),model.getPeerActionHandler(), model.getPeerManager());
        controllerManager.init();

    }

    /**
     * Get instance of controller
     * @return controller instance
     */
    public static P2PChatController getInstance() {
        if (instance == null) {
            instance = new P2PChatController();
        }
        return instance;
    }

    /**
     * Get instance of model
     * @return model instance
     */
    public static P2PChatModel getModel() {
        return model;
    }

    /**
     * Get instance of view
     * @return view instance
     */
    public static P2PChatView getView() {
        return view;
    }

    /**
     * Close the connection
     */
    public void close() {
        
    }

    /**
     * Exit the P2PChat program
     */
    public void exit() {
        P2PChat.getInstance().exit();
    }
}
