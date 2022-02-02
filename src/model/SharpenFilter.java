package model;

import model.image.Image;

/**
 * This class extends the {@link ImageFilters} class which extends the abstract
 * {@link ImageModelImpl} class and implements the {@link ImageModel} interface.
 * Contains the operations related to the sharpen filtering of an image defined
 * in the {@link ImageModel} interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class SharpenFilter extends ImageFilters {

  /**
   * Constructor for the {@link SharpenFilter} class which takes the {@link Image}
   * as argument to provide the model with the {@link Image} that has to be
   * operated on.
   * 
   * @param image which is the image on which the filter is to be applied.
   */
  public SharpenFilter(Image image) {
    super(image);
  }

  @Override
  public void sharpenImage() {
    double[][] sharpenFilterMatrix = { { -0.125, -0.125, -0.125, -0.125, -0.125 },
        { -0.125, 0.25, 0.25, 0.25, -0.125 }, { -0.125, 0.25, 1, 0.25, -0.125 },
        { -0.125, 0.25, 0.25, 0.25, -0.125 }, { -0.125, -0.125, -0.125, -0.125, -0.125 } };
    this.filterImage(sharpenFilterMatrix);
  }

}
