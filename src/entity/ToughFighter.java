package entity;

import entity.base.Fighter;
import entity.base.HitPointRegenerable;
import entity.base.StatsIncreasable;
import logic.Sprites;

public class ToughFighter extends Fighter implements HitPointRegenerable, StatsIncreasable {
	// defense and hitPoint boost
	private double bonusDefense;
	private double bonusHitPoint;
	private double regeneratedHitPoint;

	public ToughFighter(String type, int team, String name) {
		super(type, team, name);
		setBonusStats();
	}

	public void setSpecialAbility() {
		// ToughFighter has no special ability
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

	public void setBonusStats() {
		setBonusDefense();
		setBonusHitPoint();
		setDefense(defense + defense * bonusDefense / 100);
		setMaxHitPoint(maxHitPoint + maxHitPoint * bonusHitPoint / 100);
		setHitPoint(maxHitPoint);
	}

	public double regenerateHitPoint() {
		setRegeneratedHitPoint();
		setHitPoint(hitPoint + regeneratedHitPoint);
		return regeneratedHitPoint;
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
	
	public void setRegeneratedHitPoint() {
		regeneratedHitPoint = maxHitPoint * 2 / 100;
	}

}
