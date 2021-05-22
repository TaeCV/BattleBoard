package logic;

import entity.base.Fighter;
import sharedObject.RenderableHolder;

public class EffectUtility {
	// help get action symbols for display by actions and actors
	// play sound effects for actions

	public static int getEffectSymbol(char key, Coordinate actorCoordinate, double damageDone) {
		Fighter actor = actorCoordinate.getFighter();
		if (key == GameConstant.ATTACK_KEY) {
			if (damageDone == 0) {
				if (actor.getTeam() == GameConstant.TEAM_1) {
					return Sprites.P1_DUCKED;
				} else if (actor.getTeam() == GameConstant.TEAM_2) {
					return Sprites.P2_DUCKED;
				}
			} else {
				if (actor.getType().equals(GameConstant.MELEE_TYPE_STRING)) {
					if (actor.getTeam() == GameConstant.TEAM_1) {
						return Sprites.P1_MELEEATTACK;
					} else if (actor.getTeam() == GameConstant.TEAM_2) {
						return Sprites.P2_MELEEATTACK;
					}
				} else if (actor.getType().equals(GameConstant.RANGE_TYPE_STRING)) {
					return Sprites.RANGEATTACK;
				}
			}
		} else if (key == GameConstant.HEAL_KEY) {
			return Sprites.HEAL;
		}
		return 0;
	}

	public static void playSoundEffect(char key, Coordinate actorCoordinate, double damageDone) {
		Fighter actor = actorCoordinate.getFighter();
		if (key == GameConstant.ATTACK_KEY) {
			if (damageDone == 0) {
				if (actor.getType().equals(GameConstant.MELEE_TYPE_STRING)) {
					RenderableHolder.DodgeMelee_Sound.play();
				} else if (actor.getType().equals(GameConstant.RANGE_TYPE_STRING)) {
					RenderableHolder.DodgeRange_Sound.play();
				}
			} else {
				if (actor.getType().equals(GameConstant.MELEE_TYPE_STRING)) {
					RenderableHolder.MeleeAttack_Sound.play();
				} else if (actor.getType().equals(GameConstant.RANGE_TYPE_STRING)) {
					RenderableHolder.RangeAttack_Sound.play();
				}
			}
		} else if (key == GameConstant.HEAL_KEY) {
			RenderableHolder.Heal_Sound.play();
		}
	}
}
