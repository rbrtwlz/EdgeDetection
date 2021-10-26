package org.rbrtwlz.edgedetection.filters;

import org.rbrtwlz.edgedetection.ImageArray;

public class SobelFilter extends SlidingWindowFilter{

  private final int[][] kx = {{1,0,-1}, {2,0,-2}, {1,0,-1}};
  private final int[][] ky = {{1,2,1}, {0,0,0}, {-1,-2,-1}};
  private Kernel kernelX = new Kernel(kx);
  private Kernel kernelY = new Kernel(ky);

  @Override
  public ImageArray applyFilter(ImageArray imageArray) {

    int[][][] gx = super.applyKernel(imageArray.getPixelArray(), this.kernelX);
    int[][][] gy = super.applyKernel(imageArray.getPixelArray(), this.kernelY);

    int wRes = gx[0][0].length;
    int hRes = gx[0].length;
    int cRes = gx.length;

    int[][][] res = new int[cRes][hRes][wRes];

    for(int h=0; h<hRes; h++){
      for(int w=0; w<wRes; w++){
        int grayX = 0;
        int grayY = 0;
        for(int c=0; c<cRes; c++){
          grayX += gx[c][h][w];
          grayY += gy[c][h][w];
        }
        int gray = (int) Math.sqrt((grayX*grayX) / 9 + (grayY*grayY) / 9);
        for(int c=0; c<cRes; c++){
          res[c][h][w] = gray;
        }
      }
    }
    
    return new ImageArray(res);
  }

  public void setKernels(Kernel kernelX, Kernel kernelY){
    this.kernelX = kernelX;
    this.kernelY = kernelY;
  }
  
}
