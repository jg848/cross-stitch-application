package model.channel;

import model.image.Image;

/**
 * Implementation class of the {@link ColorChannel} interface which implements
 * the operations defined in the interface. Represents the color channels of an
 * {@link Image}.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ColorChannelImpl implements ColorChannel {

  private int[] colors;

  /**
   * Constructor for the implementation class which takes in the array of color
   * channels and sets the attribute of the class.
   * 
   * @param colors which is an array containing the color channels.
   * 
   */
  public ColorChannelImpl(int[] colors) {
    if (null == colors || colors.length != 3) {
      throw new IllegalArgumentException("Invalid length of color channel.");
    }
    this.colors = colors;
  }

  @Override
  public int[] getColorChannel() {
    return this.colors;
  }

  @Override
  public void setColorChannel(int[] colors) {
    if (null == colors || colors.length != 3) {
      throw new IllegalArgumentException("Invalid length of color channel.");
    }
    this.colors = colors;
  }

  @Override
  public int[] findClosestPaletteColor(int numberOfColors) {
    if (numberOfColors <= 0) {
      throw new IllegalArgumentException("Number of colors to be reduced to has to be positive.");
    }
    int[] closest = new int[3];
    for (int i = 0; i < this.colors.length; i++) {
      closest[i] = (int) Math.round(numberOfColors * this.colors[i] / 255.0)
          * (255 / numberOfColors);
    }
    return clampValues(closest);
  }

  /**
   * Helper method to clamp the values after modifying the channel values.
   * 
   * @param colors which is an array containing the color channels.
   * @return an array containing the clamped values of the color channels.
   */
  private int[] clampValues(int[] colors) {
    if (null == colors) {
      throw new IllegalArgumentException("Invalid color channels.");
    }
    for (int i = 0; i < colors.length; i++) {
      if (colors[i] < 0) {
        colors[i] = 0;
      } else if (colors[i] > 255) {
        colors[i] = 255;
      }
    }
    return colors;
  }
}
