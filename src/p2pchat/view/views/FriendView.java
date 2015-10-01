/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.views;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import p2pchat.view.ButtonType;
import p2pchat.view.FieldType;
import p2pchat.view.UIFactory;
import p2pchat.view.WindowType;
import p2pchat.view.custom.JUpdatableButton;
import utils.SpringUtilities;

/**
 *
 * @author USER
 */
public class FriendView extends JFrame implements IView {

    //Constants
    private static final int X_SIZE = 240;
    private static final int Y_SIZE = 90;
    //Variables
    private JTextField friendField;
    private JUpdatableButton submitBtn;
    private JUpdatableButton cancelBtn;

    public FriendView() {

        JLabel label;

        //Create content pane
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        //Create and populate the panel.
        JPanel inputs = new JPanel(new SpringLayout());

        label = new JLabel(FieldType.FRIENDNAME.getName(), JLabel.TRAILING);
        inputs.add(label);
        friendField = new JTextField(10);
        friendField.setActionCommand(ButtonType.SUBMIT_F.getName());
        label.setLabelFor(friendField);
        inputs.add(friendField);

        //Lay out the panel.
        SpringUtilities.makeCompactGrid(inputs,
                1, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad

        //Add buttons
        submitBtn = UIFactory.createButton(ButtonType.SUBMIT_F);
        submitBtn.addField(FieldType.FRIENDNAME.getName(), friendField);
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
        this.setTitle(WindowType.FRIEND.getName());
        this.setMinimumSize(new Dimension(X_SIZE, Y_SIZE));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public final void reset() {
        friendField.setText("");

    }

    public void addSubmitListener(ActionListener sul) {
        submitBtn.addActionListener(sul);
        cancelBtn.addActionListener(sul);
    }

    @Override
    public void setFocus() {
        friendField.requestFocus();
        setVisible(true);
    }

    @Override
    public void looseFocus() {
        setVisible(false);
        reset();
    }
}