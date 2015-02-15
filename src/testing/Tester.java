package testing;

import se.miun.dt015a.logic.model.ValidPuzzleSolution;
import se.miun.dt015a.logic.model.impuzzable.Impuzzable;
import se.miun.dt015a.logic.model.impuzzable.ImpuzzableAlgorithm;
import se.miun.dt015a.logic.model.impuzzable.SearchAlgorithm;

/**
 * @author Albin Engström
 */
public class Tester {

	/**
	 * Tests and times my Searchalgorith implementation
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// An ImpuzzableAlgorithm variable
		ImpuzzableAlgorithm solver;

		// An Impuzzable variable
		Impuzzable puzzle;
		
		// A ValidPuzzleSolution
		ValidPuzzleSolution solution = null;
		
		// Create a SearchAlgorith object
		solver = new SearchAlgorithm();

		// Create an Impuzzable object
		puzzle = new Impuzzable();
		
		// Get the starting time
		final double startTime = System.nanoTime();
		
		
		// Solve puzzle 1000 times to get an average
		for (int i = 0; i < 1000; i++) {
			
			// Clear puzzle
			puzzle.clear();

			// Solve puzzle
			solution = solver.solve(puzzle);
		}
		
		// Get a stopping time
		final double stopTime = System.nanoTime();
		
		// Print the solution
		System.out.println(solution);

		// Print the average execution time
		System.out.println("Total execution time: " + (stopTime - startTime)
				/ 1000 + " nanoseconds");

	}

}
