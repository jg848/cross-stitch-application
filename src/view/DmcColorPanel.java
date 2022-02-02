package view;

import controller.ImageFeatures;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * {@link DmcColorPanel} is an extension of {@link JPanel} which represents a
 * panel for displaying the dmc colors to the user.
 * 
 * @author Jaswin Gumpella
 *
 */
public class DmcColorPanel extends JPanel {

  private Map<Character, int[]> customColors;
  private String changedColor;
  private String symbol;
  private JButton selectButton;
  private JButton selectAllButton;
  private JButton changeButton;

  /**
   * Constructor for the {@link DmcColorPanel} which takes an instance of
   * {@link ImageFeatures} to initialize the panel and the view.
   * 
   * @param feature which represents an instance of {@link ImageFeatures}
   *                representing the feature of the application.
   * @throws IOException for invalid file.
   */
  public DmcColorPanel(ImageFeatures feature) throws IOException {
    if (null == feature) {
      throw new IllegalArgumentException();
    }
    buildColorsPanel(feature);
    buildButtons();
    setSelectAction(feature);
    setSelectAllAction(feature);
    setChangeButtonAction();
  }

  private void buildButtons() {
    customColors = new HashMap<>();
    selectButton = new JButton("Select Color");
    this.add(selectButton);
    selectButton.setVisible(false);
    selectAllButton = new JButton("Select All Colors");
    this.add(selectAllButton);
    selectAllButton.setVisible(false);
    changeButton = new JButton("Swap Color");
    this.add(changeButton);
    changeButton.setVisible(false);
  }

  private void setChangeButtonAction() {
    changeButton.addActionListener(l -> {
      changedColor = symbol;
    });
  }

  private void setSelectAllAction(ImageFeatures feature) {
    selectAllButton.addActionListener(l -> {
      try {
        customColors = feature.displayImageFlossColors();
        JOptionPane.showMessageDialog(null, "Colors selected");
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  private void setSelectAction(ImageFeatures feature) {
    selectButton.addActionListener(l -> {
      if (!symbol.isEmpty()) {
        try {
          customColors.put(symbol.charAt(0),
              feature.displayImageFlossColors().get(symbol.charAt(0)));
          JOptionPane.showMessageDialog(null, "Color selected");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  private void buildColorsPanel(ImageFeatures feature) throws IOException {
    Map<Character, int[]> noOfColors = feature.displayImageFlossColors();
    JPanel colorPanel = new JPanel();
    colorPanel.setLayout(new GridLayout(10, 200));
    for (Character legend : noOfColors.keySet()) {
      JButton button = new JButton(String.valueOf(legend));
      button.setFocusable(false);
      button.setBorder(BorderFactory.createLineBorder(Color.black));
      int[] color = noOfColors.get(legend);
      button.setBackground(new Color(color[0], color[1], color[2]));
      colorPanel.add(button);
      setButtonAction(button);
    }
    this.setPreferredSize(new Dimension(1000, 300));
    JScrollPane scrollPane = new JScrollPane(colorPanel);
    this.add(scrollPane);
  }

  private void setButtonAction(JButton button) {
    button.addActionListener(l -> {
      String symbolText = button.getText();
      if (null == symbolText || symbolText.isBlank()) {
        throw new IllegalArgumentException("Invalid symbol.");
      }
      this.symbol = symbolText;
    });
  }

  /**
   * Method to access custom colors of the user.
   * 
   * @return map containing the symbols as keys and the corresponding colors as
   *         values.
   */
  public Map<Character, int[]> getCustomColors() {
    return customColors;
  }

  /**
   * Method used to toggle the visibility of select and change buttons.
   */
  public void setChangeVisible() {
    changeButton.setVisible(true);
    selectButton.setVisible(false);
    selectAllButton.setVisible(false);
  }

  /**
   * Method used to toggle the visibility of select and change buttons.
   */
  public void setSelectVisible() {
    selectButton.setVisible(true);
    selectAllButton.setVisible(true);
    changeButton.setVisible(false);
  }

  /**
   * Method to retrieve the changed symbol.
   * 
   * @return string representing the changed symbol.
   */
  public String getChangedDmc() {
    return this.changedColor;
  }
}
