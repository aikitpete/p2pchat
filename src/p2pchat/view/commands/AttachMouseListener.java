/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.commands;

import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 * Class for attaching mouse listener
 *
 * @author USER
 */
public class AttachMouseListener implements ICallable {

    /**
     * Attaches mouse listener to an object (Command pattern)
     * @param object target object
     * @param parameter listener to be attached
     */
    @Override
    public void call(Object object, Object parameter) {
        JButton component = (JButton) object;
        MouseListener listener = (MouseListener) parameter;
        component.addMouseListener(listener);
    }
}
