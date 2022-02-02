package controller.commands;

import model.ImageProcessingModel;

/**
 * Implementation class of the {@link Command} class which is used for applying
 * greyscale transformation to an image. Represents the command which applies
 * greyscale transformation on the image.
 * 
 * @author Jaswin Gumpella
 *
 */
public class GreyScale implements Command {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    if (null == model) {
      throw new IllegalArgumentException("Model not initialized.");
    }
    model.greyscaleImage();
    return model;
  }
}
