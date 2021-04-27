package entity;

import entity.base.Fighter;
import logic.Sprites;

public class SpeedyFighter extends Fighter {
	// can move 2 spaces per move
	public SpeedyFighter(String type) {
		super(type);
	}

	public void setSpecialAbility() {
		totalMoves = 2;
	}

	public String getSymbol() {
		if (type.equals("melee")) {
			return Sprites.SPEEDYMELEE;
		} else {
			return Sprites.SPEEDYRANGE;
		}
	}
}
