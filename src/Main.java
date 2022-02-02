import controller.ImageController;
import controller.ImageControllerImpl;
import controller.ImageControllerInteractive;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main class to initialize controller and the model to start the application.
 */
public class Main {
  /**
   * Main method to run the program.
   *
   * @param args represents the command line arguments.
   */
  public static void main(String[] args) throws IOException {
    if (args[0].equals("-script")) {
      try {
        File file = new File("");
        ImageProcessingModel model = new ImageProcessingModelImpl();
        BufferedReader input = new BufferedReader(
            new FileReader(file.getAbsolutePath() + "\\" + args[1]));
        ImageController controller = new ImageControllerImpl(input, System.out);
        controller.start(model);
      } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if (args[0].equals("-interactive")) {
      ImageProcessingModel model = new ImageProcessingModelImpl();
      ImageController controller = new ImageControllerInteractive();
      controller.start(model);
    } else {
      throw new IllegalArgumentException("Invalid Command");
    }
  }
}
