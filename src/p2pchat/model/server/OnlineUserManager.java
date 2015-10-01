/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.server;

import java.util.Arrays;
import p2pchat.connection.P2PChatConnection;
import p2pchat.model.*;
import utils.DateUtils;
import utils.VerifyUtils;
import p2pchat.model.User;

/**
 * Class for user management
 *
 * @author USER
 */
public class OnlineUserManager implements IOnlineUserManagerServer {

    //Constants
    //private final static int MAXIMUM_USERS = 100;
    public final static int MAXIMUM_ONLINE_USERS = 10;
    //Variables
    private P2PChatConnection connectionManager;
    private User onlineUsers[];

    /**
     * Constructor
     */
    public OnlineUserManager() {
        onlineUsers = new User[MAXIMUM_ONLINE_USERS];


    }

    /**
     * Authenticate user
     *
     * @param User online user
     */
    @Override
    public boolean addOnlineUser(User user) {

        boolean ret = false;
        
        //Check if user is online already
        if (findOnlineUser(user.getUsername())!=null) {
            return ret;
        }

        if (user != null) {
            for (int i = 0; i < MAXIMUM_ONLINE_USERS; i++) {

                //Check if field is empty
                if (onlineUsers[i] == null) {
                    onlineUsers[i] = user;

                    checkOnline();
                    ret = true;

                    break;
                }

                //Check array full condition
                if (i == MAXIMUM_ONLINE_USERS) {
                    System.out.println("Warning: UserManager - Maximum number of users reached");
                    return ret;
                }
            }
        }

        return ret;
    }

    /**
     * Check if user is online based on username
     *
     * @param username
     * @return search result
     */
    @Override
    public boolean isOnline(String username) {

        //Initialize return variable
        boolean ret = false;

        for (int i = 0; i < onlineUsers.length; i++) {

            //Check possible match
            if (onlineUsers[i] != null && onlineUsers[i].getUsername().equals(username)) {
                ret = true;
                break;
            }
        }

        return ret;
    }

    /**
     * Logout user based on username
     *
     * @param username username
     */
    @Override
    public void logout(String username) {
        for (int i = 0; i < onlineUsers.length; i++) {

            //Check possible match
            if (onlineUsers[i] != null && onlineUsers[i].getUsername().equals(username)) {
                onlineUsers[i] = null;
            }
        }
        checkOnline();
    }

    /**
     * Check who is online by printing out all online users into console
     */
    private void checkOnline() {
        System.out.print("Online users: ");
        for (int j = 0; j < MAXIMUM_ONLINE_USERS; j++) {

            //Check if selected position contains user
            if (onlineUsers[j] != null) {

                //Print username of user at current position
                System.out.print(onlineUsers[j].getUsername() + ", ");
            }
        }
        System.out.println("");
    }

    /**
     * End session with selected index
     *
     * @param index session number
     * @return user associated with the session
     */
    @Override
    public User endSession(int index) {

        //Initialize return variable
        User tmp = null;

        for (int i = 0; i < onlineUsers.length; i++) {

            //Check possible match
            if (onlineUsers[i] != null && onlineUsers[i].getSession() == index) {
                tmp = onlineUsers[i];
                onlineUsers[i] = null;
            }
        }

        //Check who is online
        checkOnline();

        return tmp;
    }

    /**
     * Find online user by username
     *
     * @param username
     * @return matching user instance
     */
    @Override
    public User findOnlineUser(String username) {
        User ret = null;

        for (int i = 0; i < onlineUsers.length; i++) {
            if (onlineUsers[i] != null && onlineUsers[i].getUsername().equals(username)) {
                return onlineUsers[i];
            }
        }
        return ret;
    }

    /**
     * Get online users
     *
     * @return online users array
     */
    @Override
    public User[] getOnlineUsers() {
        return onlineUsers;
    }
    
        /**
     * Get number of online users
     *
     * @return number of online users
     */
    @Override
    public int getOnlineUsersNumber() {
        int current = 0;
        for (int i = 0; i < onlineUsers.length; i++) {
            if (onlineUsers[i] != null) {
                current++;
            }
        }
        return current;
    }
}