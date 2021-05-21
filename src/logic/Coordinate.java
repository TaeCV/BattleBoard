package logic;

import entity.base.Fighter;

public class Coordinate implements Updatable, Comparable<Coordinate> {
	private int i;
	private int j;

	private boolean isEmpty;

	private Fighter myFighter;

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
		int[] pixel = { GameController.originX + (j * GameController.PIXEL_X),
				GameController.originY + (i * GameController.PIXEL_Y) };
		return pixel;
	}

	public String toString() {
		return "(" + i + "," + j + ")";
	}

	public int compareTo(Coordinate other) {
		return this.toString().compareTo(other.toString());
	}
}