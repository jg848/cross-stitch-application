package model;

import model.channel.ColorChannel;
import model.image.Image;
import model.pixel.Pixel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * Extension of the {@link ImageModel} interface which contains the operations
 * that can be performed on an image. Contains the extended features of image
 * chunking like mosaic, pixelation and pattern.
 * 
 * @author Jaswin Gumpella
 *
 */
public interface ImageProcessingModel extends ImageModel {

  /**
   * Method to apply mosaic image chunking to an image by taking input of the
   * number of seeds for the image pixels to be reduced to.
   * 
   * @param numberOfSeeds which represents the number of seeds the image pixels
   *                      are to be reduced to.
   * @throws IllegalArgumentException for invalid number of seeds value.
   */
  public void mosaicImage(int numberOfSeeds) throws IllegalArgumentException;

  /**
   * Method to apply pixelation image chunking to an image by taking input of the
   * number of super pixels the image pixels are to be reduced to.
   * 
   * @param numberOfSuperPixels represents the number of super pixels the image
   *                            pixels are to be reduced to.
   * @throws IllegalArgumentException for invalid number of super pixels value.
   */
  public void pixelateImage(int numberOfSuperPixels) throws IllegalArgumentException;

  /**
   * Method to apply cross stitch pattern image chunking.
   * 
   * @param filename which represents the location of the file to be read.
   * 
   * @throws IOException for invalid filename input.
   */
  public void patternImage(String filename) throws IOException;

  /**
   * Method to save the image pattern generated into a text file by taking input
   * of the destination.
   * 
   * @param filename  which represents the location of the file to be saved.
   * @param flossFile which represents the location of the file containing floss
   *                  colors.
   * @throws IOException for invalid filename input.
   */
  public void savePattern(String filename, String flossFile) throws IOException;

  /**
   * Method to load the image from the given filename.
   * 
   * @param filename which represents the location of the file to be saved.
   * @throws IOException for invalid filename input.
   */
  public void loadImage(String filename) throws IOException;

  /**
   * Method used to retrieve a {@link BufferedImage} from the {@link Image} data.
   * 
   * @return a {@link BufferedImage} generated from the {@link Image} data.
   */
  public BufferedImage getBufferedImage();

  /**
   * Method used to retrieve a {@link BufferedImage} from the original
   * {@link Image} data.
   * 
   * @return a {@link BufferedImage} generated from the original {@link Image}
   *         data.
   */
  public BufferedImage getOriginalImage();

  /**
   * Method used to swap the current dmc color channel with the selected color
   * channel.
   * 
   * @param currentChannel which is the color channel to be replaced.
   * @param newChannel     which is the new color channel to replace.
   * @param flossFile      which is the location of the file containing the dmc
   *                       colors data.
   * @throws IOException for invalid file.
   */
  public void swapColorInPattern(int[] currentChannel, int[] newChannel, String flossFile)
      throws IOException;

  /**
   * Method used to retrieve a map of the available floss colors which are read
   * from the file specified.
   * 
   * @param flossFile which represents the location of the file containing the
   *                  details of dmc floss colors.
   * @return a map containing the symbols as keys and the corresponding color
   *         channels as values.
   * @throws IOException for invalid file.
   */
  public Map<Character, int[]> getAvailableFlossColors(String flossFile) throws IOException;

  /**
   * Method used to generate a cross stitch pattern for an image with the
   * specified custom dmc colors.
   * 
   * @param customDmcColors which is the custom selected dmc colors.
   * @param flossFile       which represents the location of the file containing
   *                        the details of the dmc floss colors.
   * @throws IOException for invalid file.
   */
  public void patternImageCustomDmc(Map<Character, int[]> customDmcColors, String flossFile)
      throws IOException;

  /**
   * Method used to retrieve the {@link ColorChannel} of a {@link Pixel}.
   * 
   * @param row which represents the row number of the pixel in the image.
   * @param col which represents the column numnber of the pixel in the image.
   * @return an instance of {@link ColorChannel} which contains the color channel
   *         of the pixel.
   * @throws IOException for invalid file.
   */
  public ColorChannel getPixelColors(int row, int col) throws IOException;

  /**
   * Method used to remove a dmc color from the image.
   * 
   * @param color     which represents the color to be removed.
   * @param flossFile which is the file containing the details of dmc colors.
   */
  public void removeDmc(int[] color, String flossFile);

}
