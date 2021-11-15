import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class IOImage {
    public static String getImageName(String imagePath) {
        String[] imageName = imagePath.split("\\\\");
        return imageName[imageName.length - 1];
    }

    public static String findImage(Scanner input) {
        System.out.print("Enter file name : ");
        String result = input.next();
        return "img\\raw\\" + result;
    }

    public static BufferedImage readImage(BufferedImage image, String imagePath) {
        try {
            File inputFile = new File(imagePath);
            image = ImageIO.read(inputFile);
        } catch (IOException e) {
            System.err.println("Error: Failed reading image");
        }

        if (image != null) {
            System.out.println("Reading Image Complete");
        }

        return image;
    }

    public static void writeImage(BufferedImage image, String savedImagePath) {
        try {
            File outputFile = new File(savedImagePath);
            ImageIO.write(image, "jpg", outputFile);
            System.out.println("Writing Image Complete (" + getImageName(savedImagePath) + ")");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static int truncateValue(int value) {
        value = (value > 255) ? 255 : ((value < 0) ? 0 : value);
        return value;
    }

    public static BufferedImage makeBlack(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(0, 0, 0);
                image.setRGB(i, j, c.getRGB());
            }
        }
        
        return image;
    }
}
