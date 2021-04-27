package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import entity.*;
import entity.base.Fighter;

public class GameBoard {
	// private Cell[][] cellBoard;
	private Coordinate[][] board;
	public int[][] map;

	private int rows;
	private int cols;

	public ArrayList<Fighter> Player1Fighters;
	public ArrayList<Fighter> Player2Fighters;

	private ArrayList<Fighter> allFighters;

	private int width;
	private int height;

	public GameBoard() {
		Player1Fighters = new ArrayList<>();
		Player2Fighters = new ArrayList<>();

		allFighters = new ArrayList<Fighter>();
		rows = GameController.N_ROWS;
		cols = GameController.N_COLS;
		board = new Coordinate[rows][cols];
		map = new int[rows][cols];

		setWidth(rows);
		setHeight(cols);
//		cellBoard = new Cell[rows][cols];
//		for (int i = 0; i < rows; i++) {
//			for (int j = 0; j < cols; j++) {
//				cellBoard[i][j] = new Cell(new Coordinate(i, j));
//			}
//		}	
		setDefault();
	}

	// --------------------------------------initializer
	// methods-------------------------------------------
	// method random fighters return Fighter[8]
	public Fighter[] getRandomFighters() {
		Fighter[] randomFighters = new Fighter[8];
		Random r = new Random();
		for (int i = 0; i < 8; i++) {
			int x = r.nextInt(10);
			switch (x) {
			case 0:
				randomFighters[i] = new DuckFighter("melee");
				break;
			case 1:
				randomFighters[i] = new HealerFighter("melee");
				break;
			case 2:
				randomFighters[i] = new SpeedyFighter("melee");
				break;
			case 3:
				randomFighters[i] = new ToughFighter("melee");
				break;
			case 4:
				randomFighters[i] = new WildFighter("melee");
				break;
			case 5:
				randomFighters[i] = new DuckFighter("range");
				break;
			case 6:
				randomFighters[i] = new HealerFighter("range");
				break;
			case 7:
				randomFighters[i] = new SpeedyFighter("range");
				break;
			case 8:
				randomFighters[i] = new ToughFighter("range");
				break;
			case 9:
				randomFighters[i] = new WildFighter("range");
				break;
			}
		}
		return randomFighters;
	}

	public void setPlayerFighters(ArrayList<Fighter> fighters1, ArrayList<Fighter> fighters2) {
		int i = 0;
		for (Fighter f : fighters1) {
			f.setTeam(1);
			Player1Fighters.add(f);
			addFighter(f, new Coordinate(i, 0));
			i++;
		}
		i = 0;
		for (Fighter f : fighters2) {
			f.setTeam(2);
			Player2Fighters.add(f);
			addFighter(f, new Coordinate(i, 6));
			i++;
		}
	}

	public void setDefault() {
		setBoard();
		setRiver();
		setMap();
	}

