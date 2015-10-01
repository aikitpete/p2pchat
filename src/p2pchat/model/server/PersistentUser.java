/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.server;

import p2pchat.model.User;
import p2pchat.persistence.server.IPersistenceUser;

/**
 * Class allowing to map users on relational database
 *
 * @author USER
 */
public class PersistentUser extends User {

    private static IPersistenceUser persistenceUser;

    /**
     * Constructor
     *
     * @param username
     * @param password
     * @param photoPath
     */
    public PersistentUser(String username, String password, String photoPath) {
        super(username, password, photoPath);
    }

    /**
     * Add new user
     *
     * @param args
     * @return
     */
    public static boolean addNewUser(String args[]) {
        return addNewUser(args[0], args[1], args[2]);
    }

    /**
     * Add new user
     *
     * @param username
     * @param password
     * @param photoPath
     * @return
     */
    public static boolean addNewUser(String username, String password, String photoPath) {

        //Check if user already exists
        if (findUserByName(username) != null) {
            System.err.println("Warning: PersistentUser - User already exists " + username);
            return false;
        }

        //Attempt to create user
        persistenceUser.addNewUser(username, password, photoPath);

        //Check if user created
        if (findUserByName(username) == null) {
            System.err.println("Error: PersistentUser - Error Creating user " + username);
            return false;
        }

        return true;
    }

    /**
     * Find user by name
     *
     * @param username
     * @return
     */
    public static PersistentUser findUserByName(String username) {
        PersistentUser ret = null;

        String args[] = persistenceUser.findUserByName(username);

        if (args != null) {
            ret = new PersistentUser(args[0], args[1], args[2]);
        }

        return ret;
    }

    /**
     * Set persistence
     *
     * @param persistence
     */
    public static void setPersistence(IPersistenceUser persistence) {
        persistenceUser = persistence;
    }
}
