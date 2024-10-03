package utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;

public class ImageUtils {
	
	// Receives an InputStream (Normally obtained from a SQL query result set) and returns a BufferedImage
	 public static BufferedImage fromBinaryToBufferedImage(byte[] imageBytes) {
       BufferedImage image = null;
       try {
           ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
           
           image = ImageIO.read(bais);
           
           if (image == null) {
               System.out.println("Error: Image couldn't be read");
           }
       } catch (IOException e) {
           e.printStackTrace();
       }

       return image;
   }
	
	// Receives a File (Normally selected from a JFileChooser) and returns a byte array that can be saved into the database as BLOB
	public static byte[] toBinary(File imageFile) {
		byte[] imageBytes = new byte[0];
		try (FileInputStream fis = new FileInputStream(imageFile)) {
         imageBytes = new byte[(int) imageFile.length()];
         fis.read(imageBytes);
         
     } catch (IOException e) {
         e.printStackTrace();
     }
		
		return imageBytes;
	}

	/* Receives a BufferedImage and the format to convert that image to, and returns a byte[] array that can be saved into the database as BLOB
	 * @param image -> The image thats going to be converted
	 * @param format -> Can be "PNG" or "JPEG"
	 * @return
	 */
	public static byte[] bufferedImageToByteArray(BufferedImage image, String format) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try {
          ImageIO.write(image, format, baos);
          baos.flush();
          
          return baos.toByteArray();
      } catch (IOException e) {
          e.printStackTrace();
      } finally {
          try {
              baos.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      return null;
	}
	
	// Receives an Icon and returns a BufferedImage
	public static BufferedImage iconToBufferedImage(Icon icon) {
      BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
      
      Graphics2D g2d = bufferedImage.createGraphics();
      icon.paintIcon(null, g2d, 0, 0);
      g2d.dispose(); //
      
      return bufferedImage;
	}
	
}
