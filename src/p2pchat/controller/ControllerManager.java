/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.controller;

import p2pchat.controller.listeners.DefaultActionListener;
import p2pchat.controller.listeners.DefaultMouseListener;
import p2pchat.controller.listeners.DefaultWindowListener;
import p2pchat.controller.listeners.SubmitActionListener;
import p2pchat.model.peer.IOnlineUserManagerPeer;
import p2pchat.model.peer.IPeerActionHandler;
import p2pchat.view.IViewManager;

/**
 * Class representing P2PChat controller of the MVC
 * @author USER
 */
public class ControllerManager implements IControllerManager {

    IViewManager viewManager;
    IPeerActionHandler peerHandler;
    IOnlineUserManagerPeer peerManager;
    DefaultActionListener defaultActionListener;
    DefaultMouseListener defaultMouseListener;
    DefaultWindowListener defaultWindowListener;
    SubmitActionListener submitActionListener;
    
    public ControllerManager(IViewManager viewManager, IPeerActionHandler peerHandler, IOnlineUserManagerPeer peerManager) {
        this.viewManager = viewManager;
        this.peerHandler = peerHandler;
        this.peerManager = peerManager;
        
        //Create listeners
        defaultActionListener = new DefaultActionListener(peerHandler);
        defaultMouseListener = new DefaultMouseListener(peerManager, viewManager);
        submitActionListener = new SubmitActionListener(peerHandler, peerManager, viewManager);
        defaultWindowListener = new DefaultWindowListener();
    }
    
    @Override
    public void init() {
        //Add listeners/observers.
        
        viewManager.addDefaultListener(defaultActionListener);
        viewManager.addFeaturesListener(defaultMouseListener);
        viewManager.addSubmitListener(submitActionListener);
        viewManager.addWindowListener(defaultWindowListener);
    }
    
}
