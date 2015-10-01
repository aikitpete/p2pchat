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
import utils.Image;

public class JUpdatablePanel extends JPanel implements IUpdatable {

    public static final String DEFAULT_IMAGE = "profile/default.jpg";
    
    protected BufferedImage currentImage;
    protected ButtonType type;
    protected boolean hide;

    public JUpdatablePanel(String path, Dimension dim, boolean hide) {
        super();

        this.setMinimumSize(dim);
        this.setPreferredSize(dim);
        this.setMaximumSize(dim);

        this.hide = hide;
        setStatus(StatusType.OFFLINE);

        currentImage = loadImage(path,dim);

    }

    protected final BufferedImage loadImage(String path, Dimension dim) {

        BufferedImage ret = null;
        
        try {
            ret = ImageIO.read(new File(path));
            ret = Image.getScaledImage(ret, dim.height, dim.width);
            
             //Image thumbnail = ret.getScaledInstance(dim.width, dim.height, Image.SCALE_SMOOTH);
             //ret = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null), BufferedImage.TYPE_INT_RGB);
            

        } catch (IOException ex) {
            System.err.println("Error: Image can't be loaded.");
        }
        
        return ret;
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(currentImage, 0, 0, null);
    }

    @Override
    public void setActive(boolean active) {
    }

    @Override
    public final void setStatus(StatusType type) {
        if (hide) {
            switch (type) {
                case ONLINE:
                    this.setVisible(true);
                    break;
                case OFFLINE:
                    this.setVisible(false);
                    break;
            }
        }
    }

    @Override
    public void addActionListener(ActionListener listener) {
    }

    @Override
    public ButtonType getButtonType() {
        return type;
    }

    @Override
    public void setButtonType(ButtonType type) {
        this.type = type;
    }

    @Override
    public StatusType getStatus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}