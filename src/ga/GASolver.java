package ga;

import java.util.Iterator;

public class GASolver {
	Population colony;
	int shortestDist[][];
	public GASolver(int noOfCities, int cost[][]){
		shortestDist = new int[noOfCities][noOfCities];
		this.calShortestDist(cost, noOfCities);
		
		colony = new Population(noOfCities, cost);
		
		for(int i = 0; i < 100; i++){
			Chromosome parent1, parent2, child1, child2;
		
			parent1 = this.getRandomChromosome(noOfCities, colony);
			parent2 = this.getRandomChromosome(noOfCities, colony);
			child1 = colony.crossover(parent1, parent2, cost);
			child2 = colony.crossover(parent1, parent2, cost);
			
			
			if(child1.getScore() < child2.getScore())
				colony.population.add(child1);
			else if(child2.getScore() < child1.getScore())
				colony.population.add(child2);
			if(parent1.getScore() < child1.getScore() && parent1.getScore() < child2.getScore())
				colony.population.add(parent1);
			if(parent2.getScore() < child1.getScore() && parent2.getScore() < child2.getScore())
				colony.population.add(parent2);
			
			int mutationChances = (int) (Math.random() * 10);
			if(mutationChances > 8){													//chances of mutation
				Chromosome mutateGene = this.getRandomChromosome(noOfCities, colony);
				colony.mutate(mutateGene);
				colony.population.add(mutateGene);
			}
		}
	}
	private Chromosome getRandomChromosome(int noOfCities, Population colony){
		int i = 0;
		Iterator<Chromosome> rndNavi = colony.population.iterator();
		Chromosome rndChromosome = null;
		while(i < (int) (Math.random() * noOfCities) && rndNavi.hasNext()){
			rndChromosome = rndNavi.next();
			i++;
		}
		if(i == 0){
			rndChromosome = colony.population.poll();
		}
		else{
			rndNavi.remove();
		}
		return rndChromosome;
	}
	
	public void printAns(int ai){
		for(int i = 0; i < ai; i++){
			Chromosome temp = this.colony.population.poll();
			temp.printGenes();
			System.out.print("(" + temp.getScore() + ")");
			System.out.println();
		}
	}
	
	private void calShortestDist(int cost[][], int noOfCities){
		for(int i = 0; i < noOfCities; i++){
			for(int j = 0; j < noOfCities; j++){
				for(int k = 0; k < noOfCities; k++){
					this.shortestDist[j][k] = this.min(cost[i][j],cost[j][k], cost[i][k]);
				}
			}
		}
	}
	private int min(int i, int j, int k){
		if(i < j)
			if(i < k)
				return i;
			else
				return k;
		else
			if(j < k)
				return j;
			else
				return k;
	}
}
