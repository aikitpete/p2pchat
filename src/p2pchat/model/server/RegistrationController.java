/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.server;

import p2pchat.model.ErrorType;
import p2pchat.persistence.server.IPersistenceUser;

/**
 *
 * @author USER
 */
public class RegistrationController implements IRegistrationController {

    public RegistrationController() {
    }

    /**
     * Register new user
     *
     * @param username username
     * @param password password
     * @param rePassword re-typed password
     * @param photo photo path
     * @return
     */
    @Override
    public boolean register(String username, String password, String rePassword, String photo) {

        //Check password length
        if (username == null || password == null || rePassword == null || 
                username.length() == 0 || password.length() == 0 || rePassword.length() == 0) {
            System.err.println(ErrorType.BLANK_REGISTRATION_FIELD);
            return false;
        }

        //Check if passwords match
        if (!password.equals(rePassword)) {
            System.err.println(ErrorType.PASSWORDS_DONT_MATCH);
            return false;
        }

        //Check if user already exists
        if (PersistentUser.findUserByName(username) != null) {
            System.err.println(ErrorType.ALREADY_REGISTERED);
            return false;
        }

        //Perform registration
        boolean ret = PersistentUser.addNewUser(username, password, photo);

        return ret;
    }
}
