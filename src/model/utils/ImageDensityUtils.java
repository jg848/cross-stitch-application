package model.utils;

import model.channel.ColorChannel;
import model.image.Image;

/**
 * Utility class to apply the density related operations of an {@link Image}.
 * Extends the {@link ImageModelUtilsImpl} abstract class and implements the
 * density related utility operations defined in the {@link ImageModelUtils}
 * interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ImageDensityUtils extends ImageModelUtilsImpl {

  /**
   * Default constructor of the utility class.
   */
  public ImageDensityUtils() {
    super();
  }

  @Override
  public int[] applyColorDitherToImage(ColorChannel oldColor, int numberOfColors) {
    if (null == oldColor || numberOfColors <= 0) {
      throw new IllegalArgumentException("Invalid dithering arguments.");
    }
    int[] colors = oldColor.findClosestPaletteColor(numberOfColors);
    if (colors == null) {
      throw new IllegalArgumentException("Invalid color channels.");
    }
    return colors;
  }

}
