package IO;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;

import net.parasec.tsp.impl.Point;
import localSearch.*;
public class TestGenerator {
	public static void main(String[] args) {
		int x = 800;
		int y = 600;// age akset afghi bod y add bozorgast
		int[][] data = new int[x][y];
		int iii = 0;
		while (iii < 5000) {
			int tx = (int) (Math.random() * x);
			int ty = (int) (Math.random() * y);
			if (data[tx][ty] == 0) {
				data[tx][ty] = 1;
				iii++;
			}
		}
		
		ArrayList<Point> point = new ArrayList<>();
		for (int i = 0; i < 800; i++) {
			for (int j = 0; j < 600; j++) {
				if (data[i][j] == 1)
					point.add(new Point((int) i, (int) j));
			}
		}
		saveText(point.toArray(new Point[] {}), "RandomGenerate");
		System.err.println("\n>>Done Generating");
	}

	public static void saveText(Point[] data, String name) {
		try {
			Files.deleteIfExists(Paths.get("C:\\Users\\Alireza\\Desktop\\"+name+".txt"));
			System.out.println(">Deletion successful.");
		} catch (NoSuchFileException e) {
			System.out.println(">No such file/directory exists");
		} catch (DirectoryNotEmptyException e) {
			System.out.println(">Directory is not empty.");
		} catch (IOException e) {
			System.out.println(">Invalid permissions.");
		}

		

		try {
			FileWriter writer = new FileWriter("C:\\Users\\Alireza\\Desktop\\"+name+".txt", true);
	
			for (int i = 0 ; i < data.length-1 ; i ++) {
				Point nod = data[i];
				writer.write(String.valueOf((int) nod.x) + "," + String.valueOf((int) nod.y));
				writer.write("\r\n");
			}
			Point nod = data[data.length-1];
			writer.write(String.valueOf((int) nod.x) + "," + String.valueOf((int) nod.y));
			writer.close();
			System.out.println(">Text saved!");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: 2" + e);
		}
	}
}
