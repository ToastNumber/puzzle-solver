package puzzlesolver;

public class Puzzle {
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

	private boolean canMove(Move move) {
		Position spacePos = getPositionOfSpace();

		if (move == Move.UP) {
			return spacePos.row < height - 1;
		} else if (move == Move.RIGHT) {
			return spacePos.column > 0;
		} else if (move == Move.DOWN) {
			return spacePos.row > 0;
		} else if (move == Move.LEFT) {
			return spacePos.column < width - 1;
		} else {
			return false;
		}
	}

	private void swap(Position a, Position b) {
		int temp = elements[a.row][a.column];
		elements[a.row][a.column] = elements[b.row][b.column];
		elements[b.row][b.column] = temp;
	}

	public void move(Move move) {
		if (!canMove(move)) throw new IllegalArgumentException("Move not allowed");
		else {
			Position spacePos = getPositionOfSpace();

			if (move == Move.UP) {
				Position below = new Position(spacePos.row + 1, spacePos.column);
				swap(spacePos, below);
			} else if (move == Move.RIGHT) {
				Position left = new Position(spacePos.row, spacePos.column - 1);
				swap(spacePos, left);
			} else if (move == Move.DOWN) {
				Position above = new Position(spacePos.row - 1, spacePos.column);
				swap(spacePos, above);
			} else if (move == Move.LEFT) {
				Position right = new Position(spacePos.row, spacePos.column + 1);
				swap(spacePos, right);
			} else return;
		}
	}

	public static void main(String[] args) {
		Puzzle p = new Puzzle(4, 4, "1 2 3 0 5 6 7 4 9 10 11 8 13 14 15 12");
		System.out.println(p);
		System.out.println();
		
		System.out.println("Terminated");
	}

}
