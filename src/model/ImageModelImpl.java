package model;

import model.image.Image;
import model.utils.ImageUtilities;

import java.io.IOException;

/**
 * Abstract implementation class for the {@link ImageModel} interface. Contains
 * the common features required for the operations defined in the
 * {@link ImageModel} interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ImageModelImpl implements ImageModel {

  protected Image image;
  protected Image originalImage;

  /**
   * Default constructor for the abstract implementation class of
   * {@link ImageModel}.
   */
  protected ImageModelImpl() {
    this.image = null;
    this.originalImage = null;
  }

  /**
   * Constructor for the abstract implementation class of {@link ImageModel}.
   */
  protected ImageModelImpl(Image image) {
    if (null == image) {
      throw new IllegalArgumentException("Image is invalid.");
    }
    this.image = image;
    this.originalImage = image;
  }

  @Override
  public void saveImage(String filename) throws IOException {
    checkImage();
    if (null == filename || filename.isBlank()) {
      throw new IllegalArgumentException("Filename cannot be empty.");
    }
    try {
      ImageUtilities.writeImage(this.image.getImage(), this.image.getWidth(),
          this.image.getHeight(), filename);
    } catch (IOException e) {
      throw new IOException("Unable to write image.");
    }
  }

  @Override
  public void blurImage() {
    checkImage();
    ImageFilters imageFilters = new BlurFilter(image);
    imageFilters.blurImage();
    this.image = imageFilters.image;
  }

  @Override
  public void sharpenImage() {
    checkImage();
    ImageFilters imageFilters = new SharpenFilter(image);
    imageFilters.sharpenImage();
    this.image = imageFilters.image;
  }

  @Override
  public void greyscaleImage() {
    checkImage();
    ImageTransformations imageTransformations = new GreyscaleTransformation(image);
    imageTransformations.greyscaleImage();
    this.image = imageTransformations.image;
  }

  @Override
  public void sepiatoneImage() {
    checkImage();
    ImageTransformations imageTransformations = new SepiatoneTransformation(image);
    imageTransformations.sepiatoneImage();
    this.image = imageTransformations.image;
  }

  @Override
  public void colorDitherImage(int numberOfColors) {
    checkImage();
    ImageDensity imageDensity = new ColorDither(image);
    imageDensity.colorDitherImage(numberOfColors);
    this.image = imageDensity.image;
  }

  @Override
  public void greyscaleDitherImage(int numberOfColors) {
    checkImage();
    ImageDensity imageDensity = new GreyscaleDither(image);
    imageDensity.greyscaleDitherImage(numberOfColors);
    this.image = imageDensity.image;
  }

  protected void checkImage() {
    if (null == image) {
      throw new IllegalArgumentException("Image not loaded yet.");
    }
  }

}
