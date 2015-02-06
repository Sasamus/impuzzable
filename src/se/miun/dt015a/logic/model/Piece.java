package se.miun.dt015a.logic.model;

import java.util.Observable;

/**
 * Representing a piece of a puzzle.
 * 
 * @author feldob
 * 
 */
public class Piece extends Observable {

	public Edge up;
	public Edge right;
	public Edge down;
	public Edge left;

	protected Piece(Edge up, Edge right, Edge down, Edge left) {
		this.up = up;
		this.right = right;
		this.down = down;
		this.left = left;
	}

	/**
	 * rotate piece to the right (clockwise).
	 */
	public Piece rotate() {
		final Edge buffer = this.up;
		this.up = this.left;
		this.left = this.down;
		this.down = this.right;
		this.right = buffer;
		notifyObservers();
		return this;
	}

	public boolean fitsRightOf(Piece other) {
	  return edgesFit(left, other.right);
	}
	public boolean fitsLeftOf(Piece other) {
    return edgesFit(right, other.left);
  }
	public boolean fitsAbove(Piece other) {
    return edgesFit(down, other.up);
  }
	public boolean fitsBelow(Piece other) {
    return edgesFit(up, other.down);
  }
	private boolean edgesFit(Edge e1, Edge e2) {
	  return e1.fits(e2);
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("[");
		b.append(up);
		b.append(", ");
		b.append(right);
		b.append(", ");
		b.append(down);
		b.append(", ");
		b.append(left);
		b.append("]");
		return b.toString();
	}
}