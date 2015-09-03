package puzzlesolver;

public class Puzzle implements Comparable<Puzzle> {
	private final int[][] elements;
	private final int width;
	private final int height;

	public Puzzle(int width, int height, String data) {
		if (!data.contains("0")) throw new IllegalArgumentException("No space");
		else {
			this.width = width;
			this.height = height;
			this.elements = new int[height][width];

			String[] split = data.split(" ");
			for (int row = 0; row < height; ++row) {
				for (int col = 0; col < width; ++col) {
					elements[row][col] = Integer.valueOf(split[row * width + col]);
				}
			}
		}
	}

	public Puzzle(int width, int height, int[][] elements) {
		this.width = width;
		this.height = height;
		this.elements = elements;
	}

	public boolean isSolved() {
		for (int row = 0; row < height; ++row) {
			for (int col = 0; col < (row == height - 1 ? width - 1 : width); ++col) {
				if (elements[row][col] != row * width + col + 1) return false;
			}
		}

		return elements[height - 1][width - 1] == 0;
	}

	public int[][] getElements() {
		return elements;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Puzzle) {
			Puzzle other = (Puzzle) o;

			if (other.getWidth() != this.width || other.getHeight() != this.height) return false;
			else {
				int[][] otherElements = other.getElements();

				for (int row = 0; row < height; ++row) {
					for (int col = 0; col < width; ++col) {
						if (this.elements[row][col] != otherElements[row][col]) return false;
					}
				}

				return true;
			}

		} else return false;
	}

	@Override
	public int hashCode() {
		String cat = "";

		for (int row = 0; row < height; ++row) {
			for (int col = 0; col < width; ++col) {
				cat += elements[row][col];
			}
		}

		return cat.hashCode();
	}

	public String toString() {
		String svaret = "";

		for (int row = 0; row < height; ++row) {
			for (int col = 0; col < width; ++col) {
				svaret += String.format("%2d", elements[row][col]);
				if (col < width - 1) svaret += " ";
			}

			if (row < height - 1) svaret += "\n";
		}

		return svaret;
	}

	private Position getPositionOfSpace() {
		for (int row = 0; row < height; ++row) {
			for (int col = 0; col < width; ++col) {
				if (elements[row][col] == 0) return new Position(row, col);
			}
		}

		return new Position(-1, -1);
	}

	public static Move getMoveFrom(Puzzle a, Puzzle b) {
		Position pa = a.getPositionOfSpace();
		Position pb = b.getPositionOfSpace();

		if (pa.row == pb.row) {
			if (pa.col + 1 == pb.col) return Move.RIGHT;
			else if (pa.col - 1 == pb.col) return Move.LEFT;
			else return null;
		} else if (pa.col == pb.col) {
			if (pa.row + 1 == pb.row) return Move.DOWN;
			else if (pa.row - 1 == pb.row) return Move.UP;
			else return null;
		} else return null;
	}

	public boolean canMove(Move move) {
		Position spacePos = getPositionOfSpace();

		if (move == Move.UP) {
			return spacePos.row < height - 1;
		} else if (move == Move.RIGHT) {
			return spacePos.col > 0;
		} else if (move == Move.DOWN) {
			return spacePos.row > 0;
		} else if (move == Move.LEFT) {
			return spacePos.col < width - 1;
		} else {
			return false;
		}
	}

	private void swap(Position a, Position b) {
		int temp = elements[a.row][a.col];
		elements[a.row][a.col] = elements[b.row][b.col];
		elements[b.row][b.col] = temp;
	}

	@Override
	public Puzzle clone() {
		int[][] clonedElements = new int[height][width];
		for (int row = 0; row < height; ++row) {
			for (int col = 0; col < width; ++col) {
				clonedElements[row][col] = elements[row][col];
			}
		}

		return new Puzzle(width, height, clonedElements);
	}

	public Puzzle move(Move move) {
		Puzzle copy = this.clone();
		Position spacePos = copy.getPositionOfSpace();

		if (move == Move.UP) {
			Position below = new Position(spacePos.row + 1, spacePos.col);
			copy.swap(spacePos, below);
		} else if (move == Move.RIGHT) {
			Position left = new Position(spacePos.row, spacePos.col - 1);
			copy.swap(spacePos, left);
		} else if (move == Move.DOWN) {
			Position above = new Position(spacePos.row - 1, spacePos.col);
			copy.swap(spacePos, above);
		} else /* if (move == Move.LEFT) */{
			Position right = new Position(spacePos.row, spacePos.col + 1);
			copy.swap(spacePos, right);
		}

		return copy;
	}

	public int getScore() {
		int count = 0;

		for (int row = 0; row < height; ++row) {
			for (int col = 0; col < width; ++col) {
				if (elements[row][col] == row * width + col + 1) ++count;
			}
		}

		return count;
	}

	@Override
	public int compareTo(Puzzle o) {
		return this.getScore() - o.getScore();
	}

	public static void main(String[] args) {
		// Puzzle p = new Puzzle(3, 3, "3 6 4 7 5 2 0 1 8");
		// Puzzle p = new Puzzle(4, 4, "6 14 1 2 3 12 11 10 4 7 5 15 9 8 0 13");
		Puzzle p = new Puzzle(4, 4, "1 9 3 7 8 14 4 13 0 5 2 12 6 15 10 11");
		
		System.out.println(p);
		System.out.println(PuzzleSolver.calculateSolution(p));
	}

}
