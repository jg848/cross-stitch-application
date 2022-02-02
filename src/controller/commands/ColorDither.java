package controller.commands;

import model.ImageProcessingModel;

/**
 * Implementation class of the {@link Command} class which is used for applying
 * color dithering to an image. Represents the command which applies color
 * dithering on the image.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ColorDither implements Command {
  private final int numberOfColors;

  /**
   * Constructor for the {@link ColorDither} class which takes the number of
   * colors as input.
   * 
   * @param numberOfColors which represents the number of colors the image density
   *                       is to be reduced to.
   */
  public ColorDither(int numberOfColors) {
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
    model.colorDitherImage(numberOfColors);
    return model;
  }

}
