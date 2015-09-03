package puzzlesolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class PuzzleSolver {
	private Puzzle puzzle;
	private String solution;
	
	public PuzzleSolver(Puzzle puzzle) {
		this.puzzle = puzzle;
		
		solution = calculatePath();
	}
	
	public String getSolution() {
		return solution;
	}
	
	private String calculatePath() {
		Queue<Puzzle> q = new Queue<>();
		q.push(puzzle);
		Set<Puzzle> visited = new HashSet<>();
		Map<Puzzle, Pair<Puzzle, Move>> map = new LinkedHashMap<>();
		
		while (!q.isEmpty()) {
			Puzzle current = q.pop();
			
			if (!visited.contains(current)) {
				if (current.isSolved()) return reconstructPath(map, current);
				else {
					visited.add(current);
					List<Pair<Puzzle, Move>> succ = successors(puzzle);
					
					for (Pair<Puzzle, Move> puzzleMove : succ) {
						Puzzle p = puzzleMove.a();
						
						if (!visited.contains(p)) {
							q.push(p);
							map.put(p, puzzleMove);
						}
					}
				}
			}
		}
		
		return "PATH NOT FOUND";
	}
	
	private String reconstructPath(Map<Puzzle, Pair<Puzzle, Move>> map, Puzzle current) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Pair<Puzzle, Move>> successors(Puzzle p) {
		List<Pair<Puzzle, Move>> svaret = new ArrayList<>();
		Move[] moves = {Move.UP, Move.RIGHT, Move.DOWN, Move.LEFT};

		for (Move move : moves) {
			if (p.canMove(move)) svaret.add(new Pair<Puzzle, Move>(p.move(move), move));
		}
		
		return svaret;
	}

}
