package se.miun.dt015a.logic.model.puzzle;

import java.util.List;

import se.miun.dt015a.logic.model.Piece;
import se.miun.dt015a.logic.model.Puzzle;

/**
 * Puzzle Helper class.
 * 
 * @author feldob
 * 
 */
public class PuzzleValidator {

	public void validateSize(List<Piece> pieces, int sizeX, int sizeY) {
		if (sizeX < 1 || sizeY < 1)
			throw new IllegalArgumentException(
					"A puzzle must at last be of size 1x1.");
		if (sizeX * sizeY != pieces.size())
			throw new IllegalArgumentException(
					"Your puzzle must contain exactly the amount of pieces that can be placed on the x and y axes.");
	}

	public void validatePosition(Puzzle puzzle, int x, int y) {
		if (!validPosition(puzzle, x, y))
			throw new IllegalArgumentException("You try to access position ("
					+ x + "," + y + ") in a puzzle of size " + x + "x" + y
					+ ".");
	}

	private boolean validPosition(Puzzle puzzle, int x, int y) {
		return puzzle.getSizeY() > y && puzzle.getSizeX() > x;
	}

	public void validatePiece(Puzzle puzzle, Piece piece) {
		if (!puzzle.belongsToPuzzle(piece)) {
		  String msg = "That piece does not belong to this puzzle.";
		  throw new IllegalArgumentException(msg);
		}
		if (puzzle.hasBeenPlaced(piece)) {
		  String msg = "The piece you try to place is already part of the puzzle.";
			throw new IllegalArgumentException(msg);
		}
	}
}