package org.rbrtwlz.edgedetection.filters;

import org.rbrtwlz.edgedetection.ImageArray;

public class SobelFilter extends SlidingWindowFilter{

  private int[][] kx = {{1,0,-1}, {2,0,-2}, {1,0,-1}};
  private int[][] ky = {{1,2,1}, {0,0,0}, {-1,-2,-1}};
  private Kernel kernelX = new Kernel(kx);
  private Kernel kernelY = new Kernel(ky);

  @Override
  public ImageArray applyFilter(ImageArray imageArray) {

    int[][][] gx = super.applyKernel(imageArray.getPixelArray(), this.kernelX);
    int[][][] gy = super.applyKernel(imageArray.getPixelArray(), this.kernelY);

    int w_res = gx[0][0].length;
    int h_res = gx[0].length;
    int c_res = gx.length;

    int[][][] res = new int[c_res][h_res][w_res];

    for(int h=0; h<h_res; h++){
      for(int w=0; w<w_res; w++){
        int gray_x = 0;
        int gray_y = 0;
        for(int c=0; c<c_res; c++){
          gray_x += gx[c][h][w];
          gray_y += gy[c][h][w];
        }
        int gray = (int) Math.sqrt((gray_x*gray_x) / 9 + (gray_y*gray_y) / 9);
        for(int c=0; c<c_res; c++){
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
