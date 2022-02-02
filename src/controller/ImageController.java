package controller;

import model.ImageProcessingModel;

import java.io.IOException;

/**
 * Interface for the controller class which contains the operations supported by
 * the controller for the image processing.
 * 
 * @author Jaswin Gumpella
 *
 */
public interface ImageController {

  /**
   * Method to start the controller of the image processing model.
   * 
   * @param model which is an instance of the model class of the application.
   * @throws IOException for invalid file locations.
   */
  public void start(ImageProcessingModel model) throws IOException;
}
