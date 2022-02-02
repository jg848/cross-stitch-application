package model;

import model.channel.ColorChannel;
import model.channel.FlossColor;
import model.channel.FlossColorImpl;
import model.image.Image;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Extension of the {@link ImageProcessingModelImpl} class to implement the
 * cross stitch pattern image chunking. Represents the implementations related
 * to cross stitch pattern defined in the interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class CrossStitchPattern extends ImageProcessingModelImpl {

  protected String availablePaletteFileName;
  private final StringBuilder pattern;
  private final Map<Character, ColorChannel> flossColors;

  /**
   * Constructor of the {@link CrossStitchPattern} which takes input of the image
   * to be modified and the location of the available floss color palette.
   * 
   * @param image                    which represents the image on which the
   *                                 operations are to be performed.
   * @param filename                 which represents the location of the file
   *                                 containing the dmc floss colors details.
   */
  public CrossStitchPattern(Image image, String filename) {
    super(image);
    if (null == filename || filename.isBlank()) {
      throw new IllegalArgumentException("Invalid filename.");
    }
    this.availablePaletteFileName = filename;
    this.pattern = new StringBuilder();
    this.flossColors = new HashMap<>();
  }

  /**
   * Method used to generate a cross stitch pattern and apply the available dmc
   * colors to the image.
   * 
   * @throws IOException for invalid file.
   */
  public void patternImage() throws IOException {
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        FlossColor flossColor = new FlossColorImpl(image.getImage()[i][j],
            this.availablePaletteFileName);
        flossColor.modifyFlossColorChannel();
      }
    }
  }

  private void generatePattern() throws IOException {
    Map<Integer, Character> dmcLegendMap = new TreeMap<>();
    pattern.append(image.getWidth());
    pattern.append("x");
    pattern.append(image.getHeight());
    pattern.append("\n");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        FlossColor flossColor = new FlossColorImpl(image.getImage()[i][j],
            this.availablePaletteFileName);
        dmcLegendMap.put(flossColor.findClosestAvailableFloss(),
            flossColor.getLegend(flossColor.findClosestAvailableFloss()));
        flossColors.put(flossColor.getLegend(flossColor.findClosestAvailableFloss()),
            flossColor.getFlossColorChannel(flossColor.findClosestAvailableFloss()));
        pattern.append(flossColor.getLegend(flossColor.findClosestAvailableFloss()));
      }
      pattern.append("\n");
    }

    pattern.append("\nLEGEND:\n");
    dmcLegendMap.keySet().forEach(code -> {
      pattern.append(dmcLegendMap.get(code));
      pattern.append(" ");
      pattern.append("DMC-");
      pattern.append(code);
      pattern.append("\n");
    });
  }

  /**
   * Method used to save a cross stitch pattern to a text file.
   * 
   * @param filename which is the location where the file is to be saved.
   * @throws IOException for invalid file.
   */
  public void savePattern(String filename) throws IOException {
    if (null == filename || filename.isBlank()) {
      throw new IllegalArgumentException("Invalid filename.");
    }
    generatePattern();
    FileWriter fileWriter = new FileWriter(filename);
    fileWriter.append(pattern);
    fileWriter.close();
  }

  /**
   * Method used to get the available floss colors.
   * 
   * @return a map containing the symbols as keys and the corresponding color
   *         channels as values.
   * @throws IOException for invalid file.
   */
  public Map<Character, ColorChannel> getFlossColors() throws IOException {
    if (flossColors.isEmpty()) {
      generatePattern();
    }
    return flossColors;
  }

}