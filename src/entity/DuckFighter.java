package entity;

import entity.base.Fighter;
import logic.GameController;
import logic.Sprites;

public class DuckFighter extends Fighter {
	// when this is attacked there is chance to dodge the attack
	private double dodgeChance; // percent to dodge
	
	private final int MIN_DODGECHANCE = 25;
	private final int MAX_EXTRA_DODGECHANCE = 10;

	public DuckFighter(String type, int team, String name) {
		super(type, team, name);
	}

	public void setSpecialAbility() {
		setDodgeChance();
	}

	public int getSymbol() {
		if (team == GameController.TEAM_1) {
			if (type.equals(GameController.MELEE_TYPE_STRING)) {
				return Sprites.P1_DUCKMELEE;
			} else {
				return Sprites.P1_DUCKRANGE;
			}
		} else if (team == GameController.TEAM_2) {
			if (type.equals(GameController.RANGE_TYPE_STRING)) {
				return Sprites.P2_DUCKMELEE;
			} else {
				return Sprites.P2_DUCKRANGE;
			}
		}
		return 0;
	}

	public double getDodgeChance() {
		return dodgeChance;
	}

	public void setDodgeChance() {
		dodgeChance = MIN_DODGECHANCE + Math.random() * MAX_EXTRA_DODGECHANCE; // dodge chance is between 25,35 percent
	}
}
