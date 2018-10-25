package MST;

import java.util.ArrayList;

public class testMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 10;
		int y = 11;

		int[][] data = new int[x][y];
		for (int i = 0; i < Math.min(x, y); i++) {
			data[i][i] = 1;
		}

		
		ArrayList<int[][]> temp=GridME(data, x, y);
		int cou = 0 ;
		for (int[][] is : temp) {
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < 1; j++) {
					System.out.println( cou+" "+is[i][j]+" ");
					cou++;
				}
				
			}
			
		}
		
		int [][] tempp=temp.get(99);
		for (int[] is : tempp) {
			for (int i : is) {
				System.out.println(i);
			}
		}
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				System.out.print(data[i][j]);
			}
			System.out.println();
		}

	}

	public static ArrayList<int[][]> GridME(int[][] data, int x, int y) {
		// B = Bucket
		ArrayList<int[][]> Bgrided = new ArrayList<>();
		int Blen = (int) Math.floor(x / 10);
		int Bheg = (int) Math.floor(y / 10);
		System.out.println(Blen+" "+ Bheg);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i < 9 && j < 9) {
					Bgrided.add(new int[Blen][Bheg]);
				} else if (i == 9) {
					Bgrided.add(new int[x - (Blen * 8)][Bheg]);
				} else if (j == 9) {
					Bgrided.add(new int[Blen][y - (Bheg * 8)]);
				} else if (i == 9 && j == 9) {
					Bgrided.add(new int[x - (Blen * 8)][y - (Bheg * 8)]);
				}
			}
		}
		System.out.println(Bgrided.size());

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int d=data[i][j];
				int buckNum=(int)(i/Blen)+(int)(j/Bheg)*10;
			//	if(buckNum>=100)
				//	buckNum-=10;
				
				System.out.println(i+" "+ j+" "+ buckNum+" ");
				Bgrided.get(buckNum)[(int) (i-Math.ceil(i/Blen)*Blen)][(int) (j-Math.ceil(j/Bheg)*Bheg)]=d;
				
			}
		}
		return Bgrided;

	}
}

