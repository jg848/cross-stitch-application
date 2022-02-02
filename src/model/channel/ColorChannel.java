package model.channel;

/**
 * Interface to represent the color channels of an {@link Image} and the
 * operations to be performed by it.
 * 
 * @author Jaswin Gumpella
 *
 */
public interface ColorChannel {

  /**
   * Method to get the color channels.
   * 
   * @return an array containing the color channels.
   */
  public int[] getColorChannel();

  /**
   * Method to set the color channels.
   * 
   * @param colors which is an array containing the color channels.
   */
  public void setColorChannel(int[] colors);

  /**
   * Method to find the closest palette color for the color channels.
   * 
   * @param numberOfColors which is the number of colors to be reduced to for the
   *                       {@link Image}.
   * @return an array containing the color channels.
   */
  public int[] findClosestPaletteColor(int numberOfColors);
}
