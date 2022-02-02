package view;

import controller.ImageFeatures;

/**
 * Interface for the view of the application. Represents the view that interacts
 * with the controller to enable the user to utilize the application.
 * 
 * @author Jaswin Gumpella
 *
 */
public interface ImageView {

  /**
   * Method to set the features made available to the user and define the related
   * operation.
   * 
   * @param feature which is the feature to be set to the view for the user to
   *                utilize.
   */
  public void setFeatures(ImageFeatures feature);
}
