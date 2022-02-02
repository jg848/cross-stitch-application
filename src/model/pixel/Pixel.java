package model.pixel;

import model.channel.ColorChannel;
import model.image.Image;

/**
 * Interface for representing the pixels of an {@link Image}. Contains the
 * operations that are to be supported by the {@link Pixel}.
 * 
 * @author Jaswin Gumpella
 *
 */
public interface Pixel {

  /**
   * Method to get the row of the {@link Pixel}.
   * 
   * @return an integer which represents the row of the {@link Pixel} in the
   *         {@link Image}.
   */
  public int getRow();

  /**
   * Method to get the column of the {@link Pixel}.
   * 
   * @return an integer which represents the column of the {@link Pixel} in the
   *         {@link Image}.
   */
  public int getColumn();

  /**
   * Method to modify the {@link Pixel} when given a kernel and a filter matrix to
   * apply to the {@link Image} filtering operation.
   * 
   * @param kernel which is a matrix generated from the {@link Image} matrix and
   *               filter matrix on which the filter matrix is to be applied.
   * @param filter which is the matrix to be applied on the {@link Image} matrix.
   * @return an array containing the modified {@link ColorChannel}.
   */
  public int[] modifyPixel(int[][][] kernel, double[][] filter);

  /**
   * Method to modify the {@link Pixel} when given a transformation matrix to
   * apply to the {@link Image} transformation operation.
   * 
   * @param transformation which is the matrix to be applied on the {@link Image}
   *                       matrix.
   * @return an array containing the modified {@link ColorChannel}.
   */
  public int[] modifyPixel(double[][] transformation);
}
