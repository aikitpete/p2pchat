/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model;

import p2pchat.IP2PChat;
import p2pchat.P2PChatType;
import p2pchat.connection.P2PChatConnection;
import p2pchat.model.peer.IOnlineUserManagerPeer;
import p2pchat.model.peer.PeerActionHandler;
import p2pchat.model.peer.PeerManager;
import p2pchat.model.peer.PeerMessageHandler;
import p2pchat.model.server.*;
import p2pchat.persistence.P2PChatPersistence;
import p2pchat.view.P2PChatView;
import utils.VerifyUtils;

/**
 * Class used to represent the P2PChat model from the MVC
 *
 * @author USER
 */
public class P2PChatModel {

    //Variables
    private static P2PChatModel instance;
    private OnlineUserManager onlineUserManager;
    private PeerManager peerManager;
    private LoginController loginController;
    private RegistrationController registrationController;
    private P2PChatConnection connectionManager;
    private ServerRequestHandler serverMessageHandler;
    private PeerActionHandler peerActionHandler;
    private PeerMessageHandler peerMessageHandler;

    /**
     * Constructor
     */
    private P2PChatModel() {
    }

    /**
     * Get class instance (Singletton pattern)
     *
     * @return model instance
     */
    public static P2PChatModel getInstance() {
        if (instance == null) {
            instance = new P2PChatModel();
        }
        return instance;
    }

    /**
     * Initialize model
     *
     * @param type P2PChat type identifier (server/peer)
     */
    public void init(IP2PChat p2pChat, P2PChatType type, P2PChatConnection connection, P2PChatPersistence persistence, P2PChatView view) {
        switch (type) {
            case s:
                PersistentUser.setPersistence(persistence.getPersistenceUser());
                this.onlineUserManager = new OnlineUserManager();
                this.registrationController = new RegistrationController();
                this.loginController = new LoginController(onlineUserManager);
                this.serverMessageHandler = new ServerRequestHandler(connection, 
                        registrationController, loginController, onlineUserManager);
                break;
            case p:
                this.peerManager = new PeerManager(view.getViewManager());
                this.peerActionHandler = new PeerActionHandler(p2pChat, connection, peerManager, view.getViewManager());
                this.peerMessageHandler = new PeerMessageHandler(connection, peerManager, view.getViewManager());
                break;
        }
    }

    /**
     * Get peer action handler
     *
     * @return peer action handler
     */
    public PeerActionHandler getPeerActionHandler() {
        return peerActionHandler;
    }

    /**
     * Get peer message handler
     *
     * @return peer message handler
     */
    public IDataHandler getPeerMessageHandler() {
        return peerMessageHandler;
    }

    /**
     * Get server message handler
     *
     * @return server message handler
     */
    public IDataHandler getServerMessageHandler() {
        return serverMessageHandler;
    }

    /**
     * Get peer manager
     *
     * @return peer manager
     */
    public IOnlineUserManagerPeer getPeerManager() {
        return peerManager;
    }

    /**
     * Get user manager
     *
     * @return user manager
     */
    public IOnlineUserManagerServer getUserManager() {
        return onlineUserManager;
    }

    /**
     * Change server and port settings
     *
     * @param server new server
     * @param port new port
     * @return result
     */
    public boolean settings(String server, String port) {
        if (server.length() == 0 || port.length() == 0) {
            return false;
        }

        if (!VerifyUtils.verifyServer(server)) {
            return false;
        }

        int portNumber = Integer.valueOf(port);

        if (!VerifyUtils.verifyPort(portNumber)) {
            return false;
        }

        return true;
    }

    /**
     * Check if server is connnected
     * @return server status
     */
    public boolean serverConnected() {
        return connectionManager.serverConnected();
    }

    /**
     * Set server
     */
    public void setServer() {
        peerManager = null;
    }

    /**
     * Set peer
     */
    public void setPeer() {
        onlineUserManager = null;
    }

    /**
     * Close instance
     */
    public void close() {
    }
}
