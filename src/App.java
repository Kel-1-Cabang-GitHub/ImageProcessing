import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;
import javax.imageio.ImageIO;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        BufferedImage image = null;
        BufferedImage image2 = null;
        String imagePath = "img\\view.jpg";
        String imagePath2 = "img\\view2.jpg";

        // Read Image
        image = readImage(image, imagePath);
        image2 = readImage(image2, imagePath2);

        while (true) {
            try {
                System.out.println("Basic Operation Of Image Processing");
                System.out.println("1. Grayscale");
                System.out.println("2. Threshold");
                System.out.println("3. Brightness");
                System.out.println("4. Arithmetic Operation");
                System.out.println("5. Exit");
                System.out.print("Choose from 1 to 4: ");

                int option = input.nextInt();

                switch (option) {
                case 1:
                    BufferedImage imageGrayscale = grayscale(image);
                    writeImage(imageGrayscale, "img\\grayscale\\grayscale_" + getImageName(imagePath));
                    break;
                case 2:
                    BufferedImage imageThreshold = thresholding(image, 128);
                    writeImage(imageThreshold, "img\\threshold\\threshold_" + getImageName(imagePath));
                    break;
                case 3:
                    BufferedImage imageBrightness = brightening(image, 100);
                    writeImage(imageBrightness, "img\\brightness\\brightness_" + getImageName(imagePath));
                    break;
                case 4:
                    System.out.println("Arithmetic Operation: ");
                    System.out.println("1. Add");
                    System.out.println("2. Subtract");
                    System.out.println("3. Multiply");
                    System.out.println("4. Divide");
                    System.out.print("Choose from 1 to 4: ");

                    int arithmeticOption = input.nextInt();
                    String arithmeticOperation = "";

                    switch (arithmeticOption) {
                    case 1:
                        arithmeticOperation = "add";
                        break;
                    case 2:
                        arithmeticOperation = "subtract";
                        break;
                    case 3:
                        arithmeticOperation = "multiply";
                        break;
                    case 4:
                        arithmeticOperation = "divide";
                        break;
                    default:
                        System.out.println("Please input number between 1-4");
                        continue;
                    }

                    BufferedImage imageArithmetic = arithmeticOperation(image, image2, arithmeticOperation);
                    writeImage(imageArithmetic, "img\\arithmetic\\" + arithmeticOperation + "\\arithmetic_"
                            + arithmeticOperation + "_" + getImageName(imagePath));
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input number between 1-4");
                    continue;
                }
                // If input from user is not integer
            } catch (InputMismatchException e) {
                System.out.println("Please input number between 1-4");
                break;
            }

            // Check if user want to do more image processing
            System.out.print("Again? (Y/N) ");
            String again = input.next();

            if (again.equalsIgnoreCase("N")) {
                break;
            }
        }

        // Close input
        input.close();
    }

    public static BufferedImage readImage(BufferedImage image, String imagePath) {
        try {
            File inputFile = new File(imagePath);
            image = ImageIO.read(inputFile);
            System.out.println("Reading Image Complete");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return image;
    }

    public static void writeImage(BufferedImage image, String savedImagePath) {
        try {
            File outputFile = new File(savedImagePath);
            ImageIO.write(image, "jpg", outputFile);
            System.out.println("Writing Image Complete (" + getImageName(savedImagePath) + ")");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static BufferedImage grayscale(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(image.getRGB(i, j));
                int newRGB = (((int) (c.getRed() * 0.299)) + ((int) (c.getGreen() * 0.587))
                        + ((int) (c.getBlue() * 0.114)));
                Color newColor = new Color(newRGB, newRGB, newRGB);
                image.setRGB(i, j, newColor.getRGB());
            }
        }

        return image;
    }

    public static BufferedImage thresholding(BufferedImage image, int thresholdValue) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(image.getRGB(i, j));
                int avg = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                if (avg >= thresholdValue) {
                    Color white = new Color(255, 255, 255);
                    image.setRGB(i, j, white.getRGB());
                } else {
                    Color black = new Color(0, 0, 0);
                    image.setRGB(i, j, black.getRGB());
                }
            }
        }

        return image;
    }

    public static BufferedImage brightening(BufferedImage image, int brightnessValue) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(image.getRGB(i, j));
                int newRed = truncateValue(c.getRed() + brightnessValue);
                int newBlue = truncateValue(c.getBlue() + brightnessValue);
                int newGreen = truncateValue(c.getGreen() + brightnessValue);
                Color newColor = new Color(newRed, newGreen, newBlue);
                image.setRGB(i, j, newColor.getRGB());
            }
        }

        return image;
    }

    public static int truncateValue(int value) {
        value = (value > 255) ? 255 : ((value < 0) ? 0 : value);
        return value;
    }

    public static String getImageName(String imagePath) {
        String[] imageName = imagePath.split("\\\\");
        return imageName[imageName.length - 1];
    }

    public static BufferedImage arithmeticOperation(BufferedImage image, BufferedImage image2, String operation) {
        int width = image.getWidth();
        int height = image.getHeight();

        if (operation.equals("add")) {
            add(image, image2, width, height);
        } else if (operation.equals("subtract")) {
            subtract(image, image2, width, height);
        } else if (operation.equals("multiply")) {
            multiply(image, image2, width, height);
        } else if (operation.equals("divide")) {
            divide(image, image2, width, height);
        }

        return image;
    }

    public static void add(BufferedImage image, BufferedImage image2, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c1 = new Color(image.getRGB(i, j));
                Color c2 = new Color(image2.getRGB(i, j));
                int newRed = truncateValue(c1.getRed() + c2.getRed());
                int newGreen = truncateValue(c1.getGreen() + c2.getGreen());
                int newBlue = truncateValue(c1.getBlue() + c2.getBlue());
                Color newColor = new Color(newRed, newGreen, newBlue);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public static void subtract(BufferedImage image, BufferedImage image2, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c1 = new Color(image.getRGB(i, j));
                Color c2 = new Color(image2.getRGB(i, j));
                int newRed = truncateValue(c1.getRed() - c2.getRed());
                int newGreen = truncateValue(c1.getGreen() - c2.getGreen());
                int newBlue = truncateValue(c1.getBlue() - c2.getBlue());
                Color newColor = new Color(newRed, newGreen, newBlue);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public static void multiply(BufferedImage image, BufferedImage image2, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c1 = new Color(image.getRGB(i, j));
                Color c2 = new Color(image2.getRGB(i, j));
                int newRed = truncateValue(c1.getRed() * c2.getRed());
                int newGreen = truncateValue(c1.getGreen() * c2.getGreen());
                int newBlue = truncateValue(c1.getBlue() * c2.getBlue());
                Color newColor = new Color(newRed, newGreen, newBlue);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public static void divide(BufferedImage image, BufferedImage image2, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c1 = new Color(image.getRGB(i, j));
                Color c2 = new Color(image2.getRGB(i, j));
                int newRed = 0, newGreen = 0, newBlue = 0;

                if (c2.getRed() != 0) newRed = truncateValue(c1.getRed() / c2.getRed());
                if (c2.getGreen() != 0) newGreen = truncateValue(c1.getGreen() / c2.getGreen());
                if (c2.getBlue() != 0) newBlue = truncateValue(c1.getBlue() / c2.getBlue());

                Color newColor = new Color(newRed, newGreen, newBlue);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }
}
