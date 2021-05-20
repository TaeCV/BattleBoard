package entity;

import entity.base.Fighter;
import javafx.scene.canvas.GraphicsContext;
import logic.Sprites;

public class DuckFighter extends Fighter {
	// when this is attacked there is chance to dodge the attack
	private double dodgeChance; // percent to dodge

	public DuckFighter(String type, int team, String name) {
		super(type, team, name);
	}

	public void setSpecialAbility() {
		setDodgeChance();
	}

	public int getSymbol() {
		if (team == 1) {
			if (type.equals("melee")) {
				return Sprites.P1_DUCKMELEE;
			} else {
				return Sprites.P1_DUCKRANGE;
			}
		} else if (team == 2) {
			if (type.equals("melee")) {
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
		dodgeChance = Math.random() * 10 + 25; // dodge chance is between 25,35
	}
}
