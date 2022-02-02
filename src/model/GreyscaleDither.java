package model;

import model.image.Image;
import model.image.ImageImpl;
import model.utils.ImageModelUtils;

/**
 * This class extends the {@link ImageDensity} class and extends the abstract
 * {@link ImageModelImpl} class and implements the {@link ImageModel} interface.
 * Class to be used as an extension to implement the operations related to the
 * greyscale dithering of an image defined in the {@link ImageModel} interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class GreyscaleDither extends ImageDensity {

  /**
   * Constructor for the {@link GreyscaleDither} class which takes the
   * {@link Image} as argument to provide the model with the {@link Image} that
   * has to be operated on.
   * 
   * @param image which is the image on which the filter is to be applied.
   */
  public GreyscaleDither(Image image) {
    super(image);
  }

  @Override
  public void greyscaleDitherImage(int numberOfColors) {
    if (numberOfColors <= 0) {
      throw new IllegalArgumentException("Number of colors to be reduced to has to be positive.");
    }
    newImage = ImageModelUtils.deepClone(image.getImage());
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int[] newColor = ditherHelper(i, j, numberOfColors);
        newImage[i][j] = new int[] { newColor[0], newColor[0], newColor[0] };
        int error = oldColor.getColorChannel()[0] - newColor[0];
        for (int k = 0; k < newColor.length; k++) {
          newImage = ditherUtils.calculateErrors(newImage, i, j, k, error);
        }
      }
    }
    this.image = new ImageImpl(newImage);

  }

}
