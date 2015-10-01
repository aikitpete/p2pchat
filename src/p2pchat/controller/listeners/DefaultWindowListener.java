/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.controller.listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import p2pchat.controller.P2PChatController;

/**
 * Class for handling all window actions
 * @author USER
 */
public class DefaultWindowListener implements WindowListener {

    /**
     * Triggered when window is opened
     * @param e window event
     */
    @Override
    public void windowOpened(WindowEvent e) {
    }

    /**
     * Triggered when window is closing
     * @param e window event
     */
    @Override
    public void windowClosing(WindowEvent e) {
        P2PChatController.getInstance().exit();
    }

    /**
     * Triggered when window is closed
     * @param e window event
     */
    @Override
    public void windowClosed(WindowEvent e) {
    }

    /**
     * Triggered when window is iconified
     * @param e window event
     */
    @Override
    public void windowIconified(WindowEvent e) {
    }

    /**
     * Triggered when window is deiconified
     * @param e window event
     */
    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    /**
     * Triggered when window is activated
     * @param e window event
     */
    @Override
    public void windowActivated(WindowEvent e) {
    }

    /**
     * Triggered when window is deactivated
     * @param e window event
     */
    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
