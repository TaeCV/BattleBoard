package logic;

import entity.base.Fighter;

public class Coordinate implements Comparable<Coordinate> {
	private int i;
	private int j;

	private boolean isEmpty;

	private Fighter myFighter;

	public Coordinate(int i, int j) {
		setI(i);
		setJ(j);
		isEmpty = true;
		myFighter = null;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public Fighter getFighter() {
		return myFighter;
	}

	public void setFighter(Fighter f) {
		this.myFighter = f;
		setEmpty(false);
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

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public void update() {
		if (myFighter == null) {
			setEmpty(true);
		}
	}

	public int[] coordinate2Pixel() {
		int[] pixel = { GameController.originX + (j * GameController.PIXEL_X),
				GameController.originY + (i * GameController.PIXEL_Y) };
		return pixel;
	}

	public String toString() {
		return "(" + i + "," + j + ")";
	}

	public boolean equals(Coordinate other) {
		if ((other.getI() == i) && (other.getJ() == j)) {
			return true;
		}
		return false;
	}

	public int compareTo(Coordinate other) {
		return this.toString().compareTo(other.toString());
	}
}