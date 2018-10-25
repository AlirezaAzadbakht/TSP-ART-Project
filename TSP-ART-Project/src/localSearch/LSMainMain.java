package localSearch;

import IO.*;
import java.io.IOException;

import Draw.DrawLineLS;
import IO.io;

public class LSMainMain {
	
	public static void main(String[] args) throws IOException {
		// RandomGenerate 
		// imageTXT
		
		System.out.println(">> i am up! \n");
		Point[] points = io.GetCity("C:\\Users\\Alireza\\Desktop\\imageTXT.txt");
	
		Local_search LS = new Local_search();
		
		long l = System.currentTimeMillis();
		
		LS.optimise(points);
		io.saveCity(points, "doneTSP");
		
		DrawLineLS.drawME(800, 600, "TSP art", points);
		
		
		double cost=0;
		for ( int i =0 ; i < points.length-1 ;i++)
		{
			Point a= points[i];
			Point b = points[i+1];
			cost = cost+Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
		}
		Point a= points[0];
		Point b = points[points.length-1];
		cost = cost+Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
		System.out.println(cost);
		
		
		
		System.out.printf("Time : "+(System.currentTimeMillis() - l) / 1000d);
		System.err.println("\n>>Done");
	}
}
