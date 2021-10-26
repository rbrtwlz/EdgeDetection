package org.rbrtwlz.edgedetection.filters;


import org.rbrtwlz.edgedetection.ImageArray;

public class LaplaceFilter extends SlidingWindowFilter{

  int[][] k = {{0,1,0}, {1,-4,1}, {0,1,0}};
  Kernel kernel = new Kernel(k);

  public ImageArray applyFilter(ImageArray imageArray){
    return new ImageArray(super.applyKernel(imageArray.getPixelArray(), this.kernel));
  }

}
