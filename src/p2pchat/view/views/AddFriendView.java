/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.views;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.*;
import p2pchat.view.ButtonType;
import p2pchat.view.FieldType;
import p2pchat.view.UIFactory;
import p2pchat.view.WindowType;
import utils.SpringUtils;

/**
 * Class representing window for adding friends
 * @author USER
 */
public class AddFriendView extends JFrame implements IView {

    //Constants
    private static final int X_SIZE = 240;
    private static final int Y_SIZE = 90;
    //Variables
    private JTextField friendField;
    private JButton submitBtn;
    private JButton cancelBtn;

    /**
     * Constructor
     */
    public AddFriendView() {

        JLabel label;

        //Create content pane
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        //Create and populate the panel.
        JPanel inputs = new JPanel(new SpringLayout());

        label = new JLabel(FieldType.FRIENDNAME.getName(), JLabel.TRAILING);
        inputs.add(label);
        friendField = UIFactory.createTextField(ButtonType.SUBMIT_ADD_FRIEND);
        label.setLabelFor(friendField);
        inputs.add(friendField);

        //Lay out the panel.
        SpringUtils.makeCompactGrid(inputs,
                1, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad

        //Add buttons
        submitBtn = UIFactory.createButton(ButtonType.SUBMIT_ADD_FRIEND);
        cancelBtn = UIFactory.createButton(ButtonType.CANCEL);
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(submitBtn);
        buttons.add(cancelBtn);

        //Put everything together
        content.add(inputs);
        content.add(buttons);

        //finalize layout
        this.setContentPane(content);
        this.pack();

        this.reset();
        this.setTitle(WindowType.ADD_FRIEND.getName());
        this.setMinimumSize(new Dimension(X_SIZE, Y_SIZE));
        this.setResizable(false);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Reset text fields
     */
    @Override
    public final void reset() {
        friendField.setText("");

    }

    /**
     * Add submit listener
     * @param sul submit action listener
     */
    public void addSubmitListener(ActionListener sul) {
        friendField.addActionListener(sul);
        submitBtn.addActionListener(sul);
        cancelBtn.addActionListener(sul);
    }

    /**
     * Set focus on friend window
     */
    @Override
    public void setFocus() {
        reset();
        friendField.requestFocus();
        setVisible(true);
    }

    /**
     * Loose focus
     */
    @Override
    public void looseFocus() {
        setVisible(false);
        reset();
    }
}