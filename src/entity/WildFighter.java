package entity;

import entity.base.Fighter;
import entity.base.StatsIncreasable;
import logic.Sprites;

public class WildFighter extends Fighter implements StatsIncreasable {
	// attack boost
	private double bonusAttack; // bonusAttack is percent attack increased

	public WildFighter(String type, int team, String name) {
		super(type, team, name);
		setBonusStats();
	}

	public void setSpecialAbility() {
		// WildFighter has no special ability
	}

	public int getSymbol() {
		if (team == 1) {
			if (type.equals("melee")) {
				return Sprites.P1_WILDMELEE;
			} else {
				return Sprites.P1_WILDRANGE;
			}
		} else if (team == 2) {
			if (type.equals("melee")) {
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

	public double getBonusAttack() {
		return bonusAttack;
	}

	public void setBonusAttack() {
		bonusAttack = Math.random() * 30 + 10; // attack is increased by between 10,40 percent
	}
}
