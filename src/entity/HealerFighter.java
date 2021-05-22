package entity;

import entity.base.Fighter;
import entity.base.HitPointRegenerable;
import logic.GameConstant;
import logic.Sprites;

public class HealerFighter extends Fighter implements HitPointRegenerable {
	// can choose to attack or heal any team mates including itself

	private double healingPoint;

	private final int MIN_HEALING_POINT = 5;
	private final int MAX_EXTRA_HEALING_POINT = 10;

	private final int REGENERATING = 5; // percent
	private final double REGENERATED_HIT_POINT = maxHitPoint * REGENERATING / 100;

	public HealerFighter(String type, int team) {
		super(type, team);
		setName(GameConstant.HEALER_NAME);
	}

	public double heal(Fighter ally) {
		double healed;
		double beforeHealed = ally.getHitPoint();
		ally.setHitPoint(ally.getHitPoint() + healingPoint);
		healed = ally.getHitPoint() - beforeHealed;
		return healed;
	}

	protected void setSpecialAbility() {
		setHealingPoint();
	}

	public int getSymbol() {
		if (team == GameConstant.TEAM_1) {
			if (type.equals(GameConstant.MELEE_TYPE_STRING)) {
				return Sprites.P1_HEALERMELEE;
			} else {
				return Sprites.P1_HEALERRANGE;
			}
		} else if (team == GameConstant.TEAM_2) {
			if (type.equals(GameConstant.MELEE_TYPE_STRING)) {
				return Sprites.P2_HEALERMELEE;
			} else {
				return Sprites.P2_HEALERRANGE;
			}
		}
		return 0;
	}

	public void regenerateHitPoint() {
		setHitPoint(hitPoint + REGENERATED_HIT_POINT);
	}

	private void setHealingPoint() {
		healingPoint = MIN_HEALING_POINT + Math.random() * MAX_EXTRA_HEALING_POINT; // healingPoint is between 5,10
	}
}
