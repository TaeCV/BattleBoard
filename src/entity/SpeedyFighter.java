package entity;

import entity.base.Fighter;
import logic.GameConstant;
import logic.Sprites;

public class SpeedyFighter extends Fighter {
	// can move 2 spaces per move

	private final int EXTRA_MOVE = 1;

	public SpeedyFighter(String type, int team) {
		super(type, team);
		setName(GameConstant.SPEEDY_NAME);
	}

	protected void setSpecialAbility() {
		setTotalMoves(getTotalMoves() + EXTRA_MOVE);
	}

	public int getSymbol() {
		if (team == GameConstant.TEAM_1) {
			if (type.equals(GameConstant.MELEE_TYPE_STRING)) {
				return Sprites.P1_SPEEDYMELEE;
			} else {
				return Sprites.P1_SPEEDYRANGE;
			}
		} else if (team == GameConstant.TEAM_2) {
			if (type.equals(GameConstant.MELEE_TYPE_STRING)) {
				return Sprites.P2_SPEEDYMELEE;
			} else {
				return Sprites.P2_SPEEDYRANGE;
			}
		}
		return 0;
	}
}
