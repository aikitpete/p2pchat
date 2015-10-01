/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.peer;

import p2pchat.model.User;
import p2pchat.model.StatusType;

/**
 * Interface for account management (Server)
 *
 * @author USER
 */
public interface IOnlineUserManagerPeer {

    /**
     * Method used to check if account is online
     *
     * @return
     */
    public boolean isOnline();

    /**
     * Method used to add online user
     *
     * @param username username
     * @param address address
     * @param port port;
     * @return
     */
    public boolean addOnlineUser(String username, String address, int port);

    /**
     * Method used to get current user
     *
     * @return
     */
    public String getCurrentUser();

    /**
     * Method used to find online user
     *
     * @param username username
     * @return match
     */
    public User findOnlineUser(String username);

    /**
     * Method used to remove online user
     *
     * @param username username
     * @return  deletion status
     */
    public boolean removeOnlineUser(String username);

    /**
     * Method used to get status
     *
     * @return peer status
     */
    public StatusType getStatus();

    /**
     * Method used to get user names
     *
     * @return user array
     */
    public String[] getOnlineUserNames();

    /**
     * Method to set status
     *
     * @param statusType status type
     */
    public void setStatus(StatusType statusType);

    /**
     * Methof used to set new user
     *
     * @param username username
     * @param photo photo
     */
    public void setUser(String username, String photo);

    /**
     * Get number of online users
     *
     * @return number of online users
     */
    public int getOnlineUsersNumber();

    /**
     * Get users
     *
     * @return users
     */
    public User[] getUsers();

}
