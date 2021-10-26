package org.rbrtwlz.edgedetection.filters;

import org.rbrtwlz.edgedetection.ImageArray;

public class GaussianFilter extends SlidingWindowFilter {

  private int[][] k = {{1,2,1}, {2,4,2}, {1,2,1}};
  private Kernel kernel = new Kernel(k);

  public ImageArray applyFilter(ImageArray imageArray){
    
    int[][][] res = super.applyKernel(imageArray.getPixelArray(), this.kernel);

    for(int c=0; c<res.length;c++){
      for(int i=0; i<res[0].length; i++){
        for(int j=0; j<res[0][0].length; j++){
          res[c][i][j] /= 16;
        }
      }
    }
    
    return new ImageArray(res);
  }
}
