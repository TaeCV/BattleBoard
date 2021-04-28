package entity;

import entity.base.Fighter;
import logic.Sprites;

public class SpeedyFighter extends Fighter {
	// can move 2 spaces per move
	public SpeedyFighter(String type, int team) {
		super(type, team);
	}

	public void setSpecialAbility() {
		totalMoves = 2;
	}

	public int getSymbol() {
		if (team == 1) {
			if (type.equals("melee")) {
				return Sprites.P1_SPEEDYMELEE;
			} else {
				return Sprites.P1_SPEEDYRANGE;
			}
		} else if (team == 2) {
			if (type.equals("melee")) {
				return Sprites.P2_SPEEDYMELEE;
			} else {
				return Sprites.P2_SPEEDYRANGE;
			}
		}
		return 0;
	}
}
