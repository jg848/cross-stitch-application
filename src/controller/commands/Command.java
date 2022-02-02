package controller.commands;

import model.ImageProcessingModel;
import model.image.Image;

import java.io.IOException;

/**
 * Interface which represents the operations to be performed on the
 * {@link Image} for the given input command.
 * 
 * @author Jaswin Gumpella
 *
 */
public interface Command {

  /**
   * Method which executes the given command to the controller.
   * 
   * @param model which represents the model class instance of the image.
   * @return an instance of {@link ImageProcessingModel} which contains the
   *         modified {@link Image}.
   * @throws IOException when invalid file name is given.
   */
  public ImageProcessingModel execute(ImageProcessingModel model) throws IOException;
}
