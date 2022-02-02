package controller.commands;

import model.ImageProcessingModel;

/**
 * Implementation class of the {@link Command} class which is used for applying
 * sepiatone transformation to an image. Represents the command which applies
 * sepiatone transformation on the image.
 * 
 * @author Jaswin Gumpella
 *
 */
public class Sepia implements Command {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    if (null == model) {
      throw new IllegalArgumentException("Model not initialized.");
    }
    model.sepiatoneImage();
    return model;
  }
}
