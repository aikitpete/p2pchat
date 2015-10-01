/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.view.custom;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ImageUtils;

/**
 * Class representing button responding to status change, displaying custom resizable image
 * @author USER
 */
public class JUpdatableImageButton extends JUpdatableButton {

    public static final String DEFAULT_IMAGE = "profile/default.jpg";
    protected BufferedImage currentImage;

    /**
     * Constructor
     * @param name button name
     * @param hide flag used to specify responsiveness to status change
     * @param dim  dimensions
     */
    public JUpdatableImageButton(String name, boolean hide, Dimension dim) {
        super(name, hide, dim);
        try {
            currentImage = ImageUtils.loadImage(DEFAULT_IMAGE, super.dim);
        } catch (IOException ex) {
            System.err.println("Error: JUpdatableImageButton - Image can't be loaded: ");
        }
    }
    
    /**
     * Set image displayed in the button
     * @param path image path
     */
    public void setImage(String path) {
        try {
            currentImage = ImageUtils.loadImage(path, super.dim);
        } catch (IOException ex) {
            setImage(DEFAULT_IMAGE);
        }
    }

    /**
     * Paint the image
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(currentImage, 0, 0, null);
    }
}
