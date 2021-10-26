package org.rbrtwlz.edgedetection.filters;

public abstract class SlidingWindowFilter implements Filter {

  protected int[][][] applyKernel(int[][][] pixelArray, Kernel kernel){

    int[][] k = kernel.getKernelMatrix();

    int kHeight = k.length;
    int kWidth = k[0].length;
    
    int paChannels = pixelArray.length;
    int paHeight = pixelArray[0].length;
    int paWidth = pixelArray[0][0].length;

    int endX = paWidth - kWidth + 1;
    int endY = paHeight - kHeight + 1;

    int[][][] res = new int[paChannels][endY][endX];

    int x_res = 0;
    int y_res = 0;

    for(int c=0; c<paChannels; c++){
      for(int y=0; y<endY; y++){
        for(int x=0; x<endX; x++){
          int[] dim0 = {x, (x+kWidth)};
          int[] dim1 = {y, (y+kHeight)};
          int[][] temp_arr = this.array2dSlice(pixelArray[c], dim0, dim1);
          res[c][y_res][x_res] = this.arrayWeightedSum(k, temp_arr);
          x_res++;
        }
        x_res = 0;
        y_res++;
      }
      y_res = 0;
    }
    return res;
  }

  protected int[][] array2dSlice(int[][] array, int[] dim0, int[] dim1){
    int[][] res = new int[dim1[1]-dim1[0]][dim0[1]-dim0[0]];
    int x = 0;
    int y = 0;
    for(int h=dim1[0]; h<dim1[1]; h++){
      for(int w=dim0[0]; w<dim0[1]; w++){
        res[y][x] = array[h][w];
        x++;
      }
      x=0;
      y++;
    }
    return res;
  }

  protected int arrayWeightedSum(int[][] array1, int[][] array2){
    int sum = 0;
    for(int h=0; h<array1.length; h++){
      for(int w=0; w<array1[0].length; w++){
        sum += array1[h][w] * array2[h][w];
      }
    }
    return sum;
  }
  
}
