/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.server;

/**
 *
 * @author USER
 */
public interface IRegistrationController {

    /**
     * Register new user
     *
     * @param username username
     * @param password password
     * @param rePassword re-typed password
     * @param photo photo path
     * @return
     */
    boolean register(String username, String password, String rePassword, String photo);
    
}
