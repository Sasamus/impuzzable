package se.miun.dt015a.logic.model;


public enum Edge {

  SPADES_OUT(Symbol.SPADES, Orientation.OUT),
  SPADES_IN(Symbol.SPADES, Orientation.IN),
  HEARTS_OUT(Symbol.HEARTS, Orientation.OUT),
  HEARTS_IN(Symbol.HEARTS, Orientation.IN),
  DIAMONDS_OUT(Symbol.DIAMONDS, Orientation.OUT),
  DIAMONDS_IN(Symbol.DIAMONDS, Orientation.IN),
  CLUBS_OUT(Symbol.CLUBS, Orientation.OUT),
  CLUBS_IN(Symbol.CLUBS, Orientation.IN);

	public final Symbol symbol;

	public final Orientation orientation;

	private Edge(Symbol symbol, Orientation orientation) {
		this.symbol = symbol;
		this.orientation = orientation;
	}

	/**
	 * checks if the edge fits into another given edge.
	 * 
	 * @param other
	 * @return
	 */
	public boolean fits(Edge other) {
		return symbol == other.symbol && orientation != other.orientation;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("(");
		b.append(symbol);
		b.append(", ");
		b.append(orientation);
		b.append(")");
		return b.toString();
	}
}