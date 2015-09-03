package puzzlesolver;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


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
				int current = elements[row][col];
				if ((row == 0 && col == 0) || current == 0) continue;
				else {
					if (elements[row][col] < previous) return false;
					else previous = elements[row][col];
				}
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
		int sum = 0;
		
		for (int row = 0; row < height; ++row) {
			int prod = 1;
			for (int col = 0; col < width; ++col) {
				prod *= (elements[row][col] << col);
			}
			
			sum += prod;
		}
		
		return sum;
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
		} else /*if (move == Move.LEFT)*/ {
			Position right = new Position(spacePos.row, spacePos.col + 1);
			copy.swap(spacePos, right);
		}
		
		return copy;
	}

	@DataProvider(name="scrambleProvider")
	public Object[][] scrambles() {
		return new Object[][] {
			{new Puzzle(3, 2, "4 1 3 0 2 5")},
			{new Puzzle(3, 3, "8 5 7 4 1 0 2 6 3")},
			{new Puzzle(4, 4, "1 2 3 0 5 6 7 4 9 10 11 8 13 14 15 12")},
			{new Puzzle(4, 4, "0 1 2 3 5 6 7 4 9 10 11 8 13 14 15 12")},
			{new Puzzle(4, 4, "12 3 9 4 11 1 6 10 2 13 7 0 5 8 14 15")}			
		};
	}
	
	@Test(dataProvider="scrambleProvider")
	public void test(Puzzle puzzle) {
		System.out.println(puzzle);
		String solution = PuzzleSolver.calculateSolution(puzzle);
		Assert.assertNotEquals(solution, PuzzleSolver.PATH_NOT_FOUND);
	}
	
	public static void main(String[] args) {
		Puzzle p = new Puzzle(3, 2, "3 5 4 2 0 1");
		System.out.println(p);
		System.out.println(PuzzleSolver.calculateSolution(p));
		
		System.out.println("\n\nTERMINATED");
	}

}
