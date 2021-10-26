package org.rbrtwlz.edgedetection;

public class ImageArray {

  private int channels;
  private int height;
  private int width;
  private int[][][] pixelArray;

  public int getChannels(){ return this.channels; }
  public int getHeight(){ return this.height; }
  public int getWidth(){ return this.width;}
  public int[][][] getPixelArray(){ return this.pixelArray; }

  public ImageArray(int[][][] pixelArray){
    
    this.pixelArray = pixelArray;
    this.channels = pixelArray.length;
    this.height = pixelArray[0].length;
    this.width = pixelArray[0][0].length;

  }
  

  
}
