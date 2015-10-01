/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.mock;

import p2pchat.model.server.IRegistrationController;

/**
 *
 * @author USER
 */
public class RegistrationControllerMock implements IRegistrationController {

    public String registerInput;
    public boolean registerReturn;
    
    @Override
    public boolean register(String username, String password, String rePassword, String photo) {
        registerInput = username;
        return registerReturn;
    }
    
}
