/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.server;

import p2pchat.model.User;

/**
 * Interface for account management (Server)
 *
 * @author USER
 */
public interface IOnlineUserManagerServer {

    /**
     * Add online user
     * @param user
     * @return 
     */
    public boolean addOnlineUser(User user);
    
    /**
     * End session
     *
     * @param index session number
     * @return user associated with the session
     */
    public User endSession(int index);

    /**
     * Find online user with a specified username
     *
     * @param username username
     * @return matching user instance
     */
    public User findOnlineUser(String username);

    /**
     * Get online users
     *
     * @return online users array
     */
    public User[] getOnlineUsers();

    public boolean isOnline(String username);
    
    public void logout(String username);
    
    public int getOnlineUsersNumber();
}
