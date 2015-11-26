package mergeImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.CvType;
import org.opencv.core.Scalar;

import org.opencv.imgcodecs.Imgcodecs;
import javax.imageio.ImageIO;

public class MergeImages {
	
	void combineImage(String picName) throws IOException{
		//File input = new File("digital_image_processing.jpg");
		
		//BufferedImage image = ImageIO.read(input);
		//convert Buffered Image to Mat.
		

	      try{
	          System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	          
	          Mat source = Imgcodecs.imread(picName,  Imgcodecs.CV_LOAD_IMAGE_COLOR);
	          Mat waterMark = Imgcodecs.imread("watermark.png",  Imgcodecs.CV_LOAD_IMAGE_COLOR);
	          Rect ROI = new Rect(waterMark.rows() * 4,waterMark.cols(),  waterMark.cols(),waterMark.rows());
	          
	          Core.addWeighted(source.submat(ROI), 0.8, waterMark, 0.2, 1,  source.submat(ROI));
	          Imgcodecs.imwrite("watermarkedImage.jpg", source);
	          
	       } catch (Exception e) {
	          System.out.println("Error: " + e.getMessage());
	       }

		
	}
	

	

	  static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

	  public static void main(String[] args) {
	    System.out.println("Welcome to OpenCV " + Core.VERSION);
	    Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
	    System.out.println("OpenCV Mat: " + m);
	    Mat mr1 = m.row(1);
	    mr1.setTo(new Scalar(1));
	    Mat mc5 = m.col(5);
	    mc5.setTo(new Scalar(5));
	    System.out.println("OpenCV Mat data:\n" + m.dump());
	  }

	

}
