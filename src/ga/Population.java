package ga;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Population {
	PriorityQueue<Chromosome> population;
	private Comparator<Chromosome> comparator;
	private int noOfCities;
	static final int populationSize = 100000;
	
	public Population(int noOfCities, int cost[][]){
		this.noOfCities = noOfCities;
		comparator = new scoreComparator();
		
		this.population = new PriorityQueue<Chromosome>(Population.populationSize, comparator);
		for(int i = 0; i < Population.populationSize; i++){
			Chromosome tempChromo = new Chromosome(noOfCities, cost);
			this.population.add(tempChromo);
		}
	}
	
	public Chromosome crossover(Chromosome parent1, Chromosome parent2, int cost[][]){
		Chromosome child;
		child = new Chromosome(this.noOfCities);
		
		int strtPt = 0, endPt = 0;
		for(boolean jobDone = false; !jobDone;){
			strtPt = (int)(Math.random()*this.noOfCities);
			endPt = strtPt + (int)(Math.random()*(this.noOfCities-strtPt));
			if(strtPt >= 0 && endPt <= noOfCities && strtPt < endPt)
				jobDone = true;
		}
		for(int i = strtPt; i < endPt; i++){
			child.path.set(i, parent1.path.get(i));
		}
		for(int i = 0; i < strtPt; i++){
			child.add(i, parent2.path.get(i));
		}
		for(int i = endPt; i < this.noOfCities; i++){
			child.add(i, parent2.path.get(i));
		}
		child.add();
		child.calculateScore(cost);
		
		return child;
	}
	
	public void mutate(Chromosome gene){
		int rndNum1, rndNum2;
		rndNum1 = (int) (this.noOfCities * Math.random());
		rndNum2 = (int) (this.noOfCities * Math.random());
		
		int temp = gene.path.get(rndNum1);
		gene.path.set(rndNum1, gene.path.get(rndNum2));
		gene.path.set(rndNum2, temp);
	}
}
