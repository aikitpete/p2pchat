/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.custom;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import p2pchat.model.StatusType;
import p2pchat.view.ButtonType;

/**
 * Interface for UI components wich need to be updated when changing status
 * @author USER
 */
public interface IUpdatable {

    /**
     * Method called when updating status of an object
     * @param status 
     */
    void setStatus(StatusType status);
}
