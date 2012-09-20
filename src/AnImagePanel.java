import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class AnImagePanel  extends JPanel {
    BufferedImage image;
    Dimension size = new Dimension();
 
    public AnImagePanel() {
        image = loadImage();
        size.setSize(image.getWidth(), image.getHeight());
    }
 
    protected void paintComponent(Graphics g) {
        // Center image in this component.
        int x = (getWidth() - size.width)/2;
        int y = (getHeight() - size.height)/2;
        g.drawImage(image, x, y, this);
    }
 
    /**
     * This method handles the communication of
     * size requirements with the parent JScrollPane.
     */
    public Dimension getPreferredSize() {
        return size;
    }
 
    private BufferedImage loadImage() {
        String path = "test.png";
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch(IOException e) {
            // May as well use what is given...
            System.out.println("read error:" + e.getMessage());
        }
        return image;
    }
    
    public BufferedImage loadImage2() {
    	System.out.println("Trying");
        String path = "test2.jpg";
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
            System.out.println("success");
        } catch(IOException e) {
            // May as well use what is given...
            System.out.println("read error:" + e.getMessage());
        }

        return image;
    }
}