/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.controller.listeners;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import p2pchat.controller.P2PChatController;
import p2pchat.model.peer.IOnlineUserManagerPeer;
import p2pchat.model.peer.IPeerActionHandler;
import p2pchat.model.peer.PeerActionHandler;
import p2pchat.view.ButtonType;
import p2pchat.view.FieldType;
import p2pchat.view.IViewManager;
import p2pchat.view.custom.JUpdatableButton;

/**
 * Listener to all submit events
 *
 */
public class SubmitActionListener implements ActionListener {

    //Variables
    IPeerActionHandler peerActionHandler;
    IOnlineUserManagerPeer status;
    IViewManager viewManager;

    /**
     * Constructor
     * @param peerActionHandler peer action handler
     * @param status peer status manager
     */
    public SubmitActionListener(IPeerActionHandler peerActionHandler, IOnlineUserManagerPeer status, IViewManager viewManager) {
        this.peerActionHandler = peerActionHandler;
        this.status = status;
        this.viewManager = viewManager;
    }

    /**
     * Triggered when submit action is performed
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Container container;
        container = (Container) e.getSource();

        if (container instanceof JButton) {
            container = (Container) container.getParent();
            container = (Container) container.getParent();
            container = (Container) container.getComponent(0);
        } else {
            container = (Container) container.getParent();
        }

        ButtonType command = ButtonType.valueOf(e.getActionCommand());
        String args[] = getArgs(container.getComponents());

        switch (command) {
            case SUBMIT_LOGIN:
                loginActionPerformed(args);
                break;
            case SUBMIT_REGISTER:
                registerActionPerformed(args);
                break;
            case SUBMIT_ADD_FRIEND:
                addFriendActionPerformed(args);
                break;
            case SUBMIT_SETTINGS:
                settingsActionPerformed(args);
                break;
            case SUBMIT_REMOVE_FRIEND:
                removeFriendActionPerformed(args);
                break;
            case CANCEL:
                cancelActionPerformed(args);
                break;
            default:
                System.err.println("Warning: SubmitActionListener - Unsupported command: " + command);
                break;
        }

    }

    /**
     * Triggered when register submit action is performed
     * @param args array of strings
     */
    private void registerActionPerformed(String args[]) {

        peerActionHandler.handleRegisterAction(args[0], args[1], args[2]);
    }

    /**
     * Triggered when add friend submit action is performed
     * @param args array of strings
     */
    private void addFriendActionPerformed(String args[]) {

        peerActionHandler.handleAddFriendAction(args[0]);
    }

    /**
     * Triggered when settings submit action is performed
     * @param args array of strings
     */
    private void settingsActionPerformed(String args[]) {

        peerActionHandler.handleSettingsAction(args[0], args[1]);

        viewManager.setFocus();
    }

    /**
     * Triggered when remove friend submit action is performed
     * @param args array of strings
     */
    private void removeFriendActionPerformed(String args[]) {
        peerActionHandler.handleRemoveFriendAction(args[0]);

    }

    /**
     * Triggered when login submit action is performed
     * @param args array of strings
     */
    private void loginActionPerformed(String args[]) {

        peerActionHandler.handleLoginAction(args[0], args[1]);

    }

    /**
     * Trigegred when cancel action is performed
     * @param args array of strings
     */
    private void cancelActionPerformed(String args[]) {
        viewManager.setStatus(status.getStatus());
        viewManager.setFocus();

    }

    /**
     * Used to get arguments from UI components
     * @param components UI component array
     * @return array of strings to be used as arguments
     */
    private String[] getArgs(Component[] components) {
        String args[] = new String[components.length];

        int current = 0;

        JTextField textField;

        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JTextField) {

                textField = (JTextField) components[i];
                args[current] = textField.getText();
                current++;
            }
        }

        return args;
    }
}
