import controller.ImageControllerImpl;
import model.ImageProcessingModel;
import model.channel.ColorChannel;
import model.image.ImageImpl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * Mock model class implementing {@link ImageProcessingModel}. Represents a mock
 * class used to test the {@link ImageControllerImpl}.
 * 
 * @author Jaswin Gumpella
 *
 */
public class MockImageProcessingModelImpl implements ImageProcessingModel {

  private StringBuilder log;

  /**
   * Default constructor for the {@link MockImageProcessingModelImpl} class.
   */
  public MockImageProcessingModelImpl(StringBuilder log) {
    if (null == log) {
      throw new IllegalArgumentException("Invalid arguments.");
    }
    this.log = log;
  }

  @Override
  public void blurImage() {
    log.append("mock blur successful\n");
  }

  @Override
  public void sharpenImage() {
    log.append("mock sharpen successful\n");
  }

  @Override
  public void greyscaleImage() {
    log.append("mock greyscale successful\n");
  }

  @Override
  public void sepiatoneImage() {
    log.append("mock sepia successful\n");
  }

  @Override
  public void colorDitherImage(int numberOfColors) {
    log.append("mock colordither successful\n");
  }

  @Override
  public void greyscaleDitherImage(int numberOfColors) {
    log.append("mock greyscaledither successful\n");
  }

  @Override
  public void saveImage(String filename) throws IOException {
    log.append("mock Saving to file succesful\n");
  }

  @Override
  public void mosaicImage(int numberOfSeeds) throws IllegalArgumentException {
    log.append("mock mosaic successful\n");
  }

  @Override
  public void pixelateImage(int numberOfSuperPixels) throws IllegalArgumentException {
    log.append("mock pixelate successful\n");
  }

  @Override
  public void patternImage(String filename) throws IOException {
    log.append("mock pattern successful\n");
  }

  @Override
  public void savePattern(String filename, String flossFile) throws IOException {
    log.append("mock Saving to text successful\n");
  }

  @Override
  public void loadImage(String filename) throws IOException {
    new ImageImpl(filename);
    log.append("mock load successful\n");
  }

  @Override
  public BufferedImage getBufferedImage() {
    return null;
  }

  @Override
  public BufferedImage getOriginalImage() {
    return null;
  }

  @Override
  public void swapColorInPattern(int[] currentChannel, int[] newChannel, String flossFile)
      throws IOException {
    // no action required
  }

  @Override
  public Map<Character, int[]> getAvailableFlossColors(String flossFile) throws IOException {
    return null;
  }

  @Override
  public void patternImageCustomDmc(Map<Character, int[]> customDmcColors, String flossFile)
      throws IOException {
    // no action required
  }

  @Override
  public ColorChannel getPixelColors(int row, int col) throws IOException {
    return null;
  }

  @Override
  public void removeDmc(int[] color, String flossFile) {
    // no action required
  }

}
