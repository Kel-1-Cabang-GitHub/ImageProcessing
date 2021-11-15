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
        String imagePath;
        Boolean loop;


        while (true) {
            try {
                System.out.println("Basic Operation Of Image Processing");
                System.out.println("1. Grayscale");
                System.out.println("2. Threshold");
                System.out.println("3. Brightness");
                System.out.println("4. Arithmetic Operation");
                System.out.println("5. Boolean Operation");
                System.out.println("6. Geometry Operation");
                System.out.println("7. Exit");
                System.out.print("Choose from 1 to 7: ");

                int option = input.nextInt();

                switch (option) {
                case 1:
                    imagePath = findImage(input);
                    image = readImage(image, imagePath);
                    try{
                        BufferedImage imageGrayscale = grayscale(image);
                        writeImage(imageGrayscale, "img\\grayscale\\grayscale_" + getImageName(imagePath));
                    }catch(NullPointerException e){
                        System.err.println("Please input image!");
                    }
                    break;
                case 2:
                    imagePath = findImage(input);
                    image = readImage(image, imagePath);
                    try{
                        BufferedImage imageThreshold = thresholding(image, 128);
                        writeImage(imageThreshold, "img\\threshold\\threshold_" + getImageName(imagePath));
                    }catch(NullPointerException e){
                        System.err.println("Please input image!");
                    }
                    break;
                case 3:
                    imagePath = findImage(input);
                    image = readImage(image, imagePath);
                    try{
                        BufferedImage imageBrightness = brightening(image, 100);
                        writeImage(imageBrightness, "img\\brightness\\brightness_" + getImageName(imagePath));
                    }catch(NullPointerException e){
                        System.err.println("Please input image!");
                    }
                    break;
                case 4:
                    System.out.println("You need 2 images for this operation!");
                    System.out.println("Input Image 1");
                    imagePath = findImage(input);
                    image = readImage(image, imagePath);
                    System.out.println("Input Image 2");
                    imagePath = findImage(input);
                    image2 = readImage(image2, imagePath);
                    System.out.println("Arithmetic Operation: ");
                    System.out.println("1. Add");
                    System.out.println("2. Subtract");
                    System.out.println("3. Multiply");
                    System.out.println("4. Divide");
                    loop = true;
                    while(loop){
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
                        loop = false;
                        try{
                            BufferedImage imageArithmetic = arithmeticOperation(image, image2, arithmeticOperation);
                            writeImage(imageArithmetic, "img\\arithmetic\\" + arithmeticOperation + "\\arithmetic_"
                                    + arithmeticOperation + "_" + getImageName(imagePath));
                        }catch(NullPointerException e){
                            System.err.println("Please input image!");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Boolean Operation: ");
                    System.out.println("1. NOT");
                    System.out.println("2. AND");
                    System.out.println("3. OR");
                    System.out.println("4. XOR");
                    loop = true;
                    while(loop){
                        System.out.print("Choose from 1 to 4: ");
                        int booleanOption = input.nextInt();
                        String booleanOperation = "";
                        switch (booleanOption) {
                            case 1:
                                booleanOperation = "not";
                                imagePath = findImage(input);
                                image = readImage(image, imagePath);
                                try{
                                    BufferedImage imageBoolean = NotOperation(image);
                                    writeImage(imageBoolean, "img\\boolean\\" + booleanOperation + "\\boolean_"
                                    + booleanOperation + "_" + getImageName(imagePath));
                                }catch(NullPointerException e){
                                    System.err.println("Please input image!");
                                }
                                loop = false;
                                break;
                            case 2:
                                booleanOperation = "and";
                                System.out.println("You need 2 images for this operation!");
                                System.out.println("Input Image 1");
                                imagePath = findImage(input);
                                image = readImage(image, imagePath);
                                System.out.println("Input Image 2");
                                imagePath = findImage(input);
                                image2 = readImage(image2, imagePath);
                                try{
                                    BufferedImage imageBoolean = AndOperation(image,image2);
                                    writeImage(imageBoolean, "img\\boolean\\" + booleanOperation + "\\boolean_"
                                    + booleanOperation + "_" + getImageName(imagePath));
                                }catch(NullPointerException e){
                                    System.err.println("Please input image!");
                                }
                                loop = false;
                                break;
                            case 3:
                                booleanOperation = "or";
                                System.out.println("You need 2 images for this operation!");
                                System.out.println("Input Image 1");
                                imagePath = findImage(input);
                                image = readImage(image, imagePath);
                                System.out.println("Input Image 2");
                                imagePath = findImage(input);
                                image2 = readImage(image2, imagePath);
                                try{
                                    BufferedImage imageBoolean = OrOperation(image,image2);
                                    writeImage(imageBoolean, "img\\boolean\\" + booleanOperation + "\\boolean_"
                                    + booleanOperation + "_" + getImageName(imagePath));
                                }catch(NullPointerException e){
                                    System.err.println("Please input image!");
                                }
                                loop = false;
                                break;
                            case 4:
                                booleanOperation = "xor";
                                System.out.println("You need 2 images for this operation!");
                                System.out.println("Input Image 1");
                                imagePath = findImage(input);
                                image = readImage(image, imagePath);
                                System.out.println("Input Image 2");
                                imagePath = findImage(input);
                                image2 = readImage(image2, imagePath);
                                try{
                                    BufferedImage imageBoolean = XorOperation(image,image2);
                                    writeImage(imageBoolean, "img\\boolean\\" + booleanOperation + "\\boolean_"
                                    + booleanOperation + "_" + getImageName(imagePath));
                                }catch(NullPointerException e){
                                    System.err.println("Please input image!");
                                }
                                loop = false;
                                break;
                            default:
                                System.out.println("Please input number between 1-4");
                        }
                    }
                    break;
                case 6:
                    System.out.println("Geometry Operation: ");
                    System.out.println("1. Translation");
                    System.out.println("2. Rotation (broken result)");
                    System.out.println("3. Mirror");
                    System.out.println("4. Flip");
                    System.out.println("5. Zoom");
                    loop = true;
                    while(loop){
                        System.out.print("Choose from 1 to 5: ");
                        int geometryOption = input.nextInt();
                        String geometryOperation = "";
                        switch (geometryOption) {
                            case 1:
                                geometryOperation = "translation";
                                imagePath = findImage(input);
                                image = readImage(image, imagePath);
                                try{
                                    BufferedImage temp = makeBlack(readImage(image, imagePath));
                                    BufferedImage imageTranslation = translation(image,temp,100,100);
                                    writeImage(imageTranslation, "img\\geometry\\" + geometryOperation + "\\geometry_"
                                    + geometryOperation + "_" + getImageName(imagePath));
                                }catch(NullPointerException e){
                                    System.err.println("Please input image!");
                                }
                                loop = false;
                                break;
                            case 2:
                                geometryOperation = "rotation";
                                imagePath = findImage(input);
                                image = readImage(image, imagePath);
                                try{
                                    BufferedImage temp = makeBlack(readImage(image, imagePath));
                                    BufferedImage imageRotation = rotation(image,temp,45);
                                    writeImage(imageRotation, "img\\geometry\\" + geometryOperation + "\\geometry_"
                                    + geometryOperation + "_" + getImageName(imagePath));
                                }catch(NullPointerException e){
                                    System.err.println("Please input image!");
                                }
                                loop = false;
                                break;
                            case 3:
                                geometryOperation = "mirror";
                                imagePath = findImage(input);
                                image = readImage(image, imagePath);
                                try{
                                    BufferedImage temp = makeBlack(readImage(image, imagePath));
                                    BufferedImage imageMirror = mirror(image,temp);
                                    writeImage(imageMirror, "img\\geometry\\" + geometryOperation + "\\geometry_"
                                    + geometryOperation + "_" + getImageName(imagePath));
                                }catch(NullPointerException e){
                                    System.err.println("Please input image!");
                                }
                                loop = false;
                                break;
                            case 4:
                                geometryOperation = "flip";
                                imagePath = findImage(input);
                                image = readImage(image, imagePath);
                                try{
                                    BufferedImage temp = makeBlack(readImage(image, imagePath));
                                    BufferedImage imageMirror = flip(image,temp);
                                    writeImage(imageMirror, "img\\geometry\\" + geometryOperation + "\\geometry_"
                                    + geometryOperation + "_" + getImageName(imagePath));
                                }catch(NullPointerException e){
                                    System.err.println("Please input image!");
                                }
                                loop = false;
                                break;
                            case 5:
                                geometryOperation = "zoom";
                                imagePath = findImage(input);
                                image = readImage(image, imagePath);
                                try{
                                    BufferedImage temp = makeBlack(readImage(image, imagePath));
                                    BufferedImage imageMirror = zoom(image,temp,2);
                                    writeImage(imageMirror, "img\\geometry\\" + geometryOperation + "\\geometry_"
                                    + geometryOperation + "_" + getImageName(imagePath));
                                }catch(NullPointerException e){
                                    System.err.println("Please input image!");
                                }
                                loop = false;
                                break;
                            default:
                                System.out.println("Please input number between 1-5");
                        }
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input number between 1-7");
                    continue;
                }
                // If input from user is not integer
            } catch (InputMismatchException e) {
                System.out.println("Please input number between 1-7");
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

    public static String findImage(Scanner input){
        System.out.print("Enter file name : ");
        String result = input.next();
        return "img\\raw\\"+result;
    }

    public static BufferedImage readImage(BufferedImage image, String imagePath) {
        try {
            File inputFile = new File(imagePath);
            image = ImageIO.read(inputFile);
        } catch (IOException e) {
            System.out.println("Error: Failed reading image");
        }
        if(image != null) System.out.println("Reading Image Complete");
        return image;
    }

    public static String getImageName(String imagePath) {
        String[] imageName = imagePath.split("\\\\");
        return imageName[imageName.length - 1];
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

    public static int truncateValue(int value) {
        value = (value > 255) ? 255 : ((value < 0) ? 0 : value);
        return value;
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
    public static BufferedImage AndOperation(BufferedImage img, BufferedImage img2){
        int width = img.getWidth();
        int height = img.getHeight();
        
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                Color c1 = new Color(img.getRGB(i, j));
                Color c2 = new Color(img2.getRGB(i, j));
                Color newColor = new Color((c1.getRed() & c2.getRed()),(c1.getGreen() & c2.getGreen()),(c1.getBlue() & c2.getBlue()));
                img.setRGB(i, j, newColor.getRGB());
            }
        }
        return img;
    }
    public static BufferedImage OrOperation(BufferedImage img, BufferedImage img2){
        int width = img.getWidth();
        int height = img.getHeight();
        
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                Color c1 = new Color(img.getRGB(i, j));
                Color c2 = new Color(img2.getRGB(i, j));
                Color newColor = new Color((c1.getRed() | c2.getRed()),(c1.getGreen() | c2.getGreen()),(c1.getBlue() | c2.getBlue()));
                img.setRGB(i, j, newColor.getRGB());
            }
        }
        return img;
    }
    public static BufferedImage XorOperation(BufferedImage img, BufferedImage img2){
        int width = img.getWidth();
        int height = img.getHeight();
        
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                Color c1 = new Color(img.getRGB(i, j));
                Color c2 = new Color(img2.getRGB(i, j));
                Color newColor = new Color((c1.getRed() ^ c2.getRed()),(c1.getGreen() ^ c2.getGreen()),(c1.getBlue() ^ c2.getBlue()));
                img.setRGB(i, j, newColor.getRGB());
            }
        }
        return img;
    }
    public static BufferedImage NotOperation(BufferedImage img){
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                Color c = new Color(img.getRGB(i, j));
                int newRed = -1 * (c.getRed() - 255);
                int newGreen = -1 * (c.getGreen() - 255);
                int newBlue = -1 * (c.getBlue() - 255);

                Color newColor = new Color(newRed,newGreen,newBlue);
                img.setRGB(i, j, newColor.getRGB());
            }
        }
        return img;
    }
    public static BufferedImage translation(BufferedImage img,BufferedImage temp, int x, int y){
        int width = img.getWidth();
        int height = img.getHeight();
        
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if((i+x) < width && (i+x) >= 0){
                    if((j+y) < height && (j+y) >= 0){
                        temp.setRGB((i+x), (j+y), img.getRGB(i, j));
                    }
                }
            }
        }
        return temp;
    }
    public static BufferedImage rotation(BufferedImage img,BufferedImage temp, double deg){
        int width = img.getWidth();
        int height = img.getHeight();

        double radians = Math.toRadians(deg);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                Color c = new Color(img.getRGB(i, j));
                int centerX = width/2;
                int centerY = height/2;
                int m = i - centerX;
                int n = j - centerY;
                int newX = (int)(m*cos+n*sin+centerX);
                int newY = (int)(n*cos-m*sin+centerY);
                if(newX < width && newX >= 0 && newY < height && newY >= 0){
                    temp.setRGB(newX, newY, c.getRGB());
                }
            }
        }
        return temp;
    }
    public static BufferedImage mirror(BufferedImage img, BufferedImage temp){
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                Color c = new Color(img.getRGB(i, j));
                int newX = (-1 * i) + (width-1);
                temp.setRGB(newX, j, c.getRGB());            
            }
        }
        return temp;
    }
    public static BufferedImage flip(BufferedImage img, BufferedImage temp){
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                Color c = new Color(img.getRGB(i, j));
                int newY = (-1 * j) + (height-1);
                temp.setRGB(i, newY, c.getRGB());            
            }
        }
        return temp;
    }
    public static BufferedImage zoom(BufferedImage img, BufferedImage temp, int scaleFactor){
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                Color c = new Color(img.getRGB(i, j));
                int newX = scaleFactor*i;
                int newY = scaleFactor*j;
                if(newX < width && newX >= 0 && newY < height && newY >= 0){
                    for(int k = newX; k < (newX+scaleFactor);k++){
                        for(int l = newY; l < (newY+scaleFactor);l++){
                            if(k < width && k >= 0 && l < height && l >= 0){
                                temp.setRGB(k, l, c.getRGB());
                            }
                        }
                    }
                }
            }
        }
        return temp;
    }
    public static BufferedImage makeBlack(BufferedImage img){
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                Color c = new Color(0,0,0);
                img.setRGB(i, j, c.getRGB());
            }
        }
        return img;
    }
}
