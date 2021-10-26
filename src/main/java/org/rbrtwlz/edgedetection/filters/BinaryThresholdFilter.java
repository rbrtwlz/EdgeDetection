package org.rbrtwlz.edgedetection.filters;


import org.rbrtwlz.edgedetection.ImageArray;

public class BinaryThresholdFilter implements Filter{

  private int threshold;

  public BinaryThresholdFilter(int threshold){
    this.threshold = threshold;
  }

  @Override
  public ImageArray applyFilter(ImageArray imageArray) {
    
    int channels = imageArray.getChannels();
    int height = imageArray.getHeight();
    int width = imageArray.getWidth();
    int[][][] pa = imageArray.getPixelArray();

    int[][][] res = new int[channels][height][width];

    for(int i=0; i<height; i++){
      for(int j=0; j<width; j++){
        int gray = (pa[0][i][j] + pa[1][i][j] + pa[2][i][j]) / 3;
        int p = 255;
        if(gray < this.threshold){
          p = 0;
        } 
        for(int c=0; c<channels; c++){
          res[c][i][j] = p;
        }
      }
    }

    return new ImageArray(res);
  }

}
