package p2pchat.controller.listeners;

import java.awt.Component;
import java.awt.MenuItem;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import p2pchat.P2PChat;
import p2pchat.controller.P2PChatController;
import p2pchat.model.peer.IOnlineUserManagerPeer;
import p2pchat.view.ButtonType;
import p2pchat.view.IViewManager;
import p2pchat.view.custom.IUpdatable;

/**
 * Reset content of input field
 *
 */
public class DefaultMouseListener implements MouseListener {

    //Interfaces
    IOnlineUserManagerPeer peer;
    IViewManager viewManager;

    /**
     * Constructor
     * @param peer peer account manager 
     */
    public DefaultMouseListener(IOnlineUserManagerPeer peer, IViewManager viewManager) {
        this.peer = peer;
        this.viewManager = viewManager;
    }

    /**
     * Triggered when button is clicked
     * @param e mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Triggered when button is pressed
     * @param e mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        ButtonType command = null;
        Object source = e.getSource();

        if (source instanceof JMenuItem) {
            JMenuItem menuItem = (JMenuItem) source;
            command = ButtonType.valueOf(menuItem.getActionCommand());
        } else if (source instanceof JButton) {
            JButton button = (JButton) source;
            command = ButtonType.valueOf(button.getActionCommand());
        } else {
            System.err.print("Error: DefaultMouseListener - Unrecognized UI component: " + source.toString());
        }

        switch (command) {
            case REMOVE_FRIEND:
                removeFriendActionPerformed();
                break;
            case REGISTER:
                registerActionPerformed();
                break;
            case ADD_FRIEND:
                addFriendActionPerformed();
                break;
            case SETTINGS:
                settingsActionPerformed();
                break;
            case PROFILE:
                profileActionPerformed();
                break;
            case QUIT:
                P2PChatController.getInstance().exit();
            default:
                System.err.println("Warning: DefaultMouseHandler - Unsupported command: "+command);
                break;
        }
    }
    
    /**
     * Triggered when remove friend button is clicked
     */
    private void removeFriendActionPerformed() {
        if (peer.isOnline()) {
            viewManager.displayRemoveFriend();
        }
    }

    /**
     * Triggered when register button is clicked
     */
    private void registerActionPerformed() {
        viewManager.displayRegister();
    }

    /**
     * Triggered when add friend button is clicked
     */
    private void addFriendActionPerformed() {
        if (peer.isOnline()) {
            viewManager.displayAddFriend();
        }
    }

    /**
     * Triggered when settings button is clicked
     */
    private void settingsActionPerformed() {
        viewManager.displaySettings();
    }

    /**
     * Triggered when profile button is clicked
     */
    private void profileActionPerformed() {
        System.out.println("Changing profile pictures will be supported in later version");
    }

    /**
     * Triggered when mouse button is released
     * @param e mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Triggered when component area is entered by mouse
     * @param e  mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        //IUpdatable icon = (IUpdatable) e.getSource();
        //icon.setActive(true);
    }

    /**
     * Triggered when component area is exited by mouse
     * @param e 
     */
    @Override
    public void mouseExited(MouseEvent e) {
        //IUpdatable icon = (IUpdatable) e.getSource();
        //icon.setActive(false);
    }
}
