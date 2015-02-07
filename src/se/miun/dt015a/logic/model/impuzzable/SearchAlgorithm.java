package se.miun.dt015a.logic.model.impuzzable;

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
public class SearchAlgorithm implements ImpuzzableAlgorithm {

	/*
	 * Ideas:
	 * 
	 * Get string versions of all pieces, maybe convert it to be easier to
	 * handle. Check what pieces have the needed edge, on those check the other
	 * edges if present. If all edges fit, place it. If no free pieces fit,
	 * check if a placed piece will. If one does, move it there. Redo the
	 * process on the newly made free space.
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
	List<Piece> pieces;

	public SearchAlgorithm() {
		// Add code here if necessary, but do not add other constructors.
	}

	@Override
	public ValidPuzzleSolution solve(Puzzle puzzle) {
		
		//Get puzzle's size
		sizeX = puzzle.getSizeX();
		sizeY = puzzle.getSizeY();
		
		//Get puzzle's Pieces
		pieces = puzzle.getPieces();
		
		// Return a ValidPuzzleSolution object
		return new ValidPuzzleSolution(puzzle, null);
		
		
//		return new RandomSearch().solve(puzzle); // Your code here.
	}

}
