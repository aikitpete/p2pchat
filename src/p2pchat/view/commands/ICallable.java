/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.commands;

/**
 * Interface definition for commands
 * @author USER
 */
public interface ICallable {
    
    /**
     * Call method for executing commands on objects (Command pattern)
     * @param object target object
     * @param parameter command parameter
     */
    void call(Object component, Object parameter);

}
