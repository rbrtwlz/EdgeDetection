package org.rbrtwlz.edgedetection.filters;

public class Kernel {

  private final int[][] kernelMatrix;

  public int[][] getKernelMatrix(){ return this.kernelMatrix; }
  
  public Kernel(int[][] k){ this.kernelMatrix = k; }
  
}
