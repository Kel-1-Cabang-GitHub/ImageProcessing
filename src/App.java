import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.util.InputMismatchException;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        BufferedImage image = null;
        BufferedImage image2 = null;
        String imagePath, imagePath2;
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
                System.out.println("7. Edge Detection");
                System.out.println("8. Exit");
                System.out.print("Choose from 1 to 8: ");

                int option = input.nextInt();

                switch (option) {
                case 1:
                    imagePath = IOImage.findImage(input);
                    image = IOImage.readImage(image, imagePath);
                    try {
                        BufferedImage imageGrayscale = ImageProcessing.grayscale(image);
                        IOImage.writeImage(imageGrayscale,
                                "img\\grayscale\\grayscale_" + IOImage.getImageName(imagePath));
                    } catch (NullPointerException e) {
                        System.err.println("Please input image!");
                    }
                    break;
                case 2:
                    imagePath = IOImage.findImage(input);
                    image = IOImage.readImage(image, imagePath);
                    try {
                        BufferedImage imageThreshold = ImageProcessing.thresholding(image, 128);
                        IOImage.writeImage(imageThreshold,
                                "img\\threshold\\threshold_" + IOImage.getImageName(imagePath));
                    } catch (NullPointerException e) {
                        System.err.println("Please input image!");
                    }
                    break;
                case 3:
                    imagePath = IOImage.findImage(input);
                    image = IOImage.readImage(image, imagePath);
                    try {
                        BufferedImage imageBrightness = ImageProcessing.brightening(image, 100);
                        IOImage.writeImage(imageBrightness,
                                "img\\brightness\\brightness_" + IOImage.getImageName(imagePath));
                    } catch (NullPointerException e) {
                        System.err.println("Please input image!");
                    }
                    break;
                case 4:
                    System.out.println("Arithmetic Operation: ");
                    System.out.println("1. Add");
                    System.out.println("2. Subtract");
                    System.out.println("3. Multiply");
                    System.out.println("4. Divide");
                    loop = true;
                    while (loop) {
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

                        System.out.println("You need 2 images for this operation!");
                        System.out.println("Input Image 1");
                        imagePath = IOImage.findImage(input);
                        image = IOImage.readImage(image, imagePath);
                        System.out.println("Input Image 2");
                        imagePath2 = IOImage.findImage(input);
                        image2 = IOImage.readImage(image2, imagePath2);

                        try {
                            BufferedImage arithmeticImage = ImageProcessing.arithmeticOperation(image, image2, arithmeticOperation);
                            IOImage.writeImage(arithmeticImage, "img\\arithmetic\\" + arithmeticOperation
                                    + "\\arithmetic_" + arithmeticOperation + "_" + IOImage.getImageName(imagePath));
                        } catch (NullPointerException e) {
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
                    while (loop) {
                        System.out.print("Choose from 1 to 4: ");
                        int booleanOption = input.nextInt();
                        String booleanOperation = "";

                        switch (booleanOption) {
                        case 1:
                            booleanOperation = "not";
                            break;
                        case 2:
                            booleanOperation = "and";
                            break;
                        case 3:
                            booleanOperation = "or";
                            break;
                        case 4:
                            booleanOperation = "xor";
                            break;
                        default:
                            System.out.println("Please input number between 1-4");
                            continue;
                        }
                        loop = false;

                        if (!booleanOperation.equals("not")) {
                            System.out.println("You need 2 images for this operation!");
                            System.out.println("Input Image 1");
                            imagePath = IOImage.findImage(input);
                            image = IOImage.readImage(image, imagePath);
                            System.out.println("Input Image 2");
                            imagePath2 = IOImage.findImage(input);
                            image2 = IOImage.readImage(image2, imagePath2);
                        } else {
                            imagePath = IOImage.findImage(input);
                            image = IOImage.readImage(image, imagePath);
                        }

                        try {
                            BufferedImage booleanImage = ImageProcessing.booleanOperation(image, image2, booleanOperation);
                            IOImage.writeImage(booleanImage, "img\\boolean\\" + booleanOperation + "\\boolean_"
                                    + booleanOperation + "_" + IOImage.getImageName(imagePath));
                        } catch (NullPointerException e) {
                            System.err.println("Please input image!");
                        }

                    }
                    break;
                case 6:
                    System.out.println("Geometry Operation: ");
                    System.out.println("1. Translate");
                    System.out.println("2. Rotate");
                    System.out.println("3. Mirror");
                    System.out.println("4. Flip");
                    System.out.println("5. Zoom");
                    loop = true;
                    while (loop) {
                        System.out.print("Choose from 1 to 5: ");
                        int geometryOption = input.nextInt();
                        String geometryOperation = "";

                        switch (geometryOption) {
                        case 1:
                            geometryOperation = "translate";
                            break;
                        case 2:
                            geometryOperation = "rotate";
                            break;
                        case 3:
                            geometryOperation = "mirror";
                            break;
                        case 4:
                            geometryOperation = "flip";
                            break;
                        case 5:
                            geometryOperation = "zoom";
                            break;
                        default:
                            System.out.println("Please input number between 1-5");
                            continue;
                        }
                        loop = false;

                        imagePath = IOImage.findImage(input);
                        image = IOImage.readImage(image, imagePath);
                        BufferedImage temp = IOImage.makeBlack(IOImage.readImage(image, imagePath));

                        try {
                            BufferedImage geometryImage = ImageProcessing.geometryOperation(image, temp, geometryOperation);
                            IOImage.writeImage(geometryImage, "img\\geometry\\" + geometryOperation + "\\geometry_"
                                    + geometryOperation + "_" + IOImage.getImageName(imagePath));
                        } catch (NullPointerException e) {
                            System.err.println("Please input image!");
                        }
                    }
                    break;
                case 7:
                    System.out.println("Edge Detection");
                    System.out.println("1. Sobel");
                    System.out.println("2. Canny");
                    System.out.println("3. Gaussian");

                    loop = true;
                    while (loop) {
                        System.out.print("Choose from 1 to 3: ");
                        int edgeDetectionOption = input.nextInt();
                        String edgeDetectionOperation = "";
                        switch (edgeDetectionOption) {
                        case 1:
                            edgeDetectionOperation = "sobel";
                            break;
                        case 2:
                            edgeDetectionOperation = "canny";
                            break;
                        case 3:
                            edgeDetectionOperation = "gaussian";
                            break;
                        default:
                            System.out.println("Please input number between 1-3");
                            continue;
                        }
                        loop = false;

                        imagePath = IOImage.findImage(input);

                        try {
                            ImageProcessing.edgeDetectionOperation(imagePath, edgeDetectionOperation);
                        } catch (NullPointerException e) {
                            System.err.println("Please input image!");
                        }
                    }
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input number between 1-8");
                    continue;
                }
                // If input from user is not integer
            } catch (InputMismatchException e) {
                System.err.println("Please input number between 1-8");
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
}