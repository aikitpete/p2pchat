/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.views;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.*;
import p2pchat.connection.P2PChatConnection;
import p2pchat.view.ButtonType;
import p2pchat.view.FieldType;
import p2pchat.view.UIFactory;
import p2pchat.view.WindowType;
import p2pchat.view.custom.JUpdatableButton;
import utils.SpringUtils;

/**
 * Class representing the settings window
 * @author USER
 */
public class SettingsView extends JFrame implements IView {

    //Constants
    private static final int X_SIZE = 240;
    private static final int Y_SIZE = 140;
    //Variables
    private JTextField serverField;
    private JTextField portField;
    private JButton submitBtn;
    private JButton cancelBtn;

    /**
     * Constructor
     */
    public SettingsView() {

        JLabel label;

        //Create content pane
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        //Create and populate the panel.
        JPanel inputs = new JPanel(new SpringLayout());

        label = new JLabel(FieldType.SERVER.getName(), JLabel.TRAILING);
        inputs.add(label);
        serverField = UIFactory.createTextField(ButtonType.SUBMIT_SETTINGS);
        label.setLabelFor(serverField);
        inputs.add(serverField);

        label = new JLabel(FieldType.PORT.getName(), JLabel.TRAILING);
        inputs.add(label);
        portField = UIFactory.createTextField(ButtonType.SUBMIT_SETTINGS);
        label.setLabelFor(portField);
        inputs.add(portField);

        //Lay out the panel.
        SpringUtils.makeCompactGrid(inputs,
                2, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad

        //Add buttons
        submitBtn = UIFactory.createButton(ButtonType.SUBMIT_SETTINGS);
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
        
        //Set window title
        this.setTitle(WindowType.SETTINGS.getName());
        
        //Set minimum window size
        this.setMinimumSize(new Dimension(X_SIZE, Y_SIZE));
        
        //Set window not resizable
        this.setResizable(false);
        
        //Set default close operation
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Reset window content
     */
    @Override
    public final void reset() {
        serverField.setText(P2PChatConnection.DEFAULT_SERVER);
        portField.setText(String.valueOf(P2PChatConnection.DEFAULT_SERVER_PORT));

    }

    /**
     * Add submit action listener
     * @param sul submit action listener
     */
    public void addSubmitListener(ActionListener sul) {
        serverField.addActionListener(sul);
        portField.addActionListener(sul);
        submitBtn.addActionListener(sul);
        cancelBtn.addActionListener(sul);
    }

    /**
     * Set focus on settrings window
     */
    @Override
    public void setFocus() {
        reset();
        serverField.requestFocus();
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