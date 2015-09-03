package puzzlesolver;

public enum Move {
	UP, LEFT, DOWN, RIGHT;
	
	public String toString() {
		if (this == UP) return "U";
		else if (this == LEFT) return "L";
		else if (this == DOWN) return "D";
		else return "R";
	}
}
