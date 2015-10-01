/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.mock;

import p2pchat.model.User;
import p2pchat.model.server.ILoginController;

/**
 *
 * @author USER
 */
public class LoginControllerMock implements ILoginController {

    public String logout;
    
    @Override
    public User login(int session, String address, int port, String username, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void logout(String username) {
        logout = username;
    }
    
}
