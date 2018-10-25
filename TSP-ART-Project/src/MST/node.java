package MST;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class node {
	static int idi=0;
	public int x;
	public int y;
	int id;
	int parentDistance;
	PriorityQueue<node> children = new PriorityQueue<node>(new myComparator());
	public node(int i , int j) {
		this.x=i;
		this.y=j;
		this.id= idi++;
	}
	void addChild(node child){
		children.add(child);
	}
	int getDistance(){
		return parentDistance;
	}
	
}
class myComparator implements Comparator<node> {
	
	@Override
	public int compare(node a, node b) {
		// TODO Auto-generated method stub
		return Integer.compare(b.parentDistance,(a.parentDistance));
	}
	
}
