package controller.commands;

import model.ImageProcessingModel;

/**
 * Implementation class of the {@link Command} class which is used for applying
 * blur filtering to an image. Represents the command which applies blur filter
 * on the image.
 * 
 * @author Jaswin Gumpella
 *
 */
public class Blur implements Command {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    if (null == model) {
      throw new IllegalArgumentException("Model not initialized.");
    }
    model.blurImage();
    return model;
  }
}
