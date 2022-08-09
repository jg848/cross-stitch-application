# Cross-Stitch Application

### About/Overview
Image Processing application which is a full MVC application.
Overview:
  - Has a simple user interface which shows basic instructions on how to use the interface and perform operations.
  - Interface contains **Menu Items** which are **Load Image**, **Save Image**, **Save Pattern to text file**, **Blur Image**, **Sharpen Image**, **Greyscale Image**, **Sepiatone Image**, **Color Dither Image**, **Greyscale Dither Image**, **Mosaic Image**, **Pixelate Image**, **Mosaic Pattern Image** and **Pixelate Pattern Image**.
  - Utilizes a file chooser which can be used to load and save files. 
  - Contains text area which can be used to insert commands.
  - Has different buttons for displaying original image, displaying processed image, reloading an image, customizing the dmc colors for generating patterns, applying the given batch commands, uploading a batch file to execute commands and clearing the text area.
  - Filtering operations can be performed on an image like **_Blurring_** and **_Sharpening_**.
  - Transformation operations can be performed on an image like **_Greyscale_** and **_Sepia Tone_**.
  - Can reduce the color density of the images using _floyd-steinberg_ dithering algorithm to reduce the number of colors used in the image.
  - Can perform image chunking like mosaic image chunking for the given number of seeds, cross stitch pattern image chunking, pixelation image chunking for the given number of super pixels.

### List of features
  - The **_Image_** interface represents an image in the program. **_ImageImpl_** is the implementation class for this interface which implements the operations defined like retrieving image height, image width and a 3D image matrix.
  - The **_ImageModel_** Interface represents the operations that can be performed on the **_Image_**. **_ImageModelImpl_** is an abstract class which is the implementation class of the **_ImageModel_** interface which provides the implementations to the defined operations.
	> **_ImageFilters_** extends the **_ImageModelImpl_** class to implement the filtering operations defined in the **_ImageModel_**.
	>> **_BlurFilter_** and **_SharpenFilter_** are the extensions of **_ImageFilters_** class to apply blur and sharpen filters.
	> **_ImageTransformations_** extends the **_ImageModelImpl_** class to implement the transformation operations defined in the **_ImageModel_**.
	>> **_GreyscaleTransformation_** and **_SepiatoneTransformation_** are extensions of **_ImageTransformations_** class to apply greyscale and sepiatone transformations.
	> **_ImageDensity_** extends the **_ImageModelImpl_** class to implement the density related operations defined in the **_ImageModel_**.
	>> **_ColorDither_** and **_GreyscaleDither_** are the extensions of **_ImageDensity_** class to apply the color dithering and greyscale dithering image density operations.
  - The **_ImageModelUtils_** interface represents the utilities that are provided for the **_ImageModel_** to perform the required operations. Abstract class **_ImageModelUtilsImpl_** is the implementation class for this interface which provides the implementations to the defined operations.
	> **_ImageFiltersUtils_** extends the abstract **_ImageModelUtilsImpl_** class to implement the filtering operation utilities defined in the **_ImageModelUtils_**.
	> **_ImageTransformationsUtils_** extends the abstract **_ImageModelUtilsImpl_** class to implement the transformation operation utilities defined in the **_ImageModelUtils_**.
	> **_ImageDensityUtils_** extends the abstract **_ImageModelUtilsImpl_** class to implement the density related operation utilities defined in the **_ImageModelUtils_**.
  - The **_Pixel_** interface represents a pixel in the image. **_PixelImpl_** class is the implementation class which implements the operations defined in the interface. Can provide details of the pixel like the row number, column number and color channels. Also used to modify the pixel based on the operations of the **_ImageModel_** and clamp the values as required.
  - The **_ColorChannel_** interface represents the color channels of a pixel in an image which has 3 color values **_RED, GREEN and BLUE_**. **_ColorChannelImpl_** is the implementation class of this interface which implements the operations defined in the interface. Can provide the details of the color channels, set the color channels of a pixel and find a color closest in the palette and clamp the color values accordingly for a given color.
	> **_FlossColor_** is the extension of **_ColorChannel_** class to represent the floss colors and **_FlossColorImpl_** is its implementation class which extends the **_ColorChannelImpl_** class.
  - The **_ImageProcessingModel_** interface is an extension to image model to implement image chunking. **_ImageProcessingModelImpl_** is the implementation class which extends the **_ImageModelImpl_** class.
  - The **_ImagePattern_** extends the **_ImageProcessingModelImpl_** class to implement the image chunking operations defined in the **_ImageProcessingModel_**.
	> **_CrossStitchPattern_** is the class for implementing pattern image chunking.
	>>>**_CustomCrossStitchPattern_** is the extension of **_CrossStitchPattern_** class which implements the DMC Floss Color related operations defined in **_ImageProcessingModel_** class.
	> **_ImageMosaic_** is the class for implementing mosaic image chunking for the given number of seeds.
	> **_ImagePixelation_** is the class for implementing pixelation image chunking for the given number of super pixels.
  - The **_ImageController_** is the controller interface which passes the control to the respective classes. **_ImageControllerImpl_**	is the implementation class. The Controller class takes the input of the command given and passes the control to the respective class declared in the **_Commands_** package.
  - The **_Command_** interface is the class which represents a command to be given to the controller. The interface contains the operations to be supported by a command when the control is passed into it.
	> **_Blur_** is the implementation of **_Command_** which executes the blur filtering operation.
	> **_Sharpen_** is the implementation of **_Command_** which executes the sharpen filtering operation.
	> **_Greyscale_** is the implementation of **_Command_** which executes the greyscale transformation operation.
	> **_Sepia_** is the implementation of **_Command_** which executes the sepiatone transformation operation.
	> **_ColorDither_** is the implementation of **_Command_** which executes the color dithering operation for the given number of colors.
	> **_GreyscaleDither_** is the implementation of **_Command_** which executes the greyscale dithering operation for the given number of colors.
	> **_Mosaic_** is the implementation of **_Command_** which executes the mosaic image chunking operation for the given number of seeds.
	> **_Pixelate_** is the implementation of **_Command_** which executes the pixelation image chunking operation for the given number of super pixels.
	> **_Pattern_** is the implementation of **_Command_** which executes the cross stitch pattern image chunking operation.
	> **_Save_** is the implementation of **_Command_** which executes the save operation for the given destination filename.
	> **_Load_** is the implementation of **_Command_** which executes the load operation for the given source filename.
  - The **_ImageControllerInteractive_** is the extension of **_ImageControllerImpl_** class and the implementation class of **_ImageFeatures_** interface which contains the operations to be supported by the view of the application.
  - **_ImageView_** interface represents the view of the application and contains the operations to be supported by the implementation classes.
	> **_ImageViewImpl_** is the implementation class of the **_ImageView_** interface and contains the user interface related definitions of the application.
	> **_ImagePanel_** is an extension of **_JPanel_** which represents a panel containing the view implementation for the image to be displayed.
	> **_DmcColorsWindow_** is an extension of the **_JFrame_** which represents the window responsible for displaying DMC Floss Colors related operations.
	> **_DmcColorPanel_** is an extension of the **_JPanel_** which represents the view of the dmc floss colors panel to the user.
	> **_CrossStitchPatternPanel_** is an extension of **_JFrame_** and represents the generation of cross stitch pattern for the colors displayed throw the view panels.
  
