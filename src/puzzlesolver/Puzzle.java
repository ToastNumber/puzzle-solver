package puzzlesolver;

import java.util.Arrays;

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
	
	public Puzzle(int width, int height, int[][] elements) {
		this.width = width;
		this.height = height;
		this.elements = elements;
	}

	public boolean isSolved() {
		int previous = elements[0][0];

		for (int row = 0; row < height; ++row) {
			for (int col = 0; col < width; ++col) {
				if (row == 0 && col == 0) continue;
				else {
					if (elements[row][col] < previous) return false;
					else previous = elements[row][col];
				}
			}
		}

		return true;
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

	public boolean canMove(Move move) {
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

	public void swap(Position a, Position b) {
		int temp = elements[a.row][a.column];
		elements[a.row][a.column] = elements[b.row][b.column];
		elements[b.row][b.column] = temp;
	}
	
	@Override
	public Puzzle clone() {
		return new Puzzle(width, height, Arrays.copyOf(elements, elements.length));
	}

	public Puzzle move(Move move) {
		Puzzle copy = this.clone();
		Position spacePos = getPositionOfSpace();

		if (move == Move.UP) {
			Position below = new Position(spacePos.row + 1, spacePos.column);
			copy.swap(spacePos, below);
		} else if (move == Move.RIGHT) {
			Position left = new Position(spacePos.row, spacePos.column - 1);
			copy.swap(spacePos, left);
		} else if (move == Move.DOWN) {
			Position above = new Position(spacePos.row - 1, spacePos.column);
			copy.swap(spacePos, above);
		} else /*if (move == Move.LEFT)*/ {
			Position right = new Position(spacePos.row, spacePos.column + 1);
			copy.swap(spacePos, right);
		}
		
		return copy;
	}

	public static void main(String[] args) {
		Puzzle p = new Puzzle(4, 4, "1 2 3 0 5 6 7 4 9 10 11 8 13 14 15 12");
		System.out.println(p);
		System.out.println();

		System.out.println("Terminated");
	}

}
