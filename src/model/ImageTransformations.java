package model;

import model.channel.ColorChannelImpl;
import model.image.Image;
import model.image.ImageImpl;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.utils.ImageModelUtils;
import model.utils.ImageTransformationUtils;

/**
 * This class extends the abstract {@link ImageModelImpl} class and implements
 * the {@link ImageModel} interface. Contains the operations related to the
 * transformation of an image defined in the {@link ImageModel} interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public abstract class ImageTransformations extends ImageModelImpl {

  /**
   * Constructor for the {@link ImageTransformations} class which takes the
   * {@link Image} as argument to provide the model with the {@link Image} that
   * has to be operated on.
   * 
   * @param image which is the image on which the filter is to be applied.
   */
  protected ImageTransformations(Image image) {
    super(image);
  }

  /**
   * Helper method for the transformation operation on the {@link Image}.
   * 
   * @param filter which is the transformation matrix to be applied to the
   *               {@link Image}.
   */
  protected void transformImage(double[][] transformation) {
    if (null == transformation) {
      throw new IllegalArgumentException("Invalid transformation matrix.");
    }
    int[][][] newImage = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel curPixel = new PixelImpl(i, j, new ColorChannelImpl(image.getImage()[i][j]));
        ImageModelUtils transformationUtils = new ImageTransformationUtils();
        newImage[i][j] = transformationUtils.applyTransformationToImage(transformation, curPixel);
      }
    }
    this.image = new ImageImpl(newImage);
  }

}
