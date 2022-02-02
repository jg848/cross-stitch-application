package view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * {@link ImagePanel} is an extension of the {@link JPanel} class and represents
 * the view for the image being displayed to the user.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ImagePanel extends JPanel {
  private BufferedImage image;
  private final JLabel label;

  /**
   * Constructor for the {@link ImagePanel} class which takes an instance of the
   * {@link BufferedImage} representing the image being shown to the user.
   * 
   * @param image which represents the image shown to the user.
   */
  public ImagePanel(BufferedImage image) {
    if (null == image) {
      throw new IllegalArgumentException();
    }
    this.image = image;
    label = new JLabel(new ImageIcon(image));
    label.setMinimumSize(new Dimension(10, 10));
    add(label);
  }

  /**
   * Method used to change the displayed image with the given processed image.
   * 
   * @param image which represents the new image to be shown to the user.
   */
  public void changeImage(BufferedImage image) {
    if (null == image) {
      throw new IllegalArgumentException();
    }
    this.image = image;
    label.setIcon(new ImageIcon(this.image));
  }
}
