package aStar;

import java.util.Comparator;
import java.util.PriorityQueue;


public class AStarSolver {
	private PriorityQueue<searchNode> openList;
	private Comparator<searchNode> comparator;
	private int strtCity;
	
	private int visited[];
	public AStarSolver(int [][] cost, int numOfCities, int startCity){
		this.strtCity = startCity;
		this.visited = new int[numOfCities];
		for(int i = 0; i < numOfCities; i++){
			this.visited[i] = 0;
		}
		
		comparator = new costComparator();
		openList = new PriorityQueue<searchNode>(numOfCities, comparator);
		
		this.solve(cost, numOfCities, startCity);
	}
	
	private void solve(int [][] cost, int numOfCities, int startCity){
		int numOfVisited = 1;
		int currentCity = startCity;
		while(numOfVisited <= numOfCities){
			for(int i = 0; i < numOfCities; i++){
				if(i != currentCity && cost[currentCity][i] < Integer.MAX_VALUE){
					if(visited[i] == 0){				//i.e. city is not visited
						minSpanTree tempSpanTree = new minSpanTree(cost, visited, numOfCities);
						int hCost = tempSpanTree.getTotalCost();
						searchNode tempSearchNode = new searchNode(i, (hCost + cost[currentCity][i]) );
						openList.add(tempSearchNode);
					}
				}
			}
			currentCity = openList.poll().cityId;
			visited[currentCity] = numOfVisited++;
		}
	}
	
	public void printOrder(){
		System.out.print(this.strtCity + " ");
		for(int i = 1; i <= this.visited.length; i++){
			for(int j =0; j < this.visited.length; j++)
				if(visited[j] == i)
					System.out.print(j + " ");
		}
		//System.out.println("\n");
		//for(int i = 0; i < this.visited.length; i++){
		//	System.out.print(i + "(" + visited[i] + ")");
		//}
	}
}