package org.rbrtwlz.edgedetection.filters;

import org.rbrtwlz.edgedetection.ImageArray;

public class ScharrFilter extends SlidingWindowFilter{

  private final int[][] k = {{47,162,47}, {0,0,0}, {-47,-162,-47}};
  private Kernel kernel = new Kernel(k);
  private SobelFilter sobelFilter;

  @Override
  public ImageArray applyFilter(ImageArray imageArray) {
    return new ImageArray(super.applyKernel(imageArray.getPixelArray(), this.kernel));
  }
  
}
