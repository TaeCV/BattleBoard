package entity.base;

import entity.DuckFighter;
import logic.Coordinate;
import logic.GameConstant;
import logic.Updatable;

public abstract class Fighter implements Updatable {
	// Fighter is battlers on the board

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

	private final int BASE_MELEE_ATTACK = 40;
	private final int BASE_MELEE_DEFENSE = 25;
	private final int BASE_MELEE_MAXHITPOINT = 60;
	private final int MELEE_ATTACK_RANGE = 1;

	private final int BASE_RANGE_ATTACK = 25;
	private final int BASE_RANGE_DEFENSE = 10;
	private final int BASE_RANGE_MAXHITPOINT = 50;
	private final int RANGE_ATTACK_RANGE = 2;

	private final int BASE_TOTAL_MOVES = 1;

	public Fighter(String type, int team) {
		setType(type);
		setTeam(team);
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

	public void move(Coordinate targetCoordinate) {
		coordinate.setFighter(null);
		setCoordinate(targetCoordinate);
		targetCoordinate.setFighter(this);
	}

	private void setBaseStats() {
		if (type.equals(GameConstant.MELEE_TYPE_STRING)) {
			setAttack(BASE_MELEE_ATTACK);
			setDefense(BASE_MELEE_DEFENSE);
			setMaxHitPoint(BASE_MELEE_MAXHITPOINT);
			setHitPoint(maxHitPoint);
			setAttackRange(MELEE_ATTACK_RANGE);
		} else if (type.equals(GameConstant.RANGE_TYPE_STRING)) {
			setAttack(BASE_RANGE_ATTACK);
			setDefense(BASE_RANGE_DEFENSE);
			setMaxHitPoint(BASE_RANGE_MAXHITPOINT);
			setHitPoint(maxHitPoint);
			setAttackRange(RANGE_ATTACK_RANGE);
		}
		setTotalMoves(BASE_TOTAL_MOVES);
		setAlive(true);
		setVisible(true);
		setReady(true);
	}

	protected abstract void setSpecialAbility();

	public abstract int getSymbol();

	protected void setAttack(double attack) {
		this.attack = attack;
	}

	protected double getDefense() {
		return defense;
	}

	protected void setDefense(double defense) {
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

	public boolean isAlive() {
		return isAlive;
	}

	private void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public int getAttackRange() {
		return attackRange;
	}

	private void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}

	public int getTotalMoves() {
		return totalMoves;
	}

	protected void setTotalMoves(int totalMoves) {
		this.totalMoves = totalMoves;
	}

	public double getMaxHitPoint() {
		return maxHitPoint;
	}

	protected void setMaxHitPoint(double maxHitPoint) {
		this.maxHitPoint = maxHitPoint;
	}

	public int getTeam() {
		return team;
	}

	private void setTeam(int team) {
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

	public String getName() {
		return this.name;
	}

	protected void setName(String name) {
		if (name.isBlank()) {
			name = "anonymous";
		}
		this.name = name;
	}

	public void update() {
		if (hitPoint <= 0) {
			setAlive(false);
		}
		if (!isAlive) {
			setVisible(false);
		}

	}
}
