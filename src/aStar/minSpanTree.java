package aStar;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;


public class minSpanTree {
	private ArrayList<spanTreeEdge> tree;
	private Comparator<spanTreeEdge> comparator;
	private PriorityQueue<spanTreeEdge> q;
	
	public minSpanTree(int [][] cost, int visited[], int numOfCities){
		tree = new ArrayList<spanTreeEdge>();
		comparator = new distComparator();
		q = new PriorityQueue<spanTreeEdge>(numOfCities, comparator);
		
		for(int i = 0; i < numOfCities; i++){								//TC: we are taking upper triangular matrix
			for(int j = i + 1; j < numOfCities; j++){
				if(visited[i] == 0 && visited[j] == 0){
					spanTreeEdge tempEdge = new spanTreeEdge(i, j, cost[i][j]);
					q.add(tempEdge);
				} 
			}
		}
		for(int i = 0, tempNum = numOfCities; i < tempNum; i++){
			if(visited[i] > 0)
				numOfCities--;
		}
		this.calMinSpanTree(cost, numOfCities);
	}
	
	private void calMinSpanTree(int [][] cost, int numOfCities){
		spanTreeEdge tempEdge;
		int i = 0;
		while((tempEdge = q.poll()) != null  && i < numOfCities-1){
			//System.out.println(tempEdge.getFromNode() + "->" + tempEdge.getToNode() + "(" + tempEdge.getCost()+")");
			if( !this.isCycle(tempEdge)){
				tree.add(tempEdge);
				i++;
			}
		}
	}
	
	private boolean isCycle(spanTreeEdge newEdge){
		boolean node1Match, node2Match;
		node1Match = node2Match = false;
		Iterator<spanTreeEdge> navi = this.tree.iterator();
		while(navi.hasNext()){
			spanTreeEdge tempEdge = navi.next();
			if(!node1Match && (newEdge.getFromNode() == tempEdge.getFromNode() || newEdge.getFromNode() == tempEdge.getToNode())){
				node1Match = true;
			}
			if(!node2Match && (newEdge.getToNode() == tempEdge.getFromNode() || newEdge.getToNode() == tempEdge.getToNode())){
				node2Match = true;
			}
		}
		if(node1Match && node2Match)
			return true;
		else
			return false;
	}
	
	public int getTotalCost(){
		int totalCost = 0;
		Iterator<spanTreeEdge> navi = this.tree.iterator();
		while(navi.hasNext()){
			spanTreeEdge tempEdge = navi.next();
			totalCost += tempEdge.getCost();
			//System.out.println(tempEdge.getFromNode() + "->" + tempEdge.getToNode()/* + "(" + tempEdge.getCost() + ")"*/);
		}
		return totalCost;
	}
}