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
		
		ImpuzzableAlgorithm solver = new SearchAlgorithm();
		Impuzzable puzzle = new Impuzzable();
		ValidPuzzleSolution solution = solver.solve(puzzle);
		System.out.println(solution);

	}

}
