package entity;

import entity.base.Fighter;
import logic.Sprites;

public class WildFighter extends Fighter {
	// attack boost
	private double bonusAttack; // bonusAttack is percent attack increased

	public WildFighter(String type) {
		super(type);
	}

	public void setSpecialAbility() {
		setBonusAttack();
		attack += attack * bonusAttack / 100;
	}

	public String getSymbol() {
		if (type.equals("melee")) {
			return Sprites.WILDMELEE;
		} else {
			return Sprites.WILDRANGE;
		}
	}

	public double getBonusAttack() {
		return bonusAttack;
	}

	public void setBonusAttack() {
		bonusAttack = Math.random() * 30 + 10; // attack is increased by between 10,40 percent
	}
}
