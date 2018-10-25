package IO;
import localSearch.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import MST.testMatrix;
import net.parasec.tsp.impl.Point;

public class io {
	public static Point[] GetCity(String fileDir) throws IOException {
		final ArrayList<Point> points = new ArrayList<Point>();
		File file = new File(fileDir);

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;
		while ((st = br.readLine()) != null) {

			StringTokenizer mytoken = new StringTokenizer(st, ",");
			double x = Double.valueOf(mytoken.nextToken());
			double y = Double.valueOf(mytoken.nextToken());

			points.add(new Point(x, y));
		}

		return points.toArray(new Point[] {});
	}
	
	public static void saveCity(Point[]data, String name)
	{
		TestGenerator.saveText(data, name);
	}

}
