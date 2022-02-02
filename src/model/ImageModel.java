package model;

import model.image.Image;

import java.io.IOException;

/**
 * Interface for the model class of an {@link Image}. Contains the list of all
 * the operations that can be performed on an {@link Image}.
 * 
 * @author Jaswin Gumpella
 *
 */
public interface ImageModel {

  /**
   * Method to apply a blur filter to an {@link Image}.
   */
  public void blurImage();

  /**
   * Method to apply a sharp filter to an {@link Image}.
   */
  public void sharpenImage();

  /**
   * Method to apply a greyscale transformation to an {@link Image}.
   */
  public void greyscaleImage();

  /**
   * Method to apply a sepiatone transformation to an {@link Image}.
   */
  public void sepiatoneImage();

  /**
   * Method to dither a color {@link Image} with the number of colors provided.
   * 
   * @param numberOfColors which is the number of colors to be reduced to for the
   *                       {@link Image}.
   */
  public void colorDitherImage(int numberOfColors);

  /**
   * Method to dither a greyscale {@link Image} with the number of colors
   * provided.
   * 
   * @param numberOfColors which is the number of colors to be reduced to for the
   *                       {@link Image}.
   */
  public void greyscaleDitherImage(int numberOfColors);

  /**
   * Method to save the {@link Image} after performing the operation.
   * 
   * @param filename which is the destination path to save the {@link Image}.
   * @throws IOException when unable to write the image to the given filename.
   */
  public void saveImage(String filename) throws IOException;

}
