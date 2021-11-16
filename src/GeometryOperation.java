import java.awt.Color;
import java.awt.image.BufferedImage;

public class GeometryOperation {
    public static void translateImage(BufferedImage image, BufferedImage temp, int width, int height, int x, int y) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((i + x) < width && (i + x) >= 0) {
                    if ((j + y) < height && (j + y) >= 0) {
                        temp.setRGB((i + x), (j + y), image.getRGB(i, j));
                    }
                }
            }
        }
    }

    public static void rotateImage(BufferedImage image, BufferedImage temp, int width, int height, double deg) {
        double radians = Math.toRadians(deg);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(image.getRGB(i, j));
                int centerX = width / 2;
                int centerY = height / 2;
                int m = i - centerX;
                int n = j - centerY;
                int newX = (int) (m * cos + n * sin + centerX);
                int newY = (int) (n * cos - m * sin + centerY);
                if (newX < width && newX >= 0 && newY < height && newY >= 0) {
                    temp.setRGB(newX, newY, c.getRGB());
                }
            }
        }
    }

    public static void mirrorImage(BufferedImage image, BufferedImage temp, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(image.getRGB(i, j));
                int newX = (-1 * i) + (width - 1);
                temp.setRGB(newX, j, c.getRGB());
            }
        }
    }

    public static void flipImage(BufferedImage image, BufferedImage temp, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(image.getRGB(i, j));
                int newY = (-1 * j) + (height - 1);
                temp.setRGB(i, newY, c.getRGB());
            }
        }
    }

    public static void zoomImage(BufferedImage image, BufferedImage temp, int width, int height, int scaleFactor) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(image.getRGB(i, j));
                int newX = scaleFactor * i;
                int newY = scaleFactor * j;
                if (newX < temp.getWidth() && newX >= 0 && newY < temp.getHeight() && newY >= 0) {
                    for (int k = newX; k < (newX + scaleFactor); k++) {
                        for (int l = newY; l < (newY + scaleFactor); l++) {
                            if (k < temp.getWidth() && k >= 0 && l < temp.getHeight() && l >= 0) {
                                temp.setRGB(k, l, c.getRGB());
                            }
                        }
                    }
                }
            }
        }
    }
}
