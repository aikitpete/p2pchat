/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import p2pchat.model.peer.IPeerActionHandler;
import p2pchat.view.ButtonType;

/**
 * Default action listener
 * This class listens to most events that are triggered by mouse click
 */
public class DefaultActionListener implements ActionListener {

    //Variables
    IPeerActionHandler peerActionHandler;
    
    /*
     * Constructor
     */
    public DefaultActionListener(IPeerActionHandler peerActionHandler) {
        this.peerActionHandler = peerActionHandler;
    }

    /**
     * Triggered when action is performend
     * (mouse click)
     * @param e event performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonType command = null;
        Object source = e.getSource();
        String args[] = null;
                
        if (!(source instanceof JComboBox)) {
            
            command = ButtonType.valueOf(e.getActionCommand());
            
        } else if (source instanceof JComboBox) {
            
            JComboBox comboBox = (JComboBox) source;
            command = ButtonType.valueOf(comboBox.getActionCommand());
            args = new String[1];
            args[0] = comboBox.getSelectedItem().toString();
            
        } else {
            
            System.err.print("Warning: DefaultActionListener - Unrecognized UI component: "+source.toString());
            
        }

        switch (command) {
            case STATUS:
                peerActionHandler.handleStatusAction(args);
                break;
            case SUBMIT_MESSAGE:
                peerActionHandler.handleSendAction();
                break;
            case INPUT_BOX:
                peerActionHandler.handleSendAction();
                break;
            case QUIT:
                peerActionHandler.handleQuitAction();
                break;
            default:
                System.err.println("Warning: DefaultActionListener - Unrecognized action command: "+command);
                break;
        }

    }
}
