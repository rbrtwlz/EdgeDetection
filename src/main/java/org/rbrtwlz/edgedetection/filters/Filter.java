package org.rbrtwlz.edgedetection.filters;

import org.rbrtwlz.edgedetection.ImageArray;

public interface Filter {

  ImageArray applyFilter(ImageArray imageArray);
  
}
