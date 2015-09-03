package puzzlesolver;

import java.util.ArrayList;
import java.util.List;

public class PriorityPuzzleQueue {
	private class PuzzlePieces {
		public Puzzle p;
		public int numPiecesSolved;

		public PuzzlePieces(Puzzle p, int n) {
			this.p = p;
			this.numPiecesSolved = n;
		}
	}

	private List<PuzzlePieces> list = new ArrayList<>();

	public void add(Puzzle p) {
		int index = 0;
		int numPiecesSolved = p.getScore();

		for (PuzzlePieces i : list) {
			int n = i.numPiecesSolved;
			if (numPiecesSolved > n) {
				list.add(index, new PuzzlePieces(p, numPiecesSolved));
				return;
			}
			
			++index;
		}
		
		list.add(new PuzzlePieces(p, numPiecesSolved));
	}

	public Puzzle pop() {
		Puzzle svaret = list.get(0).p;
		list.remove(0);

		return svaret;
	}

	public boolean isEmpty() {
		return list.size() == 0;
	}

	public String toString() {
		String svaret = "[";
		for (int i = 0; i < list.size(); ++i) {
			svaret += list.get(i);
			if (i < list.size() - 1) svaret += " ";
		}
		
		return svaret;
	}

}
