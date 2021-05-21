package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import entity.*;
import entity.base.Fighter;
import entity.base.HitPointRegenerable;

public class GameBoard {
	// private Cell[][] cellBoard;
	private Coordinate[][] board;
	public int[][] map;

	private int rows;
	private int cols;

	public ArrayList<Fighter> Player1Fighters;
	public ArrayList<Fighter> Player2Fighters;

	private int width;
	private int height;

	public GameBoard() {
		rows = GameController.N_ROWS;
		cols = GameController.N_COLS;
		board = new Coordinate[rows][cols];
		map = new int[rows][cols];

		setWidth(rows);
		setHeight(cols);
		setDefault();
	}

	// --------------------------------------initializer
	// methods-------------------------------------------
	// method random fighters return Fighter[8]
	public Fighter[] getRandomFighters(int team) {
		Fighter[] randomFighters = new Fighter[8];
		Random r = new Random();
		for (int i = 0; i < 8; i++) {
			int x = r.nextInt(10);
			switch (x) {
			case 0:
				randomFighters[i] = new DuckFighter(GameController.MELEE_TYPE_STRING, team);
				break;
			case 1:
				randomFighters[i] = new HealerFighter(GameController.MELEE_TYPE_STRING, team);
				break;
			case 2:
				randomFighters[i] = new SpeedyFighter(GameController.MELEE_TYPE_STRING, team);
				break;
			case 3:
				randomFighters[i] = new ToughFighter(GameController.MELEE_TYPE_STRING, team);
				break;
			case 4:
				randomFighters[i] = new WildFighter(GameController.MELEE_TYPE_STRING, team);
				break;
			case 5:
				randomFighters[i] = new DuckFighter(GameController.RANGE_TYPE_STRING, team);
				break;
			case 6:
				randomFighters[i] = new HealerFighter(GameController.RANGE_TYPE_STRING, team);
				break;
			case 7:
				randomFighters[i] = new SpeedyFighter(GameController.RANGE_TYPE_STRING, team);
				break;
			case 8:
				randomFighters[i] = new ToughFighter(GameController.RANGE_TYPE_STRING, team);
				break;
			case 9:
				randomFighters[i] = new WildFighter(GameController.RANGE_TYPE_STRING, team);
				break;
			}
		}
		return randomFighters;
	}

	public void setPlayerFighters(ArrayList<Fighter> fighters1, ArrayList<Fighter> fighters2) {
		for (int i = 0; i < 5; i++) {
			Fighter fighter = fighters1.get(i);
			addFighter(fighter, board[i][0], GameController.TEAM_1);
		}
		for (int i = 0; i < 5; i++) {
			Fighter fighter = fighters2.get(i);
			addFighter(fighter, board[i][6], GameController.TEAM_2);
		}
	}

	public void setDefault() {
		System.out.println("Set Default :" + GameController.getRoundCount());
		Player1Fighters = new ArrayList<>();
		Player2Fighters = new ArrayList<>();
		setBoardAndMap();
	}

