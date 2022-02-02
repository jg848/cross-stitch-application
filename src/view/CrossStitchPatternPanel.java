package view;

import controller.ImageFeatures;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * {@link CrossStitchPatternPanel} is an extension of {@link JFrame} which is
 * responsible to contain the definition of the view of the cross stitch image
 * display to the user.
 * 
 * @author Jaswin Gumpella
 *
 */
public class CrossStitchPatternPanel extends JFrame {

  private String symbol;
  private final Map<Character, int[]> colorPalette;
  private JFrame frame;

  /**
   * Constructor for the {@link CrossStitchPatternPanel} which takes the
   * {@link ImageFeatures} instance as the argument and initializes the view of
   * the frame.
   * 
   * @param feature which is an instance of the {@link ImageFeatures} representing
   *                a feature of the application.
   * @throws IOException for invalid file.
   */
  public CrossStitchPatternPanel(ImageFeatures feature) throws IOException {
    if (null == feature) {
      throw new IllegalArgumentException();
    }
    frame = new JFrame();
    JPanel imagePanel = new JPanel();
    colorPalette = feature.displayImageFlossColors();
    BufferedImage image = feature.displayImage();
    imagePanel.setLayout(new GridLayout(image.getHeight(), image.getWidth()));
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int[] color = feature.getPixelColors(i, j);
        Character legend = getLegendForColor(color);
        JButton button = new JButton(String.valueOf(legend));
        button.setPreferredSize(new Dimension(0, 0));
        button.setFocusable(false);
        button.setBackground(new Color(color[0], color[1], color[2]));
        imagePanel.add(button);
        setButtonAction(button, feature);
      }
    }
    frame.setPreferredSize(new Dimension(800, 600));
    JScrollPane scrollPane = new JScrollPane(imagePanel);
    frame.add(scrollPane);
    frame.pack();
    frame.setVisible(true);
  }

  private Character getLegendForColor(int[] color) {
    if (null == color) {
      throw new IllegalArgumentException();
    }
    if (color[0] == 255 && color[1] == 255 && color[2] == 255) {
      return '.';
    } else {
      Character legend = '.';
      for (Character c : colorPalette.keySet()) {
        int[] colorChannel = colorPalette.get(c);
        if (colorChannel[0] == color[0] && colorChannel[1] == color[1]
            && colorChannel[2] == color[2]) {
          legend = c;
        }
      }
      return legend;
    }
  }

  private void setButtonAction(JButton button, ImageFeatures feature) {
    button.addActionListener(l -> {
      String symbolText = button.getText();
      this.symbol = symbolText;
      String[] options = { "Swap Color", "Remove Color" };
      int option = JOptionPane.showOptionDialog(null,
          "Select whether you want to remove pixel or swap it", "Pattern Type",
          JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, 0);
      if (option == 0) {
        try {
          DmcColorsWindow window = new DmcColorsWindow(feature);
          window.setConfirmVisible();
          window.setCurrentSymbol(this.symbol);
          window.getDmcPanel().setChangeVisible();
          frame.dispose();
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else if (option == 1) {
        try {
          int[] removedColor = colorPalette.get(symbolText.charAt(0));
          feature.removeDmc(removedColor);
          new CrossStitchPatternPanel(feature);
          frame.dispose();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

}
