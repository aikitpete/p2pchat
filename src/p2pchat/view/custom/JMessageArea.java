/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.custom;

import javax.swing.JTextArea;
import p2pchat.view.ViewConstants;

/**
 * Class for UI component used to display messages
 * @author USER
 */
public class JMessageArea extends JTextArea {
    
    //Constants

    /**
     * Constructor
     */
    public JMessageArea () {
        super();
        setEditable(false);
    }
    
    /**
     * Append login message
     */
    public void loginMessage() {
        append(ViewConstants.LOGIN_MESSAGE);
    }
    
    /**
     * Append logout message
     */
    public void logoutMessage() {
        append(ViewConstants.LOGOUT_MESSAGE);
    }
    
    /**
     * Append custom string
     * @param text custom string
     */
    @Override
    public void append(String text) {
        super.append(text);
    }
    
}
