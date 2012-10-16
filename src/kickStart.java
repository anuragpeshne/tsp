import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ga.GASolver;
import aStar.AStarSolver;

public class kickStart {
	public static final int INFI = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		
		Scanner scanner;
		try {
			scanner = new Scanner(new File("input.txt"));
			
			int numOfCities = scanner.nextInt();;
			int [][] cost = new int[numOfCities][numOfCities];
			
			for(int i = 0; i < numOfCities; i++)
				for(int j = 0; j < numOfCities && scanner.hasNextInt(); j++){
					int nxtInt = scanner.nextInt();
					if(nxtInt < 0)							//not connected
						cost[i][j] = kickStart.INFI;
					else
						cost[i][j] = nxtInt;
				}
				   
			
			AStarSolver tspSolver = new AStarSolver(cost, numOfCities, 0);
			System.out.println("Using A* Search:");
			tspSolver.printPath();
			System.out.println();
			
			GASolver tspGASolver = new GASolver(numOfCities, cost);
			System.out.println("Using Genetic Algorithm:");
			tspGASolver.printAns(3);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		/*
		cost[0][0] = 0; cost[0][1] = 2; cost[0][2] = 4; cost[0][3] = 9;
		cost[1][0] = 2; cost[1][1] = 0; cost[1][2] = 1; cost[1][3] = 5;
		cost[2][0] = 4; cost[2][1] = 1; cost[2][2] = 0; cost[2][3] = 6;
		cost[3][0] = 9; cost[3][1] = 5; cost[3][2] = 6; cost[3][3] = 0;
		*/
		//int visited [] = new int[numOfCities];
		//visited[1] = 2;
		
		//minSpanTree temp = new minSpanTree(cost, visited, numOfCities);
		//System.out.println(temp.getTotalCost());
		
	}

}
