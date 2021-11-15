import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class EdgeDetection {
    public static void sobel(String imagePath, Mat source) {
        try {
            // Creating an empty matrix for the destination image
            Mat destination = new Mat();

            // Writing the image
            // Applying sobel derivative with values x:1 y:0
            Imgproc.Sobel(source, destination, -1, 1, 0);
            Imgcodecs.imwrite("img\\edge-detection\\sobel\\sobel-x\\sobel-x_" + IOImage.getImageName(imagePath), destination);
            System.out.println("Writing Image Complete (sobel-x_" + IOImage.getImageName(imagePath) + ")");

            // Applying sobel derivative with values x:0 y:1
            Imgproc.Sobel(source, destination, -1, 0, 1);
            Imgcodecs.imwrite("img\\edge-detection\\sobel\\sobel-y\\sobel-y_" + IOImage.getImageName(imagePath), destination);
            System.out.println("Writing Image Complete (sobel-y_" + IOImage.getImageName(imagePath) + ")");

            // Applying sobel derivative with values x:1 y:1
            Imgproc.Sobel(source, destination, -1, 1, 1);
            Imgcodecs.imwrite("img\\edge-detection\\sobel\\sobel-xy\\sobel-xy_" + IOImage.getImageName(imagePath), destination);
            System.out.println("Writing Image Complete (sobel-xy_" + IOImage.getImageName(imagePath) + ")");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void canny(String imagePath, Mat source) {
        try {
            // Creating an empty matrices to store edges, source, destination
            Mat gray = new Mat(source.rows(), source.cols(), source.type());
            Mat edges = new Mat(source.rows(), source.cols(), source.type());
            Mat destination = new Mat(source.rows(), source.cols(), source.type(), new Scalar(0));

            // Converting the image to Gray
            Imgproc.cvtColor(source, gray, Imgproc.COLOR_RGB2GRAY);

            // Blurring the image
            Imgproc.blur(gray, edges, new Size(3, 3));

            // Detecting the edges
            Imgproc.Canny(edges, edges, 100, 100 * 3);

            // Copying the detected edges to the destination matrix
            source.copyTo(destination, edges);

            // Writing the image
            Imgcodecs.imwrite("img\\edge-detection\\canny\\canny_" + IOImage.getImageName(imagePath), destination);
            System.out.println("Writing Image Complete (canny_" + IOImage.getImageName(imagePath) + ")");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void gaussian(String imagePath, Mat source) {
        try {
            // Creating destination matrix
            Mat destination = new Mat(source.rows(), source.cols(), source.type());

            // Applying GaussianBlur on the Image
            Imgproc.GaussianBlur(source, destination, new Size(15, 15), 0);

            Imgcodecs.imwrite("img\\edge-detection\\gaussian\\gaussian_" + IOImage.getImageName(imagePath), destination);
            System.out.println("Writing Image Complete (gaussian_" + IOImage.getImageName(imagePath) + ")");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
