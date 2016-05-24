/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.riflemansd.courierdb.gui.test.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author RiflemanSD
 */
public class Images {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("A4.jpg"));
            
            int h = image.getHeight();
            int w = image.getWidth();
            
            BufferedImage i1 = image.getSubimage(0, 0, w-1, (int)(h-1)/3);
            BufferedImage i2 = image.getSubimage(0, (int)(h-1)/3, w-1, (int)(h-1)/3);
            BufferedImage i3 = image.getSubimage(0, (int)2*(h-1)/3, w-1, (int)(h-1)/3);
            
            ImageIO.write(i1, "JPEG", new File("A4_1.jpg"));
            ImageIO.write(i2, "JPEG", new File("A4_2.jpg"));
            ImageIO.write(i3, "JPEG", new File("A4_3.jpg"));
            
            //yourImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        } catch (IOException ex) {
            Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
