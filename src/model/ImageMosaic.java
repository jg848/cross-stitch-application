package model;

import model.channel.ColorChannelImpl;
import model.image.Image;
import model.image.ImageImpl;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.utils.Point2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class which extends the {@link ImageProcessingModelImpl} class to implement
 * the operations related to mosaic image chunking defined in
 * {@link ImageProcessingModel}. Represents the implementation of the mosaic
 * image chunking related operations defined in the interface.
 * 
 * @author Jaswin Gumpella
 *
 */
public class ImageMosaic extends ImageProcessingModelImpl {

  /**
   * Constructor of the {@link ImageMosaic} class which takes input of the image.
   * 
   * @param image which represents the image on which the operations are to be
   *              performed.
   */
  public ImageMosaic(Image image) {
    super(image);
  }

  @Override
  public void mosaicImage(int numberOfSeeds) {
    if (numberOfSeeds < 1 || numberOfSeeds > this.image.getHeight() * this.image.getWidth()) {
      throw new IllegalArgumentException("Invalid number of seeds.");
    }
    int[][][] mosaicImage = new int[image.getHeight()][image.getWidth()][3];
    List<Pixel> seeds = generateSeeds(numberOfSeeds);
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Point2D pixel = new Point2D(i, j);
        List<Double> distances = new ArrayList<>();
        for (Pixel seed : seeds) {
          distances.add(pixel.calculateDistance(new Point2D(seed.getRow(), seed.getColumn())));
        }
        Pixel mosaicSeed = seeds.get(distances.indexOf(Collections.min(distances)));
        mosaicImage[i][j] = image.getImage()[mosaicSeed.getRow()][mosaicSeed.getColumn()];
      }
    }
    this.image = new ImageImpl(mosaicImage);
  }

  /**
   * Helper method to generate random seeds.
   * 
   * @param numberOfSeeds which represents the number of seeds to be generated.
   * @return a list of type {@link Pixel} which contains the generated seeds.
   */
  private List<Pixel> generateSeeds(int numberOfSeeds) {
    if (numberOfSeeds < 1 || numberOfSeeds > this.image.getHeight() * this.image.getWidth()) {
      throw new IllegalArgumentException("Invalid number of seeds.");
    }
    List<Pixel> seeds = new ArrayList<>();
    Random rand = new Random();
    while (seeds.size() < numberOfSeeds) {
      int row = rand.nextInt(image.getHeight());
      int column = rand.nextInt(image.getWidth());
      Pixel pixel = new PixelImpl(row, column, new ColorChannelImpl(image.getImage()[row][column]));
      if (seeds.isEmpty()) {
        seeds.add(pixel);
      } else {
        for (Pixel p : seeds) {
          if (p.getRow() != pixel.getRow() || p.getColumn() != pixel.getColumn()) {
            seeds.add(pixel);
            break;
          }
        }
      }
    }
    return seeds;
  }
}
