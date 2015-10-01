/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.views;

/**
 * Interface containing functions which must be implemented by views
 * @author USER
 */
public interface IView {
    
    /**
     * Reset window content
     */
    void reset();
    
    /**
     * Set focus on window
     */
    void setFocus();
    
    /**
     * Loose focus
     */
    void looseFocus();
}
