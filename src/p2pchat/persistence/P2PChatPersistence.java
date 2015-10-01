/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.persistence;

import p2pchat.P2PChatType;
import p2pchat.persistence.server.IPersistenceUser;
import p2pchat.persistence.server.PersistenceUserMock;

/**
 * Class for managing connection with database
 *
 * @author USER
 */
public class P2PChatPersistence {

    //Variables
    private PersistenceUserMock persistenceServer;
    //Instance variable
    private static P2PChatPersistence instance;

    /**
     * Constructor
     */
    private P2PChatPersistence() {
    }

    /**
     * Get class instance (Singleton pattern)
     *
     * @return
     */
    public static P2PChatPersistence getInstance() {
        if (instance == null) {
            instance = new P2PChatPersistence();
        }
        return instance;
    }

    /**
     * Initialize database connection
     *
     * @param type
     */
    public void init(P2PChatType type) {
        switch (type) {
            case s:
                this.persistenceServer = new PersistenceUserMock();
                break;
            case p:
                break;
        }
    }

    /**
     * Close database connection
     */
    public void close() {
    }

    public IPersistenceUser getPersistenceUser() {
        return persistenceServer;
    }
}
