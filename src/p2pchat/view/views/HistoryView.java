/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.views;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.*;
import p2pchat.view.ButtonType;
import p2pchat.view.UIFactory;
import p2pchat.view.WindowType;
import p2pchat.view.custom.JUpdatableButton;

/**
 *
 * @author USER
 */
public class HistoryView extends JFrame implements IView {

    //Constants
    private static final int X_SIZE = 240;
    private static final int Y_SIZE = 90;
    //Variables
    private JTextField friendField;
    private JUpdatableButton submitBtn;
    private JUpdatableButton cancelBtn;

    public HistoryView() {

        JLabel label;

        //Create content pane
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));





        //Add buttons
        submitBtn = UIFactory.createButton(ButtonType.SUBMIT_H);
        cancelBtn = UIFactory.createButton(ButtonType.CANCEL);
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(submitBtn);
        buttons.add(cancelBtn);

        //Put everything together
        content.add(buttons);

        //finalize layout
        this.setContentPane(content);
        this.pack();

        this.reset();
        this.setTitle(WindowType.HISTORY.getName());
        this.setMinimumSize(new Dimension(X_SIZE, Y_SIZE));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void reset() {
    }

    public void addSubmitListener(ActionListener sul) {
        submitBtn.addActionListener(sul);
        cancelBtn.addActionListener(sul);
    }

    @Override
    public void setFocus() {
        setVisible(true);
    }

    @Override
    public void looseFocus() {
        setVisible(false);
        reset();
    }
}
