package controller.commands;

import model.ImageProcessingModel;

import java.io.File;
import java.io.IOException;

/**
 * Implementation class of the {@link Command} class which is used for applying
 * pattern image chunking to an image. Represents the command which applies
 * pattern image chunking on the image.
 * 
 * @author Jaswin Gumpella
 *
 */
public class Pattern implements Command {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) throws IOException {
    if (null == model) {
      throw new IllegalArgumentException("Model not initialized.");
    }
    File file = new File("");
    model.patternImage(file.getAbsolutePath() + "\\dmc-floss.csv");
    return model;
  }
}
