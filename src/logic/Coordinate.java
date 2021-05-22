package logic;

import entity.base.Fighter;

public class Coordinate implements Updatable, Comparable<Coordinate> {
	// a box on the board

	private int i; // row index
	private int j; // column index

	private boolean isEmpty;

	private Fighter myFighter; // Fighter in this Coordinate

	public Coordinate(int i, int j) {
		setI(i);
		setJ(j);
		setFighter(null);
	}

	public int getI() {
		return i;
	}

	private void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	private void setJ(int j) {
		this.j = j;
	}

	public Fighter getFighter() {
		return myFighter;
	}

	public void setFighter(Fighter fighter) {
		this.myFighter = fighter;
		if (fighter == null) {
			setEmpty(true);
		} else {
			setEmpty(false);
		}
	}

	public int getSymbol() {
		if (myFighter == null) {
			if (!isEmpty()) {
				return Sprites.RIVER;
			}
			return Sprites.GROUND;
		}
		return myFighter.getSymbol();
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	private void setEmpty(boolean isEmpty) {
		if (j == 3 && i % 2 == 0) {
			isEmpty = false;
		}
		this.isEmpty = isEmpty;
	}

	public void update() {
		if (myFighter == null) {
			setEmpty(true);
		}
	}

	public int[] coordinate2Pixel() {
		int[] pixel = { GameConstant.ORIGIN_X + (j * GameConstant.BOX_WIDTH),
				GameConstant.ORIGIN_Y + (i * GameConstant.BOX_HEIGHT) };
		return pixel;
	}

	public String toString() {
		return "(" + i + "," + j + ")";
	}

	public int compareTo(Coordinate other) {
		return this.toString().compareTo(other.toString());
	}
}