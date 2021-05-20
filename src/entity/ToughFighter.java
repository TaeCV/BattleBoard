package entity;

import entity.base.Fighter;
import entity.base.HitPointRegenerable;
import entity.base.StatsIncreasable;
import logic.GameController;
import logic.Sprites;

public class ToughFighter extends Fighter implements HitPointRegenerable, StatsIncreasable {
	// defense and hitPoint boost
	private double bonusDefense;
	private double bonusHitPoint;
	private double regeneratedHitPoint;

	private final int MAX_BONUS_DEFENSE = 20;
	private final int MIN_BONUS_HIT_POINT = 20;
	private final int MAX_EXTRA_BONUS_HIT_POINT = 30;
	
	private final int REGENERATING = 2; // percent
	private final double REGENERATED_HIT_POINT = maxHitPoint * REGENERATING / 100;
	
	public ToughFighter(String type, int team, String name) {
		super(type, team, name);
		setBonusStats();
	}

	public void setSpecialAbility() {
		// ToughFighter has no special ability
	}

	public int getSymbol() {
		if (team == GameController.TEAM_1) {
			if (type.equals(GameController.MELEE_TYPE_STRING)) {
				return Sprites.P1_TOUGHMELEE;
			} else {
				return Sprites.P1_TOUGHRANGE;
			}
		} else if (team == GameController.TEAM_2) {
			if (type.equals(GameController.RANGE_TYPE_STRING)) {
				return Sprites.P2_TOUGHMELEE;
			} else {
				return Sprites.P2_TOUGHRANGE;
			}
		}
		return 0;
	}

	public void setBonusStats() {
		setBonusDefense();
		setBonusHitPoint();
		setDefense(defense + defense * bonusDefense / 100);
		setMaxHitPoint(maxHitPoint + maxHitPoint * bonusHitPoint / 100);
		setHitPoint(maxHitPoint);
	}

	public double regenerateHitPoint() {
		setHitPoint(hitPoint + REGENERATED_HIT_POINT);
		return regeneratedHitPoint;
	}
	
	public double getBonusDefense() {
		return bonusDefense;
	}

	public void setBonusDefense() {
		bonusDefense = Math.random() * MAX_BONUS_DEFENSE;
	}

	public double getBonusHitPoint() {
		return bonusHitPoint;
	}

	public void setBonusHitPoint() {
		bonusHitPoint = MIN_BONUS_HIT_POINT + Math.random() * MAX_EXTRA_BONUS_HIT_POINT;
	}


}
