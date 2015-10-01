/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model;

import java.awt.image.BufferedImage;
import p2pchat.persistence.server.IPersistenceUser;

/**
 * Class representing a user
 *
 * @author USER
 */
public class User {

    private String username;
    private String password;
    private String photoPath;
    private String address;
    private int port;
    private BufferedImage image;
    private int session;

    /**
     * Constructor with userame, password and photo
     *
     * @param username username
     * @param password password
     * @param photoPath photo
     */
    public User(String username, String password, String photoPath) {
        this.username = username;
        this.password = password;
        this.photoPath = photoPath;
    }

    /**
     * Constructor with username and photo
     *
     * @param username username
     * @param photoPath photo
     */
    public User(String username, String photoPath) {
        this.username = username;
        this.photoPath = photoPath;
    }

    /**
     * Get username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get password
     *
     * @return username
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set address
     *
     * @param address new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Set port
     *
     * @param port new port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Get address
     *
     * @return address string
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get port
     *
     * @return port number
     */
    public int getPort() {
        return port;
    }

    /**
     * Get image
     *
     * @return buffered image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Set session
     *
     * @param session session index
     */
    public void setSession(int session) {
        this.session = session;
    }

    /**
     * Get session
     *
     * @return session session index;
     */
    public int getSession() {
        return session;
    }
}