### How To Run
  Instructions to run the **_JAR_** file provided in the **_res/_** folder
  > Use: **_java -jar .\ImageController.jar -script test.txt_** for executing a batch file through command line or use **_java -jar .\ImageController.jar -interactive_** in the command prompt(Windows) for starting the GUI defined for the application.
  > **jdk-11 Java version** is used to compile and build the application.

### How to Use the Program
  > The **_Main_** class has a **_main_** function that can control the user input through command line and demonstrate all the features of **_ImageModel_** and **_ImageProcessingModel_** through script or interactively.
  > It can take a text file which contains the commands to execute and perform the given operations.
  > The user can interact with the program by utilizing the user interface created.
  > **load** command with filename loads an image.
  > **blur** command applies the blur filter.
  > **sharpen** command applies the sharpen filter.
  > **greyscale** command applies the greyscale transformation.
  > **sepia** command applies the sepiatone transformation.
  > **colordither** command with the number of colors applies the color dithering.
  > **greyscaledither** command with the number of colors applies the greyscale dithering.
  > **mosaic** command with the number of seeds applies the mosaic image chunking.
  > **pixelate** command with the number of super pixels applies the pixelation image chunking.
  > **pattern** command applies the cross stitch pattern image chunking.
  > **save** command with the destination filename saves the image.
  
  > For the interactive user interface after the program is loaded, the basic instructions can be seen on the right side which is the image panel where the output is shown.
  > The interface contains **File** menu under which the user can **Load**, **Save** and **Save pattern to text**.
  > It also contains a **Features** menu under which there are **Blur**, **Sharpen**, **Greyscale**, **Sepiatone**, **Color Dither**, **Greyscale Dither**, **Mosaic**, **Pixelate**, **Mosaic Pattern** and **Pixelate Pattern** which are the features of the application and can be selected to perform the selected operation.
  > On the interface we have buttons **Display Original Image**, **Display Processed Image** and **Reload Original Image** which can be used to display the related image.
  > We also have a **Text Area** to input commands. User can enter the commands in this area and click on **Apply** button to execute the commands and can click on **Clear** button to clear the text area.
  > **Upload Batch File** button can be clicked to upload a batch file containing the commands to execute.
  > On loading the image, **Customize DMC Colors** button is made visible and can be clicked to perform cross stitch patter related operations.
  > Clicking the **Customize DMC Colors** button will display a popup window of the DMC colors. In this window we have the DMC colors printed which are all buttons and can be clicked and selected. After selecting the color buttons click on the **Generate Pattern with selected colors** at the bottom to create a cross stitch pattern with the selected colors.
  > After clicking **Generate Pattern with selected colors**, a new popup window is displayed with the pixels of the image shown as buttons which can all be clicked to perform two further operations which are to **Swap** a color and **Remove** a color from the image. 
  > On clicking **Remove Color** the selected color is removed from image and the colors with symbols are displayed again.
  > On clicking **Swap Color** the color panel is displayed and two other buttons **Swap Color** and **Confirm** are displayed. Clicking on the dmc color button, then clicking on swap color button and finally on Confirm will swap the colors in the image and display the image with symbols and colors as buttons.

