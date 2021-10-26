package org.rbrtwlz.edgedetection.filters;

import org.rbrtwlz.edgedetection.ImageArray;

public class PrewittFilter extends SlidingWindowFilter{

  private final int[][] kx = {{1,0,-1}, {1,0,-1}, {1,0,-1}};
  private final int[][] ky = {{1,1,1}, {0,0,0}, {-1,-1,-1}};
  private Kernel kernelX = new Kernel(kx);
  private Kernel kernelY = new Kernel(ky);
  private SobelFilter sobelFilter;
    
  @Override
  public ImageArray applyFilter(ImageArray imageArray) {
    this.sobelFilter = new SobelFilter();
    this.sobelFilter.setKernels(this.kernelX, this.kernelY);
    return this.sobelFilter.applyFilter(imageArray);
  }
}
    
