import java.awt.Color;
import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ImageProcessing {
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
                int newRed = IOImage.truncateValue(c.getRed() + brightnessValue);
                int newBlue = IOImage.truncateValue(c.getBlue() + brightnessValue);
                int newGreen = IOImage.truncateValue(c.getGreen() + brightnessValue);
                Color newColor = new Color(newRed, newGreen, newBlue);
                image.setRGB(i, j, newColor.getRGB());
            }
        }

        return image;
    }

    public static BufferedImage arithmeticOperation(BufferedImage image, BufferedImage image2, String operation) {
        int width = image.getWidth();
        int height = image.getHeight();

        if (operation.equals("add")) {
            ArithmeticOperation.addImage(image, image2, width, height);
        } else if (operation.equals("subtract")) {
            ArithmeticOperation.subtractImage(image, image2, width, height);
        } else if (operation.equals("multiply")) {
            ArithmeticOperation.multiplyImage(image, image2, width, height);
        } else if (operation.equals("divide")) {
            ArithmeticOperation.divideImage(image, image2, width, height);
        }

        return image;
    }

    public static BufferedImage booleanOperation(BufferedImage image, BufferedImage image2, String operation) {
        int width = image.getWidth();
        int height = image.getHeight();

        if (operation.equals("not")) {
            BooleanOperation.notOperation(image, width, height);
        } else if (operation.equals("and")) {
            BooleanOperation.andOperation(image, image2, width, height);
        } else if (operation.equals("or")) {
            BooleanOperation.orOperation(image, image2, width, height);
        } else if (operation.equals("xor")) {
            BooleanOperation.xorOperation(image, image2, width, height);
        }

        return image;
    }

    public static BufferedImage geometryOperation(BufferedImage image, BufferedImage temp, String operation) {
        int width = image.getWidth();
        int height = image.getHeight();

        if (operation.equals("translate")) {
            GeometryOperation.translateImage(image, temp, width, height, 100, 100);
        } else if (operation.equals("rotate")) {
            GeometryOperation.rotateImage(image, temp, width, height, 45);
        } else if (operation.equals("mirror")) {
            GeometryOperation.mirrorImage(image, temp, width, height);
        } else if (operation.equals("flip")) {
            GeometryOperation.flipImage(image, temp, width, height);
        } else if (operation.equals("zoom")) {
            GeometryOperation.zoomImage(image, temp, width, height, 2);
        }
        return temp;
    }

    public static void edgeDetectionOperation(String imagePath, String operation) {
        // Loading the OpenCV core library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat source = Imgcodecs.imread(imagePath);

        if (operation.equals("sobel")) {
            EdgeDetection.sobel(imagePath, source);
        } else if (operation.equals("canny")) {
            EdgeDetection.canny(imagePath, source);
        } else if (operation.equals("gaussian")) {
            EdgeDetection.gaussian(imagePath, source);
        }
    }
}
