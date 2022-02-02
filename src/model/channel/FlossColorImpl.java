package model.channel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implementation class of the {@link FlossColor} interface which represents the
 * implementations of the operations defined in the interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class FlossColorImpl extends ColorChannelImpl implements FlossColor {
  private final Map<Integer, Character> legend;
  private final Map<Integer, ColorChannel> colorPalette;
  private final Map<Character, int[]> flossColors;

  /**
   * Constructs a {@link FlossColor} instance with 3 color channels R, G and B by
   * taking input of the color palette.
   *
   * @param colors   represents the RGB values.
   * @param filename represents the name of the file to fetch the color palette.
   * @throws IllegalArgumentException for invalid size of colors.
   */
  public FlossColorImpl(int[] colors, String filename) throws IOException {
    super(colors);
    colorPalette = new TreeMap<>();
    legend = new TreeMap<>();
    flossColors = new HashMap<>();
    if (null == filename || filename.isBlank()) {
      throw new IllegalArgumentException("Invalid filename.");
    }
    this.getColorPalette(filename);
  }

  private void getColorPalette(String filename) throws IOException {
    if (null == filename || filename.isBlank()) {
      throw new IllegalArgumentException("Invalid filename.");
    }
    BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      String[] lineArray = line.split(",");
      Integer dmcCode = Integer.parseInt(lineArray[0]);
      int colorR = Integer.parseInt(lineArray[2]);
      int colorG = Integer.parseInt(lineArray[3]);
      int colorB = Integer.parseInt(lineArray[4]);
      Character symbol = (char) Integer.parseInt(lineArray[5]);
      this.colorPalette.put(dmcCode, new ColorChannelImpl(new int[] { colorR, colorG, colorB }));
      this.legend.put(dmcCode, symbol);
      this.flossColors.put(symbol, new int[] { colorR, colorG, colorB });
    }
    bufferedReader.close();
  }

  @Override
  public Integer findClosestAvailableFloss() {
    return this.findClosestAvailableFlossHelper(colorPalette);
  }

  @Override
  public Integer findClosestAvailableFloss(Map<Character, int[]> colorPalette) {
    if (null == colorPalette) {
      throw new IllegalArgumentException("Color palette is invalid.");
    }
    Map<Integer, ColorChannel> palette = new TreeMap<>();
    for (Character c : colorPalette.keySet()) {
      for (Integer code : legend.keySet()) {
        if (legend.get(code).equals(c)) {
          palette.put(code, new ColorChannelImpl(colorPalette.get(c)));
        }
      }
    }
    return this.findClosestAvailableFlossHelper(palette);
  }

  @Override
  public Character getLegend(int dmcCode) {
    if (dmcCode < 0) {
      throw new IllegalArgumentException("Invalid dmc code.");
    }
    return this.legend.get(dmcCode);
  }

  @Override
  public void modifyFlossColorChannel() {
    setColorChannel(this.colorPalette.get(findClosestAvailableFloss()).getColorChannel());
  }

  @Override
  public ColorChannel getFlossColorChannel(int dmcCode) {
    if (dmcCode < 0) {
      throw new IllegalArgumentException("Invalid dmc code.");
    }
    return this.colorPalette.get(dmcCode);
  }

  @Override
  public Map<Character, int[]> getAvailableFloss() {
    return this.flossColors;
  }

  private Integer findClosestAvailableFlossHelper(Map<Integer, ColorChannel> colorPalette) {
    if (null == colorPalette) {
      throw new IllegalArgumentException("Color palette is invalid.");
    }
    Integer index = (Integer) colorPalette.keySet().toArray()[0];
    double closestDistance = applyMean(this, colorPalette.get(index));
    for (Integer code : colorPalette.keySet()) {
      double newDistance = applyMean(this, colorPalette.get(code));
      if (newDistance < closestDistance) {
        closestDistance = newDistance;
        index = code;
      }
    }
    return index;
  }

  private double applyMean(ColorChannel c1, ColorChannel c2) {
    if (null == c1 || null == c2) {
      throw new IllegalArgumentException("Invalid parameters to apply red mean.");
    }
    double redMean = (c1.getColorChannel()[0] + c2.getColorChannel()[0]) / 2.0;
    double red = (2 + redMean / 256)
        * (Math.abs(c1.getColorChannel()[0] - c2.getColorChannel()[0]));
    double green = Math.pow((Math.abs(c1.getColorChannel()[1] - c2.getColorChannel()[1])), 2) * 4;
    double blue = (2 + (255 - redMean) / 256)
        * Math.pow((Math.abs(c1.getColorChannel()[2] - c2.getColorChannel()[2])), 2);
    return Math.sqrt(red + green + blue);
  }

}
