/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.custom;

import javax.swing.JTextField;
import p2pchat.model.StatusType;
import p2pchat.view.ButtonType;

/**
 *
 * @author USER
 */
public class JUpdatableTextField extends JTextField implements IUpdatable {

    ButtonType buttonType;
    
    public JUpdatableTextField() {
        super();
        
    }

    @Override
    public ButtonType getButtonType() {
        return buttonType;
    }
    
    @Override
    public void setButtonType(ButtonType buttonType) {
        this.buttonType = buttonType;
    }

    @Override
    public void setActive(boolean active) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setStatus(StatusType status) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StatusType getStatus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
