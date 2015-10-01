/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.mock;

import p2pchat.model.StatusType;
import p2pchat.model.User;
import p2pchat.model.peer.IOnlineUserManagerPeer;

/**
 *
 * @author USER
 */
public class OnlineUserManagerPeerMock implements IOnlineUserManagerPeer {

    public boolean online;
    public StatusType status;
    public StatusType setStatus;
    public int getOnlineUsersNumber;
    public User[] getUsers;
    public String findOnlineUser;
    public User findOnlineUserUser;
    public String getCurrentUser;
    public String addOnlineUserUsername;
    public String addOnlineUserAddress;
    public int addOnlineUserPort;
    public String[] getOnlineUserNames;
    public String removeOnlineUser;
    public String setUserUsername;
    public String setUserPhoto;

    @Override
    public boolean isOnline() {
        return online;
    }

    @Override
    public boolean addOnlineUser(String username, String address, int port) {
        addOnlineUserUsername = username;
        addOnlineUserAddress = address;
        addOnlineUserPort = port;
        return true;
    }

    @Override
    public String getCurrentUser() {
        return getCurrentUser;
    }

    @Override
    public User findOnlineUser(String username) {
        findOnlineUser = username;
        User ret = null;
        if (findOnlineUserUser != null && findOnlineUserUser.getUsername().equals(username)) {
            ret = findOnlineUserUser;
        }

        return ret;
    }

    @Override
    public boolean removeOnlineUser(String username) {
        removeOnlineUser = username;
        return true;
    }

    @Override
    public StatusType getStatus() {
        return status;
    }

    @Override
    public String[] getOnlineUserNames() {
        return getOnlineUserNames;
    }

    @Override
    public void setStatus(StatusType statusType) {
        setStatus = statusType;;
    }

    @Override
    public void setUser(String username, String photo) {
        setUserUsername = username;
        setUserPhoto = photo;
    }

    @Override
    public int getOnlineUsersNumber() {
        return getOnlineUsersNumber;
    }

    @Override
    public User[] getUsers() {
        return getUsers;
    }
}
