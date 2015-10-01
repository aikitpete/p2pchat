/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.custom;

import java.awt.Dimension;
import javax.swing.JButton;
import p2pchat.model.StatusType;

/**
 * Class representing button responding to status change
 * @author USER
 */
public class JUpdatableButton extends JButton implements IUpdatable {
    
    //Variables
    private boolean hide;
    protected Dimension dim;

    /**
     * Constructor
     * @param name button name
     * @param hide flag to specify hiding when status changed
     * @param dim dimensions
     */
    public JUpdatableButton(String name, boolean hide, Dimension dim) {
        super(name);
        this.dim = dim;

        this.hide = hide;
        
        this.setMaximumSize(this.dim);
        this.setMinimumSize(this.dim);
        this.setSize(this.dim);
        this.setPreferredSize(this.dim);

        if (hide) {
            this.setVisible(false);
        }
    }

    /**
     * Update button status
     * @param status new status
     */
    @Override
    public void setStatus(StatusType status) {
        if (hide) {
            if (status == StatusType.ONLINE) {
                this.setVisible(true);
            } else {
                this.setVisible(false);
            }
        }
    }
}
