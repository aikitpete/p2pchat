/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.commands;

import p2pchat.view.custom.IUpdatable;

/**
 *
 * @author USER
 */
public class SetActive implements ICallable {

    @Override
    public void call(Object object, Object parameter) {
        IUpdatable component = (IUpdatable) object;
        boolean value = (boolean) parameter;
        component.setActive(value);
    }
    
}
