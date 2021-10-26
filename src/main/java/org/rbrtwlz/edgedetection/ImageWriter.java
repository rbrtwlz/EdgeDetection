package org.rbrtwlz.edgedetection;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class ImageWriter {

  public static void writeImage(String imagePath, ImageArray imgArray, String formatString){

    BufferedImage bi = new BufferedImage(imgArray.getWidth(), imgArray.getHeight(), BufferedImage.TYPE_INT_RGB);

    int[][][] pa = imgArray.getPixelArray();

    for(int i=0; i<imgArray.getWidth(); i++) {
      for(int j=0; j< imgArray.getHeight(); j++) {
          int rgbPixel = (int)pa[0][j][i]<<16 | (int)pa[1][j][i] << 8 | (int)pa[2][j][i]; // TODO ? 
          bi.setRGB(i, j, rgbPixel);
      }
    }
    try {
      ImageIO.write(bi, formatString, new File(imagePath));
    } catch (Exception e) {
        //e.printStackTrace();
        System.out.println(e);
    }
  }
}
