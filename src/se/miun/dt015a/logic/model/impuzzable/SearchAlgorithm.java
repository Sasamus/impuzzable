package se.miun.dt015a.logic.model.impuzzable;

import java.util.ArrayList;
import java.util.List;

import se.miun.dt015a.logic.model.Piece;
import se.miun.dt015a.logic.model.Puzzle;
import se.miun.dt015a.logic.model.ValidPuzzleSolution;

/**
 * Implements an algorithm for solving the Impuzzable puzzle. Define your own
 * (smart/efficient) solve method.
 *
 * You may want to create additional helper classes. However, you are not
 * allowed to alter the other existing classes.
 */
/**
 * @author Albin Engström
 */
public class SearchAlgorithm implements ImpuzzableAlgorithm {

	/*
	 * Ideas:
	 * 
	 * Get string versions of all pieces, maybe convert it to be easier to
	 * handle. Check what pieces have the needed edge, try to place those,
	 * rotating the piece until it fits or not. (Checking all edges if the piece
	 * fits before placing may be faster) If no free pieces fit, check if a
	 * placed piece will. If one does, move it there. Redo the process on the
	 * newly made free space.
	 * 
	 * If the temporary puzzle is filled, place pieces in the actual puzzle.
	 */

	// Add member fields if necessary.

	/**
	 * Holds the size of X
	 */
	int sizeX;

	/**
	 * Holds the size of Y
	 */
	int sizeY;

	/**
	 * Holds the Piece objects
	 */
	List<Piece> pieces = new ArrayList<Piece>();

	/**
	 * Holds the string representations of the pieces
	 */
	List<String> strPieces = new ArrayList<String>();

	/**
	 * Keeps track of which pieces are placed
	 */
	List<Boolean> piecesPlaced = new ArrayList<Boolean>();

	public SearchAlgorithm() {
		// Add code here if necessary, but do not add other constructors.
	}

	@Override
	public ValidPuzzleSolution solve(Puzzle puzzle) {

		// Get puzzle's size
		sizeX = puzzle.getSizeX();
		sizeY = puzzle.getSizeY();

		// Get puzzle's Piece objects
		pieces = puzzle.getPieces();

		// Fill strPieces with the string representation of the Piece objects in
		// pieces
		for (Piece piece : pieces) {
			strPieces.add(piece.toString());
		}

		// Return a ValidPuzzleSolution object
		return new ValidPuzzleSolution(puzzle, null);

		// return new RandomSearch().solve(puzzle); // Your code here.
	}

}
