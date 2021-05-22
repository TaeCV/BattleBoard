package logic;

import gui.SimulationManager;

public class GameController {
	// control game's processes

	private static GameBoard gameBoard;

	private static int P1Score;
	private static int P2Score;
	private static int roundCount;
	private static int turnCount;
	private static boolean isRoundDone; // done round
	private static boolean isRoundOver; // got the winner of the round
	private static boolean isGame;
	private static boolean isWin;
	private static boolean isP1;
	private static boolean isEndPreBattle;
	private static boolean isTurnDone;
	private static boolean isSelected;
	private static boolean isChose;
	private static String P1Name;
	private static String P2Name;
	private static String winner;

	public static void setDefault(String P1Name, String P2Name) {
		setGameBoard(new GameBoard());
		setP1Score(0);
		setP2Score(0);
		setRoundCount(1);
		setTurnCount(1);
		setP1Name(P1Name);
		setP2Name(P2Name);
		setP1(true);
		setGame(false);
		setSelected(false);
		setChose(false);
	}

	public static void update() {
		SimulationManager.getBoard().checkUnDoneMove();
		GameController.addTurnCount();
		GameController.setSelected(false);
		GameController.setChose(false);
		GameController.setTurnDone(true);
		setP1(!isP1());
		gameBoard.resetReady();
		gameBoard.update();
		if (GameController.getTurnCount() == GameConstant.MAX_TURN_PER_PLAYER * 2 + 1 && !isRoundOver) {
			checkWinnerAtTheEnd();
			GameController.setRoundOver(true);
		}
		if (isRoundOver()) {
			GameController.addRoundCount();
			setP1(GameController.getRoundCount() % 2 == 1);
			GameController.setTurnCount(1);
			GameController.getGameBoard().setDefault();
			GameController.setRoundDone(true); // finished managing data
			GameController.setRoundOver(false);
		}
		if ((GameController.getRoundCount() == GameConstant.MAX_ROUND + 1) || GameController.getP1Score() == 2
				|| GameController.getP2Score() == 2) {
			GameController.setGame(true);
			GameController.checkWinner();
		}
	}

	public static boolean checkRoundOver() {
		if (GameController.getGameBoard().Player1Fighters.size() == 0) {
			P2Score++;
			setRoundOver(true);
			return true;
		} else if (GameController.getGameBoard().Player2Fighters.size() == 0) {
			P1Score++;
			setRoundOver(true);
			return true;
		}
		return false;
	}

	private static void checkWinnerAtTheEnd() {
		if (gameBoard.Player1Fighters.size() > gameBoard.Player2Fighters.size()) {
			P1Score++;
		} else if (gameBoard.Player2Fighters.size() > gameBoard.Player1Fighters.size()) {
			P2Score++;
		} else if (LogicUtility.calculatePercentSumOfHitPointRemain(gameBoard.Player1Fighters) > LogicUtility
				.calculatePercentSumOfHitPointRemain(gameBoard.Player2Fighters)) {
			P1Score++;
		} else if (LogicUtility.calculatePercentSumOfHitPointRemain(gameBoard.Player2Fighters) > LogicUtility
				.calculatePercentSumOfHitPointRemain(gameBoard.Player1Fighters)) {
			P2Score++;
		}
	}

	private static void checkWinner() {
		if (GameController.getP1Score() > GameController.getP2Score()) {
			GameController.setWin(true);
			GameController.setWinner(GameController.getP1Name());
		} else if (GameController.getP1Score() < GameController.getP2Score()) {
			GameController.setWin(true);
			GameController.setWinner(GameController.getP2Name());
		} else {
			GameController.setWin(false);
		}
	}

	// =================utilities=================
	private static void addRoundCount() {
		GameController.roundCount++;
	}

	private static void addTurnCount() {
		GameController.turnCount++;
	}

	public static boolean isP1() {
		return isP1;
	}

	public static void setP1(boolean isP1) {
		GameController.isP1 = isP1;
	}

	public static GameBoard getGameBoard() {
		return gameBoard;
	}

	public static void setGameBoard(GameBoard gameBoard) {
		GameController.gameBoard = gameBoard;
	}

	public static int getP1Score() {
		return P1Score;
	}

	public static int getP2Score() {
		return P2Score;
	}

	public static int getRoundCount() {
		return roundCount;
	}

	public static void setRoundCount(int roundCount) {
		GameController.roundCount = roundCount;
	}

	public static boolean isGame() {
		return isGame;
	}

	public static void setP1Score(int p1Score) {
		P1Score = p1Score;
	}

	public static void setP2Score(int p2Score) {
		P2Score = p2Score;
	}

	public static void setGame(boolean isGame) {
		GameController.isGame = isGame;
	}

	public static String getWinner() {
		return winner;
	}

	public static void setWinner(String winner) {
		GameController.winner = winner;
	}

	public static String getP1Name() {
		return P1Name;
	}

	public static String getP2Name() {
		return P2Name;
	}

	public static void setP1Name(String p1Name) {
		P1Name = p1Name;
	}

	public static void setP2Name(String p2Name) {
		P2Name = p2Name;
	}

	public static boolean isRoundDone() {
		return isRoundDone;
	}

	public static void setRoundDone(boolean isRoundDone) {
		GameController.isRoundDone = isRoundDone;
	}

	public static boolean isRoundOver() {
		return isRoundOver;
	}

	public static void setRoundOver(boolean isRoundOver) {
		GameController.isRoundOver = isRoundOver;
	}

	public static int getTurnCount() {
		return turnCount;
	}

	public static void setTurnCount(int turnCount) {
		GameController.turnCount = turnCount;
	}

	public static boolean isEndPreBattle() {
		return isEndPreBattle;
	}

	public static boolean isTurnDone() {
		return isTurnDone;
	}

	public static void setEndPreBattle(boolean isEndPreBattle) {
		GameController.isEndPreBattle = isEndPreBattle;
	}

	public static void setTurnDone(boolean isTurnDone) {
		GameController.isTurnDone = isTurnDone;
	}

	public static boolean isSelected() {
		return isSelected;
	}

	public static void setSelected(boolean isSelect) {
		GameController.isSelected = isSelect;
	}

	public static boolean isChose() {
		return isChose;
	}

	public static void setChose(boolean isChoose) {
		GameController.isChose = isChoose;
	}

	public static boolean isWin() {
		return isWin;
	}

	public static void setWin(boolean isWin) {
		GameController.isWin = isWin;
	}
}
