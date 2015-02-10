package se.miun.dt015a.logic.model.impuzzable;

import java.util.Collections;
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

		Vector<Piece> didNotWorkAsFirstPiece = new Vector<Piece>();

		int rotateFirstPiece = 0;

		// TODO: Creating Lists with all pieces with a certain edge may speed
		// things up

		// TODO: Remember, the Piece object's Edge objects are public

		// TODO: Make the check more efficient? Impossible?
		// While puzzle isn't complete
		while (!puzzle.isComplete()) {

			// Clear puzzle
			puzzle.clear();

			// Convert tmpPieces to an Vector
			freePieces = new Vector<Piece>(tmpPieces);

			// If the Piece placed at 0,0 don't work, make sure it's not placed
			// there anymore
			if (rotateFirstPiece < 4) {

				// Rotate the first Piece in freePieces
				freePieces.get(0).rotate();

				// Increment rotateFirstPiece
				rotateFirstPiece++;

			} else {

				// Add the first Piece to didNotWorksAsFirstPiece
				didNotWorkAsFirstPiece.add(freePieces.get(0));

				// Shuffle if and unil the first Piece isn't one recorded as not
				// working
				while (didNotWorkAsFirstPiece.contains(freePieces.get(0))) {

					// Shuffle freePieces
					Collections.shuffle(freePieces);
				}

			}

			// Iterate through all x positions
			for (int x = 0; x < sizeX; x++) {

				// Iterate through all y positions
				for (int y = 0; y < sizeY; y++) {

					// Check if the position is free
					if (puzzle.isFree(x, y)) {

						// Iterate through all free pieces
						for (int i = 0; i < freePieces.size(); i++) {

							// System.out.println("Trying to place:" +
							// freePieces.get(i).toString() + ":" + x + "," +
							// y);

							// Try to place a Piece
							if (tryPlacePiece(puzzle, freePieces.get(i), x, y)) {
								break;
							}
						}

						// Check if x, y is free. If so, start checking the
						// placed Pieces
						if (puzzle.isFree(x, y)) {

							// // Holds the Piece being worked with
							// Piece piece;
							//
							// ArrayList<Integer> occupiedPositions = new
							// ArrayList<Integer>(sizeX*sizeY*2);
							//
							// // Iterate through all x occupied positions
							// for (int tmpX = 0; tmpX < sizeX; tmpX++) {
							//
							// // Iterate through all occupied y positions
							// for (int tmpY = 0; tmpY < sizeY; tmpY++) {
							//
							// if(!puzzle.isFree(tmpX, tmpY)){
							// occupiedPositions.add(tmpX);
							// occupiedPositions.add(tmpY);
							// }
							//
							// }
							// }
							//
							// Collections.shuffle(occupiedPositions);
							//
							// for(int i=0; i < occupiedPositions.size() -1;
							// i++){
							//
							// // Remove the Piece at tmpX, tmpY
							// try {
							// piece =
							// puzzle.removePiece(occupiedPositions.get(i),
							// occupiedPositions.get(i + 1));
							// } catch (Exception e) {
							// break;
							// }
							//
							// // Try to place piece at x, y
							// if (tryPlacePiece(puzzle, piece, x, y)) {
							//
							// // If it succeeds, restart the iteration
							// // an leave the loops
							// // for checking placed pieces
							// x = 0;
							// y = 0;
							// break;
							//
							// } else {
							//
							// // If it fails, put piece back
							// tryPlacePiece(puzzle, piece,
							// occupiedPositions.get(i), occupiedPositions.get(i
							// + 1));
							// }
							// }

							// // Iterate through all x occupied positions
							// for (int tmpX = 0; tmpX < x; tmpX++) {
							//
							// // Iterate through all occupied y positions
							// for (int tmpY = 0; tmpY < y; tmpY++) {
							//
							// // Remove the Piece at tmpX, tmpY
							// try {
							// piece = puzzle.removePiece(tmpX, tmpY);
							// } catch (Exception e) {
							// break;
							// }
							//
							// // Try to place piece at x, y
							// if (tryPlacePiece(puzzle, piece, x, y)) {
							//
							// // If it succeeds, restart the iteration
							// // an leave the loops
							// // for checking placed pieces
							// x = 0;
							// y = 0;
							// tmpX = -1;
							// break;
							//
							// } else {
							//
							// // If it fails, put piece back
							// tryPlacePiece(puzzle, piece, tmpX, tmpY);
							// }
							// }
							//
							// // If the loop should be broken, break
							// if (tmpX == -1) {
							// break;
							// }
							// }
						}
					}
				}
			}
		}

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

	/**
	 * Tries to place a Pece
	 * 
	 * @param piece
	 *            The Piece to place
	 * @return true if piece was placed, else false
	 */
	private boolean tryPlacePiece(Puzzle puzzle, Piece piece, int x, int y) {

		// Allows for at most 4 rotations
		for (int j = 0; j < 4; j++) {

			// Tries to set piece at the current x,y
			// position
			if (puzzle.setPiece(x, y, piece)) {

				// Call pieceIsPlaced so that the program
				// knows which Pieces
				// are placed
				pieceIsPlaced(piece);

				// System.out.println("Placed:" + piece.toString() + ":" + x +
				// "," + y);

				// Break to stop trying to find a Piece that
				// fits
				break;

			} else {

				// System.out.println("Rotated");

				// Rotate piece
				piece.rotate();
			}
		}

		return !puzzle.isFree(x, y);

	}

}