### Description of Example Run

  - Executing the command **_java -jar ImageController.jar -script test.txt_**, we have the below output:
  > Loading image., which shows that the image has been loaded successfully.
  > load successful, which shows that the loading of the image is successful.
  > blur successful, which shows that the blur filter has been applied.
  > Saving to file successful, which shows that the image has been saved with the given filename.
  > load successful, which shows that the loading of the image is successful.
  > sharpen successful, which shows that sharpen filter has been applied.
  > Saving to file successful, which shows that the image has been saved with the given filename.
  > load successful, which shows that the loading of the image is successful.
  > greyscale successful, which shows that greyscale transformation has been applied.
  > Saving to file successful, which shows that the image has been saved with the given filename.
  > load successful, which shows that the loading of the image is successful.
  > sepia successful, which shows that sepiatone transformation has been applied.
  > Saving to file successful, which shows that the image has been saved with the given filename.
  > load successful, which shows that the loading of the image is successful.
  > colordither successful, which shows that color dithering has been applied.
  > Saving to file successful, which shows that the image has been saved with the given filename.
  > load successful, which shows that the loading of the image is successful.
  > greyscaledither successful, which shows that greyscale dithering has been applied.
  > Saving to file successful, which shows that the image has been saved with the given filename.
  > load successful, which shows that the loading of the image is successful.
  > mosaic successful, , which shows that mosaic image chunking has been applied.
  > Saving to file successful, which shows that the image has been saved with the given filename.
  > load successful, which shows that the loading of the image is successful.
  > pixelate successful, which shows that pixelation image chunking has been applied.
  > Saving to file successful, which shows that the image has been saved with the given filename.
  > load successful, which shows that the loading of the image is successful.
  > pattern successful,, which shows that cross stitch pattern image chunking has been applied.
  > Saving to text successful, which shows that the image has been saved with the given filename.
  
  - Executing the command **_java -jar ImageController.jar -interactive_** will render the user interface and the screenshots can be found in the **\\res** folder.

### Design/Model Changes
> Methods of the classes that implement the functionalities have been modified and few helper methods have been included.
> View has been broken down and implemented in multiple classes.
> A new class has been added to implement the custom DMC colors related operations and few additions to the ImageProcessingModel has been done.

### Assumptions
> Filename has to be a valid string for reading an image.

> For dithering the image, the number of colors per channel has to be positive.

> Filter matrix for filtering an image has to be of odd dimensions.

> Number of seeds for applying Mosaic has to be valid.

> Number of super pixels for applying pixelation has to be less than any of the dimensions of the image.

> Valid filename has to be given with the commands for saving the files.

> Input while uploading batch file should be without blank lines.

### Limitations
> Images used to test the program are of size less than 40kb.

> For filtering and transformation operations, the amount of the operation cannot be given and the operations are to be done on the modified images multiple times.

> The user does not have any message shown while the operations are being performed. The loading implementation could not be accomplished and the user has to wait till the output is visible.

> While using the custom dmc colors, to use swap color user has to click on color first then on swap color button and then on confirm.

### Citations
> These images are my own and I authorize to use these images as part of this project.
