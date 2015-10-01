/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.commands;

import java.awt.event.ActionListener;
import p2pchat.view.custom.JUpdatableButton;

/**
 * Class for attaching action listener
 * @author USER
 */
public class AttachActionListener implements ICallable {

    /**
     * Attaches action listener to object (Command pattern)
     * @param object target object
     * @param parameter listener to be attched
     */
    @Override
    public void call(Object object, Object parameter) {
        JUpdatableButton component = (JUpdatableButton) object;
        ActionListener listener = (ActionListener) parameter;
        component.addActionListener(listener);
    }

}
