/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.*;
import p2pchat.model.StatusType;
import p2pchat.view.ButtonType;
import p2pchat.view.P2PChatView;
import p2pchat.view.UIFactory;
import p2pchat.view.WindowType;
import p2pchat.view.custom.*;

/**
 * Class representing the main window
 *
 * @author USER
 */
public class MainView extends JFrame implements IView {

    //Components
    private JLabel caption = new JLabel();
    private JTextField input;
    private JPanel main = new JPanel();
    private JPanel inputPanel = new JPanel();
    private JMenu menu;
    private JUpdatableImageButton profile;
    private JButton sendBtn;
    private JButton emoticonBtn;
    private JComboBox status;
    //Message area
    JMessageArea messages;
    //Panel with extra features
    private JFeaturesPanel features = new JFeaturesPanel();
    //Menu items
    private JMenuItem friendItem;
    private JMenuItem registerItem;
    private JMenuItem removeItem;
    private JMenuItem settingsItem;
    private JMenuItem quitItem;

    /**
     * Constructor
     */
    public MainView() {

        //Initialize basic parameters
        this.setMinimumSize(new Dimension(P2PChatView.MIN_WIDTH, P2PChatView.MIN_HEIGHT));
        this.setPreferredSize(new Dimension(P2PChatView.PREF_WIDTH, P2PChatView.PREF_HEIGHT));

        //Create menu components
        menu = new JMenu(ButtonType.FILE.getCaption());
        JMenuItem menuItem;
        JMenuBar menuBar = new JMenuBar();

        //Add menu
        menu.getAccessibleContext().setAccessibleDescription("");

        registerItem = UIFactory.createMenuItem(ButtonType.REGISTER);
        registerItem.setEnabled(true);

        friendItem = UIFactory.createMenuItem(ButtonType.ADD_FRIEND);
        friendItem.setEnabled(false);

        removeItem = UIFactory.createMenuItem(ButtonType.REMOVE_FRIEND);
        removeItem.setEnabled(false);

        settingsItem = UIFactory.createMenuItem(ButtonType.SETTINGS);
        settingsItem.setEnabled(true);

        quitItem = UIFactory.createMenuItem(ButtonType.QUIT);

        //Put menu together
        menuBar.add(menu);
        menu.add(registerItem);
        menu.add(friendItem);
        menu.add(removeItem);
        menu.addSeparator();
        menu.add(settingsItem);
        menu.addSeparator();
        menu.add(quitItem);

        //Layout the components.      
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        //Create main panel
        status = UIFactory.createComboBox(ButtonType.STATUS);
        status.addItem(StatusType.OFFLINE);
        status.addItem(StatusType.ONLINE);
        main.setLayout(new BorderLayout());
        features.init(new Dimension(P2PChatView.SIDE_WIDTH, P2PChatView.ICON_DIM));

        //Create messages panel
        messages = new JMessageArea();
        messages.setLineWrap(true);
        JScrollPane messagesp = new JScrollPane(messages);

        //Create panel for user input
        input = UIFactory.createTextField(ButtonType.INPUT_BOX);
        sendBtn = UIFactory.createButton(ButtonType.SUBMIT_MESSAGE);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.add(input);
        inputPanel.add(sendBtn);

        //Create side panel      
        JPanel side = UIFactory.createSidePanel(new Dimension(P2PChatView.SIDE_WIDTH, P2PChatView.MIN_HEIGHT));;
        profile = UIFactory.createImageButton(ButtonType.PROFILE, new Dimension(P2PChatView.SIDE_WIDTH, P2PChatView.SIDE_WIDTH), true);


        //Put main together
        this.setJMenuBar(menuBar);
        main.add(status, BorderLayout.NORTH);
        main.add(messagesp, BorderLayout.CENTER);

        //Put side together
        side.add(features);
        side.add(profile);

        //Fix alignment
        features.setAlignmentX(Component.CENTER_ALIGNMENT);
        profile.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Put everything together
        content.add(caption, BorderLayout.NORTH);
        content.add(main, BorderLayout.CENTER);
        content.add(side, BorderLayout.EAST);
        content.add(inputPanel, BorderLayout.SOUTH);

        //finalize layout
        this.setContentPane(content);

        this.pack();
        this.setTitle(WindowType.MAIN.getName());
    }

    /**
     * Append message to the message area
     *
     * @param newText text to be added
     */
    public void addMessage(String newText) {
        //Add message
        messages.append(newText + P2PChatView.NEW_LINE);
        
        //Set possition to last message
        messages.selectAll();
        int x = messages.getSelectionEnd();
        messages.select(x, x);
    }

    /**
     * Get user input
     *
     * @return user input
     */
    public String getUserInput() {
        return input.getText();
    }

    /**
     * Set user input
     *
     * @param text text to be set as input
     */
    public void setUserInput(String text) {
        input.setText(text);
    }

    /**
     * Set status
     *
     * @param status new status
     */
    public void setStatus(StatusType status) {
        //Update UI content
        this.features.setStatus(status);
        this.profile.setStatus(status);
        this.status.setSelectedItem(status);
        if (status == StatusType.ONLINE) {
            friendItem.setEnabled(true);
            removeItem.setEnabled(true);
        } else if (status == StatusType.OFFLINE) {
            friendItem.setEnabled(false);
            removeItem.setEnabled(false);
        }
    }

    /**
     * Set status caption
     *
     * @param text new status caption
     */
    public void setStatusCaption(String text) {
        caption.setText(text);
    }

    /**
     * Set user image
     *
     * @param path image path
     */
    public void setUserImage(String path) {
        profile.setImage(path);
    }

    /**
     * Add default action listener
     *
     * @param listener action listener
     */
    public void addDefaultListener(ActionListener listener) {
        input.addActionListener(listener);
        sendBtn.addActionListener(listener);
        status.addActionListener(listener);
    }

    /**
     * Add listener for special feature buttons
     *
     * @param listener mouse listener
     */
    public void addFeaturesListener(MouseListener listener) {
        //Add menu listeners
        friendItem.addMouseListener(listener);
        registerItem.addMouseListener(listener);
        removeItem.addMouseListener(listener);
        settingsItem.addMouseListener(listener);
        quitItem.addMouseListener(listener);

        //Add features bar listeners
        features.addMouseListener(listener);

        //Add profile listener
        profile.addMouseListener(listener);
    }

    /**
     * Reset window content
     */
    @Override
    public void reset() {
    }

    /**
     * Set focus on main window
     */
    @Override
    public void setFocus() {
        input.requestFocus();
        setVisible(true);
    }

    /**
     * Loose focus
     */
    @Override
    public void looseFocus() {
    }
}
