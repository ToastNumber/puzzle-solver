package puzzlesolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PuzzleSolver {
	public static final String PATH_NOT_FOUND = "PATH NOT FOUND";
	
	public static String calculateSolution(Puzzle puzzle) {
		Queue<Puzzle> q = new Queue<>();
		q.push(puzzle);
		Set<Puzzle> visited = new HashSet<>();
		Map<Puzzle, Puzzle> map = new LinkedHashMap<>();

		while (!q.isEmpty()) {
			Puzzle current = q.pop();

			if (current.isSolved()) return reconstructPath(map, current);
			else {
				visited.add(current);
				List<Puzzle> successors = getSuccessors(current);

				for (Puzzle successor : successors) {
					if (!visited.contains(successor)) {
						q.push(successor);
						map.put(successor, current);
					}
				}
			}
		}

		return "PATH NOT FOUND";
	}

	private static String reconstructPath(Map<Puzzle, Puzzle> map, Puzzle end) {
		Puzzle current = end;
		String svaret = "";

		do {
			Puzzle previous = map.get(current);
			svaret = Puzzle.getMoveFrom(current, previous) + svaret;
			current = previous;
		} while (map.containsKey(current));

		return svaret;
	}
	
	private static List<Puzzle> getSuccessors(Puzzle p) {
		List<Puzzle> svaret = new ArrayList<>();
		Move[] moves = { Move.UP, Move.RIGHT, Move.DOWN, Move.LEFT };

		for (Move move : moves) {
			if (p.canMove(move)) svaret.add(p.move(move));
		}

		return svaret;
	}

}
