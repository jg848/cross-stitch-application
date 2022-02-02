package model;

import model.channel.FlossColor;
import model.channel.FlossColorImpl;
import model.image.Image;

import java.io.IOException;
import java.util.Map;

/**
 * {@link CustomCrossStitchPattern} is an extension of
 * {@link CrossStitchPattern} which implements the custom dmc colors related
 * operations defined in the {@link ImageProcessingModel} interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class CustomCrossStitchPattern extends CrossStitchPattern {

  /**
   * Constructs a CrossStitchPattern instance for a given image.
   *
   * @param image represents an image instance.
   */
  public CustomCrossStitchPattern(Image image, String filename) {
    super(image, filename);
  }

  /**
   * Method used to swap the old color channel with the new color channel of the
   * image.
   * 
   * @param oldColor which represents the color channel to be replaced.
   * @param newColor which represents the new color channel to replace.
   */
  public void swapDmcColor(int[] oldColor, int[] newColor) {
    if (null == oldColor || null == newColor) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        if (image.getImage()[i][j][0] == oldColor[0] && image.getImage()[i][j][1] == oldColor[1]
            && image.getImage()[i][j][2] == oldColor[2]) {
          image.getImage()[i][j] = newColor;
        }
      }
    }
  }

  /**
   * Method used to generate a cross stitch pattern with the given custom selected
   * color palette.
   * 
   * @param colorPalette which represents the custom selected color palette from
   *                     which the pattern is to be generated.
   * @throws IOException for invalid file.
   */
  public void patternImageWithCustomDmc(Map<Character, int[]> colorPalette) throws IOException {
    if (null == colorPalette) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        FlossColor flossColor = new FlossColorImpl(image.getImage()[i][j],
            availablePaletteFileName);
        image.getImage()[i][j] = flossColor
            .getFlossColorChannel(flossColor.findClosestAvailableFloss(colorPalette))
            .getColorChannel();
      }
    }
  }

  /**
   * Method used to remove the given color channel from the image.
   * 
   * @param color which represents the color channel to be removed.
   */
  public void removeDmc(int[] color) {
    if (null == color) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        if (image.getImage()[i][j][0] == color[0] && image.getImage()[i][j][1] == color[1]
            && image.getImage()[i][j][2] == color[2]) {
          image.getImage()[i][j] = new int[] { 255, 255, 255 };
        }
      }
    }
  }
}
