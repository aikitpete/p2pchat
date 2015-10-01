/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.custom;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import p2pchat.model.StatusType;
import p2pchat.view.ButtonType;

/**
 *
 * @author USER
 */
public class JUpdatableMenuItem extends JMenuItem implements IUpdatable {

    ButtonType type;

    public JUpdatableMenuItem(String name, ImageIcon icon) {
        super(name, icon);
    }

    @Override
    public void setActive(boolean active) {
    }

    @Override
    public void setStatus(StatusType status) {
    }

    @Override
    public ButtonType getButtonType() {
        return type;
    }

    @Override
    public void setButtonType(ButtonType type) {
        this.type = type;
    }

    @Override
    public StatusType getStatus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
