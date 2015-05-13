package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JToggleButton;

/**
 *
 * @author Balint
 */
public class GameButton extends JToggleButton {

    private int x;
    private int y;
    private BufferedImage image;
    private BufferedImage mineImage;

    public GameButton(int x, int y) {
        //super();
        this.x = x;
        this.y = y;
        image = null;

        try {
            mineImage = ImageIO.read(getClass().getResourceAsStream("/img/mine.png"));
        } catch (IOException ex) {
            System.out.println("File not found!");
        }
    }

    public int getXB() {
        return x;
    }

    public int getYB() {
        return y;
    }

    public void setMineImage() {
        image = mineImage;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, (this.getWidth() - 38)/2, (this.getHeight() - 38)/2, null);
        }
    }

}
