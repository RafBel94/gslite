package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtils {
	
	// Receives an InputStream (Normally obtained from a SQL query result set) and returns a BufferedImage
	public static BufferedImage fromBinary(InputStream inputStream) {
		BufferedImage bufferedImage = null;
		
		try {
			bufferedImage =  ImageIO.read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bufferedImage;
	}
	
	
}
