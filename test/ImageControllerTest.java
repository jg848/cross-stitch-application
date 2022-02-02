import static org.junit.Assert.assertEquals;

import controller.ImageController;
import controller.ImageControllerImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Test class for {@link ImageController}.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ImageControllerTest {
  private ImageController controller;
  private MockImageProcessingModelImpl mockModel;
  private StringBuilder log;

  /**
   * Method to setup the test class with required attributes.
   */
  @Before
  public void setUp() {
    controller = new ImageControllerImpl();
    log = new StringBuilder();
    mockModel = new MockImageProcessingModelImpl(log);
  }

  /**
   * Test method for load command.
   * 
   * @throws IOException for invalid file locations.
   */
  @Test
  public void testLoadCommand() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n", log.toString());
  }

  /**
   * Test method for command with invalid load.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testInvalidLoadCommand() throws IOException {
    Reader in = new StringReader("load res/sample.jpg");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("load failed\n" + "Unable to read file.", log.toString());
  }

  /**
   * Test method for blur command.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testBlurCommand() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "blur");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n" + "mock blur successful\n"
        + "blur successful\n", log.toString());
  }

  /**
   * Test method for sharpen command.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testSharpenCommand() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "sharpen");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n" + "mock sharpen successful\n"
        + "sharpen successful\n", log.toString());
  }

  /**
   * Test method for greyscale command.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testGreyscaleCommand() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "greyscale");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n" + "mock greyscale successful\n"
        + "greyscale successful\n", log.toString());
  }

  /**
   * Test method for sepia command.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testSepiaCommand() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "sepia");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n" + "mock sepia successful\n"
        + "sepia successful\n", log.toString());
  }

  /**
   * Test method for colordither command.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testColorDitherCommand() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "colordither 8");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n" + "mock colordither successful\n"
        + "colordither successful\n", log.toString());
  }

  /**
   * Test method for colordither command.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testColorDitherCommandInvalidArguments() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "colordither -8");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n" + "colordither failed\n"
        + "Invalid number of colors.", log.toString());
  }

  /**
   * Test method for greyscaledither command.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testGreyscaleDitherCommand() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "greyscaledither 8");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n"
        + "mock greyscaledither successful\n" + "greyscaledither successful\n", log.toString());
  }

  /**
   * Test method for greyscaledither command.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testGreyscaleDitherCommandInvalidArguments() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "greyscaledither -8");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n" + "greyscaledither failed\n"
        + "Invalid number of colors.", log.toString());
  }

  /**
   * Test method for mosaic command.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testMosaicCommand() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "mosaic 100");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n" + "mock mosaic successful\n"
        + "mosaic successful\n", log.toString());
  }

  /**
   * Test method for mosaic command.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testMosaicCommandInvalidArguments() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "mosaic -100");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n" + "mosaic failed\n"
        + "Invalid number of seeds.", log.toString());
  }

  /**
   * Test method for pixelate command.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testPixelateCommand() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "pixelate 100");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n" + "mock pixelate successful\n"
        + "pixelate successful\n", log.toString());
  }

  /**
   * Test method for pixelate command.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testPixelateCommandInvalidArguments() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "pixelate -100");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n" + "pixelate failed\n"
        + "Invalid number of squares.", log.toString());
  }

  /**
   * Test method for pattern command.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testPatternCommand() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "pattern");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n" + "mock pattern successful\n"
        + "pattern successful\n", log.toString());
  }

  /**
   * Test method for save command.
   * 
   * @throws IOException for invalid file locations.
   */

  @Test
  public void testSaveImageCommand() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "blur\n" + "save res/sample1_blur.jpg");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals(
        "mock load successful\n" + "load successful\n" + "mock blur successful\n"
            + "blur successful\n" + "mock Saving to file succesful\nSaving to file successful\n",
        log.toString());
  }

  /**
   * Test method for savetext command.
   * 
   * @throws IOException for invalid file locations.
   */
  @Test
  public void testSaveTextCommand() throws IOException {
    Reader in = new StringReader(
        "load res/sample1.jpg\n" + "pattern\n" + "save res/sample1_pattern.txt");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
    assertEquals("mock load successful\n" + "load successful\n" + "mock pattern successful\n"
        + "pattern successful\n" + "mock Saving to text successful\n"
        + "Saving to text successful\n", log.toString());
  }

  /**
   * Test method for invalid command.
   * 
   * @throws IOException for invalid file locations.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommand() throws IOException {
    Reader in = new StringReader("load res/sample1.jpg\n" + "image");
    controller = new ImageControllerImpl(in, log);
    controller.start(mockModel);
  }

}
