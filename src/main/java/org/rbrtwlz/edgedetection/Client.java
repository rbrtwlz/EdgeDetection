package org.rbrtwlz.edgedetection;

import org.rbrtwlz.edgedetection.filters.Filter;

public class Client {
  
  private ImageArray originalImage, resultImage;
  private Filter filter;
  private String outputFileFormat;

  public Client(String originalImagePath, Filter filter){
    this.loadImage(originalImagePath);
    this.filter = filter;
    this.outputFileFormat = "png";
  }

  public void loadImage(String originalImagePath){
    this.originalImage = ImageReader.readImage(originalImagePath);
  }

  public void setFilter(Filter filter){
    this.filter = filter;
  }

  public void setImage(String imagePath){ this.loadImage(imagePath); }

  public void setOutputFileFormat(String outputFileFormat) {
    this.outputFileFormat = outputFileFormat;
  }

  public void applyFilter(){
    this.resultImage = this.filter.applyFilter(originalImage);
  }

  public void saveImage(String resultImagePath){
    ImageWriter.writeImage(resultImagePath, resultImage, this.outputFileFormat);
  }


}
