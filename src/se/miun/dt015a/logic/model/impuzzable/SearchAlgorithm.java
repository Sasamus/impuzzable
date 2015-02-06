package se.miun.dt015a.logic.model.impuzzable;

import se.miun.dt015a.logic.model.Puzzle;
import se.miun.dt015a.logic.model.ValidPuzzleSolution;

/**
 * Implements an algorithm for solving the Impuzzable puzzle.
 * Define your own (smart/efficient) solve method.
 *
 * You may want to create additional helper classes. However, you are not
 * allowed to alter the other existing classes.
 */
public class SearchAlgorithm implements ImpuzzableAlgorithm {

  // Add member fields if necessary.

  public SearchAlgorithm() {
    // Add code here if necessary, but do not add other constructors.
  }

  @Override
  public ValidPuzzleSolution solve(Puzzle puzzle) {
    return new RandomSearch().solve(puzzle); // Your code here.
  }

}
