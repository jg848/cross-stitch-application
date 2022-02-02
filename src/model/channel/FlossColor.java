package model.channel;

import java.util.Map;

/**
 * Extension of the {@link ColorChannel} interface which represents the color of
 * DMC Floss. Contains the operations to be supported by the floss color.
 * 
 * @author Jaswin Gumpella
 *
 */
public interface FlossColor extends ColorChannel {

  /**
   * Method used to find the closest available floss color.
   * 
   * @return an {@link Integer} object which contains the number of the floss
   *         color.
   */
  public Integer findClosestAvailableFloss();
  
  /**
   * Method used to find the closest available floss color from the color palette
   * given.
   * 
   * @param colorPalette which is the color palette containing a map of the
   *                     symbols and their corresponding colors.
   * @return an {@link Integer} representing the code of the color.
   */
  public Integer findClosestAvailableFloss(Map<Character, int[]> colorPalette);

  /**
   * Method used to get the legend to be put into the pattern text file which
   * contains the symbols.
   * 
   * @param dmcCode which is the number of the DMC Floss color.
   * @return an object of {@link Character} which represents the character to be
   *         printed in the pattern text file.
   */
  public Character getLegend(int dmcCode);

  /**
   * Method used to modify the color channel of the image with the closest
   * available floss color.
   */
  public void modifyFlossColorChannel();

  /**
   * Method used to get the color channel of the image for the specified dmc color
   * code.
   * 
   * @param dmcCode which is a number representing the dmc color code.
   * @return an instance of {@link ColorChannel} representing the color channel of
   *         the image.
   */
  public ColorChannel getFlossColorChannel(int dmcCode);

  /**
   * Method used to retrieve the available floss colors.
   * 
   * @return a map containing the symbols as keys and their corresponding colors
   *         as values.
   */
  public Map<Character, int[]> getAvailableFloss();
}
