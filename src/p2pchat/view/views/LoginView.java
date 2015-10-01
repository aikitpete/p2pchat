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
import p2pchat.view.custom.JUpdatableButton;
import utils.SpringUtils;

/**
 * Class representing the login window
 * @author USER
 */
public class LoginView extends JFrame implements IView {

    //Constants
    private static final int X_SIZE = 240;
    private static final int Y_SIZE = 140;
    //Variables
    private JTextField userField;
    private JTextField passField;
    private JButton submitBtn;
    private JButton cancelBtn;

    /**
     * Constructor
     */
    public LoginView() {

        JLabel label;

        //Create content pane
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        //Create and populate the panel.
        JPanel inputs = new JPanel(new SpringLayout());

        label = new JLabel(FieldType.USERNAME.getName(), JLabel.TRAILING);
        inputs.add(label);
        userField = UIFactory.createTextField(ButtonType.SUBMIT_LOGIN);
        label.setLabelFor(userField);
        inputs.add(userField);

        label = new JLabel(FieldType.PASSWORD.getName(), JLabel.TRAILING);
        inputs.add(label);
        passField = UIFactory.createTextField(ButtonType.SUBMIT_LOGIN);
        label.setLabelFor(passField);
        inputs.add(passField);

        //Lay out the panel.
        SpringUtils.makeCompactGrid(inputs,
                2, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad

        //Add buttons
        submitBtn = UIFactory.createButton(ButtonType.SUBMIT_LOGIN);
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
        this.setTitle(WindowType.LOGIN.getName());
        this.setMinimumSize(new Dimension(X_SIZE, Y_SIZE));
        this.setResizable(false);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Reset window content
     */
    @Override
    public final void reset() {
        userField.setText("");
        passField.setText("");

    }

    /**
     * Add submit action listener
     * @param sul submit action listener
     */
    public void addSubmitListener(ActionListener sul) {
        userField.addActionListener(sul);
        passField.addActionListener(sul);
        submitBtn.addActionListener(sul);
        cancelBtn.addActionListener(sul);
    }

    /**
     * Set focus on login window
     */
    @Override
    public void setFocus() {
        userField.requestFocus();
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