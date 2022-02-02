package model;

import model.image.Image;
import model.image.ImageImpl;

/**
 * Class which extends the {@link ImageProcessingModelImpl} class to implement
 * the operations related to pixelation image chunking defined in
 * {@link ImageProcessingModel}. Represents the implementation of the pixelation
 * image chunking related operations defined in the interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ImagePixelation extends ImageProcessingModelImpl {

  /**
   * Constructor for the {@link ImagePixelation} by taking input of the image.
   * 
   * @param image which represents the image on which the operations are to be
   *              performed.
   */
  public ImagePixelation(Image image) {
    super(image);
  }

  @Override
  public void pixelateImage(int numberOfSuperPixels) {
    validateSuperPixels(numberOfSuperPixels);
    int squareSizeWidth = image.getWidth() / numberOfSuperPixels;
    int squareSizeWidthExtra = squareSizeWidth;
    int extraPixelsHorizontal = image.getWidth() % numberOfSuperPixels;
    int numberOfPixelsHeight = image.getHeight() / squareSizeWidth;
    int squareSizeHeight = image.getHeight() / numberOfPixelsHeight;
    int squareSizeHeightExtra = squareSizeHeight;
    int extraPixelsHeight = image.getHeight() % numberOfPixelsHeight;
    int[][][] pixelatedImage = new int[image.getHeight()][image.getWidth()][3];
    int countWidth = 0;
    int countHeight = 0;
    for (int i = 0; i < image.getHeight(); i += squareSizeHeightExtra) {
      for (int j = 0; j < image.getWidth(); j += squareSizeWidthExtra) {
        if (countHeight >= numberOfPixelsHeight - extraPixelsHeight) {
          squareSizeHeightExtra = squareSizeHeight + 1;
        }
        if (countWidth == numberOfSuperPixels) {
          countWidth = 1;
          squareSizeWidthExtra = squareSizeWidth;
        } else {
          if (countWidth < numberOfSuperPixels - extraPixelsHorizontal) {
            squareSizeWidthExtra = squareSizeWidth;
          } else {
            squareSizeWidthExtra = squareSizeWidth + 1;
          }
          countWidth++;
        }
        createSuperPixel(pixelatedImage, i, j, squareSizeHeightExtra, squareSizeWidthExtra);
      }
      countHeight++;
    }
    this.image = new ImageImpl(pixelatedImage);
  }

  private void validateSuperPixels(int numberOfSuperPixels) {
    if (numberOfSuperPixels <= 0) {
      throw new IllegalArgumentException("Incorrect number for super pixels of new image.");
    }
    if (image.getHeight() <= numberOfSuperPixels || image.getWidth() <= numberOfSuperPixels) {
      throw new IllegalArgumentException(
          "Given number of super pixels greater than image dimensions.");
    }
  }

  private void createSuperPixel(int[][][] pixelatedImage, int rowStart, int colStart,
      int squareSizeHeight, int squareSizeWidth) {
    for (int row = rowStart; (row < rowStart + squareSizeHeight
        && row < image.getHeight()); row++) {
      for (int col = colStart; (col < colStart + squareSizeWidth
          && col < image.getWidth()); col++) {
        pixelatedImage[row][col] = image.getImage()[rowStart + squareSizeHeight / 2][colStart
            + squareSizeWidth / 2];
      }
    }
  }
}