/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view;

import java.awt.Dimension;
import javax.swing.*;
import p2pchat.view.custom.*;

/**
 * Classed used to instantiate UI components (Factory pattern)
 * @author USER
 */
public class UIFactory {

    /**
     * Create JMenuItem instance
     * @param type button type
     * @return JMenuItem instance
     */
    public static JMenuItem createMenuItem(ButtonType type) {
        JMenuItem ret = new JMenuItem(type.getCaption(), new ImageIcon(type.getCaption().toLowerCase() + ".png"));
        ret.setActionCommand(type.getText());
        ret.getAccessibleContext().setAccessibleDescription(type.getDescription());
        return ret;
    }

    /**
     * Create JButton instance
     * @param buttonType button type
     * @return JButton instance
     */
    public static JButton createButton(ButtonType buttonType) {
        JButton ret = new JButton(buttonType.getCaption());
        ret.setActionCommand(buttonType.getText());
        ret.setToolTipText(buttonType.getDescription());
        ret.getAccessibleContext().setAccessibleDescription(buttonType.getDescription());
        return ret;
    }

    /**
     * Create special button instance (containing icon)
     * @param buttonType button type
     * @param dim dimensions
     * @param hide hide on status update flag
     * @return button instance
     */
    public static JUpdatableButton createIconButton(ButtonType buttonType, Dimension dim, boolean hide) {

        JUpdatableButton ret = new JUpdatableButton(null, hide, dim);
        ret.setIcon(new ImageIcon(buttonType.getCaption().toLowerCase() + ".png"));
        ret.setRolloverIcon(new ImageIcon(buttonType.getCaption().toLowerCase() + "-invert.png"));

        ret.setActionCommand(buttonType.getText());
        ret.setToolTipText(buttonType.getDescription());
        ret.getAccessibleContext().setAccessibleDescription(buttonType.getDescription());

        return ret;
    }

    /**
     * Create special button instance (containing image)
     * @param buttonType button type
     * @param dim dimentions
     * @param hide hide on status update flag
     * @return button instance
     */
    public static JUpdatableImageButton createImageButton(ButtonType buttonType, Dimension dim, boolean hide) {

        JUpdatableImageButton ret = new JUpdatableImageButton(null, hide, dim);

        ret.setActionCommand(buttonType.getText());
        ret.setToolTipText(buttonType.getDescription());
        ret.getAccessibleContext().setAccessibleDescription(buttonType.getDescription());

        return ret;
    }

    /**
     * Create JComboBox instace
     * @param buttonType button type
     * @return JComboBox instance
     */
    public static JComboBox createComboBox(ButtonType buttonType) {
        JComboBox ret = new JComboBox();
        ret.setActionCommand(buttonType.getText());
        ret.setToolTipText(buttonType.getDescription());
        ret.getAccessibleContext().setAccessibleDescription(buttonType.getDescription());
        return ret;
    }

    /**
     * Create JTextField instance
     * @param buttonType button type
     * @return JTextField instance
     */
    public static JTextField createTextField(ButtonType buttonType) {
        JTextField ret = new JTextField();
        ret.setActionCommand(buttonType.getText());
        ret.setToolTipText(buttonType.getDescription());
        ret.getAccessibleContext().setAccessibleDescription(buttonType.getDescription());
        return ret;
    }

    /**
     * Create JPanel instance
     * @param dimension dimension
     * @return JPanel instance
     */
    public static JPanel createSidePanel(Dimension dimension) {
        JPanel side = new JPanel();
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
        side.setMaximumSize(new Dimension(P2PChatView.SIDE_WIDTH, P2PChatView.MIN_HEIGHT));
        side.setMinimumSize(new Dimension(P2PChatView.SIDE_WIDTH, P2PChatView.MIN_HEIGHT));
        side.setPreferredSize(new Dimension(P2PChatView.SIDE_WIDTH, P2PChatView.MIN_HEIGHT));
        side.setSize(new Dimension(P2PChatView.SIDE_WIDTH, P2PChatView.MIN_HEIGHT));
        return side;
    }
}
