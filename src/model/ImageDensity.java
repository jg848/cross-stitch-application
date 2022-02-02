package model;

import model.channel.ColorChannel;
import model.channel.ColorChannelImpl;
import model.image.Image;
import model.utils.ImageDensityUtils;
import model.utils.ImageModelUtils;

/**
 * This class extends the abstract {@link ImageModelImpl} class and implements
 * the {@link ImageModel} interface. Contains the operations related to the
 * density of an image defined in the {@link ImageModel} interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public abstract class ImageDensity extends ImageModelImpl {

  protected int[][][] newImage;
  protected ColorChannel oldColor;
  protected final ImageModelUtils ditherUtils;

  /**
   * Constructor for the {@link ImageDensity} class which takes the {@link Image}
   * as argument to provide the model with the {@link Image} that has to be
   * operated on.
   * 
   * @param image which is the image on which the filter is to be applied.
   */
  protected ImageDensity(Image image) {
    super(image);
    newImage = ImageModelUtils.deepClone(image.getImage());
    oldColor = new ColorChannelImpl(new int[3]);
    ditherUtils = new ImageDensityUtils();
  }

  /**
   * Helper method to apply the dither to an {@link Image}.
   * 
   * @param i              which is the height index of the {@link Image}.
   * @param j              which is the width index of the {@link Image}.
   * @param numberOfColors which is the number of colors the dithering has to be
   *                       applied to the {@link Image}.
   * @return an array containing the color channels.
   */
  protected int[] ditherHelper(int i, int j, int numberOfColors) {
    if (i < 0 || j < 0 || numberOfColors <= 0) {
      throw new IllegalArgumentException("Invalid dithering arguments.");
    }
    oldColor = new ColorChannelImpl(newImage[i][j]);
    return ditherUtils.applyColorDitherToImage(oldColor, numberOfColors);
  }

}
