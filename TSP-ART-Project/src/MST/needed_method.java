package MST;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.text.StyledEditorKit.ForegroundAction;

public class needed_method {


	public static double sqrt(double x) {
		//in saari tare
		double temp = x;
		double xhalf = 0.5d * x;
		long i = Double.doubleToLongBits(x);
		i = 0x5fe6eb50c7b537a9L - (i >> 1);
		x = Double.longBitsToDouble(i);
		x *= (1.5d - xhalf * x * x);
		x *= (1.5d - xhalf * x * x); 
		x *= (1.5d - xhalf * x * x); 
		x *= (1.5d - xhalf * x * x); 
		return temp * x;
	}

	public static int distance(int i, int j, int ii, int jj) {
		int temp = (int) Math.sqrt(Math.pow(i - ii, 2) + Math.pow(j - jj, 2));

		return temp;
	}

	public static int[][] RemoveCity(int[][] data, int x, int y, int removeto) {
		int city = 0;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (data[i][j] == 1) {
					city++;
				}

			}
		}
		System.out.println(city);

		int count = 0;
		int //
		ratio = city / removeto;

		System.out.println(ratio);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (data[i][j] == 1 && count < ratio) {
					data[i][j] = 0;
					count++;
				} else if (count == ratio)
					count = 0;
			}
		}
		city = 0;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (data[i][j] == 1) {
					city++;
				}

			}
		}
		System.out.println(city);
		return data;
	}

	public static void matrixImage(int data[][], int x, int y, String name) {
		BufferedImage img = new BufferedImage(x, y, BufferedImage.TYPE_BYTE_INDEXED);
		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {

				if (data[i][j] == 0) {

				} else {
					try {

						img.setRGB(i, j, new Color(255, 255, 255).getRGB());
						img.setRGB(i, j - 1, new Color(255, 255, 255).getRGB());
						img.setRGB(i, j + 1, new Color(255, 255, 255).getRGB());
						img.setRGB(i + 1, j - 1, new Color(255, 255, 255).getRGB());
						img.setRGB(i + 1, j, new Color(255, 255, 255).getRGB());
						img.setRGB(i + 1, j + 1, new Color(255, 255, 255).getRGB());
						img.setRGB(i - 1, j, new Color(255, 255, 255).getRGB());
						img.setRGB(i - 1, j + 1, new Color(255, 255, 255).getRGB());
						img.setRGB(i - 1, j - 1, new Color(255, 255, 255).getRGB());

					} catch (Exception e) {
						// TODO: handle exception
					}
					// img.setRGB(i,j, new Color(0,0,0).getRGB());
				}
			}
		}
		try {
			File file = new File("C:\\Users\\Alireza\\Desktop\\" + name + ".jpg");
			ImageIO.write(img, "jpg", file);
			System.out.println("write image ok ...");
		} catch (Exception e) {
			System.out.println("Error" + e);
		}
	}

	public static void printMST(int parent[], int n, Long[][] graph, int city) {
		System.out.println("MST\nEdge   Weight");
		for (int i = 1; i < city; i++)
			System.out.println(parent[i] + " - " + i + "    " + graph[i][parent[i]]);
	}

	public static void traceMST(ArrayList<node> data) {
		System.out.println("\n\ntraceMST");
		for (node node : data) {
			System.out.print("children of " + node.id + " { ");
			for (node child : node.children) {
				System.out.print(child.id + " ");
			}
			System.out.println("}");
		}
	}

	public static ArrayList<node> sewPreorders(ArrayList<ArrayList<node>> data) {
		ArrayList<node> result = new ArrayList<>();
		for (ArrayList<node> arr : data) {
			for (node nod : arr) {
				result.add(nod);
			}
		}
		return result;
	}

	public static ArrayList<ArrayList<node>> FixeBucketCordinate(ArrayList<ArrayList<node>> data, int x, int y) {
		for (int k = 0; k < data.size(); k++) {
			for (int i = 0; i < data.get(k).size(); i++) {

				int yB = y / 60 - 1;
				int xB = x / 60 - 1;

				int offX = (int) ((Math.floor(k / yB)) * 60);
				int offY = (k % yB) * 60;
				// System.err.println(k+" "+offX+" "+offY);
				data.get(k).get(i).x += offX;
				data.get(k).get(i).y += offY;

			}
		}

		return data;

	}

	public static void saveText(ArrayList<node> data) {
		try {
			FileWriter writer = new FileWriter("C:\\Users\\Alireza\\Desktop\\Preorders.txt", true);
			for (node nod : data) {
				writer.write(String.valueOf(nod.x) + "," + String.valueOf(nod.y));
				writer.write("\r\n");

			}
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: 2" + e);
		}
	}

	public static void saveImage(BufferedImage img) {
		try {
			// write the image as a PNG
			ImageIO.write(img, "jpg", new File("C:\\Users\\Alireza\\Desktop\\done1.jpg"));
			System.out.println("write completed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void PrintArray(int data[], String title) {
		int i = 0;

		System.out.println("\n\n" + title);
		for (int ii : data) {
			System.out.print(i++ + " ");
		}
		System.out.println("\n");
		for (int ii : data) {
			System.out.print(ii + " ");
		}
		System.out.println();
	}

	public static void PrintArrayList(ArrayList<node> data, String title) {
		int i = 0;

		System.out.println("\n\n" + title);
		for (node ii : data) {
			System.out.print(i++ + " ");
		}
		System.out.println("\n");
		for (node ii : data) {
			System.out.print(ii.id + " ");
		}
		System.out.println();
	}

	public static void PrintMatrix(int data[][], String title) {
		int i = 0;

		System.out.println("\n\n" + title);
		for (int[] is : data) {
			System.out.print(i++ + "\t");
		}
		i = 0;
		System.out.println();

		System.out.println();
		for (int[] is : data) {
			System.out.print(i++ + "\t");
			for (int ii : is) {
				System.out.print(ii + " ");
			}
			System.out.println();
		}
	}
}
