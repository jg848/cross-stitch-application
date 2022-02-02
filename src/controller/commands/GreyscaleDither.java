package controller.commands;

import model.ImageProcessingModel;

/**
 * Implementation class of the {@link Command} class which is used for applying
 * greyscale dithering to an image. Represents the command which applies
 * greyscale dithering on the image.
 * 
 * @author Jaswin Gumpella
 *
 */
public class GreyscaleDither implements Command {
  private final int numberOfColors;

  /**
   * Constructor for the {@link GreyscaleDither} class which takes the number of
   * colors as input.
   * 
   * @param numberOfColors which represents the number of colors the image density
   *                       is to be reduced to.
   */
  public GreyscaleDither(int numberOfColors) {
    if (numberOfColors < 0) {
      throw new IllegalArgumentException("Invalid number of colors.");
    }
    this.numberOfColors = numberOfColors;
  }

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    if (null == model) {
      throw new IllegalArgumentException("Model not initialized.");
    }
    model.greyscaleDitherImage(numberOfColors);
    return model;
  }

}
