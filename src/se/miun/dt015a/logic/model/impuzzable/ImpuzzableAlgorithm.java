package se.miun.dt015a.logic.model.impuzzable;

import se.miun.dt015a.logic.model.Puzzle;
import se.miun.dt015a.logic.model.ValidPuzzleSolution;

/** An algorithm that solves the Impuzzable puzzle. */
public interface ImpuzzableAlgorithm {

	ValidPuzzleSolution solve(Puzzle puzzle);
}