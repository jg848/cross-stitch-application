package controller;

import controller.commands.Blur;
import controller.commands.ColorDither;
import controller.commands.Command;
import controller.commands.GreyScale;
import controller.commands.GreyscaleDither;
import controller.commands.Load;
import controller.commands.Mosaic;
import controller.commands.Pattern;
import controller.commands.Pixelate;
import controller.commands.Save;
import controller.commands.SaveText;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import model.ImageProcessingModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Implementation class of the {@link ImageController} interface which contains
 * the implementations of the operations to be supported by the controller
 * defined in the interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ImageControllerImpl implements ImageController {
  private Readable input;
  private Appendable output;
  protected Map<String, Function<String, Command>> listOfCommands;

  /**
   * Default constructor for the {@link ImageControllerImpl}.
   */
  public ImageControllerImpl() {
    this.input = null;
    this.output = null;
    initializeListOfCommands();
  }

  /**
   * Constructor for the {@link ImageControllerImpl}.
   * 
   * @param input  which is the command input given to the controller.
   * @param output which represents the output to be given after execution.
   */
  public ImageControllerImpl(Readable input, Appendable output) {
    if (null == input || null == output) {
      throw new IllegalArgumentException("Input to controller is invalid.");
    }
    initializeListOfCommands();
    this.input = input;
    this.output = output;
  }

  private void initializeListOfCommands() {
    this.listOfCommands = new HashMap<>();
    this.listOfCommands.put("load", Load::new);
    this.listOfCommands.put("save", Save::new);
    this.listOfCommands.put("blur", in -> new Blur());
    this.listOfCommands.put("sharpen", in -> new Sharpen());
    this.listOfCommands.put("greyscale", in -> new GreyScale());
    this.listOfCommands.put("sepia", in -> new Sepia());
    this.listOfCommands.put("colordither", in -> new ColorDither(Integer.parseInt(in)));
    this.listOfCommands.put("greyscaledither", in -> new GreyscaleDither(Integer.parseInt(in)));
    this.listOfCommands.put("pixelate", in -> new Pixelate(Integer.parseInt(in)));
    this.listOfCommands.put("mosaic", in -> new Mosaic(Integer.parseInt(in)));
    this.listOfCommands.put("pattern", in -> new Pattern());
  }

  @Override
  public void start(ImageProcessingModel model) throws IOException {
    if (null == model) {
      throw new IllegalArgumentException("Model class cannot be null.");
    }
    execute(this.input, model, this.output);
  }

  protected void execute(Readable input, ImageProcessingModel model, Appendable output)
      throws IOException {
    if (null == input || null == model || null == output) {
      throw new IllegalArgumentException("Invalid parameters.");
    }
    List<String> commands = new ArrayList<>();
    readCommands(input, commands);
    executeCommand(commands, model, output);

  }

  private void readCommands(Readable input, List<String> commands) {
    if (null == input || null == commands) {
      throw new IllegalArgumentException("Commands cannot be empty.");
    }
    Scanner scanner = new Scanner(input);
    while (scanner.hasNextLine()) {
      commands.add(scanner.nextLine());
    }
    scanner.close();
  }

  private void executeCommand(List<String> commands, ImageProcessingModel model, Appendable output)
      throws IllegalArgumentException, NullPointerException, IOException {
    if (null == commands || null == model || null == output) {
      throw new IllegalArgumentException("Invalid inputs to execute commands.");
    }
    for (String commandString : commands) {
      String command = commandString.split(" ")[0];
      if (this.listOfCommands.containsKey(command)) {
        if (commandString.split(" ").length > 1) {
          if (command.equals("save")) {
            saveHelper(commandString, model, output);
          } else {
            model = applyCommand(command, model, commandString.split(" ")[1], output);
          }
        } else {
          model = applyCommand(command, model, "", output);
        }
      } else {
        throw new IllegalArgumentException("Command does not exist.");
      }
    }
  }

  private void saveHelper(String commandString, ImageProcessingModel model, Appendable output)
      throws IOException, NullPointerException {
    if (null == commandString || null == model || null == output) {
      throw new IllegalArgumentException("Invalid inputs to execute save command.");
    }
    if (commandString.split(" ")[1].split("\\.")[1].equals("txt")) {
      try {
        Command saveText = new SaveText(commandString.split(" ")[1]);
        saveText.execute(model);
        output.append("Saving to text successful\n");
      } catch (IOException | NullPointerException e) {
        output.append("Saving to text failed\n");
      }
    } else {
      try {
        Command save = new Save(commandString.split(" ")[1]);
        save.execute(model);
        output.append("Saving to file successful\n");
      } catch (IOException | NullPointerException e) {
        output.append("Saving to file failed\n");
      }
    }
  }

  private ImageProcessingModel applyCommand(String command, ImageProcessingModel model,
      String applyString, Appendable output)
      throws IllegalArgumentException, IOException, NullPointerException {
    if (null == command || null == model || null == applyString || null == output) {
      throw new IllegalArgumentException("Invalid inputs to apply command.");
    }
    try {
      model = this.listOfCommands.get(command).apply(applyString).execute(model);
      output.append(command).append(" successful\n");
    } catch (IllegalArgumentException | IOException | NullPointerException exception) {
      output.append(command).append(" failed\n").append(exception.getMessage());
    }
    return model;
  }

}