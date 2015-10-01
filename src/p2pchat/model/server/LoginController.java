/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.server;

import p2pchat.model.ErrorType;
import p2pchat.model.User;
import p2pchat.persistence.server.IPersistenceUser;

/**
 *
 * @author USER
 */
public class LoginController implements ILoginController {

    private IOnlineUserManagerServer onlineUserManager;
    private IPersistenceUser persistenceServer;

    public LoginController(IOnlineUserManagerServer onlineUserManager) {
        this.onlineUserManager = onlineUserManager;
        this.persistenceServer = persistenceServer;
    }

    /**
     * Login user
     *
     * @param session session number
     * @param address peer address used by user
     * @param port port number used by user
     * @param username username
     * @param password password
     * @return logged in user instance
     */
    @Override
    public User login(int session, String address, int port, String username, String password) {

        //Check online status
        if (onlineUserManager.isOnline(username)) {
            System.err.println(ErrorType.ALREADY_ONLINE);
            return null;
        }

        //Check password length
        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            System.err.println(ErrorType.BLANK_FIELD);
            return null;
        }

        //Find user
        PersistentUser user = PersistentUser.findUserByName(username);

        //Check search result
        if (user == null) {
            return null;
        }
        
        //Verify password
        if (!user.getPassword().equals(password)) {
            return null;
        }
        
        //Set session parameters for user
        user.setSession(session);
        user.setAddress(address);
        user.setPort(session+2010);

        return user;
    }

    /**
     * Logout user based on username
     *
     * @param username username
     */
    @Override
    public void logout(String username) {
        onlineUserManager.logout(username);
    }
;


}
