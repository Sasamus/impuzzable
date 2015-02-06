package se.miun.dt015a.logic.model;


public final class ValidPuzzleSolution {

	private final Puzzle puzzle;

	public ValidPuzzleSolution(Puzzle puzzle, Piece[][] solution) {
		validatePuzzle(puzzle, solution);
		this.puzzle = puzzle;
	}

	private void validatePuzzle(Puzzle puzzle, Piece[][] solution) {
		if (!puzzle.isComplete() || !puzzle.isCurrentSolution(solution)) {
			throw new IllegalArgumentException(
					"The puzzle is either not complete, or the solution you entered is not the current one on the puzzle board.");
		}
	}

	@Override
	public String toString() {
		return puzzle.toString();
	}
}
