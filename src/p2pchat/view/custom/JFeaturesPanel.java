/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.custom;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.*;
import p2pchat.model.StatusType;
import p2pchat.view.ButtonType;
import p2pchat.view.UIFactory;
import p2pchat.view.commands.AttachActionListener;
import p2pchat.view.commands.AttachMouseListener;
import p2pchat.view.commands.ICallable;
import p2pchat.view.commands.UpdateStatus;

/**
 * Container holding special feature UI buttons
 * @author USER
 */
public class JFeaturesPanel extends JPanel implements IUpdatable {

    //Variables
    private HashMap<ButtonType, JButton> iconMap = new HashMap<>();
    private ButtonType type;

    /**
     * Constructor
     */
    public JFeaturesPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    /**
     * Initialize features panel
     * @param dim panel dimensions
     */
    public void init(Dimension dim) {
        
        JButton tmp;
        
        //Set panel dimensions
        this.setMinimumSize(dim);
        this.setPreferredSize(dim);
        this.setMaximumSize(dim);
        
        //Add icons to main panel
        tmp = UIFactory.createIconButton(ButtonType.REGISTER, new Dimension(dim.height, dim.height), false);
        iconMap.put(ButtonType.REGISTER, tmp);
        this.add(tmp);

        tmp = UIFactory.createIconButton(ButtonType.ADD_FRIEND, new Dimension(dim.height, dim.height), true);
        iconMap.put(ButtonType.ADD_FRIEND, tmp);
        this.add(tmp);

        tmp = UIFactory.createIconButton(ButtonType.REMOVE_FRIEND, new Dimension(dim.height, dim.height), true);
        iconMap.put(ButtonType.REMOVE_FRIEND, tmp);
        this.add(tmp);

    }

    //Iterate map with buttons (useful when many buttons are displayed, map contains ButtonType->Button mapping)
    public void iterateMap(HashMap hMap, ICallable command, Object parameter) {
        
        for (Object o : hMap.values()) {
            command.call(o, parameter);
        }
    }
        
    /**
     * Add mouse listener
     * @param listener mouse listener 
     */
    @Override
    public void addMouseListener(MouseListener listener) {
        iterateMap(iconMap, new AttachMouseListener(), listener);
    }

    /**
     * Set status
     * @param status new status 
     */
    @Override
    public void setStatus(StatusType status) {
        iterateMap(iconMap, new UpdateStatus(), status);
    }
    
}
