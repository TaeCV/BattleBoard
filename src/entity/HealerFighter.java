package entity;

import entity.base.Fighter;
import entity.base.HitPointRegenerable;
import logic.GameController;
import logic.Sprites;

public class HealerFighter extends Fighter implements HitPointRegenerable{
	// can choose to attack or heal any team mates including itself
	private double healingPoint;
	
	private final int MIN_HEALING_POINT = 5;
	private final int MAX_EXTRA_HEALING_POINT = 10;
	
	private final int REGENERATING = 5; // percent
	private final double REGENERATED_HIT_POINT = maxHitPoint * REGENERATING / 100;
	

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
		if (team == GameController.TEAM_1) {
			if (type.equals(GameController.MELEE_TYPE_STRING)) {
				return Sprites.P1_HEALERMELEE;
			} else {
				return Sprites.P1_HEALERRANGE;
			}
		} else if (team == GameController.TEAM_2) {
			if (type.equals(GameController.RANGE_TYPE_STRING)) {
				return Sprites.P2_HEALERMELEE;
			} else {
				return Sprites.P2_HEALERRANGE;
			}
		}
		return 0;
	}

	public double regenerateHitPoint() {
		setHitPoint(hitPoint + REGENERATED_HIT_POINT);
		return REGENERATED_HIT_POINT;
	}
	
	public double getHealingPoint() {
		return healingPoint;
	}

	public void setHealingPoint() {
		healingPoint = MIN_HEALING_POINT + Math.random() * MAX_EXTRA_HEALING_POINT; // healingPoint is between 5,10
	}
}
