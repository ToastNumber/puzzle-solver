package puzzlesolver;

public enum Move {
	UP, LEFT, DOWN, RIGHT;
	
	public String toString() {
		if (this == UP) return "UP";
		else if (this == LEFT) return "LEFT";
		else if (this == DOWN) return "DOWN";
		else return "RIGHT";
	}
}
