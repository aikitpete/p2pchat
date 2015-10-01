/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model;

/**
 * Interface implemented by data handlers
 * @author USER
 */
public interface IDataHandler {
    
    /**
     * Used when handling data (Observer pattern)
     * @param obj object to be handled
     */
    public void handleData(Object obj);
}
