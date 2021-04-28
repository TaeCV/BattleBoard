package entity;

import entity.base.Fighter;
import logic.Sprites;

public class ToughFighter extends Fighter {
	// defense and hitPoint boost
	private double bonusDefense;
	private double bonusHitPoint;

	public ToughFighter(String type, int team, String name) {
		super(type, team, name);
	}

	public void setSpecialAbility() {
		setStats();
	}

	public int getSymbol() {
		if (team == 1) {
			if (type.equals("melee")) {
				return Sprites.P1_TOUGHMELEE;
			} else {
				return Sprites.P1_TOUGHRANGE;
			}
		} else if (team == 2) {
			if (type.equals("melee")) {
				return Sprites.P2_TOUGHMELEE;
			} else {
				return Sprites.P2_TOUGHRANGE;
			}
		}
		return 0;
	}

	public void setStats() {
		setBonusDefense();
		setBonusHitPoint();
		defense += defense * bonusDefense / 100;
		hitPoint += hitPoint * bonusHitPoint / 100;
		maxHitPoint += maxHitPoint * bonusHitPoint / 100;
	}

	public double getBonusDefense() {
		return bonusDefense;
	}

	public void setBonusDefense() {
		bonusDefense = Math.random() * 20;
	}

	public double getBonusHitPoint() {
		return bonusHitPoint;
	}

	public void setBonusHitPoint() {
		bonusHitPoint = Math.random() * 30 + 10;
	}

}
