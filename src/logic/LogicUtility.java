package logic;

import java.util.ArrayList;
import java.util.Random;

import entity.DuckFighter;
import entity.HealerFighter;
import entity.SpeedyFighter;
import entity.ToughFighter;
import entity.WildFighter;
import entity.base.Fighter;

public class LogicUtility {
	// this class have calculations,

	public static int calculateDistance(Coordinate c1, Coordinate c2) {
		int i1 = c1.getI();
		int j1 = c1.getJ();
		int i2 = c2.getI();
		int j2 = c2.getJ();

		int di = Math.abs(i1 - i2);
		int dj = Math.abs(j1 - j2);
		int distance = di + dj;
		return distance;
	}

	public static double calculatePercentSumOfHitPointRemain(ArrayList<Fighter> fighters) {
		double hitPointSum = 0;
		double maxHitPointSum = 0;

		for (Fighter fighter : fighters) {
			hitPointSum += fighter.getHitPoint();
			maxHitPointSum += fighter.getMaxHitPoint();
		}

		return hitPointSum / maxHitPointSum * 100;
	}

	public static boolean isPossibleToMoveTo(Coordinate targetCoordinate) {
		if (!targetCoordinate.isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean isPossibleToAttack(Fighter attacker, Fighter target) {
		int attackRange = attacker.getAttackRange();
		Coordinate c1 = attacker.getCoordinate();
		Coordinate c2 = target.getCoordinate();
		int distance = LogicUtility.calculateDistance(c1, c2);
		if (distance <= attackRange) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isPossibleToGetHealed(Fighter target) {
		if (target.getHitPoint() == target.getMaxHitPoint()) {
			return false;
		}
		return true;
	}

	// random fighters return Fighter[8]
	public static Fighter[] getRandomFighters(int team) {
		Fighter[] randomFighters = new Fighter[8];
		Random r = new Random();
		for (int i = 0; i < 8; i++) {
			int x = r.nextInt(10);
			switch (x) {
			case 0:
				randomFighters[i] = new DuckFighter(GameConstant.MELEE_TYPE_STRING, team);
				break;
			case 1:
				randomFighters[i] = new HealerFighter(GameConstant.MELEE_TYPE_STRING, team);
				break;
			case 2:
				randomFighters[i] = new SpeedyFighter(GameConstant.MELEE_TYPE_STRING, team);
				break;
			case 3:
				randomFighters[i] = new ToughFighter(GameConstant.MELEE_TYPE_STRING, team);
				break;
			case 4:
				randomFighters[i] = new WildFighter(GameConstant.MELEE_TYPE_STRING, team);
				break;
			case 5:
				randomFighters[i] = new DuckFighter(GameConstant.RANGE_TYPE_STRING, team);
				break;
			case 6:
				randomFighters[i] = new HealerFighter(GameConstant.RANGE_TYPE_STRING, team);
				break;
			case 7:
				randomFighters[i] = new SpeedyFighter(GameConstant.RANGE_TYPE_STRING, team);
				break;
			case 8:
				randomFighters[i] = new ToughFighter(GameConstant.RANGE_TYPE_STRING, team);
				break;
			case 9:
				randomFighters[i] = new WildFighter(GameConstant.RANGE_TYPE_STRING, team);
				break;
			}
		}
		return randomFighters;
	}
}
