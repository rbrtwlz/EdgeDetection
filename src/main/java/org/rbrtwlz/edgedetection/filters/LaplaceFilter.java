package org.rbrtwlz.edgedetection.filters;

import org.rbrtwlz.edgedetection.ImageArray;

public class LaplaceFilter extends SlidingWindowFilter{

  private final int[][] k = {{0,1,0}, {1,-4,1}, {0,1,0}};
  private Kernel kernel = new Kernel(k);

  public ImageArray applyFilter(ImageArray imageArray){
    return new ImageArray(super.applyKernel(imageArray.getPixelArray(), this.kernel));
  }

}
