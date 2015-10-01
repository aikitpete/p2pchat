/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.custom;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 *
 * @author USER
 */
public class JUserList extends JScrollPane {

    JList users;
    
    public JUserList() {
        super();
        
        users = new JList();
        this.getViewport().add(users);
    }
    
}