package puzzlesolver;


public class Puzzle {
	private final int[][] elements;
	private final int width;
	private final int height;
	
	public Puzzle(int width, int height, String data) {
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
	
	public static void main(String[] args) {
		Puzzle p = new Puzzle(4, 4, "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0");
		System.out.println(p);
		
		System.out.println("Terminated");
	}
	
}
