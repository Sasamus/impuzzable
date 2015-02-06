package se.miun.dt015a.logic.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import se.miun.dt015a.logic.model.puzzle.PuzzleValidator;

/**
 * Representing a puzzle.
 * The piece in the top left corner has position (0, 0).
 * 
 * @author feldob
 * 
 */
public abstract class Puzzle implements Observer {

	private final List<Piece> pieces;

	private final Piece[][] puzzle;

	private final PuzzleValidator validator = new PuzzleValidator();

	private final Set<Piece> placed = new HashSet<>();

	public Puzzle(List<Piece> pieces, int sizeX, int sizeY) {
		validator.validateSize(pieces, sizeX, sizeY);
		this.pieces = Collections.unmodifiableList(pieces);
		puzzle = new Piece[sizeX][sizeY];
	}

	/** The pieces that belong to this puzzle. */
	public List<Piece> getPieces() {
		return pieces;
	}

	/** The width, or number of columns, of this puzzle. */
	public int getSizeX() {
		return puzzle[0].length;
	}

	/** The height, or number of rows, of this puzzle. */
	public int getSizeY() {
		return puzzle.length;
	}

	/** Is the slot at (x, y) free? */
	public boolean isFree(int x, int y) {
		return peekOnPiece(x, y) == null;
	}

	/** Put the given puzzle piece in the (x, y) slot. */
	public boolean setPiece(int x, int y, Piece piece) {
		validator.validatePiece(this, piece);
		boolean fits = fits(x, y, piece);
		if (fits) {
			puzzle[x][y] = piece;
			placed.add(piece);
			piece.addObserver(this);
		}
		return fits;
	}

	private boolean fits(int x, int y, Piece piece) {
		return fitsUp(x, y, piece) && fitsRight(x, y, piece)
				&& fitsBelow(x, y, piece) && fitsLeft(x, y, piece);
	}

	private boolean fitsBelow(int x, int y, Piece piece) {
		Piece below = getPieceBelow(x, y);
		return noPiece(below) || below.up.fits(piece.down);
	}

	private boolean fitsUp(int x, int y, Piece piece) {
		Piece above = getPieceAbove(x, y);
		return noPiece(above) || above.down.fits(piece.up);
	}

	private boolean fitsLeft(int x, int y, Piece piece) {
		Piece toTheLeft = getPieceToTheLeft(x, y);
		return noPiece(toTheLeft) || toTheLeft.right.fits(piece.left);
	}

	private boolean fitsRight(int x, int y, Piece piece) {
		Piece toTheRight = getPieceToTheRight(x, y);
		return noPiece(toTheRight) || toTheRight.left.fits(piece.right);
	}

	private Piece getPieceBelow(int x, int y) {
		if (y < getSizeY() - 1)
			return puzzle[x][y + 1];
		return null;
	}

	private Piece getPieceAbove(int x, int y) {
		if (y > 0)
			return puzzle[x][y - 1];
		return null;
	}

	private Piece getPieceToTheLeft(int x, int y) {
		if (x > 0)
			return puzzle[x - 1][y];
		return null;
	}

	private Piece getPieceToTheRight(int x, int y) {
		if (x < getSizeX() - 1)
			return puzzle[x + 1][y];
		return null;
	}

	private boolean noPiece(Piece toTheRight) {
		return toTheRight == null;
	}

	/** Remove at (x, y) if set. */
	public Piece removePiece(int x, int y) {
	  if (isFree(x, y)) {
	    String msg = "Cannot remove piece from empty slot (" + x + "," + y + ").";
	    throw new IllegalArgumentException(msg);
	  }
		validator.validatePosition(this, x, y);
		Piece removedPiece = peekOnPiece(x, y);
		puzzle[x][y] = null;
		placed.remove(removedPiece);
		removedPiece.deleteObserver(this);
		return removedPiece;
	}

	public Piece peekOnPiece(int x, int y) {
		validator.validatePosition(this, x, y);
		return puzzle[x][y];
	}

	/** Checks whether all pieces have been set (no empty slots/unused pieces). */
	public boolean isComplete() {
		for (Piece[] line : puzzle)
			for (Piece piece : line)
				if (noPiece(piece))
					return false;
		return true;
	}

	public boolean belongsToPuzzle(Piece piece) {
		return pieces.contains(piece);
	}

	public boolean hasBeenPlaced(Piece piece) {
		return placed.contains(piece);
	}

	@Override
	public void update(Observable piece, Object arg) {
		throw new IllegalStateException(
				"You have rotated a puzzle piece, while it was placed on the table. That is not allowed!");
	}

	/** Gets the final solution to the puzzle once completed. */
	public ValidPuzzleSolution getFinalOnceCompleted() {
		if (!isComplete())
			throw new IllegalStateException(
					"You may only call the method getFinalOnceCompleted, once the puzzle is completed.");
		return new ValidPuzzleSolution(this, puzzle);
	}

	public boolean isCurrentSolution(Piece[][] solution) {
		return puzzle.equals(solution);
	}

	/**
	 * removes all pieces from the puzzle.
	 */
	public void clear() {
		for (Piece piece : placed)
			piece.deleteObserver(this);
		placed.clear();
		for (int x = 0; x < puzzle.length; x++)
			for (int y = 0; y < puzzle[x].length; y++)
				puzzle[x][y] = null;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("List of pieces:\n");
		b.append(pieces);
		b.append("\n\nThe current solution looks as follows:\n");
		for (int x = 0; x < puzzle.length; x++) {
			for (int y = 0; y < puzzle[x].length; y++) {
				b.append(puzzle[x][y]);
				b.append('\t');
			}
			b.append('\n');
		}
		return b.toString();
	}
}