	public void setBoardAndMap() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new Coordinate(i, j);
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
			for (Fighter fighter : Player1Fighters) {
				if (fighter.isReady()) {
					allReadyFightersCoordinate.add(fighter.getCoordinate());
				}
			}
		} else {
			for (Fighter fighter : Player2Fighters) {
				if (fighter.isReady()) {
					allReadyFightersCoordinate.add(fighter.getCoordinate());
				}
			}
		}
		Collections.sort(allReadyFightersCoordinate);
		return allReadyFightersCoordinate;
	}

	public ArrayList<Coordinate> getAllPossibleToMoveCoordinate(Coordinate currentCoordinate) {
		// return all possible coordinate that a Fighter at currentCoordinate can move
		// to
		// logic is move 1 block at a time
		ArrayList<Coordinate> possibleCoordinate = new ArrayList<>();
		possibleCoordinate.add(currentCoordinate);
		ArrayList<Coordinate> firstTime = getAllPossibleToMoveDistance1(currentCoordinate);
		possibleCoordinate.addAll(firstTime);
		if (currentCoordinate.getFighter() instanceof SpeedyFighter) {
			for (Coordinate current : firstTime) {
				for (Coordinate possible : getAllPossibleToMoveDistance1(current)) {
					if ((!possibleCoordinate.contains(possible)) && LogicUtility.isPossibleToMove(current, possible)) {
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
		if (attacker.getTeam() == GameController.TEAM_1) {
			for (Fighter target : Player2Fighters) {
				if (LogicUtility.isPossibleToAttack(attacker, target)) {
					allPossibleTargets.add(target.getCoordinate());
				}
			}
		} else if (attacker.getTeam() == GameController.TEAM_2) {
			for (Fighter target : Player1Fighters) {
				if (LogicUtility.isPossibleToAttack(attacker, target)) {
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
		if (healer.getTeam() == GameController.TEAM_1) {
			for (Fighter ally : Player1Fighters) {
				if (LogicUtility.isPossibleToGetHealed(ally)) {
					allPossibleAllies.add(ally.getCoordinate());
				}
			}
		} else if (healer.getTeam() == GameController.TEAM_2) {
			for (Fighter ally : Player2Fighters) {
				if (LogicUtility.isPossibleToGetHealed(ally)) {
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
		update(actorCoordinate, targetCoordinate);
	}

	public double takeAttack(Coordinate attackerCoordinate, Coordinate targetCoordinate) {
		// receive attackerCoordinate and targetCoordinate from input
		Fighter attacker = attackerCoordinate.getFighter();
		Fighter target = targetCoordinate.getFighter();
		attacker.setReady(false);
		update(attackerCoordinate, targetCoordinate);
		double damageDone = attacker.attack(target);
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
	public void addFighter(Fighter fighter, Coordinate coordinate, int team) {
		if (team == GameController.TEAM_1) {
			Player1Fighters.add(fighter);
		} else if (team == GameController.TEAM_2) {
			Player2Fighters.add(fighter);
		}
		coordinate.setFighter(fighter);
		fighter.setCoordinate(coordinate);
		update(coordinate);
	}

	public void removeFighter(Coordinate coordinate) {
		Fighter removing = coordinate.getFighter();
		if (removing.getTeam() == 1) {
			Player1Fighters.remove(removing);
		} else {
			Player2Fighters.remove(removing);
		}
		System.out.println(1 + ": " + Player1Fighters.size());
		System.out.println(2 + ": " + Player2Fighters.size());
		coordinate.setFighter(null);

		update(coordinate);
	}

	// -----------------------------------------check possibilities
	// methods----------------------------------------
	public ArrayList<Coordinate> getAllPossibleToMoveDistance1(Coordinate currentCoordinate) {
		// return all Coordinate that can move to by only 1 distance far
		ArrayList<Coordinate> allPossibleCoordinate = new ArrayList<>();

		int currenti = currentCoordinate.getI();
		int currentj = currentCoordinate.getJ();
		int[] possiblei = new int[] { currenti - 1, currenti + 1 };
		int[] possiblej = new int[] { currentj - 1, currentj + 1 };
		for (int i : possiblei) {
			if (i >= 0 && i < width) {
				if (LogicUtility.isPossibleToMove(currentCoordinate, board[i][currentj])) {
					allPossibleCoordinate.add(board[i][currentj]);
				}
			}
		}
		for (int j : possiblej) {
			if (j >= 0 && j < height) {
				if (LogicUtility.isPossibleToMove(currentCoordinate, board[currenti][j])) {
					allPossibleCoordinate.add(board[currenti][j]);
				}
			}
		}
		return allPossibleCoordinate;
	}

	// -----------------------------------------other utility
	// methods----------------------------------------

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

	public int[][] getMap() {
		return map;
	}

	public void printMap() {
		for (int i = 0; i < rows; i++) {
			String eachRow = "";
			for (int j = 0; j < cols; j++) {
				if (map[i][j] >= 10) {
					eachRow += map[i][j] + " ";
				} else {
					eachRow += " " + map[i][j] + " ";
				}
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

	public void update() { // update every turn
		for (Fighter fighter : Player1Fighters) {
			if (fighter instanceof HitPointRegenerable) {
				((HitPointRegenerable) fighter).regenerateHitPoint();
			}
		}
		for (Fighter fighter : Player2Fighters) {
			if (fighter instanceof HitPointRegenerable) {
				((HitPointRegenerable) fighter).regenerateHitPoint();
			}
		}
	}

	public void resetReady() {
		for (Fighter fighter : Player1Fighters) {
			fighter.setReady(true);
		}
		for (Fighter fighter : Player2Fighters) {
			fighter.setReady(true);
		}
	}
}