	public void setBoard() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new Coordinate(i, j);
			}
		}
	}

	public void setRiver() {
		board[0][3].setEmpty(false);
		board[2][3].setEmpty(false);
		board[4][3].setEmpty(false);
	}

	public void setMap() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				map[i][j] = board[i][j].getSymbol();
			}
		}
	}

	// -----------------------------------UI linking utilities and action
	// methods------------------------------------
	public ArrayList<Coordinate> getAllReadyPlayerFightersCoordinate(int team) {
		// return coordinate for all Fighter from team 1 or 2
		ArrayList<Coordinate> allReadyFightersCoordinate = new ArrayList<>();
		if (team == 1) {
			for (Fighter f : Player1Fighters) {
				if (f.isReady()) {
					allReadyFightersCoordinate.add(f.getCoordinate());
				}
			}
		} else {
			for (Fighter f : Player2Fighters) {
				if (f.isReady()) {
					allReadyFightersCoordinate.add(f.getCoordinate());
				}
			}
		}
		return allReadyFightersCoordinate;
	}

	public ArrayList<Coordinate> getAllPossibleToMoveCoordinate(Coordinate currentCoordinate) {
		// return all possible coordinate that a Fighter at currentCoordinate can move
		// to
		// logic is move 1 block at a time
		ArrayList<Coordinate> possibleCoordinate = new ArrayList<>();
		ArrayList<Coordinate> firstTime = getAllPossibleToMoveDistance1(currentCoordinate);
		possibleCoordinate.addAll(firstTime);
		if (currentCoordinate.getFighter() instanceof SpeedyFighter) {
			for (Coordinate current : firstTime) {
				for (Coordinate possible : getAllPossibleToMoveDistance1(current)) {
					if ((!possibleCoordinate.contains(possible)) && isPossibleToMove(current, possible)) {
						possibleCoordinate.add(possible);
					}
				}
			}
		}
		Collections.sort(possibleCoordinate);
		return possibleCoordinate;
	}

	public ArrayList<Coordinate> getAllPossibleTargetsToAttack(Coordinate attackerCoordinate) {
		// return all possible target coordinate that a Fighter at attackerCoordinate
		// can attack
		ArrayList<Coordinate> allPossibleTargets = new ArrayList<>();
		Fighter attacker = attackerCoordinate.getFighter();
		if (attacker.getTeam() == 1) {
			for (Fighter target : Player2Fighters) {
				if (isPossibleToAttack(attacker, target)) {
					allPossibleTargets.add(target.getCoordinate());
				}
			}
		} else {
			for (Fighter target : Player1Fighters) {
				if (isPossibleToAttack(attacker, target)) {
					allPossibleTargets.add(target.getCoordinate());
				}
			}
		}
		return allPossibleTargets;
	}

	public ArrayList<Coordinate> getAllPossibleAlliesToHeal(Coordinate healerCoordinate) {
		// return all possible ally coordinate that a Fighter at healerCoordinate can
		// heal
		ArrayList<Coordinate> allPossibleAllies = new ArrayList<>();
		Fighter healer = healerCoordinate.getFighter();
		if (healer.getTeam() == 1) {
			for (Fighter ally : Player1Fighters) {
				if (isPossibleToHeal(healer, ally)) {
					allPossibleAllies.add(ally.getCoordinate());
				}
			}
		} else {
			for (Fighter ally : Player2Fighters) {
				if (isPossibleToHeal(healer, ally)) {
					allPossibleAllies.add(ally.getCoordinate());
				}
			}
		}
		return allPossibleAllies;
	}

	public void takeMove(Coordinate actorCoordinate, Coordinate targetCoordinate) {
		// receive actorCoordinate and target from input
		Fighter moving = actorCoordinate.getFighter();
		moving.move(targetCoordinate);
		moving.setReady(false);
		update(actorCoordinate, targetCoordinate);
	}

	public double takeAttack(Coordinate attackerCoordinate, Coordinate targetCoordinate) {
		// receive attackerCoordinate and targetCoordinate from input
		Fighter attacker = attackerCoordinate.getFighter();
		Fighter target = targetCoordinate.getFighter();
		attacker.setReady(false);
		update(attackerCoordinate, targetCoordinate);
		double damageDone = attacker.attack(target);
		GameController.checkWinRound(attacker.getTeam());
		return damageDone;
	}

	public double takeHeal(Coordinate healerCoordinate, Coordinate targetCoordinate) {
		// receive healerCoordinate and targetCoordinate from input
		HealerFighter healer = (HealerFighter) healerCoordinate.getFighter();
		Fighter target = targetCoordinate.getFighter();
		healer.setReady(false);
		update(healerCoordinate, targetCoordinate);
		return healer.heal(target);
	}

	// -----------------------------------------data managing
	// methods----------------------------------------
	public void addFighter(Fighter f, Coordinate c) {
		allFighters.add(f);
		c.setFighter(f);
		f.setCoordinate(c);
		update(c);
	}

	public Fighter getFighter(Coordinate c) {
		return c.getFighter();
	}

	public void removeFighter(Coordinate c) {
		Fighter removing = c.getFighter();
		if (removing.getTeam() == 1) {
			Player1Fighters.remove(removing);
		} else {
			Player2Fighters.remove(removing);
		}
		allFighters.remove(removing);
		c.setFighter(null);
		update(c);
	}

	// -----------------------------------------check possibilities
	// methods----------------------------------------
	public ArrayList<Coordinate> getAllPossibleToMoveDistance1(Coordinate currentCoordinate) {
		// return all Coordinate that can move to by only 1 distance far
		ArrayList<Coordinate> allPossibleCoordinate = new ArrayList<>();
//		for (Coordinate[] row: board) {
//			for (Coordinate targetc: row) {
//				if (isPossibleToMove(currentCoordinate, targetc)) {
//					allPossibleCoordinate.add(targetc);
//				}
//			}
//		}
		int currenti = currentCoordinate.getI();
		int currentj = currentCoordinate.getJ();
		int[] possiblei = new int[] { currenti - 1, currenti + 1 };
		int[] possiblej = new int[] { currentj - 1, currentj + 1 };
		for (int i : possiblei) {
			if (i >= 0 && i < width) {
				if (isPossibleToMove(currentCoordinate, board[i][currentj])) {
					allPossibleCoordinate.add(board[i][currentj]);
				}
			}
		}
		for (int j : possiblej) {
			if (j >= 0 && j < height) {
				if (isPossibleToMove(currentCoordinate, board[currenti][j])) {
					allPossibleCoordinate.add(board[currenti][j]);
				}
			}
		}
		return allPossibleCoordinate;
	}

	public boolean isPossibleToMove(Coordinate currentCoordinate, Coordinate targetc) {
		if (!targetc.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean isPossibleToAttack(Fighter attacker, Fighter target) {
		if (attacker.getTeam() == target.getTeam()) {
			return false;
		}

		int attackRange = attacker.getAttackRange();
		Coordinate c1 = attacker.getCoordinate();
		Coordinate c2 = target.getCoordinate();
		int distance = calculateDistance(c1, c2);
		if (distance <= attackRange) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPossibleToHeal(Fighter healer, Fighter target) {
		if (!(healer.getTeam() == target.getTeam())) {
			return false;
		}
		if (target.getHitPoint() == target.getMaxHitPoint()) {
			return false;
		}
		return true;
	}

	// -----------------------------------------other utility
	// methods----------------------------------------
	public int calculateDistance(Coordinate c1, Coordinate c2) {
		int i1 = c1.getI();
		int j1 = c1.getJ();
		int i2 = c2.getI();
		int j2 = c2.getJ();

		int di = Math.abs(i1 - i2);
		int dj = Math.abs(j1 - j2);
		int distance = di + dj;
		return distance;
	}

	public double calculatePercentSumOfHitPointRemain(int team) {
		double hitPointSum = 0;
		double maxHitPointSum = 0;
		if (team == 1) {
			for (Fighter f : Player1Fighters) {
				hitPointSum += f.getHitPoint();
				maxHitPointSum += f.getMaxHitPoint();
			}
		} else {
			for (Fighter f : Player2Fighters) {
				hitPointSum += f.getHitPoint();
				maxHitPointSum += f.getMaxHitPoint();
			}
		}
		return hitPointSum / maxHitPointSum;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<Fighter> getAllFighters() {
		return allFighters;
	}

	public int[][] getMap() {
		return map;
	}

	public void printMap() {
		for (int i = 0; i < rows; i++) {
			String eachRow = "";
			for (int j = 0; j < cols; j++) {
				eachRow += map[i][j] + " ";
			}
			System.out.println(eachRow);
		}
	}

	public Coordinate[][] getBoard() {
		return board;
	}

	// -----------------------------------------updater---------------------------------------
	public void updateBoard(Coordinate c) {
		int i = c.getI();
		int j = c.getJ();
		board[i][j] = c;
	}

	public void updateBoard(Coordinate c1, Coordinate c2) {
		updateBoard(c1);
		updateBoard(c2);
	}

	public void updateMap(Coordinate c) {
		int i = c.getI();
		int j = c.getJ();
		map[i][j] = c.getSymbol();
	}

	public void updateMap(Coordinate c1, Coordinate c2) {
		updateMap(c1);
		updateMap(c2);
	}

	public void update(Coordinate c) {
		c.update();
		updateBoard(c);
		updateMap(c);
	}

	public void update(Coordinate c1, Coordinate c2) {
		update(c1);
		update(c2);
	}

	public void resetReady() {
		for (Fighter f : allFighters) {
			f.setReady(true);
		}
	}
}
