import java.awt.Color;
import java.awt.image.BufferedImage;

public class ArithmeticOperation {
    public static void addImage(BufferedImage image, BufferedImage image2, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c1 = new Color(image.getRGB(i, j));
                Color c2 = new Color(image2.getRGB(i, j));
                int newRed = IOImage.truncateValue(c1.getRed() + c2.getRed());
                int newGreen = IOImage.truncateValue(c1.getGreen() + c2.getGreen());
                int newBlue = IOImage.truncateValue(c1.getBlue() + c2.getBlue());
                Color newColor = new Color(newRed, newGreen, newBlue);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public static void subtractImage(BufferedImage image, BufferedImage image2, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c1 = new Color(image.getRGB(i, j));
                Color c2 = new Color(image2.getRGB(i, j));
                int newRed = IOImage.truncateValue(c1.getRed() - c2.getRed());
                int newGreen = IOImage.truncateValue(c1.getGreen() - c2.getGreen());
                int newBlue = IOImage.truncateValue(c1.getBlue() - c2.getBlue());
                Color newColor = new Color(newRed, newGreen, newBlue);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public static void multiplyImage(BufferedImage image, BufferedImage image2, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c1 = new Color(image.getRGB(i, j));
                Color c2 = new Color(image2.getRGB(i, j));
                int newRed = IOImage.truncateValue(c1.getRed() * c2.getRed());
                int newGreen = IOImage.truncateValue(c1.getGreen() * c2.getGreen());
                int newBlue = IOImage.truncateValue(c1.getBlue() * c2.getBlue());
                Color newColor = new Color(newRed, newGreen, newBlue);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public static void divideImage(BufferedImage image, BufferedImage image2, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c1 = new Color(image.getRGB(i, j));
                Color c2 = new Color(image2.getRGB(i, j));
                int newRed = 0, newGreen = 0, newBlue = 0;

                if (c2.getRed() != 0)
                    newRed = IOImage.truncateValue(c1.getRed() / c2.getRed());
                if (c2.getGreen() != 0)
                    newGreen = IOImage.truncateValue(c1.getGreen() / c2.getGreen());
                if (c2.getBlue() != 0)
                    newBlue = IOImage.truncateValue(c1.getBlue() / c2.getBlue());

                Color newColor = new Color(newRed, newGreen, newBlue);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }
}
