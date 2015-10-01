/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.commands;

import p2pchat.model.StatusType;
import p2pchat.view.custom.IUpdatable;

/**
 * Class for updating status
 *
 * @author USER
 */
public class UpdateStatus implements ICallable {

    /**
     * Updates status for of an object (Command pattern)
     * @param object target object
     * @param parameter status parameter
     */
    @Override
    public void call(Object object, Object parameter) {
        IUpdatable component = (IUpdatable) object;
        StatusType status = (StatusType) parameter;
        component.setStatus(status);
    }
}
