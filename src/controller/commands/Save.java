package controller.commands;

import model.ImageProcessingModel;

import java.io.IOException;

/**
 * Implementation class of the {@link Command} class which is used for saving an
 * image. Represents the command which saves the image in the given location.
 * 
 * @author Jaswin Gumpella
 *
 */
public class Save implements Command {
  private final String filename;

  /**
   * Constructor for the {@link Save} class which takes input of the filename
   * where the image is to be saved.
   * 
   * @param filename which represents the location of the file to be saved.
   */
  public Save(String filename) {
    if (null == filename || filename.isBlank()) {
      throw new IllegalArgumentException("Invalid filename.");
    }
    this.filename = filename;
  }

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) throws IOException {
    if (null == model) {
      throw new IllegalArgumentException("Model not initialized.");
    }
    model.saveImage(filename);
    return model;
  }
}
