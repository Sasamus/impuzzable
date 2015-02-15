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

	// Add member fields if necessary.

	/*
	 * Thoughts:
	 * 
	 * I had a lot of ideas of things that could speed things up but most of
	 * them required work and/or preprocessing that took more time than it's use
	 * saved.
	 * 
	 * Other things I simply couldn't make work. Some, perhaps, because they
	 * shouldn't work and some because I couldn't manage to get it to work.
	 * 
	 * Backtracking, for example, always ran into the problem of repeatedly
	 * placing the same pieces or same sequences of pieces over and over again.
	 * 
	 * While it seems fairly simple to resolve none of my approaches worked out.
	 * So I gave up on that because of time constraints.
	 * 
	 * In the end, my algorithm works, and judging by the times others have
	 * talked about it's pretty fast. Which doesn't make too much sense since
	 * it's fairly crude and relies too much on brute force for my tastes,
	 * although there are some things that make it somewhat clever.
	 * 
	 * I get and average of 1-1.2 microseconds, just for reference.
	 * 
	 * So I suspect my superior times are a product of a better CPU and
	 * possibly a more efficient testing system and not a superior algorithm.
	 * 
	 * I'm not too satisfied with it but don't have time to do more. So I've
	 * given up my hopes of being fastest and have to aim at completing the
	 * assignment.
	 */

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

		// Shuffle freePieces
		Collections.shuffle(freePieces);

		// A Vector with Pieces that can't be at 0,0
		Vector<Piece> didNotWorkAsFirstPiece = new Vector<Piece>();

		// Keeps track of the rotations of the Piece that is trying to be placed
		// at 0,0
		int rotateFirstPiece = 0;

		// Keeps track of if the current position is a dead end
		boolean restartLoop = false;

		// While puzzle isn't complete
		while (!puzzle.isComplete()) {

			// Clear puzzle
			puzzle.clear();

			// Reset freePieces
			freePieces = new Vector<Piece>(tmpPieces);

			// // If the Piece placed at 0,0 don't work, make sure it's not
			// placed
			// // there anymore
			if (rotateFirstPiece < 4) {

				// Rotate the first Piece in freePieces
				freePieces.get(0).rotate();

				// Increment rotateFirstPiece
				rotateFirstPiece++;

			} else {

				// Add the first Piece to didNotWorksAsFirstPiece
				didNotWorkAsFirstPiece.add(freePieces.get(0));

				// Shuffle if and until the first Piece isn't one recorded as
				// not
				// working
				while (didNotWorkAsFirstPiece.contains(freePieces.get(0))) {

					// Shuffle freePieces
					Collections.shuffle(freePieces);
				}
			}

			// Iterate through all y positions
			for (int x = 0; x < sizeX; x++) {

				// Iterate through all x positions
				for (int y = 0; y < sizeY; y++) {

					// Check if the position is free
					if (puzzle.isFree(x, y)) {

						// Iterate through all eligible Pieces
						for (int i = 0; i < freePieces.size(); i++) {

							// Try to place a Piece
							if (tryPlacePiece(puzzle, freePieces.get(i), x, y)) {
								break;
							}
						}

						if (puzzle.isFree(x, y)) {
							restartLoop = true;
							break;
						}
					}
				}

				if (restartLoop) {
					restartLoop = false;
					break;
				}
			}
		}

		// Return a ValidPuzzleSolution object
		return puzzle.getFinalOnceCompleted();
	}

	/**
	 * Tries to place a Piece
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

				// Remove piece from freePieces
				freePieces.remove(piece);

				// Break to stop trying to find a Piece that
				// fits
				break;

			} else {

				// Rotate piece
				piece.rotate();
			}
		}

		return !puzzle.isFree(x, y);

	}

}
