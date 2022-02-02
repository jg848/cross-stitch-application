package model.utils;

import model.image.Image;
import model.pixel.Pixel;

/**
 * Utility class to apply the transformation related operations of an
 * {@link Image}. Extends the {@link ImageModelUtilsImpl} abstract class and
 * implements the transformation related utility operations defined in the
 * {@link ImageModelUtils} interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ImageTransformationUtils extends ImageModelUtilsImpl {

  /**
   * Default constructor of the utility class.
   */
  public ImageTransformationUtils() {
    super();
  }

  @Override
  public int[] applyTransformationToImage(double[][] transformation, Pixel curPixel) {
    if (null == transformation || null == curPixel) {
      throw new IllegalArgumentException("Invalid transformation arguments.");
    }
    int[] newPixel = curPixel.modifyPixel(transformation);
    if (null == newPixel) {
      throw new IllegalArgumentException("Invalid pixel.");
    }
    return newPixel;
  }

}
