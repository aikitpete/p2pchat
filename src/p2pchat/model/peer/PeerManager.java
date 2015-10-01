/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.peer;

import java.awt.image.BufferedImage;
import p2pchat.model.ErrorType;
import p2pchat.model.StatusType;
import p2pchat.model.User;
import p2pchat.view.IViewManager;

/**
 * Class for representing the peer manager
 *
 * @author USER
 */
public class PeerManager implements IOnlineUserManagerPeer {

    //Constants
    public static final int MAX_USERS = 10;
    //Variables
    private User onlineUsers[] = new User[MAX_USERS];
    private StatusType status;
    private User onlineUser;
    private IViewManager viewManager;

    /**
     * Constructor
     */
    public PeerManager(IViewManager viewManager) {
        this.viewManager = viewManager;
        setStatus(StatusType.OFFLINE);
        resetOnlineUsers();
    }

    /**
     * Sets peer status
     *
     * @param status status token
     */
    @Override
    public final void setStatus(StatusType status) {
        switch (status) {
            case ONLINE:
                System.out.println("Peer: Online");
                this.status = StatusType.ONLINE;
                resetOnlineUsers();
                break;
            case OFFLINE:
                System.out.println("Peer: Offline");
                this.status = StatusType.OFFLINE;
                resetOnlineUsers();
                break;
            default:
                System.err.println("Error: PeerManager - Unknown status");
                break;
        }
    }

    /**
     * Get peer status
     *
     * @return peer status
     */
    @Override
    public StatusType getStatus() {
        return status;
    }

    /**
     * Method for adding online user
     *
     * @param name
     * @param address
     * @param port
     * @return
     */
    @Override
    public boolean addOnlineUser(String name, String address, int port) {

        //Check if online
        if (!isOnline()) {
            viewManager.displayError(ErrorType.NOT_ONLINE);
            return false;
        }

        //Check if friend already added
        if (findOnlineUser(name) != null) {
            viewManager.displayError(ErrorType.FRIEND_ALREADY_CHATTING);
            return false;
        }

        for (int i = 0; i < MAX_USERS; i++) {
            if (onlineUsers[i] == null) {

                onlineUsers[i] = new User(name, "");
                onlineUsers[i].setAddress(address);
                onlineUsers[i].setPort(port);

                break;
            }
            if (i == MAX_USERS - 1) {
                viewManager.displayError(ErrorType.FULL_ONLINE_USER_LIST);
                return false;
            }
        }

        return true;
    }

    /**
     * Get online user names
     *
     * @return string of online users
     */
    @Override
    public String[] getOnlineUserNames() {
        String[] ret = new String[MAX_USERS];
        int current = 0;
        for (int i = 0; i < MAX_USERS; i++) {
            if (onlineUsers[i] != null) {
                ret[current] = onlineUsers[i].getUsername();
                current++;
            }
        }
        return ret;
    }

    /**
     * Method for getting online user photos
     *
     * @return array of online user photos
     */
    public BufferedImage[] getOnlineUserPhotos() {
        BufferedImage[] ret = new BufferedImage[MAX_USERS];
        int current = 0;
        for (int i = 0; i < MAX_USERS; i++) {
            if (onlineUsers[i] != null) {
                ret[current] = onlineUsers[i].getImage();
                current++;
            }
        }
        return ret;
    }

    /**
     * Resets the connection
     */
    private void resetOnlineUsers() {
        onlineUsers = new User[MAX_USERS];
    }

    /**
     * Check online status
     *
     * @return online status
     */
    @Override
    public boolean isOnline() {
        return status == StatusType.ONLINE;
    }

    /**
     * Get number of online users
     *
     * @return number of online users
     */
    @Override
    public int getOnlineUsersNumber() {
        int current = 0;
        for (int i = 0; i < MAX_USERS; i++) {
            if (onlineUsers[i] != null) {
                current++;
            }
        }
        return current;
    }

    /**
     * Get users
     *
     * @return users
     */
    @Override
    public User[] getUsers() {
        return onlineUsers;
    }

    /**
     * Set online user
     *
     * @param username online user username
     * @param photo online user photo
     */
    @Override
    public void setUser(String username, String photo) {
        onlineUser = new User(username, photo);
    }

    /**
     * Remove online user
     *
     * @param username online user username
     */
    @Override
    public boolean removeOnlineUser(String username) {
        boolean ret = false;
        for (int i = 0; i < MAX_USERS; i++) {
            if (onlineUsers[i] != null && onlineUsers[i].getUsername().equals(username)) {
                onlineUsers[i] = null;
                ret = true;
                break;
            }
        }
        return ret;
    }

    /**
     * Get current online user username
     *
     * @return online user username
     */
    @Override
    public String getCurrentUser() {
        String ret = null;
        if (!isOnline()) {
            viewManager.displayError(ErrorType.NOT_ONLINE);
            return ret;
        }
        if (onlineUser == null) {
            viewManager.displayError(ErrorType.NOT_ONLINE);
            return ret;
        }
        ret = onlineUser.getUsername();
        return ret;
    }

    /**
     * Find online user by name
     *
     * @param name username
     * @return matching user
     */
    @Override
    public User findOnlineUser(String name) {
        User ret = null;
        for (int i = 0; i < MAX_USERS; i++) {

            if (onlineUsers[i] != null && onlineUsers[i].getUsername().equals(name)) {
                ret = onlineUsers[i];
                break;
            }
        }
        return ret;
    }
}
