import MST.*;


import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

import Draw.DrawLine;
import IO.*;

public class MainMain {

	public static void main(String[] args) {
		int x = 800;
		int y = 600;// age akset afghi bod y add bozorgast
		int[][] data = new int[x][y];
		data[20][20] = 1;
		data[70][70] = 1;
		data[20][70] = 1;
		data[70][20] = 1;
		for (int i = 0; i < 10; i++) {
			data[(int) (Math.random() * x)][(int) (Math.random() * y)] = 1;
		}

		ImageProcessor b = new ImageProcessor("C:\\Users\\Alireza\\Pictures\\finalBench.jpg");
		x = b.getheight();
		y = b.getwidth();
		data = new int[b.getheight()][b.getwidth()];
		data = b.matrixOfImage();
		
		data=needed_method.RemoveCity(data, x, y, 4000);
		
		mstMain a = new mstMain(data, b.getheight(), b.getwidth());
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.err.println(">>Done");
	}
	
	

	public static ArrayList<int[][]> BucketME(int[][] data, int x, int y) {
		int offX = 60 - x % 60;
		int offY = 60 - y % 60;
		int[][] Bdata = new int[x + offX][y + offY];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				Bdata[i][j] = data[i][j];
			}
		}
		System.out.println(x + " " + offX + y);

		x = x + offX;
		y = y + offY;
		System.out.println(x + " " + y);

		ArrayList<int[][]> Bgrided = new ArrayList<>();
		int Blen = x / 60;
		int Bheg = y / 60;
		System.out.println(" " + Blen + " " + Bheg);
		for (int i = 0; i < Blen; i++) {
			for (int j = 0; j < Bheg; j++) {
				Bgrided.add(new int[60][60]);
			}
		}

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int d = Bdata[i][j];
				int buckNum = (int) (i / 60) + (int) (j / 60) * Bheg;
				// System.out.println(i+" "+ j+" "+ buckNum+" ");
				Bgrided.get(buckNum)[i % 60][j % 60] = d;
			}
		}

		return Bgrided;
	}

	public static void BucketType(int[][] data, int x, int y) {
		ArrayList<int[][]> buckets = BucketME(data, x, y);

		int offX = 60 - x % 60;
		int offY = 60 - y % 60;
		x = x + offX;
		y = y + offY;

		// data = layDowncitys(data, x, y);
		ArrayList<ArrayList<node>> Buckets_preorderwalks = new ArrayList<>();
		mstMain a;
		int countbyfar = 0;
		for (int i = 0; i < (x / 60) * (y / 60); i++) {
			System.out.println(countbyfar + " of " + (x * y) + " processed!");
			a = new mstMain(buckets.get(i), 60, 60);
			Buckets_preorderwalks.add(a.getPreorderWalk());
			countbyfar += (60 * 60);
		}

		ArrayList<node> finalPreorder = needed_method
				.sewPreorders(needed_method.FixeBucketCordinate(Buckets_preorderwalks, x, y));
		int i = 0;
		// for ( ArrayList<node> node: Buckets_preorderwalks) {
		// try {
		//
		// DrawLine.drawME(x, y,i ,node);
		// i++;
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
		// }
		needed_method.saveText(finalPreorder);

		DrawLine.drawME(y, x, 0, finalPreorder);
	}

	
}
