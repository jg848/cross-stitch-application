package model;

import model.channel.ColorChannelImpl;
import model.image.Image;
import model.image.ImageImpl;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.utils.ImageFiltersUtils;
import model.utils.ImageModelUtils;

/**
 * This class extends the abstract {@link ImageModelImpl} class and implements
 * the {@link ImageModel} interface. Class to be used as an extension for the
 * operations related to the filtering of an image defined in the
 * {@link ImageModel} interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ImageFilters extends ImageModelImpl {

  /**
   * Constructor for the {@link ImageFilters} class which takes the {@link Image}
   * as argument to provide the model with the {@link Image} that has to be
   * operated on.
   * 
   * @param image which is the image on which the filter is to be applied.
   */
  protected ImageFilters(Image image) {
    super(image);
  }
  
  /**
   * Helper method for the filtering operation on the {@link Image}.
   * 
   * @param filter which is the filter matrix to be applied to the {@link Image}.
   */
  protected void filterImage(double[][] filter) {
    if (null == filter) {
      throw new IllegalArgumentException("Invalid filter matrix.");
    }
    if (filter.length % 2 == 0 || filter.length != filter[0].length) {
      throw new IllegalArgumentException("Invalid filter matrix.");
    }
    int[][][] newImage = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel curPixel = new PixelImpl(i, j, new ColorChannelImpl(image.getImage()[i][j]));
        ImageModelUtils filterUtils = new ImageFiltersUtils();
        newImage[i][j] = filterUtils.applyFilterToImage(image, filter, curPixel);
      }
    }
    this.image = new ImageImpl(newImage);
  }

}
