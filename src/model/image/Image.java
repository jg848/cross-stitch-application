package model.image;

/**
 * Interface {@link Image} which contains the details of the {@link Image} like
 * the matrix representation, height and width.
 * 
 * @author Jaswin Gumpella
 *
 */
public interface Image {

  /**
   * Method to get the {@link Image} in a matrix format.
   * 
   * @return a matrix which is the {@link Image}.
   */
  public int[][][] getImage();

  /**
   * Method to get the width of the {@link Image}.
   * 
   * @return a numeric value which represents the width of the {@link Image}.
   */
  public int getWidth();

  /**
   * Method to get the height of the {@link Image}.
   * 
   * @return a numeric value which represents the height of the {@link Image}.
   */
  public int getHeight();

}
