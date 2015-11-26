package webCamC;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class takeImage {
	public void takeImageNow() throws IOException{
	Webcam webcam = Webcam.getDefault();
	webcam.open();
	ImageIO.write(webcam.getImage(), "PNG", new File("hello-world.png"));

	}
	
	public static void main(String[] args) {
		takeImage ts = new takeImage();
		try {
			ts.takeImageNow();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
