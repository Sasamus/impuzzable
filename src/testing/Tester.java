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
	 * @param args
	 */
	public static void main(String[] args) {

		final double startTime = System.nanoTime();

		ImpuzzableAlgorithm solver;

		Impuzzable puzzle;

		for (int i = 0; i < 1000; i++) {

			solver = new SearchAlgorithm();

			puzzle = new Impuzzable();

			ValidPuzzleSolution solution = solver.solve(puzzle);
		}

		// System.out.println(solution);

		final double endTime = System.nanoTime();

		System.out.println("Total execution time: " + (endTime - startTime)
				/ 1000 + " nanoseconds");

	}

}
