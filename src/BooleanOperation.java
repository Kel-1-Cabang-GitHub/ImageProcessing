import java.awt.Color;
import java.awt.image.BufferedImage;

public class BooleanOperation {
    public static void andOperation(BufferedImage image, BufferedImage image2, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c1 = new Color(image.getRGB(i, j));
                Color c2 = new Color(image2.getRGB(i, j));
                Color newColor = new Color((c1.getRed() & c2.getRed()), (c1.getGreen() & c2.getGreen()),
                        (c1.getBlue() & c2.getBlue()));
                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public static void orOperation(BufferedImage image, BufferedImage image2, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c1 = new Color(image.getRGB(i, j));
                Color c2 = new Color(image2.getRGB(i, j));
                Color newColor = new Color((c1.getRed() | c2.getRed()), (c1.getGreen() | c2.getGreen()),
                        (c1.getBlue() | c2.getBlue()));
                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public static void xorOperation(BufferedImage image, BufferedImage image2, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c1 = new Color(image.getRGB(i, j));
                Color c2 = new Color(image2.getRGB(i, j));
                Color newColor = new Color((c1.getRed() ^ c2.getRed()), (c1.getGreen() ^ c2.getGreen()),
                        (c1.getBlue() ^ c2.getBlue()));
                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public static void notOperation(BufferedImage image, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(image.getRGB(i, j));
                int newRed = -1 * (c.getRed() - 255);
                int newGreen = -1 * (c.getGreen() - 255);
                int newBlue = -1 * (c.getBlue() - 255);

                Color newColor = new Color(newRed, newGreen, newBlue);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }
}
