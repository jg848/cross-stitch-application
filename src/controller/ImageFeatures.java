package controller;

import model.image.Image;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Interface representing the list of features offered to the user.
 * 
 * @author Jaswin Gumpella
 *
 */
public interface ImageFeatures {

  /**
   * Constant containing the location of a file which has the details of dmc floss
   * colors in it.
   */
  public static final String FLOSS_FILE = new File("").getAbsolutePath() + "\\dmc-floss.csv";

  /**
   * Method used to execute loading of an image from the given filename.
   * 
   * @param filename which represents the file location where the image is
   *                 present.
   * @return string containing the output information of the application. string
   *         containing the output information of the application.
   * @throws IOException for invalid file. for invalid file.
   */
  public String loadImage(String filename) throws IOException;

  /**
   * Method used to save an image to the given location.
   * 
   * @param filename which represents the file location where the image is to be
   *                 saved.
   * @return string containing the output information of the application. string
   *         containing the output information of the application.
   * @throws IOException for invalid file. for invalid file.
   */
  public String saveImage(String filename) throws IOException;

  /**
   * Method used to apply blur filter to the image.
   * 
   * @return string containing the output information of the application.
   * @throws IOException for invalid file.
   */
  public String blurImage() throws IOException;

  /**
   * Method used to apply sharpen filter to the image.
   * 
   * @return string containing the output information of the application.
   * @throws IOException for invalid file.
   */
  public String sharpenImage() throws IOException;

  /**
   * Method used to apply greyscale transformation to the image.
   * 
   * @return string containing the output information of the application.
   * @throws IOException for invalid file.
   */
  public String greyscaleImage() throws IOException;

  /**
   * Method used to apply sepiatone transformation to the image.
   * 
   * @return string containing the output information of the application.
   * @throws IOException for invalid file.
   */
  public String sepiatoneImage() throws IOException;

  /**
   * Method used to apply color dithering to the image for the specified number of
   * colors.
   * 
   * @param numberOfColors which represents the number of colors the image density
   *                       has to be reduced to.
   * @return string containing the output information of the application.
   * @throws IOException for invalid file.
   */
  public String colorDitherImage(String numberOfColors) throws IOException;

  /**
   * Method used to apply greyscale dithering to the image for the specified
   * number of colors.
   * 
   * @param numberOfColors which represents the number of colors the image density
   *                       has to be reduced to.
   * @return string containing the output information of the application.
   * @throws IOException for invalid file.
   */
  public String greyscaleDitherImage(String numberOfColors) throws IOException;

  /**
   * Method used to apply mosaic chunking to the image for the specified number of
   * seeds.
   * 
   * @param numberOfSeeds which represents the random number of seeds the image
   *                      has to contain.
   * @return string containing the output information of the application.
   * @throws IOException for invalid file.
   */
  public String mosaicImage(String numberOfSeeds) throws IOException;

  /**
   * Method used to apply pixelation chunking to the image for the specified
   * number of squares.
   * 
   * @param numberOfSquares which represents the number of squares the image has
   *                        to contain.
   * @return string containing the output information of the application.
   * @throws IOException for invalid file.
   */
  public String pixelateImage(String numberOfSquares) throws IOException;

  /**
   * Method used to generate a cross stitch pattern by using a dmc color map.
   * 
   * @return string containing the output information of the application.
   * @throws IOException for invalid file.
   */
  public String patternImage() throws IOException;

  /**
   * Method used to generate a cross stitch pattern and save into a text file.
   * 
   * @param filename which represents the location where the file is to be saved.
   * @return string containing the output information of the application.
   * @throws IOException for invalid file.
   */
  public String savePatternToText(String filename) throws IOException;

  /**
   * Method used to get the legend for the dmc colors available which contains the
   * symbol and the corresponding color it represents.
   * 
   * @return a map which contains the symbol as the key and the corresponding
   *         color it represents as value.
   * @throws IOException for invalid file.
   */
  public Map<Character, int[]> displayImageFlossColors() throws IOException;

  /**
   * Method used to get a {@link BufferedImage} for the given {@link Image}
   * instance.
   * 
   * @return a {@link BufferedImage} which is generated from the given
   *         {@link Image} data.
   */
  public BufferedImage displayImage();

  /**
   * Method used to process the given commands.
   * 
   * @param batchCommands which represents the list of commands to be executed.
   * @return string containing the output information of the application.
   * @throws IOException for invalid file.
   */
  public String processCommands(String batchCommands) throws IOException;

  /**
   * Method used to retrieve the {@link BufferedImage} instance of the original
   * image stored in the model.
   * 
   * @return a {@link BufferedImage} which is generated from the stored original
   *         {@link Image} data.
   * @throws IOException for invalid file.
   */
  public BufferedImage displayOriginalImage() throws IOException;

  /**
   * Method used to reload the image by executing the load command again.
   * 
   * @return string containing the output information of the application.
   * @throws IOException for invalid file.
   */
  public String reloadOriginalImage() throws IOException;

  /**
   * Method used to swap a dmc color with another selected dmc color from the
   * displayed color panel.
   * 
   * @param currentColor which represents the selected color to be replaced.
   * @param newColor     which represents the new color to replace the selected
   *                     color.
   * @throws IOException for invalid file.
   */
  void swapDmc(String currentColor, String newColor) throws IOException;

  /**
   * Method used to retrieve the color channel of the pixel located at the given
   * position.
   * 
   * @param row which represents the row number of the pixel.
   * @param col which represents the column number of the pixel.
   * @return the color channel of a pixel.
   * @throws IllegalArgumentException for invalid arguments.
   * @throws IOException              for invalid file.
   */
  public int[] getPixelColors(int row, int col) throws IllegalArgumentException, IOException;

  /**
   * Method used to generate a cross stitch pattern from the selected custom dmc
   * colors.
   * 
   * @param customDmcColors which represents a map containing the symbol and the
   *                        corresponding color channel it represents.
   * @throws IOException for invalid file.
   */
  public void patternImageWithCustomDmc(Map<Character, int[]> customDmcColors) throws IOException;

  /**
   * Method used to remove a given color channel from the image.
   * 
   * @param removedColor which represents the color to be removed.
   */
  public void removeDmc(int[] removedColor);

  /**
   * Method to process batch file.
   * 
   * @param inputFile is the batch file to process.
   * @return string containing the output information of the application.
   * @throws IOException for invalid file.
   */
  public String processBatchCommands(BufferedReader inputFile) throws IOException;
}
