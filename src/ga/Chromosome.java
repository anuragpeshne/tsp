package ga;

import java.util.ArrayList;
import java.util.Collections;


public class Chromosome {
	ArrayList<Integer> path;
	private int score;
	
	public Chromosome(int size){
		path = new ArrayList<Integer>(size);
		
		for(int i = 0; i < size; i++){
			path.add(0);
		}
		this.score = 0;
	}
	
	public Chromosome(int size, int cost[][]){
		path = new ArrayList<Integer>(size);
		
		for(int i = 0; i < size; i++){
			path.add(i + 1);
		}
		Collections.shuffle(path);
		this.calculateScore(cost);
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void add(int index, int newCity){
		if(!this.path.contains(newCity))
			this.path.set(index, newCity);
		
	}
	public void add(){
		for(int i = 0; i < this.path.size(); i++){
			if(this.path.get(i) == 0){
				for(int j = 1; j <= this.path.size(); j++){
					if(!this.path.contains(j))
						this.path.set(i, j);
				}
			}
		}
	}
	
	public void printGenes(){
		for(int i = 0; i < this.path.size(); i++){
			System.out.print(this.path.get(i) + " ");
		}
	}
	
	public void calculateScore(int cost[][]){
		long tempTotalCost = 0;
		for(int i = 0; i < this.path.size()-1; i++){
			tempTotalCost += cost[path.get(i)-1][path.get(i+1)-1];
		}
		if(tempTotalCost > Integer.MAX_VALUE)
			this.score = Integer.MAX_VALUE;
		else
			this.score = (int) tempTotalCost;
	}
}
