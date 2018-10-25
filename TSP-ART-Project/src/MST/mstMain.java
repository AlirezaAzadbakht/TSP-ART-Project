package MST;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

import Draw.DrawLine;

public class mstMain {
	public static int [][]data ;
	static int xx;
	static int yy;
	static int parent[]; 
	static int city=0;
	static ArrayList<node> nodes;
	static ArrayList<node> preorderWalk;
	
	public mstMain(int data[][],int xx,int yy) {
		nodes= new ArrayList<>();
		preorderWalk = new ArrayList<>();
		this.data=data;
		this.xx=xx;
		this.yy=yy;
		primMST(MakeAdjacencyList(data));
		
		readyPreorder();
		makePreorder();
		
		
		DrawLine.drawME(xx, yy, 0, preorderWalk);
		
		
		
		//needed_method.matrixImage(data, xx, yy, "after");
		//needed_method.PrintArrayList(preorderWalk ,"preorderWalk");
		//needed_method.traceMST(nodes);
		//needed_method.PrintArray(parent, "parent");
		//needed_method.PrintMatrix(data, "data");
	}
	
	
	
	
	ArrayList<node> makePreorder ()
	{
		if(city==0)
			return null;
		
		preorderWalk = new ArrayList<>();
		int i=0;
		Stack<node> stack= new Stack<>();
		stack.push(nodes.get(0));
		
		while (stack.isEmpty()==false)
		{
			node temp =stack.pop();
			for (node node : temp.children) {
				stack.push(node);
			}
			preorderWalk.add(temp);
		}
		
		return preorderWalk;
	}
	
    void readyPreorder ()
    {
    	for ( int i = 1 ; i < city ; i++)
    	{
    		node pnode = nodes.get(parent[i]);
    		node cnode = nodes.get(i);
    		cnode.parentDistance= needed_method.distance(pnode.x, pnode.y, cnode.x, cnode.y);
    		pnode.addChild(cnode);
    	}
    }
	
    int minKey(Long[] key, Boolean mstSet[])
    {
        Long min = (long) Integer.MAX_VALUE;
		int min_index=-1;
 
        for (int v = 0; v < city; v++)
            if (mstSet[v] == false && key[v] < min)
            {
                min = key[v];
                min_index = v;
            }
 
        return min_index;
    }
	
	void primMST(Long graph[][])
    {
        parent = new int[city];
        Long key[] = new Long [city];
        Boolean mstSet[] = new Boolean[city];
        for (int i = 0; i < city; i++)
        {
            key[i] = (long) Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        if(city==0)
        	return;
        key[0] = (long) 0;    
        parent[0] = -1;
        for (int count = 0; count < city-1; count++)
        {
        	int u = minKey(key, mstSet);
            mstSet[u] = true;
            for (int v = 0; v < city; v++)
                if (graph[u][v]!=0 && mstSet[v] == false &&
                    graph[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = graph[u][v];
                }
        }
        //needed_method.printMST(parent, city, graph,city);
    }
	
	
    Long[][] MakeAdjacencyList( int[][] data)
	{
    	city=0;
    	nodes = new ArrayList<>();
    	for (int i = 0 ; i < xx ; i++)
		{
			for ( int j = 0 ; j < yy ; j++)
			{
				if (data[i][j]==1)
				{
					city++;
					nodes.add(new node(i, j));
				}
			}
		}
		Long [][]result = new Long [city][city];
		
		for (int i = 0 ; i < city ; i++)
		{
			for ( int j = 0 ; j < city ; j++)
			{
				result [i][j]= (long) needed_method.distance(nodes.get(i).x,nodes.get(i).y ,nodes.get(j).x,nodes.get(j).y);
			}
		}
		
		return result;

	}
    
    public ArrayList<node> getPreorderWalk()
    {
    	return preorderWalk;
    }

}
