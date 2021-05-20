package entity;

import entity.base.Fighter;
import entity.base.StatsIncreasable;
import javafx.scene.canvas.GraphicsContext;
import logic.GameController;
import logic.Sprites;

public class WildFighter extends Fighter implements StatsIncreasable {
	// attack boost
	private double bonusAttack; // bonusAttack is percent attack increased

	private final int MIN_BONUS_ATTACK = 25;
	private final int MAX_EXTRA_BONUS_ATTACK = 15;
	
	public WildFighter(String type, int team, String name) {
		super(type, team, name);
		setBonusStats();
	}

	public void setSpecialAbility() {
		// WildFighter has no special ability
	}

	public int getSymbol() {
		if (team == GameController.TEAM_1) {
			if (type.equals(GameController.MELEE_TYPE_STRING)) {
				return Sprites.P1_WILDMELEE;
			} else {
				return Sprites.P1_WILDRANGE;
			}
		} else if (team == GameController.TEAM_2) {
			if (type.equals(GameController.RANGE_TYPE_STRING)) {
				return Sprites.P2_WILDMELEE;
			} else {
				return Sprites.P2_WILDRANGE;
			}
		}
		return 0;
	}
	
	public void setBonusStats() {
		setBonusAttack();
		setAttack(attack + attack * bonusAttack / 100);
	}

	public double getBonusAttack() {
		return bonusAttack;
	}

	public void setBonusAttack() {
		bonusAttack = MIN_BONUS_ATTACK + Math.random() * MAX_EXTRA_BONUS_ATTACK; // attack is increased by between 10,40 percent
	}
}
