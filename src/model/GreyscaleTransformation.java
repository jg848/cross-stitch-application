package model;

import model.image.Image;

/**
 * This class extends the {@link ImageTransformations} class which extends the
 * abstract {@link ImageModelImpl} class and implements the {@link ImageModel}
 * interface. Class to be used as an extension to implement the operations
 * related to the greyscale transformation of an image defined in the
 * {@link ImageModel} interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class GreyscaleTransformation extends ImageTransformations {

  /**
   * Constructor for the {@link GreyscaleTransformation} class which takes the
   * {@link Image} as argument to provide the model with the {@link Image} that
   * has to be operated on.
   * 
   * @param image which is the image on which the filter is to be applied.
   */
  public GreyscaleTransformation(Image image) {
    super(image);
  }

  @Override
  public void greyscaleImage() {
    double[][] greyscaleTransformationMatrix = { { 0.2126, 0.7152, 0.0722 },
        { 0.2126, 0.7152, 0.0722 }, { 0.2126, 0.7152, 0.0722 } };
    this.transformImage(greyscaleTransformationMatrix);
  }

}
