package view;

import controller.ImageFeatures;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * Implementation class of the {@link ImageView} which represents the view of
 * the application and contains the implementation of the operations defined in
 * the interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ImageViewImpl implements ImageView {

  private JMenuBar menu;
  private ImagePanel imagePane;
  private JTextArea input;
  private JButton displayOriginalImage;
  private JButton displayProcessedImage;
  private JButton reloadOriginalImage;
  private JButton applyButton;
  private JButton clearButton;
  private JButton loadBatchButton;
  private JButton dmcColorsButton;
  private JMenuItem loadItem;
  private JMenuItem saveItem;
  private JMenuItem saveToTextItem;
  private JMenuItem blurItem;
  private JMenuItem sharpenItem;
  private JMenuItem greyscaleItem;
  private JMenuItem sepiatoneItem;
  private JMenuItem colorDitherItem;
  private JMenuItem greyscaleDitherItem;
  private JMenuItem mosaicItem;
  private JMenuItem pixelateItem;
  private JMenuItem mosaicPatternItem;
  private JMenuItem pixelatePatternItem;
  private JPanel contentPane;
  private BufferedImage image;
  private JFrame frame;
  private JPanel batchButtonsPanel;
  private JPanel textAreaPanel;
  private JPanel imageButtonsPanel;

  /**
   * Default constructor for the {@link ImageViewImpl} class which initializes the
   * view of the application.
   * 
   * @throws IOException for invalid file.
   * 
   */
  public ImageViewImpl() throws IOException {
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBackground(Color.GRAY);
    frame.setTitle("Cross Stitch Application");
    frame.setSize(1800, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    contentPane = new JPanel();
    buildMenuBar();
    buildButtons();
    JPanel inputPane = new JPanel();
    inputPane.setLayout(new GridLayout(3, 1));
    inputPane.add(imageButtonsPanel);
    inputPane.add(textAreaPanel);
    inputPane.add(batchButtonsPanel);
    contentPane.add(inputPane, BorderLayout.WEST);
    contentPane.setLayout(new GridLayout(1, 2));
    buildImageDisplay();

    frame.setJMenuBar(menu);
    frame.add(contentPane);
    frame.pack();
    frame.setVisible(true);
  }

  private void buildButtons() {
    buildImageButtons();
    buildTextArea();
    buildBatchButtons();
  }

  private void buildImageButtons() {
    imageButtonsPanel = new JPanel();
    displayOriginalImage = new JButton("Display Original Image");
    imageButtonsPanel.add(displayOriginalImage);
    displayProcessedImage = new JButton("Display Processed Image");
    imageButtonsPanel.add(displayProcessedImage);
    reloadOriginalImage = new JButton("Reload Original Image");
    imageButtonsPanel.add(reloadOriginalImage);
    dmcColorsButton = new JButton("Customize DMC Colors");
    dmcColorsButton.setFocusable(false);
    dmcColorsButton.setVisible(false);
    imageButtonsPanel.add(dmcColorsButton);
  }

  private void buildBatchButtons() {
    batchButtonsPanel = new JPanel();
    BoxLayout boxlayout = new BoxLayout(batchButtonsPanel, BoxLayout.Y_AXIS);
    batchButtonsPanel.setLayout(boxlayout);
    batchButtonsPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
    applyButton = new JButton("Apply");
    batchButtonsPanel.add(applyButton);
    loadBatchButton = new JButton("Upload Batch File");
    batchButtonsPanel.add(loadBatchButton);
    clearButton = new JButton("Clear");
    batchButtonsPanel.add(clearButton);
  }

  private void buildTextArea() {
    textAreaPanel = new JPanel();
    input = new JTextArea(20, 20);
    input.setEditable(true);
    JLabel instructions = new JLabel("");
    instructions.setText("Type commands here");
    textAreaPanel.setLayout(new GridLayout(2, 1));
    textAreaPanel.add(instructions);
    JScrollPane textScrollPane = new JScrollPane(input);
    textAreaPanel.add(textScrollPane);
  }

  private void buildImageDisplay() throws IOException {
    File file = new File("");
    image = ImageIO.read(new File(file.getAbsoluteFile() + "//home.jpg"));
    imagePane = new ImagePanel(image);
    JScrollPane imageScrollPane = new JScrollPane(imagePane);
    imageScrollPane.setPreferredSize(new Dimension(600, 600));
    contentPane.add(imageScrollPane, BorderLayout.CENTER);
  }

  private void buildMenuBar() {
    menu = new JMenuBar();
    JMenu fileSubmenu = new JMenu("File");
    loadItem = new JMenuItem("Load Picture");
    fileSubmenu.add(loadItem);
    saveItem = new JMenuItem("Save Picture");
    fileSubmenu.add(saveItem);
    saveToTextItem = new JMenuItem("Save Cross Stitch Pattern to Text");
    fileSubmenu.add(saveToTextItem);
    menu.add(fileSubmenu);
    JMenu featureSubmenu = new JMenu("Features");
    blurItem = new JMenuItem("Blur");
    featureSubmenu.add(blurItem);
    sharpenItem = new JMenuItem("Sharpen");
    featureSubmenu.add(sharpenItem);
    greyscaleItem = new JMenuItem("Greyscale");
    featureSubmenu.add(greyscaleItem);
    sepiatoneItem = new JMenuItem("Sepiatone");
    featureSubmenu.add(sepiatoneItem);
    colorDitherItem = new JMenuItem("Color Dither");
    featureSubmenu.add(colorDitherItem);
    greyscaleDitherItem = new JMenuItem("Greyscale Dither");
    featureSubmenu.add(greyscaleDitherItem);
    mosaicItem = new JMenuItem("Mosaic");
    featureSubmenu.add(mosaicItem);
    pixelateItem = new JMenuItem("Pixelate");
    featureSubmenu.add(pixelateItem);
    mosaicPatternItem = new JMenuItem("Mosaic Pattern");
    featureSubmenu.add(mosaicPatternItem);
    pixelatePatternItem = new JMenuItem("Pixelate Pattern");
    featureSubmenu.add(pixelatePatternItem);
    menu.add(featureSubmenu);
    frame.setJMenuBar(menu);
  }

  @Override
  public void setFeatures(ImageFeatures feature) {
    if (null == feature) {
      throw new IllegalArgumentException();
    }
    setLoadAction(feature);
    setSaveAction(feature);
    setBlurAction(feature);
    setSharpenAction(feature);
    setGreyscaleAction(feature);
    setSepiatoneAction(feature);
    setColorDitherAction(feature);
    setGreyscaleDitherAction(feature);
    setMosaicAction(feature);
    setPixelateAction(feature);
    setMosaicPatternAction(feature);
    setPixelatePatternAction(feature);
    setSaveToTextAction(feature);
    setClearAction();
    setLoadBatchAction(feature);
    setApplyAction(feature);
    setDisplayOriginalImageAction(feature);
    setDisplayProcessedImageAction();
    setReloadOriginalImageAction(feature);
    setDmcColorsAction(feature);
  }

  private void setDmcColorsAction(ImageFeatures feature) {
    dmcColorsButton.addActionListener(l -> {
      try {
        DmcColorsWindow window = new DmcColorsWindow(feature);
        window.setSubmitVisible();
        window.toFront();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  private void setReloadOriginalImageAction(ImageFeatures feature) {
    reloadOriginalImage.addActionListener(l -> {
      try {
        showOutput(feature.reloadOriginalImage());
        image = feature.displayImage();
        showImage(image);
      } catch (IOException | IllegalArgumentException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setDisplayProcessedImageAction() {
    displayProcessedImage.addActionListener(l -> {
      showImage(image);
    });
  }

  private void setDisplayOriginalImageAction(ImageFeatures feature) {
    displayOriginalImage.addActionListener(l -> {
      try {
        showImage(feature.displayOriginalImage());
      } catch (IOException | IllegalArgumentException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setApplyAction(ImageFeatures feature) {
    applyButton.addActionListener(l -> {
      String inputText = this.input.getText();
      if (inputText.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please insert commands");
      } else {
        try {
          showOutput(feature.processCommands(inputText));
          image = feature.displayImage();
          showImage(image);
          dmcColorsButton.setVisible(true);
        } catch (IOException e) {
          showOutput(e.getMessage());
        }
      }
    });
  }

  private void setLoadBatchAction(ImageFeatures feature) {
    loadBatchButton.addActionListener(l -> {
      try {
        String filename = getFilename();
        BufferedReader inputFile = new BufferedReader(new FileReader(filename));
        /*
         * StringBuilder commands = new StringBuilder(); Scanner scanner = new
         * Scanner(inputFile); while (scanner.hasNextLine()) {
         * commands.append(scanner.nextLine()); } scanner.close();
         */
        String output = feature.processBatchCommands(inputFile);
        image = feature.displayImage();
        showImage(image);
        showOutput(output);
      } catch (IOException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setClearAction() {
    clearButton.addActionListener(l -> {
      input.setText("");
    });
  }

  private void setSaveToTextAction(ImageFeatures feature) {
    saveToTextItem.addActionListener(l -> {
      String filename = setFilename();
      try {
        showOutput(feature.savePatternToText(filename));
      } catch (IOException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setPixelatePatternAction(ImageFeatures feature) {
    pixelatePatternItem.addActionListener(l -> {
      String inputText = getInput("Please enter number of squares to apply pixelation pattern");
      try {
        feature.pixelateImage(inputText);
        showOutput(feature.patternImage());
        image = feature.displayImage();
        showImage(image);
      } catch (IOException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setMosaicPatternAction(ImageFeatures feature) {
    mosaicPatternItem.addActionListener(l -> {
      String inputText = getInput("Please enter number of seeds to apply mosaic pattern");
      try {
        feature.mosaicImage(inputText);
        showOutput(feature.patternImage());
        image = feature.displayImage();
        showImage(image);
      } catch (IOException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setPixelateAction(ImageFeatures feature) {
    pixelateItem.addActionListener(l -> {
      String inputText = getInput("Please enter number of squares to apply pixelation");
      try {
        showOutput(feature.pixelateImage(inputText));
        image = feature.displayImage();
        showImage(image);
      } catch (IOException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setMosaicAction(ImageFeatures feature) {
    mosaicItem.addActionListener(l -> {
      String inputText = getInput("Please enter number of seeds to apply mosaic");
      try {
        showOutput(feature.mosaicImage(inputText));
        image = feature.displayImage();
        showImage(image);
      } catch (IOException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setGreyscaleDitherAction(ImageFeatures feature) {
    greyscaleDitherItem.addActionListener(l -> {
      String inputText = getInput("Please enter number of colors to apply dithering");
      try {
        showOutput(feature.greyscaleDitherImage(inputText));
        image = feature.displayImage();
        showImage(image);
      } catch (IOException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setColorDitherAction(ImageFeatures feature) {
    colorDitherItem.addActionListener(l -> {
      String inputText = getInput("Please enter number of colors to apply dithering");
      try {
        showOutput(feature.colorDitherImage(inputText));
        image = feature.displayImage();
        showImage(image);
      } catch (IOException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setSepiatoneAction(ImageFeatures feature) {
    sepiatoneItem.addActionListener(l -> {
      try {
        showOutput(feature.sepiatoneImage());
        image = feature.displayImage();
        showImage(image);
      } catch (IOException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setGreyscaleAction(ImageFeatures feature) {
    greyscaleItem.addActionListener(l -> {
      try {
        showOutput(feature.greyscaleImage());
        image = feature.displayImage();
        showImage(image);
      } catch (IOException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setSharpenAction(ImageFeatures feature) {
    sharpenItem.addActionListener(l -> {
      try {
        showOutput(feature.sharpenImage());
        image = feature.displayImage();
        showImage(image);
      } catch (IOException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setBlurAction(ImageFeatures feature) {
    blurItem.addActionListener(l -> {
      try {
        showOutput(feature.blurImage());
        image = feature.displayImage();
        showImage(image);
      } catch (IOException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setSaveAction(ImageFeatures feature) {
    saveItem.addActionListener(l -> {
      String filename = setFilename();
      try {
        showOutput(feature.saveImage(filename));
      } catch (IOException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private void setLoadAction(ImageFeatures feature) {
    loadItem.addActionListener(l -> {
      String filename = getFilename();
      try {
        showOutput(feature.loadImage(filename));
        image = feature.displayImage();
        showImage(image);
        dmcColorsButton.setVisible(true);
      } catch (IOException | IllegalArgumentException e) {
        showOutput(e.getMessage());
      }
    });
  }

  private String getFilename() {
    JFileChooser fileChooser = new JFileChooser("./");
    String filename = "";
    int response = fileChooser.showOpenDialog(null);
    if (response == JFileChooser.APPROVE_OPTION) {
      filename = fileChooser.getSelectedFile().getAbsolutePath();
    }
    return filename;
  }

  private String setFilename() {
    JFileChooser fileChooser = new JFileChooser("./");
    int response = fileChooser.showSaveDialog(null);
    String filename = "";
    if (response == JFileChooser.APPROVE_OPTION) {
      filename = fileChooser.getSelectedFile().getAbsolutePath();
    }
    return filename;
  }

  private void showImage(BufferedImage image) {
    if (null == image) {
      showOutput("Image not loaded.");
      throw new IllegalArgumentException("Image not loaded.");
    }
    imagePane.changeImage(image);
  }

  private String getInput(String message) {
    if (null == message || message.isBlank()) {
      throw new IllegalArgumentException("Invalid message.");
    }
    return JOptionPane.showInputDialog(message);
  }

  private void showOutput(String message) {
    if (null == message || message.isBlank()) {
      throw new IllegalArgumentException("Invalid message.");
    }
    JOptionPane.showMessageDialog(null, message);
  }

}
