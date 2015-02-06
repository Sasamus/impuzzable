package se.miun.dt015a.logic.model.impuzzable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import se.miun.dt015a.logic.model.Piece;
import se.miun.dt015a.logic.model.Puzzle;
import se.miun.dt015a.logic.model.ValidPuzzleSolution;

public class RandomSearch implements ImpuzzableAlgorithm {

	private static final int MAX_ROTATIONS = 4;
	private final Random rng;

	public RandomSearch() {
		rng = new Random();

	}

	public static void main(String[] args) {
		ImpuzzableAlgorithm solver = new RandomSearch();
		Impuzzable puzzle = new Impuzzable();
		ValidPuzzleSolution solution = solver.solve(puzzle);
		System.out.println(solution);
	}

	@Override
	public ValidPuzzleSolution solve(Puzzle puzzle) {

		System.out
				.println("The solver has started and counts the rounds until success:\n");
		int roundCounter = 0;
		while (!puzzle.isComplete()) {
			puzzle.clear();
			List<Piece> pieces = getRandomlyPreparedPieces(puzzle);
			for (Piece nextPiece : pieces) {
				boolean possible = placeOnNextFreePositionIfPossible(puzzle,
						nextPiece);
				if (!possible)
					break;
			}
			System.out.println("round: " + ++roundCounter);
		}
		System.out.println("Success in round " + roundCounter
				+ " Wuhuuuu, what a luck!!");
		return puzzle.getFinalOnceCompleted();
	}

	private boolean placeOnNextFreePositionIfPossible(Puzzle puzzle, Piece piece) {
		int sizeX = puzzle.getSizeX();
		int sizeY = puzzle.getSizeY();
		for (int x = 0; x < sizeX; x++)
			for (int y = 0; y < sizeY; y++)
				if (puzzle.isFree(x, y))
					return puzzle.setPiece(x, y, piece);
		return false;
	}

	private List<Piece> getRandomlyPreparedPieces(Puzzle puzzle) {
		List<Piece> pieces = new ArrayList<>(puzzle.getPieces());
		pieces = shuffleListOrder(pieces);
		pieces = rotateAtRandom(pieces);
		return pieces;
	}

	private List<Piece> rotateAtRandom(List<Piece> pieces) {
		for (Piece piece : pieces) {
			int amountRotations = rng.nextInt(MAX_ROTATIONS);
			for (int i = 0; i < amountRotations; i++)
				piece.rotate();
		}
		return pieces;
	}

	private List<Piece> shuffleListOrder(List<Piece> pieces) {
		Collections.shuffle(pieces, rng);
		return pieces;
	}
}