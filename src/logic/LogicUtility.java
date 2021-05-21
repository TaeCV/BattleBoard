package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.base.Fighter;

public class LogicUtility {
	
	public static ArrayList<Coordinate> sortByDistance(ArrayList<Coordinate> coordinates, Coordinate currentCoordinate) {
		if (coordinates != null) {
			Comparator<Coordinate> compareByDistanceFromCurrent = new Comparator<Coordinate>() {
				public int compare(Coordinate c1, Coordinate c2) {
					return Integer.compare(calculateDistance(currentCoordinate, c1),
							calculateDistance(currentCoordinate, c2));
				}
			};
			Collections.sort(coordinates, compareByDistanceFromCurrent);
			return coordinates;
		}
		return null;
	}
	
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

		return hitPointSum / maxHitPointSum;
	}
	
	public static boolean isPossibleToMove(Coordinate currentCoordinate, Coordinate targetc) {
		if (!targetc.isEmpty()) {
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
}
