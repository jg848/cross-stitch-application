package model.utils;

/**
 * Class representing the 2D points in an image.
 * 
 * @author Jaswin Gumpella
 *
 */
public class Point2D {

  private final int x;
  private final int y;

  /**
   * Constructor for the class taking the x-coordinate and y-coordinate as input.
   * 
   * @param x which represents the x-coordinate.
   * @param y which represents the y-coordinate.
   */
  public Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Method to calculate distance betweeen 2 points.
   * 
   * @param point2D which represents a point.
   * @return double value representing the distance.
   */
  public double calculateDistance(Point2D point2D) {
    if (null == point2D) {
      throw new IllegalArgumentException("Invalid point to calculate distance.");
    }
    double x = Math.pow(this.x - point2D.x, 2);
    double y = Math.pow(this.y - point2D.y, 2);
    return Math.sqrt(x + y);
  }
}
