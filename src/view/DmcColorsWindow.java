package view;

import controller.ImageFeatures;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * {@link DmcColorsWindow} which extends the {@link JFrame} and represents the
 * window for the dmc colors related operations.
 * 
 * @author Jaswin Gumpella
 *
 */
public class DmcColorsWindow extends JFrame {
  private final JFrame frame;
  private final DmcColorPanel dmcPanel;
  private JPanel buttonsPanel;
  private JButton submitButton;
  private JButton confirmButton;
  private Map<Character, int[]> dmcPalette;
  private String currentSymbol;
  private String newSymbol;

  /**
   * Constructor for the {@link DmcColorsWindow} which takes an instance of
   * {@link ImageFeatures} to initialize the view for the window.
   * 
   * @param feature which is an instance of {@link ImageFeatures} representing a
   *                feature of the application.
   * @throws IOException for invalid file.
   */
  public DmcColorsWindow(ImageFeatures feature) throws IOException {
    if (null == feature) {
      throw new IllegalArgumentException();
    }
    frame = new JFrame();
    dmcPanel = new DmcColorPanel(feature);
    dmcPanel.setSelectVisible();
    dmcPalette = new TreeMap<>();
    buildButtons();
    setConfirmAction(feature);
    setSubmitAction(feature);

    frame.setLayout(new BorderLayout());
    JScrollPane scrollPane = new JScrollPane(dmcPanel);
    frame.setSize(1000, 800);
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.add(buttonsPanel, BorderLayout.AFTER_LAST_LINE);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    JOptionPane.showMessageDialog(null,
        "Click on the desired DMC color and click on Select. "
            + "User can repeat this process to have multiple colors. "
            + "Click Generate Pattern with selected colors "
            + "button to generate custom cross stitch patten");
  }

  private void setSubmitAction(ImageFeatures feature) {
    submitButton.addActionListener(l -> {
      this.dmcPalette = dmcPanel.getCustomColors();
      try {
        feature.patternImageWithCustomDmc(dmcPalette);
        new CrossStitchPatternPanel(feature);
        frame.dispose();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  private void setConfirmAction(ImageFeatures feature) {
    confirmButton.addActionListener(l -> {
      this.newSymbol = dmcPanel.getChangedDmc();
      try {
        feature.swapDmc(currentSymbol, newSymbol);
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        new CrossStitchPatternPanel(feature);
      } catch (IOException e) {
        e.printStackTrace();
      }
      frame.dispose();
    });
  }

  private void buildButtons() {
    buttonsPanel = new JPanel();
    submitButton = new JButton("Generate Pattern with selected colors");
    confirmButton = new JButton("Confirm");
    buttonsPanel.add(submitButton);
    buttonsPanel.add(confirmButton);
    submitButton.setBounds(100, 100, 20, 10);
    submitButton.setVisible(false);
    confirmButton.setVisible(false);
  }

  /**
   * Method to retrieve the {@link DmcColorPanel} instance.
   * 
   * @return an instance of {@link DmcColorPanel}.
   */
  public DmcColorPanel getDmcPanel() {
    return dmcPanel;
  }

  /**
   * Method used to assign the given symbol as the current symbol.
   * 
   * @param symbol which represents a symbol in the legend of the dmc color
   *               palette.
   */
  public void setCurrentSymbol(String symbol) {
    if (null == symbol || symbol.isBlank()) {
      throw new IllegalArgumentException();
    }
    this.currentSymbol = symbol;
  }

  /**
   * Method used to toggle the visibility of the confirm and submit buttons.
   */
  public void setConfirmVisible() {
    confirmButton.setVisible(true);
    submitButton.setVisible(false);
  }

  /**
   * Method used to toggle the visibility of the confirm and submit buttons.
   */
  public void setSubmitVisible() {
    confirmButton.setVisible(false);
    submitButton.setVisible(true);
  }
}
