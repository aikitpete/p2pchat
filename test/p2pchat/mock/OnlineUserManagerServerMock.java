/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.mock;

import p2pchat.model.User;
import p2pchat.model.server.IOnlineUserManagerServer;

/**
 *
 * @author USER
 */
public class OnlineUserManagerServerMock implements IOnlineUserManagerServer {

    public String findOnlineUserUsername1;
    public User findOnlineUserUser1;
    public String findOnlineUserUsername2;
    public User findOnlineUserUser2;
    public User[] getOnlineUsers;

    @Override
    public boolean addOnlineUser(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User endSession(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User findOnlineUser(String username) {
        if (username.equals(findOnlineUserUsername1)) {

            return findOnlineUserUser1;

        } else if (username.equals(findOnlineUserUsername2)) {

            return findOnlineUserUser2;

        } else {
            return null;
        }
    }

    @Override
    public User[] getOnlineUsers() {
        return getOnlineUsers;
    }

    @Override
    public boolean isOnline(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void logout(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getOnlineUsersNumber() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
