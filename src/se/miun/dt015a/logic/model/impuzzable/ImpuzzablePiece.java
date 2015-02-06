package se.miun.dt015a.logic.model.impuzzable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import se.miun.dt015a.logic.model.Edge;
import se.miun.dt015a.logic.model.Piece;

public class ImpuzzablePiece extends Piece {

	private static final Piece PIECE_1 = new ImpuzzablePiece(Edge.HEARTS_OUT,
			Edge.SPADES_OUT, Edge.SPADES_IN, Edge.CLUBS_IN, 1);
	private static final Piece PIECE_2 = new ImpuzzablePiece(Edge.HEARTS_OUT,
			Edge.DIAMONDS_OUT, Edge.CLUBS_IN, Edge.CLUBS_IN, 2);
	private static final Piece PIECE_3 = new ImpuzzablePiece(Edge.CLUBS_OUT,
			Edge.HEARTS_OUT, Edge.SPADES_IN, Edge.HEARTS_IN, 3);
	private static final Piece PIECE_4 = new ImpuzzablePiece(Edge.SPADES_OUT,
			Edge.DIAMONDS_OUT, Edge.SPADES_IN, Edge.HEARTS_IN, 4);
	private static final Piece PIECE_5 = new ImpuzzablePiece(Edge.SPADES_OUT,
			Edge.SPADES_OUT, Edge.HEARTS_IN, Edge.CLUBS_IN, 5);
	private static final Piece PIECE_6 = new ImpuzzablePiece(Edge.SPADES_OUT,
			Edge.DIAMONDS_OUT, Edge.HEARTS_IN, Edge.DIAMONDS_IN, 6);
	private static final Piece PIECE_7 = new ImpuzzablePiece(Edge.DIAMONDS_OUT,
			Edge.CLUBS_OUT, Edge.CLUBS_IN, Edge.DIAMONDS_IN, 7);
	private static final Piece PIECE_8 = new ImpuzzablePiece(Edge.CLUBS_OUT,
			Edge.HEARTS_OUT, Edge.DIAMONDS_IN, Edge.CLUBS_IN, 8);
	private static final Piece PIECE_9 = new ImpuzzablePiece(Edge.HEARTS_OUT,
			Edge.DIAMONDS_OUT, Edge.DIAMONDS_IN, Edge.HEARTS_IN, 9);

	public static final List<Piece> PIECES = Collections
			.unmodifiableList(Arrays.asList(PIECE_1, PIECE_2, PIECE_3, PIECE_4,
					PIECE_5, PIECE_6, PIECE_7, PIECE_8, PIECE_9));

	private final int id;

	private ImpuzzablePiece(Edge up, Edge right, Edge down, Edge left, int id) {
		super(up, right, down, left);
		this.id = id;
	}

	@Override
	public String toString() {
		return super.toString() + " (" + id + ")";
	}
}