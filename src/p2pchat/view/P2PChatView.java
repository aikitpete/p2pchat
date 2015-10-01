/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import p2pchat.model.ErrorType;
import p2pchat.model.P2PChatModel;
import p2pchat.model.StatusType;
import p2pchat.view.views.*;

/**
 * Class representing view from MVC
 *
 * @author USER
 */
public class P2PChatView {
    //Constants

    public final static String NAME = "P2PChat";
    public final static int MIN_WIDTH = 420;
    public final static int MIN_HEIGHT = 360;
    public final static int PREF_WIDTH = 420;
    public final static int PREF_HEIGHT = 360;
    public final static int ICON_DIM = 25;
    public final static int SIDE_WIDTH = 75;
    public final static String NEW_LINE = "\n";
    public final static String OFFLINE_STATUS_STRING = "You are not connected";
    public final static String ONLINE_STATUS_STRING = " is online";
    public final static String CHAT_STATUS_STRING = " chatting with ";
    //Variables
    private static P2PChatView instance;
    private P2PChatModel model;
    private ViewManager viewManager;

    /**
     * Constructor
     */
    private P2PChatView() {
        //Get model instance
        model = P2PChatModel.getInstance();
        viewManager = new ViewManager();
    }

    /**
     * Get view instance (Singletton pattern)
     *
     * @return view instance
     */
    public static P2PChatView getInstance() {
        if (instance == null) {
            instance = new P2PChatView();
        }
        return instance;
    }

    public void init() {
        viewManager.init();
    }

    public void close() {
        viewManager.close();
    }

    public ViewManager getViewManager() {
        return viewManager;
    }
}
