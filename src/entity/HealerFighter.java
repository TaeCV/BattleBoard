package entity;

import entity.base.Fighter;
import logic.Sprites;

public class HealerFighter extends Fighter {
	// can choose to attack or heal any team mates including itself
	private double healingPoint;

	public HealerFighter(String type, int team, String name) {
		super(type, team, name);
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

	public int getSymbol() {
		if (team == 1) {
			if (type.equals("melee")) {
				return Sprites.P1_HEALERMELEE;
			} else {
				return Sprites.P1_HEALERRANGE;
			}
		} else if (team == 2) {
			if (type.equals("melee")) {
				return Sprites.P2_HEALERMELEE;
			} else {
				return Sprites.P2_HEALERRANGE;
			}
		}
		return 0;
	}

	public double getHealingPoint() {
		return healingPoint;
	}

	public void setHealingPoint() {
		healingPoint = Math.random() * 15 + 10; // healingPoint is between 10,25
	}
}
