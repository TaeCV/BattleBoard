package logic;

import entity.base.Fighter;

public class Cell {
	private Fighter myFighter;
	private boolean isEmpty;

	private Coordinate coordinate;

	public Cell(Coordinate coordinate) {
		this.coordinate = coordinate;
		isEmpty = true;
	}

	public boolean IsEmpty() {
		return isEmpty;
	}

	public boolean setFighter(Fighter f) {
		if (isEmpty) {
			myFighter = f;
			isEmpty = false;
			return true;
		} else {
			return false;
		}
	}

	public Fighter getFighter() {
		return myFighter;
	}

	public void removeFighter() {
		myFighter = null;
		isEmpty = true;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public String getSymbol() {
		if (isEmpty) {
			return Sprites.GROUND;
		}
		if ((myFighter == null) & (!isEmpty)) {
			return Sprites.RIVER;
		}
		return myFighter.getSymbol();
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
}
