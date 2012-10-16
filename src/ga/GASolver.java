package ga;

import java.util.Iterator;

public class GASolver {
	public GASolver(int noOfCities, int cost[][]){
		Population colony = new Population(noOfCities, cost);
		
		for(int i = 0; i < 100; i++){
			Chromosome parent1, parent2, child1, child2;
			
			parent1 = colony.population.poll();
			parent2 = colony.population.poll();
			child1 = colony.crossover(parent1, parent2, cost);
			child2 = colony.crossover(parent1, parent2, cost);
			
			colony.population.add(child1);
			colony.population.add(child2);
			
			int mutationChances = (int) (Math.random() * 10);
			if(mutationChances > 7){
			//	Chromosome gene = colony.population.iterator();
				
			}
		}
	}
}
