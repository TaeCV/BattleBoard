package entity;

import entity.base.Fighter;
import logic.Sprites;

public class DuckFighter extends Fighter {
	// when this is attacked there is chance to dodge the attack
	private double dodgeChance; // percent to dodge

	public DuckFighter(String type) {
		super(type);
	}

	public void setSpecialAbility() {
		setDodgeChance();
	}

	public String getSymbol() {
		if (type.equals("melee")) {
			return Sprites.DUCKMELEE;
		} else {
			return Sprites.DUCKRANGE;
		}
	}

	public double getDodgeChance() {
		return dodgeChance;
	}

	public void setDodgeChance() {
		dodgeChance = Math.random() * 10 + 5; // dodge chance is between 5,15
	}
}
