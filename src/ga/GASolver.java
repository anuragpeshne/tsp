package ga;

import java.util.Iterator;

public class GASolver {
	Population colony;
	public GASolver(int noOfCities, int cost[][]){
		colony = new Population(noOfCities, cost);
		
		for(int i = 0; i < 10000; i++){
			Chromosome parent1, parent2, child1, child2;
			
			parent1 = this.getRandomChromosome(noOfCities, colony);
			parent2 = this.getRandomChromosome(noOfCities, colony);
			child1 = colony.crossover(parent1, parent2, cost);
			child2 = colony.crossover(parent1, parent2, cost);
			
			colony.population.add(child1);
			colony.population.add(child2);
			
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
}
