/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import p2pchat.model.ErrorType;
import p2pchat.model.P2PChatModel;
import p2pchat.model.StatusType;
import p2pchat.view.views.*;

/**
 * Class representing view from MVC
 *
 * @author USER
 */
public interface IViewManager {

    /**
     * Method that initializes view
     */
    public void init();


    /**
     * Get user input
     *
     * @return user input
     */
    public String getUserInput();

    /**
     * Method for appending text into message area
     *
     * @param text message
     */
    public void addMessage(String text);

    /**
     * Set text in user input field
     *
     * @param input input
     */
    public void setUserInput(String input);

    /**
     * Set status displayed
     *
     * @param status new status
     */
    public void setStatus(StatusType status);

    /**
     * Set status caption displayed
     *
     * @param status new status
     * @param username user currently online
     * @param users users in chat
     */
    public void setStatusCaption(StatusType status, String username, String[] users);

    /**
     * Set user image
     *
     * @param path image
     */
    public void setUserImage(String path);

    /**
     * Set focus on add friend view
     */
    public void displayAddFriend();

    /**
     * Set focus on remove friend view
     */
    public void displayRemoveFriend();

    /**
     * Set focus on register view
     */
    public void displayRegister();

    /**
     * Set focus on rsettings view
     */
    public void displaySettings();

    /**
     * Set focus on login view
     */
    public void displayLogin();

    /**
     * Add listener for default actions
     *
     * @param listener action listener
     */
    public void addDefaultListener(ActionListener listener);

    /**
     * Add listener for additional functions in features panel
     *
     * @param mouseListener mouse listener
     */
    public void addFeaturesListener(MouseListener mouseListener);

    /**
     * Add listener for submit actions
     *
     * @param listener submit action listener
     */
    public void addSubmitListener(ActionListener listener);

    /**
     * Add listener for window actions
     *
     * @param windowListener window listener
     */
    public void addWindowListener(WindowListener windowListener);

    /**
     * Set focus on main view
     */
    public void setFocus();

    /**
     * Display error message based on error type
     *
     * @param error error type
     */
    public void displayError(ErrorType error);

    /**
     * Display error message based on error code
     *
     * @param errorCode error code
     */
    public void displayError(String errorCode);

    /**
     * Hides all views except main view
     */
    public void hideAll();

    /**
     * Close view - called when exiting P2PChat
     */
    public void close();
}
