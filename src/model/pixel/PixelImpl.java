package model.pixel;

import model.channel.ColorChannel;
import model.image.Image;

/**
 * Implementation class of the {@link Pixel} interface. Represents the
 * {@link Pixel} in an {@link Image} and implements all the operations defined
 * in the interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class PixelImpl implements Pixel {

  private final int row;
  private final int column;
  private final ColorChannel colors;

  /**
   * Contructor for the {@link PixelImpl} class which takes the input of the row,
   * column and colors of an {@link Image} and initializes the attributes of the
   * class.
   * 
   * @param row    which represents the row number of the {@link Pixel} in the
   *               {@link Image}.
   * @param column which represents the column number of the {@link Pixel} in the
   *               {@link Image}.
   * @param colors which represents the {@link ColorChannel} of the {@link Pixel}
   *               in the {@link Image}.
   */
  public PixelImpl(int row, int column, ColorChannel colors) {
    this.row = row;
    this.column = column;
    if (null == colors) {
      throw new IllegalArgumentException("Invalid color channels.");
    }
    this.colors = colors;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getColumn() {
    return this.column;
  }

  @Override
  public int[] modifyPixel(int[][][] kernel, double[][] filter) {
    if (null == kernel || null == filter) {
      throw new IllegalArgumentException("Invalid matrix inputs.");
    }
    int[] newChannel = new int[3];
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel.length; j++) {
        for (int k = 0; k < 3; k++) {
          newChannel[k] += kernel[i][j][k] * filter[i][j];
        }
        this.colors.setColorChannel(newChannel);
      }
    }
    return this.clampValues();
  }

  @Override
  public int[] modifyPixel(double[][] transformation) {
    if (null == transformation) {
      throw new IllegalArgumentException("Invalid matrix input.");
    }
    if (transformation.length == this.colors.getColorChannel().length) {
      int[] newChannel = new int[3];
      for (int i = 0; i < transformation.length; i++) {
        for (int j = 0; j < transformation.length; j++) {
          newChannel[i] += Math.round(this.colors.getColorChannel()[j] * transformation[i][j]);
        }
      }
      this.colors.setColorChannel(newChannel);
    } else {
      throw new IllegalArgumentException("Invalid transformation matrix.");
    }
    return this.clampValues();
  }

  /**
   * Helper method to clamp the values of the {@link ColorChannel}.
   * 
   * @return an array containing the {@link ColorChannel}.
   */
  private int[] clampValues() {
    int[] clamps = new int[3];
    for (int i = 0; i < this.colors.getColorChannel().length; i++) {
      if (this.colors.getColorChannel()[i] < 0) {
        clamps[i] = 0;
      } else if (this.colors.getColorChannel()[i] > 255) {
        clamps[i] = 255;
      } else {
        clamps[i] = this.colors.getColorChannel()[i];
      }
    }
    this.colors.setColorChannel(clamps);
    return this.colors.getColorChannel();
  }

}
