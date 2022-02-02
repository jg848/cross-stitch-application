package model;

import model.image.Image;

/**
 * This class extends the {@link ImageTransformations} class which extends the
 * abstract {@link ImageModelImpl} class and implements the {@link ImageModel}
 * interface. Class to be used as an extension to implement the operations
 * related to the sepiatone transformation of an image defined in the
 * {@link ImageModel} interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class SepiatoneTransformation extends ImageTransformations {

  /**
   * Constructor for the {@link SepiatoneTransformation} class which takes the
   * {@link Image} as argument to provide the model with the {@link Image} that
   * has to be operated on.
   * 
   * @param image which is the image on which the filter is to be applied.
   */
  public SepiatoneTransformation(Image image) {
    super(image);
  }

  @Override
  public void sepiatoneImage() {
    double[][] sepiaToneTransformationMatrix = { { 0.393, 0.769, 0.189 }, { 0.349, 0.686, 0.168 },
        { 0.272, 0.534, 0.131 } };
    this.transformImage(sepiaToneTransformationMatrix);
  }

}
