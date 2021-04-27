package entity;

import entity.base.Fighter;
import logic.Sprites;

public class ToughFighter extends Fighter {
	// defense and hitPoint boost
	private double bonusDefense;
	private double bonusHitPoint;

	public ToughFighter(String type) {
		super(type);
	}

	public void setSpecialAbility() {
		setStats();
	}

	public String getSymbol() {
		if (type.equals("melee")) {
			return Sprites.TOUGHMELEE;
		} else {
			return Sprites.TOUGHRANGE;
		}
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
