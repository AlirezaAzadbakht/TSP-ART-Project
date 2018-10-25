/*

public class Dither {

    private String src;
    private int width;
    private int height;
    private int[][] red;
    private int[][] green;
    private int[][] blue;
    private BufferedImage image;

    Dither(String src,int width, int height) {
        this.src = src;
        this.width = width;
        this.height = height;
        this.red = new int[width][height];
        this.green = new int[width][height];
        this.blue = new int[width][height];

    }
    public void readImage() {
        File f = null;
        try {
            System.out.println(this.src);
            f = new File(this.src);
            this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
            this.image = ImageIO.read(f);
            System.out.println("read file ok ..");

        }catch (Exception e) {
            System.out.println("we have exception during reading file .. ");
            System.out.println(e);

        }
    }

    public void fill_RGB_Matrix() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0 ; j < this.height; j++) {
                Color pixcelColor = new Color(image.getRGB(i,j));
                this.red[i][j] = pixcelColor.getRed();
                this.blue[i][j] = pixcelColor.getGreen();
                this.green[i][j] = pixcelColor.getBlue();
                System.out.println(pixcelColor);

            }
        }
    }

    public void graScalImage(String src) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                int color = (this.red[i][j] + this.green[i][j] + this.blue[i][j] ) / 3;
                this.image.setRGB(i,j,color);
            }
        }

        File f = null;
        try {
            f = new File(src);
            ImageIO.write(this.image,"png",f);
            System.out.println("ok ...");

        }catch (Exception e) {
            System.out.println("Error");
            System.out.println(e);
        }
    }
}
*/
package IO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageProcessor {

	
	private BufferedImage img;

	
	public ImageProcessor(String filename) {
	
		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.out.println("Error! Image \"" + filename + "\" not found!");
		}
	}

	public int getwidth() {
		return img.getWidth();
	}

	public int getheight() {
		return img.getHeight();
	}

	
	public BufferedImage resize(int maxSide) {
		// determine new dimensions:
		int width = maxSide, height = maxSide;
		if (img.getWidth() > img.getHeight())
			height = (int) (((double) maxSide / img.getWidth()) * img.getHeight());
		else if (img.getWidth() <= img.getHeight())
			width = (int) (((double) maxSide / img.getHeight()) * img.getWidth());

		
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics tmpGraphics = resized.createGraphics();
		tmpGraphics.drawImage(img, 0, 0, width, height, null);
		tmpGraphics.dispose();

		
		return img = resized;
	}

	
	// Floyd-Steinberg dithering algorithm
	
	private int[][] ditherBW() {
		// create and fill in matrix with integer magnitudes
		int[][] ditheredImg = new int[img.getHeight()][img.getWidth()];
		for (int y = 0; y < ditheredImg.length; y++)
			for (int x = 0; x < ditheredImg[y].length; x++) {
				Color pixel = new Color(img.getRGB(x, y));
				
				ditheredImg[y][x] = (pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3;
			}

		
		for (int y = 0; y < ditheredImg.length; y++) {
			for (int x = 0; x < ditheredImg[y].length; x++) {
				int oldVal = ditheredImg[y][x];
				int newVal = 255;
				if (oldVal < 128)
					newVal = 0;
				ditheredImg[y][x] = newVal;
				int error = oldVal - newVal;
				if (x < ditheredImg[y].length - 1)
					ditheredImg[y][x + 1] += (int) ((5.0 / 16) * error);
				if (x > 1 && y < ditheredImg.length - 1)
					ditheredImg[y + 1][x - 1] += (int) ((5.0 / 16) * error);
				if (y < ditheredImg.length - 1)
					ditheredImg[y + 1][x] += (int) ((5.0 / 16) * error);
				if (x < ditheredImg[y].length - 1 && y < ditheredImg.length - 1)
					ditheredImg[y + 1][x + 1] += (int) ((1.0 / 16) * error);
			}
		}
		return ditheredImg;
	}

	public int[][] matrixOfImage() {
		int[][] matrix;
		matrix = this.ditherBW();
		
		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {
				if (matrix[i][j] == 0) {
					matrix[i][j] = 1;
				} else {
					matrix[i][j] = 0;
				}
				// System.out.print(matrix[i][j] + "");
			}
			// System.out.print("\n");
		}
		return matrix;
	}

	public void showImage() {
		int[][] matrix = this.ditherBW();

		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {

				if (matrix[i][j] == 0) {
					img.setRGB(i, j, new Color(0, 0, 0).getRGB());
				} else {
					img.setRGB(i, j, new Color(255, 255, 255).getRGB());
				}
			}
		}

		try {
			File file = new File("C:\\Users\\Alireza\\Desktop\\done1.jpg");
			ImageIO.write(this.img, "jpg", file);
			System.out.println("write image ok ...");
		} catch (Exception e) {
			System.out.println("Error" + e);
		}

	}

	public void newshowImage(int[][] data) {
		int[][] matrix = data;

		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {

				if (matrix[j][i] == 0) {
					img.setRGB(j, i, new Color(0, 0, 0).getRGB());
				} else {
					img.setRGB(j, i, new Color(255, 255, 255).getRGB());
				}
			}
		}

		try {
			File file = new File("C:\\Users\\Alireza\\Desktop\\done.jpg");
			ImageIO.write(this.img, "jpg", file);
			System.out.println("write image ok ...");
		} catch (Exception e) {
			System.out.println("Error" + e);
		}

	}

	
	public int[][][] ditherRGB() {
		return null;
	}
}