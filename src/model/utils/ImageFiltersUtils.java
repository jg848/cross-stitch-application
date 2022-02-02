package model.utils;

import model.image.Image;
import model.pixel.Pixel;

/**
 * Utility class to apply the filtering related operations of an {@link Image}.
 * Extends the {@link ImageModelUtilsImpl} abstract class and implements the
 * filtering related utility operations defined in the {@link ImageModelUtils}
 * interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ImageFiltersUtils extends ImageModelUtilsImpl {

  /**
   * Default constructor of the utility class.
   */
  public ImageFiltersUtils() {
    super();
  }

  @Override
  public int[] applyFilterToImage(Image image, double[][] filter, Pixel curPixel) {
    if (null == image || null == filter || null == curPixel) {
      throw new IllegalArgumentException("Invalid filtering arguments.");
    }
    int[][][] kernel = new int[filter.length][filter.length][3];
    int halfKernelSize = filter.length / 2;
    for (int x = 0; x < filter.length; x++) {
      for (int y = 0; y < filter.length; y++) {
        try {
          kernel[x][y][0] = image.getImage()[x + (curPixel.getRow() - halfKernelSize)][y
              + (curPixel.getColumn() - halfKernelSize)][0];
          kernel[x][y][1] = image.getImage()[x + (curPixel.getRow() - halfKernelSize)][y
              + (curPixel.getColumn() - halfKernelSize)][1];
          kernel[x][y][2] = image.getImage()[x + (curPixel.getRow() - halfKernelSize)][y
              + (curPixel.getColumn() - halfKernelSize)][2];
        } catch (IndexOutOfBoundsException e) {
          kernel[x][y][0] = 0;
          kernel[x][y][1] = 0;
          kernel[x][y][2] = 0;
        }
      }
    }
    int[] newPixel = curPixel.modifyPixel(kernel, filter);
    if (null == newPixel) {
      throw new IllegalArgumentException("Invalid pixel.");
    }
    return newPixel;
  }

}
