package puzzlesolver;

import java.awt.Point;

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

	private Point getPositionOfSpace() {
		for (int row = 0; row < height; ++row) {
			for (int col = 0; col < width; ++col) {
				if (elements[row][col] == 0) return new Point(row, col);
			}
		}

		return new Point(-1, -1);
	}

	public boolean canMove(Move move) {
		Point spacePos = getPositionOfSpace();
		
		if (move == Move.UP) {
			return spacePos.y < height - 1;
		} else if (move == Move.RIGHT) {
			return spacePos.x > 0;
		} else if (move == Move.DOWN) {
			return spacePos.y > 0;
		} else if (move == Move.LEFT) {
			return spacePos.x < width - 1;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		Puzzle p = new Puzzle(4, 4, "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0");
		System.out.println(p);

		System.out.println("Terminated");
	}

}
