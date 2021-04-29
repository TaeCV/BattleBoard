package entity.base;

import entity.DuckFighter;
import logic.Coordinate;
import logic.GameController;

public abstract class Fighter implements Updatable {
	protected String type; // melee or range
	protected String name;

	protected double attack;
	protected double defense; // reduce damage income by defense percent
	protected int attackRange;
	protected double hitPoint;
	protected double maxHitPoint;
	protected int totalMoves;

	protected int team; // which side 1 or 2

	protected boolean isAlive;
	protected boolean isVisible;
	protected boolean isReady; // already took action that turn or not

	protected Coordinate coordinate;

	public Fighter(String type, int team, String name) {
		setType(type);
		setTeam(team);
		setName(name);
		setCoordinate(null);
		setBaseStats();
		setSpecialAbility();
	}

	public double attack(Fighter target) {
		if (target instanceof DuckFighter) {
			double randomDouble = Math.random() * 100;
			double dodgeChance = ((DuckFighter) target).getDodgeChance();
			if (randomDouble < dodgeChance) { // if less than missed
				return 0; // target dodged the attack
			}
		}
		double damageDone;
		damageDone = (100 - target.getDefense()) / 100 * attack;
		target.setHitPoint(target.getHitPoint() - damageDone);
		target.update();
		return damageDone;
	}

	public void move(Coordinate target) {
		coordinate.setFighter(null);
		setCoordinate(target);
		target.setFighter(this);
	}

	public boolean isAlive() {
		return isAlive;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setBaseStats() {
		if (type.equals("melee")) {
			attack = 40;
			defense = 25;
			hitPoint = 55;
			maxHitPoint = 55;
			attackRange = 1;
		} else if (type.equals("range")) {
			attack = 25;
			defense = 10;
			hitPoint = 40;
			maxHitPoint = 40;
			attackRange = 2;
		}
		totalMoves = 1;
		isAlive = true;
		isVisible = true;
		isReady = true;
	}

	public abstract void setSpecialAbility();

	public double getAttack() {
		return attack;
	}

	public void setAttack(double attack) {
		this.attack = attack;
	}

	public double getDefense() {
		return defense;
	}

	public void setDefense(double defense) {
		this.defense = defense;
	}

	public double getHitPoint() {
		return hitPoint;
	}

	public void setHitPoint(double hitPoint) {
		if (hitPoint < 0) {
			this.hitPoint = 0;
		} else if (hitPoint > maxHitPoint) {
			this.hitPoint = maxHitPoint;
		} else {
			this.hitPoint = hitPoint;
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public abstract int getSymbol();

	public int getAttackRange() {
		return attackRange;
	}

	public int getTotalMoves() {
		return totalMoves;
	}

	public void setTotalMoves(int totalMoves) {
		this.totalMoves = totalMoves;
	}

	public double getMaxHitPoint() {
		return maxHitPoint;
	}

	public void setMaxHitPoint(double maxHitPoint) {
		this.maxHitPoint = maxHitPoint;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public void setName(String name) {
		if (name.isBlank()) {
			name = "anonymous";
		}
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void update() {
		if (hitPoint <= 0) {
			isAlive = false;
		}
		if (!isAlive) {
			isVisible = false;
		}
		if (!isAlive) {
			GameController.getGameBoard().removeFighter(coordinate);
			setCoordinate(null);
		}
	}
}
