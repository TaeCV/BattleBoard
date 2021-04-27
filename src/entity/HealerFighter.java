package entity;

import entity.base.Fighter;
import logic.Sprites;

public class HealerFighter extends Fighter {
	// can choose to attack or heal any team mates including itself
	private double healingPoint;

	public HealerFighter(String type) {
		super(type);
	}

	public double heal(Fighter f) {
		double healed;
		double beforeHealed = f.getHitPoint();
		f.setHitPoint(f.getHitPoint() + healingPoint);
		if (f.getHitPoint() > f.getMaxHitPoint()) {
			f.setHitPoint(f.getMaxHitPoint());
		}
		healed = beforeHealed - f.getHitPoint();
		return healed;
	}

	public void setSpecialAbility() {
		setHealingPoint();
	}

	public String getSymbol() {
		if (type.equals("melee")) {
			return Sprites.HEALERMELEE;
		} else {
			return Sprites.HEALERRANGE;
		}
	}

	public double getHealingPoint() {
		return healingPoint;
	}

	public void setHealingPoint() {
		healingPoint = Math.random() * 15 + 10; // healingPoint is between 10,25
	}
}
