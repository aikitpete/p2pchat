/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.persistence.server;

import p2pchat.model.User;

/**
 *
 * @author USER
 */
public interface IPersistenceUser {
    
    public String[] findUserByName(String username);
    
    public boolean addNewUser(String args[]);
    
    public boolean addNewUser(String username, String password, String photoPath);
    
    public boolean updateUser(String args[]);
    
    public boolean deleteUser(String username);
    
}
