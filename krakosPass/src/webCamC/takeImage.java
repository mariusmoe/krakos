package webCamC;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.github.sarxos.webcam.WebcamImageTransformer;
import com.github.sarxos.webcam.util.jh.JHGrayFilter;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;

public class takeImage implements WebcamImageTransformer {
	private static final JHGrayFilter GRAY = new JHGrayFilter();

	
	public void takeImageNow() throws IOException, WebcamException, TimeoutException{
	
		//list all accessible webcams
	List<Webcam> wwo = Webcam.getWebcams();
	System.out.println(wwo);
	
		//get different wbcam. Use 1 for external webcam
//	Webcam webcamqq = Webcam.getDefault(0);	
		
	Webcam webcam = Webcam.getDefault();
	webcam.setViewSize(new Dimension(640,480));		//set size(640,480) of image
	webcam.setImageTransformer((WebcamImageTransformer) this);	//grayscale magic
	webcam.open();
	
	String name = String.format("test-%d.png", System.currentTimeMillis());
//	ImageIO.write(webcam.getImage(), "PNG", new File(name));
	
	System.out.format("File %s has been saved\n", name);
	webcam.close();

	}
	
	@Override
	public BufferedImage transform(BufferedImage image) {
		return GRAY.filter(image, null);
	}
	
	public static void main(String[] args) {
		takeImage ts = new takeImage();
		try {
			ts.takeImageNow();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebcamException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
	
}
