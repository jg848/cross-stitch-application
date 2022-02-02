package model.image;

import model.utils.ImageUtilities;

import java.io.IOException;

/**
 * Implementation class for the {@link Image} interface. Represents an image and
 * contains operations to get the details of the {@link Image}.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ImageImpl implements Image {

  private int[][][] image;
  private final int width;
  private final int height;

  /**
   * Constructor for the implementation class of {@link Image} which takes input
   * of the file name and initializes the attributes of the class.
   * 
   * @throws IOException if unable to read the file.
   */
  public ImageImpl(String filename) throws IOException {
    if (null == filename || filename.isBlank()) {
      throw new IllegalArgumentException("Filename cannot be empty.");
    }
    try {
      this.image = ImageUtilities.readImage(filename);
      this.width = ImageUtilities.getWidth(filename);
      this.height = ImageUtilities.getHeight(filename);
    } catch (IOException e) {
      throw new IOException("Unable to read file.");
    }
  }

  /**
   * Constructor for the implementation class of {@link Image} which takes an
   * input of integer array that represents the pixels of an image and initializes
   * the attributes of the class.
   *
   * @param image the integer array that represents the pixels of an image.
   */
  public ImageImpl(int[][][] image) {
    if (null == image) {
      throw new IllegalArgumentException("Image matrix cannot be null.");
    }
    this.image = image;
    this.width = image[0].length;
    this.height = image.length;
  }

  @Override
  public int[][][] getImage() {
    return this.image;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

}
