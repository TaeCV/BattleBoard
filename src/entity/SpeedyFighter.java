package entity;

import entity.base.Fighter;
import logic.GameController;
import logic.Sprites;

public class SpeedyFighter extends Fighter {
	// can move 2 spaces per move
	private final int EXTRA_MOVE = 1;
	
	public SpeedyFighter(String type, int team, String name) {
		super(type, team, name);
	}

	public void setSpecialAbility() {
		setTotalMoves(getTotalMoves() + EXTRA_MOVE);
	}

	public int getSymbol() {
		if (team == GameController.TEAM_1) {
			if (type.equals(GameController.MELEE_TYPE_STRING)) {
				return Sprites.P1_SPEEDYMELEE;
			} else {
				return Sprites.P1_SPEEDYRANGE;
			}
		} else if (team == GameController.TEAM_2) {
			if (type.equals(GameController.RANGE_TYPE_STRING)) {
				return Sprites.P2_SPEEDYMELEE;
			} else {
				return Sprites.P2_SPEEDYRANGE;
			}
		}
		return 0;
	}
}
