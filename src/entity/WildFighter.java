package entity;

import entity.base.Fighter;
import entity.base.StatsIncreasable;
import logic.GameConstant;
import logic.Sprites;

public class WildFighter extends Fighter implements StatsIncreasable {
	// attack boost

	private double bonusAttack; // bonusAttack is percent attack increased

	private final int MIN_BONUS_ATTACK = 25;
	private final int MAX_EXTRA_BONUS_ATTACK = 15;

	public WildFighter(String type, int team) {
		super(type, team);
		setName(GameConstant.WILD_NAME);
		setBonusStats();
	}

	public void setSpecialAbility() {
		// WildFighter has no special ability
	}

	public int getSymbol() {
		if (team == GameConstant.TEAM_1) {
			if (type.equals(GameConstant.MELEE_TYPE_STRING)) {
				return Sprites.P1_WILDMELEE;
			} else {
				return Sprites.P1_WILDRANGE;
			}
		} else if (team == GameConstant.TEAM_2) {
			if (type.equals(GameConstant.MELEE_TYPE_STRING)) {
				return Sprites.P2_WILDMELEE;
			} else {
				return Sprites.P2_WILDRANGE;
			}
		}
		return 0;
	}

	public void setBonusStats() {
		setBonusAttack();
		setAttack(attack + attack * bonusAttack / 100);
	}

	private void setBonusAttack() {
		bonusAttack = MIN_BONUS_ATTACK + Math.random() * MAX_EXTRA_BONUS_ATTACK; // attack is increased by between 10,40
																					// percent
	}
}
