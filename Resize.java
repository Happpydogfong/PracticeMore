import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Resize {

    public static void main(String[] args) {
        File file = new File("horizon.png");
        BufferedImage img = null;

        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        if (img != null) {
            System.out.println("Displaying original image.");
            display(img);

            img = resize(img, 150);

            System.out.println("Displaying resized image.");
            display(img);
        }
    }

    // display image in a JPanel popup
    public static void display(BufferedImage img) {
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        frame.setSize(img.getWidth(), img.getHeight());
        label.setIcon(new ImageIcon(img));
        frame.getContentPane().add(label);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // scale an image while preserving RGB colors
    public static BufferedImage resize(BufferedImage img, int newHeight) {
        System.out.println("Scaling image.");
        double scaleFactor = (double) newHeight / img.getHeight();
        int newWidth = (int) (scaleFactor * img.getWidth());

        BufferedImage scaledImg = new BufferedImage(
                newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = scaledImg.createGraphics();
        g2d.drawImage(img, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        System.out.println("Scaling completed.");
        return scaledImg;
    }
}
