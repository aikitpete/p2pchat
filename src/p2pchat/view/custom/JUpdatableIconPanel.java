package p2pchat.view.custom;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import p2pchat.model.StatusType;
import p2pchat.view.ButtonType;

public class JUpdatableIconPanel extends JUpdatablePanel implements IUpdatable {
    
    private BufferedImage image;
    private BufferedImage imageActive;
    
    public JUpdatableIconPanel(String path, String pathActive, Dimension dim, boolean hide) {
        super(path, dim, hide);
        
        this.setMinimumSize(dim);
        this.setMaximumSize(dim);
        
        super.hide = hide;
        setStatus(StatusType.OFFLINE);
        
        
        image = super.currentImage;
        imageActive = super.loadImage(pathActive, dim);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    @Override
    public void setActive(boolean active) {
        if (active) {
            super.currentImage = imageActive;
        } else {
            super.currentImage = image;
        }
        this.repaint();
    }
    
    @Override
    public void addActionListener(ActionListener listener) {
    }
    
    @Override
    public ButtonType getButtonType() {
        return super.getButtonType();
    }
    
    @Override
    public void setButtonType(ButtonType type) {
        super.setButtonType(type);
    }
    
    @Override
    public StatusType getStatus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
