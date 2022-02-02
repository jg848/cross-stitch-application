package controller.commands;

import model.ImageProcessingModel;

/**
 * Implementation class of the {@link Command} class which is used for applying
 * mosaic image chunking to an image. Represents the command which applies
 * mosaic image chunking on the image.
 * 
 * @author Jaswin Gumpella
 *
 */
public class Mosaic implements Command {
  private final int numberOfSeeds;

  /**
   * Constructor for the {@link Mosaic} class which takes the number of seeds as
   * input.
   * 
   * @param numberOfSeeds which represents the number of seeds the image chunking
   *                      is to be done on the image.
   */
  public Mosaic(int numberOfSeeds) {
    if (numberOfSeeds < 1) {
      throw new IllegalArgumentException("Invalid number of seeds.");
    }
    this.numberOfSeeds = numberOfSeeds;
  }

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) throws IllegalArgumentException {
    if (null == model) {
      throw new IllegalArgumentException("Model not initialized.");
    }
    model.mosaicImage(numberOfSeeds);
    return model;
  }
}
