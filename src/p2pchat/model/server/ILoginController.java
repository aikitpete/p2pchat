/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.server;

import p2pchat.model.User;

/**
 *
 * @author USER
 */
public interface ILoginController {

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
    User login(int session, String address, int port, String username, String password);

    /**
     * Logout user based on username
     *
     * @param username username
     */
    void logout(String username);
    
}
