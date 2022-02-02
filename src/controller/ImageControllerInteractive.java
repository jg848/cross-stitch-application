package controller;

import model.ImageProcessingModel;
import model.channel.ColorChannel;
import view.ImageView;
import view.ImageViewImpl;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

/**
 * Extension of the {@link ImageControllerImpl} class. Represents the class
 * which handles the control of the application.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ImageControllerInteractive extends ImageControllerImpl implements ImageFeatures {

  private final ImageView view;
  private ImageProcessingModel model;
  private String loadImageCommand;

  /**
   * Default constructor for {@link ImageControllerInteractive} class.
   * 
   * @throws IOException for invalid file location of required items to load the
   *                     view.
   */
  public ImageControllerInteractive() throws IOException {
    super();
    this.view = new ImageViewImpl();
    this.model = null;
    this.loadImageCommand = "";
  }

  @Override
  public void start(ImageProcessingModel model) throws IOException {
    if (null == model) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.model = model;
    view.setFeatures(this);
  }

  @Override
  public String loadImage(String filename) throws IOException {
    if (null == filename || filename.isBlank()) {
      throw new IllegalArgumentException("Invalid filename.");
    }
    this.loadImageCommand = "load " + filename;
    return processCommands("load " + filename);
  }

  @Override
  public String saveImage(String filename) throws IOException {
    if (null == filename || filename.isBlank()) {
      throw new IllegalArgumentException("Invalid filename.");
    }
    return processCommands("save " + filename);
  }

  @Override
  public String blurImage() throws IOException {
    return processCommands("blur");
  }

  @Override
  public String sharpenImage() throws IOException {
    return processCommands("sharpen");
  }

  @Override
  public String greyscaleImage() throws IOException {
    return processCommands("greyscale");
  }

  @Override
  public String sepiatoneImage() throws IOException {
    return processCommands("sepia");
  }

  @Override
  public String colorDitherImage(String numberOfColors) throws IOException {
    if (null == numberOfColors || numberOfColors.isBlank()) {
      throw new IllegalArgumentException("Invalid number of colors.");
    }
    return processCommands("colordither " + numberOfColors);
  }

  @Override
  public String greyscaleDitherImage(String numberOfColors) throws IOException {
    if (null == numberOfColors || numberOfColors.isBlank()) {
      throw new IllegalArgumentException("Invalid number of colors.");
    }
    return processCommands("greyscaledither " + numberOfColors);
  }

  @Override
  public String mosaicImage(String numberOfSeeds) throws IOException {
    if (null == numberOfSeeds || numberOfSeeds.isBlank()) {
      throw new IllegalArgumentException("Invalid number of seeds.");
    }
    return processCommands("mosaic " + numberOfSeeds);
  }

  @Override
  public String pixelateImage(String numberOfSquares) throws IOException {
    if (null == numberOfSquares || numberOfSquares.isBlank()) {
      throw new IllegalArgumentException("Invalid number of squares.");
    }
    return processCommands("pixelate " + numberOfSquares);
  }

  @Override
  public String patternImage() throws IOException {
    return processCommands("pattern");
  }

  @Override
  public String savePatternToText(String filename) throws IOException {
    if (null == filename || filename.isBlank() || !filename.contains(".txt")) {
      throw new IllegalArgumentException("Invalid filename.");
    }
    return processCommands("save " + filename);
  }

  @Override
  public Map<Character, int[]> displayImageFlossColors() throws IOException {
    return model.getAvailableFlossColors(FLOSS_FILE);
  }

  @Override
  public BufferedImage displayImage() {
    return model.getBufferedImage();
  }

  @Override
  public String processCommands(String batchCommands) throws IOException {
    if (null == batchCommands || batchCommands.isBlank()) {
      throw new IllegalArgumentException("Invalid batch commands.");
    }
    StringBuilder output = new StringBuilder();
    Readable input = new StringReader(batchCommands);
    execute(input, model, output);
    return output.toString();
  }

  @Override
  public BufferedImage displayOriginalImage() throws IOException {
    return model.getOriginalImage();
  }

  @Override
  public void swapDmc(String currentColor, String newColor) throws IOException {
    if (null == currentColor || currentColor.isBlank() || null == newColor || newColor.isBlank()) {
      throw new IllegalArgumentException("Invalid colors.");
    }
    int[] currentChannel = this.model.getAvailableFlossColors(FLOSS_FILE)
        .get(currentColor.charAt(0));
    int[] newChannel = this.model.getAvailableFlossColors(FLOSS_FILE).get(newColor.charAt(0));
    this.model.swapColorInPattern(currentChannel, newChannel, FLOSS_FILE);
  }

  @Override
  public int[] getPixelColors(int row, int col) throws IOException {
    ColorChannel pixelColors = model.getPixelColors(row, col);
    return pixelColors.getColorChannel();
  }

  @Override
  public void patternImageWithCustomDmc(Map<Character, int[]> customDmcColors) throws IOException {
    if (null == customDmcColors) {
      throw new IllegalArgumentException("Invalid colors.");
    }
    this.model.patternImageCustomDmc(customDmcColors, FLOSS_FILE);
  }

  @Override
  public void removeDmc(int[] color) {
    if (null == color) {
      throw new IllegalArgumentException("Invalid color.");
    }
    model.removeDmc(color, FLOSS_FILE);
  }

  @Override
  public String reloadOriginalImage() throws IOException {
    return processCommands(loadImageCommand);
  }

  @Override
  public String processBatchCommands(BufferedReader inputFile) throws IOException {
    if (null == inputFile) {
      throw new IllegalArgumentException("Invalid input.");
    }
    StringBuilder output = new StringBuilder();
    execute(inputFile, model, output);
    return output.toString();
  }

}
