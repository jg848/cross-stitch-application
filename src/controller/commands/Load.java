package controller.commands;

import model.ImageProcessingModel;

import java.io.IOException;

/**
 * Implementation class of the {@link Command} class which is used for loading
 * an image. Represents the command which loads the image from the given
 * location.
 * 
 * @author Jaswin Gumpella
 *
 */
public class Load implements Command {
  private final String filename;

  /**
   * Constructor for the {@link Load} class which takes input of the filename
   * where the image is to be loaded from.
   * 
   * @param filename which represents the location of the file to be loaded.
   */
  public Load(String filename) {
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
    model.loadImage(filename);
    return model;
  }
}
