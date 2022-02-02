package model;

import model.channel.ColorChannel;
import model.channel.ColorChannelImpl;
import model.channel.FlossColorImpl;
import model.image.Image;
import model.image.ImageImpl;
import model.utils.ImageUtilities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * Implementation class of the {@link ImageProcessingModel} interface which
 * contains the extended operations that can be performed on an image.
 * Represents the implementations defined in the interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ImageProcessingModelImpl extends ImageModelImpl implements ImageProcessingModel {

  /**
   * Default constructor for the abstract implementation class of
   * {@link ImageProcessingModel}.
   */
  public ImageProcessingModelImpl() {
    super();
  }

  /**
   * Constructs a ImageModel instance for a given image.
   *
   * @param image represents the original image.
   */
  public ImageProcessingModelImpl(Image image) {
    super(image);
  }

  @Override
  public void mosaicImage(int numberOfSeeds) throws IllegalArgumentException {
    checkImage();
    if (numberOfSeeds <= 0) {
      throw new IllegalArgumentException("Invalid number of seeds.");
    }
    ImageMosaic imageMosaic = new ImageMosaic(image);
    imageMosaic.mosaicImage(numberOfSeeds);
    this.image = imageMosaic.image;
  }

  @Override
  public void pixelateImage(int numberOfSuperPixels) throws IllegalArgumentException {
    checkImage();
    if (numberOfSuperPixels <= 0) {
      throw new IllegalArgumentException("Invalid number of super pixels.");
    }
    ImagePixelation imagePixelation = new ImagePixelation(image);
    imagePixelation.pixelateImage(numberOfSuperPixels);
    this.image = imagePixelation.image;
  }

  @Override
  public void patternImage(String filename) throws IOException {
    checkImage();
    if (null == filename || filename.isBlank()) {
      throw new IllegalArgumentException("Invalid filename.");
    }
    CrossStitchPattern crossStitchPattern = new CrossStitchPattern(image, filename);
    crossStitchPattern.patternImage();
    this.image = crossStitchPattern.image;
  }

  @Override
  public void savePattern(String filename, String flossFile) throws IOException {
    checkImage();
    if (null == filename || filename.isBlank()) {
      throw new IllegalArgumentException("Invalid filename.");
    }
    CrossStitchPattern crossStitchPattern = new CrossStitchPattern(image, flossFile);
    crossStitchPattern.savePattern(filename);
    this.image = crossStitchPattern.image;
  }

  @Override
  public void loadImage(String filename) throws IOException {
    if (null == filename || filename.isBlank()) {
      throw new IllegalArgumentException("Invalid filename.");
    }
    this.image = new ImageImpl(filename);
    this.originalImage = new ImageImpl(filename);
  }

  @Override
  public BufferedImage getBufferedImage() {
    checkImage();
    return ImageUtilities.getBufferedImage(image.getImage(), image.getWidth(), image.getHeight());
  }

  @Override
  public BufferedImage getOriginalImage() {
    checkImage();
    return ImageUtilities.getBufferedImage(originalImage.getImage(), originalImage.getWidth(),
        originalImage.getHeight());
  }

  @Override
  public void swapColorInPattern(int[] oldColor, int[] newColor, String flossFile)
      throws IOException {
    if (null == oldColor || null == newColor) {
      throw new IllegalArgumentException();
    }
    CustomCrossStitchPattern customCrossStitchPattern = new CustomCrossStitchPattern(image,
        flossFile);
    customCrossStitchPattern.swapDmcColor(oldColor, newColor);
    this.image = customCrossStitchPattern.image;
  }

  @Override
  public Map<Character, int[]> getAvailableFlossColors(String flossFile) throws IOException {
    return new FlossColorImpl(new int[3], flossFile).getAvailableFloss();
  }

  @Override
  public void patternImageCustomDmc(Map<Character, int[]> colorPalette, String flossFile)
      throws IOException {
    if (null == colorPalette) {
      throw new IllegalArgumentException();
    }
    CustomCrossStitchPattern customCrossStitchPattern = new CustomCrossStitchPattern(image,
        flossFile);
    customCrossStitchPattern.patternImageWithCustomDmc(colorPalette);
    this.image = customCrossStitchPattern.image;
  }

  @Override
  public ColorChannel getPixelColors(int row, int col) throws IOException {
    return new ColorChannelImpl(image.getImage()[row][col]);
  }

  @Override
  public void removeDmc(int[] color, String flossFile) {
    if (null == color) {
      throw new IllegalArgumentException();
    }
    CustomCrossStitchPattern customCrossStitchPattern = new CustomCrossStitchPattern(image,
        flossFile);
    customCrossStitchPattern.removeDmc(color);
    this.image = customCrossStitchPattern.image;
  }

}
