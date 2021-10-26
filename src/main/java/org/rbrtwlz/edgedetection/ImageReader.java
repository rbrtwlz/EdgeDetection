package org.rbrtwlz.edgedetection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;


public class ImageReader {

  public static ImageArray readImage(String imagePath){

    try {
      BufferedImage image = ImageIO.read(new File(imagePath));
      int channels = 3;
      int width = image.getWidth();
      int height = image.getHeight();
      int[][][] pixelArray = new int[channels][height][width];
      
      for(int i=0; i<width; i++){
        for(int j=0; j<height; j++){
            int rgb = image.getRGB(i,j);
            pixelArray[0][j][i] = (rgb >> 16) & 0xFF;
            pixelArray[1][j][i] = (rgb >> 8) & 0xFF;
            pixelArray[2][j][i] = rgb & 0xFF;
        }
      }

      return new ImageArray(pixelArray);

    } catch (Exception e) {
      System.out.println(e);
      return null;

    }
  }

}