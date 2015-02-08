package se.miun.dt015a.logic.model.impuzzable;

import java.util.List;
import java.util.Vector;

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
	 * Holds the placed Piece objects
	 */
	Vector<Piece> placedPieces = new Vector<Piece>();

	/**
	 * Holds the free Piece objects
	 */
	Vector<Piece> freePieces;

	public SearchAlgorithm() {
		// Add code here if necessary, but do not add other constructors.
	}

	@Override
	public ValidPuzzleSolution solve(Puzzle puzzle) {

		// Get puzzle's size
		sizeX = puzzle.getSizeX();
		sizeY = puzzle.getSizeY();

		// Get puzzle's Piece objects
		List<Piece> tmpPieces = puzzle.getPieces();

		// Convert tmpPieces to an Vector
		freePieces = new Vector<Piece>(tmpPieces);

		// TODO: Creating Lists with all pieces with a certain edge may speed
		// things up

		// TODO: Remember, the Piece object's Edge objects are public

		int roundCounter = 0;

		// TODO: Make the check more efficient? Impossible?
		// While puzzle isn't complete
		while (!puzzle.isComplete()) {

			// Iterate through all x positions
			for (int x = 0; x < sizeX; x++) {

				// Iterate through all x positions
				for (int y = 0; y < sizeY; y++) {

					// Check if the position is free
					if (puzzle.isFree(x, y)) {

						// Iterate through all free pieces
						for (int i = 0; i < freePieces.size(); i++) {

							// Get the Piece to work with
							Piece piece = freePieces.get(i);

							// Allows for at most 4 rotations
							for (int j = 0; j < 4; j++) {

								// Tries to set piece at the current x,y
								// position
								if (puzzle.setPiece(x, y, piece)) {

									// Call pieceIsPlaced so that the program
									// knows what Pieces
									// are placed and not
									pieceIsPlaced(piece);

									System.out.println("Placed");

									// Break to stop trying to find a Piece that
									// fits
									break;

								} else {

									System.out.println("Rotated");

									// Rotate piece
									piece.rotate();
								}
							}
							if (puzzle.isFree(x, y)) {
								System.out.println("Not Placed");
							}

						}
					}
				}
			}
			// System.out.println("round: " + ++roundCounter);
		}
		System.out.println("Success in round " + roundCounter
				+ " Wuhuuuu, what a luck!!");

		// Return a ValidPuzzleSolution object
		return puzzle.getFinalOnceCompleted();

		// return new RandomSearch().solve(puzzle); // Your code here.
	}

	/**
	 * Makes sure the Piece object that have been placed is properly tracked
	 * 
	 * @param piece
	 *            The placed Piece object
	 */
	private void pieceIsPlaced(Piece piece) {

		placedPieces.add(piece);
		freePieces.remove(piece);
	}

}
