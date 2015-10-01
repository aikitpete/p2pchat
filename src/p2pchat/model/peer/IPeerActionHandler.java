/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.peer;

/**
 *
 * @author USER
 */
public interface IPeerActionHandler {

    /**
     * Handle friend action
     * @param friendname friendname
     */
    void handleAddFriendAction(String friendname);

    /**
     * Handle login action
     * @param username username
     * @param password password
     */
    void handleLoginAction(String username, String password);

    /**
     * Handle quit action
     */
    void handleQuitAction();

    /**
     * Handle register action
     * @param username username
     * @param password password
     * @param rePassword retype password
     */
    void handleRegisterAction(String username, String password, String rePassword);

    /**
     * Handle remove friend action
     * @param friendname friendname
     */
    void handleRemoveFriendAction(String friendname);

    /**
     * Handle send action
     */
    void handleSendAction();

    /**
     * Handle sessing action
     * @param address server address
     * @param port server port
     */
    void handleSettingsAction(String address, String port);

    /**
     * Method used to handle status
     * @param args command arguments
     */
    void handleStatusAction(String[] args);
    
}
