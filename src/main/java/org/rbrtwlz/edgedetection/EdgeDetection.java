package org.rbrtwlz.edgedetection;

import org.rbrtwlz.edgedetection.filters.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Locale;
import java.util.concurrent.Callable;


@Command(name = "EdgeDetection", mixinStandardHelpOptions = true, version = "EdgeDetection 0.0.1",
          description = "Applies edge detection filter to image.")

  public class EdgeDetection implements Callable {

    private final String availableFilter = "laplace, sobel, gaussian, prewitt, scharr, bin_threshold";

    @Parameters(index = "0", description = "The path to the image file.")
    private String imagePath;

    @Option(names = {"-o", "--output"}, description = "Path and filename for output. Default: ./out.png")
    private String outPath = "./out.png";

    @Option(names = {"-f", "--filter"}, description = availableFilter)
    private String filterName = "laplace";

    @Option(names = {"-t", "--threshold"}, description = "Threshold, only required for threshold filters.")
    private int threshold = 128;

  @Option(names = {"--fileFormat"}, description = "png, jpg, jpeg. Default: png")
  private String fileFormat = "png";

    @Override
    public Integer call() throws Exception {
      Filter filter = new LaplaceFilter();
      switch (this.filterName.toLowerCase()){
        case "laplace":
          filter = new LaplaceFilter();
          break;
        case "sobel":
          filter = new SobelFilter();
          break;
        case "gaussian":
          filter = new GaussianFilter();
          break;
        case "prewitt":
          filter = new PrewittFilter();
          break;
        case "scharr":
          filter = new ScharrFilter();
          break;
        case "bin_threshold":
          filter = new BinaryThresholdFilter(this.threshold);
          break;
        default:
          System.out.println("Please specify one of following filters: "+ this.availableFilter);
          System.exit(2);
          break;
      }
      switch (this.fileFormat.toLowerCase()){
        case "png":
          this.fileFormat = "png";
          break;
        case "jpg":
          this.fileFormat = "jpg";
          break;
        case "jpeg":
          this.fileFormat = "jpeg";
          break;
        default:
          System.out.println("Please specify one of following formats: png, jpg, jpeg");
          System.exit(2);
          break;
      }
      Client client = new Client(this.imagePath, filter);
      client.applyFilter();
      client.setOutputFileFormat(this.fileFormat);
      client.saveImage(this.outPath);
      return 0;
    }

    public static void main(String... args) {
      int exitCode = new CommandLine(new EdgeDetection()).execute(args);
      System.exit(exitCode);
    }
  }
