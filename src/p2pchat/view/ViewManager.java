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
public class ViewManager extends JFrame implements IViewManager {

    //Views
    public static MainView mainView;
    public static AddFriendView addFriendView;
    public static RemoveFriendView removeFriendView;
    public static RegisterView registerView;
    public static LoginView loginView;
    public static SettingsView settingsView;

    /**
     * Constructor
     */
    public ViewManager() {

    }

    /**
     * Method that initializes view
     */
    @Override
    public void init() { 
        mainView = new MainView();
        addFriendView = new AddFriendView();
        removeFriendView = new RemoveFriendView();
        registerView = new RegisterView();
        loginView = new LoginView();
        settingsView = new SettingsView();

        setStatusCaption(StatusType.OFFLINE, null, null);
        mainView.setFocus();
    }

    /**
     * Get user input
     *
     * @return user input
     */
    @Override
    public String getUserInput() {
        return mainView.getUserInput();
    }

    /**
     * Method for appending text into message area
     *
     * @param text message
     */
    @Override
    public void addMessage(String text) {
        mainView.addMessage(text);
    }

    /**
     * Set text in user input field
     *
     * @param input input
     */
    @Override
    public void setUserInput(String input) {
        mainView.setUserInput(input);
    }

    /**
     * Set status displayed
     *
     * @param status new status
     */
    @Override
    public final void setStatus(StatusType status) {
        mainView.setStatus(status);
    }

    /**
     * Set status caption displayed
     *
     * @param status new status
     * @param username user currently online
     * @param users users in chat
     */
    @Override
    public final void setStatusCaption(StatusType status, String username, String[] users) {
        String caption;

        if (status == StatusType.OFFLINE) {

            //Message displayed when status is offline
            caption = P2PChatView.OFFLINE_STATUS_STRING;

        } else {

            if (users[0] != null) {

                //Message displayed when other users are chatting
                caption = username + P2PChatView.CHAT_STATUS_STRING;

                //Append online user names
                for (int i = 0; i < users.length; i++) {
                    if (users[i] == null) {
                        break;
                    }
                    caption = caption + users[i] + " ";
                }
            } else {

                //Message displayed when no users joined the chat
                caption = username + P2PChatView.ONLINE_STATUS_STRING;

            }
        }
        mainView.setStatusCaption(caption);
    }

    /**
     * Set user image
     *
     * @param path image
     */
    @Override
    public void setUserImage(String path) {
        mainView.setUserImage(path);
    }

    /**
     * Set focus on add friend view
     */
    @Override
    public void displayAddFriend() {
        addFriendView.setFocus();
    }

    /**
     * Set focus on remove friend view
     */
    @Override
    public void displayRemoveFriend() {
        removeFriendView.setFocus();
    }

    /**
     * Set focus on register view
     */
    @Override
    public void displayRegister() {
        registerView.setFocus();
    }

    /**
     * Set focus on rsettings view
     */
    @Override
    public void displaySettings() {
        settingsView.setFocus();
    }

    /**
     * Set focus on login view
     */
    @Override
    public void displayLogin() {
        mainView.setEnabled(false);
        loginView.setFocus();
    }

    /**
     * Add listener for default actions
     *
     * @param listener action listener
     */
    @Override
    public void addDefaultListener(ActionListener listener) {
        mainView.addDefaultListener(listener);
    }

    /**
     * Add listener for additional functions in features panel
     *
     * @param mouseListener mouse listener
     */
    @Override
    public void addFeaturesListener(MouseListener mouseListener) {
        mainView.addFeaturesListener(mouseListener);
    }

    /**
     * Add listener for submit actions
     *
     * @param listener submit action listener
     */
    @Override
    public void addSubmitListener(ActionListener listener) {
        loginView.addSubmitListener(listener);
        addFriendView.addSubmitListener(listener);
        registerView.addSubmitListener(listener);
        removeFriendView.addSubmitListener(listener);
        settingsView.addSubmitListener(listener);
    }

    /**
     * Add listener for window actions
     *
     * @param windowListener window listener
     */
    @Override
    public void addWindowListener(WindowListener windowListener) {
        mainView.addWindowListener(windowListener);
    }

    /**
     * Set focus on main view
     */
    @Override
    public final void setFocus() {
        hideAll();
        mainView.setEnabled(true);
        mainView.setFocus();
        mainView.toFront();
    }

    /**
     * Display error message based on error type
     *
     * @param error error type
     */
    @Override
    public void displayError(ErrorType error) {
        System.err.println(error.getCaption());
        JOptionPane.showMessageDialog(this, error.getCaption(), WindowType.ERROR.getDescription(), JOptionPane.ERROR_MESSAGE);
        
    }

    /**
     * Display error message based on error code
     *
     * @param errorCode error code
     */
    @Override
    public void displayError(String errorCode) {

        if (errorCode == null) {
            return;
        }

        ErrorType error = ErrorType.valueOf(errorCode);

        displayError(error);
    }

    /**
     * Hides all views except main view
     */
    @Override
    public void hideAll() {
        addFriendView.looseFocus();
        removeFriendView.looseFocus();
        loginView.looseFocus();
        registerView.looseFocus();
        settingsView.looseFocus();
    }

    /**
     * Close view - called when exiting P2PChat
     */
    @Override
    public void close() {
    }
}
