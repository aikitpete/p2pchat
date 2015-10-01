/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Class with various image utils
 *
 * @author USER
 */
public class ImageUtils {

    /**
     * Get scaled version of the original image
     *
     * @param image original image
     * @param width target width
     * @param height target height
     * @return scaled version of the image
     * @throws IOException thrown when io error occurs
     */
    public static BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        double scaleX = (double) width / imageWidth;
        double scaleY = (double) height / imageHeight;
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

        return bilinearScaleOp.filter(
                image,
                new BufferedImage(width, height, image.getType()));
    }

    /**
     * Load scaled version of an image
     *
     * @param path image path
     * @param dim image dimensions
     * @return scaled version of the image
     */
    public static BufferedImage loadImage(String path, Dimension dim) throws IOException {

        BufferedImage ret = null;


        ret = ImageIO.read(new File(path));
        ret = ImageUtils.getScaledImage(ret, dim.height, dim.width);

        //Image thumbnail = ret.getScaledInstance(dim.width, dim.height, Image.SCALE_SMOOTH);
        //ret = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null), BufferedImage.TYPE_INT_RGB);




        return ret;

    }
}
