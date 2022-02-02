package controller.commands;

import model.ImageProcessingModel;

/**
 * Implementation class of the {@link Command} class which is used for applying
 * sharpen filtering to an image. Represents the command which applies sharpen
 * filter on the image.
 * 
 * @author Jaswin Gumpella
 *
 */
public class Sharpen implements Command {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    if (null == model) {
      throw new IllegalArgumentException("Model not initialized.");
    }
    model.sharpenImage();
    return model;
  }
}
