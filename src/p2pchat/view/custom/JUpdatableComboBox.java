/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.custom;

import java.awt.Component;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import p2pchat.model.StatusType;
import p2pchat.view.ButtonType;
import p2pchat.view.UIFactory;

/**
 *
 * @author USER
 */
public class JUpdatableComboBox extends JComboBox implements IUpdatable {

    ButtonType type;

    public JUpdatableComboBox() {
        super();

        addItem(StatusType.OFFLINE);
        addItem(StatusType.ONLINE);
    }

    @Override
    public void setActive(boolean active) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StatusType getStatus() {
        return (StatusType) this.getSelectedItem();
    }

    @Override
    public void setStatus(StatusType status) {
        setSelectedItem(status);
    }

    @Override
    public ButtonType getButtonType() {
        return type;
    }

    @Override
    public void setButtonType(ButtonType type) {
        this.type = type;
    }
}
