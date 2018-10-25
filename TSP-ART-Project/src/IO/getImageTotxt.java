package IO;
import localSearch.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;

import MST.needed_method;
import net.parasec.tsp.impl.Point;

public class getImageTotxt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x ;
		int y ;// age akset afghi bod y add bozorgast
		int[][] data ;
		ImageProcessor b = new ImageProcessor("C:\\Users\\Alireza\\Pictures\\fff.jpg");
		x = b.getheight();
		y = b.getwidth();
		data = new int[b.getheight()][b.getwidth()];
		data = b.matrixOfImage();
		data=needed_method.RemoveCity(data, x, y, 100000);
		
		
		ArrayList<Point> point = new ArrayList<>();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (data[i][j] == 1)
					point.add(new Point((int) i, (int) j));
			}
		}
		saveText(point.toArray(new Point[] {}));
		System.err.println("\n>>Done Image converting Generating");
	}
	public static void saveText(Point[] data) {
		try {
			Files.deleteIfExists(Paths.get("C:\\Users\\Alireza\\Desktop\\ImageTXT.txt"));
		} catch (NoSuchFileException e) {
			System.out.println("No such file/directory exists");
		} catch (DirectoryNotEmptyException e) {
			System.out.println("Directory is not empty.");
		} catch (IOException e) {
			System.out.println("Invalid permissions.");
		}

		System.out.println("Deletion successful.");

		try {
			FileWriter writer = new FileWriter("C:\\Users\\Alireza\\Desktop\\ImageTXT.txt", true);
			for (Point nod : data) {
				writer.write(String.valueOf((int) nod.x) + "," + String.valueOf((int) nod.y));
				writer.write("\r\n");

			}
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: 2" + e);
		}
	}

}
