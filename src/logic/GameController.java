package logic;

import gui.SimulationManager;
import screen.GameScreen;

public class GameController {
	public static final int originX = 0;
	public static final int originY = 100;
	public static final int PIXEL_X = 100;
	public static final int PIXEL_Y = 88;
	public static final int N_ROWS = 5;
	public static final int N_COLS = 7;
	public static final int PRE_BATTLE_PHASE_TIME = 60;
	public static final int BATTLE_PHASE_TIME = 60;
	public static final int MAX_TURN_PER_PLAYER = 9;
	public static final int MAX_ROUND = 3;

	private static GameBoard gameBoard;

	private static int P1Score;
	private static int P2Score;
	private static int roundCount;
	private static int turnCount;
	private static boolean isRoundDone;
	private static boolean isRoundOver; // got a winner of the round
	private static boolean isGame;
	private static boolean isWin;
	private static boolean isP1;
	private static boolean isEndPreBattle;
	private static boolean isTurnDone;
	private static boolean isSelect;
	private static boolean isChoose;
	private static String winner;
	private static String P1Name;
	private static String P2Name;

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
		setSelect(false);
		setChoose(false);
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

	public static boolean isGame() {
		return isGame;
	}

	public static void setP1Score(int p1Score) {
		P1Score = p1Score;
	}

	public static void setP2Score(int p2Score) {
		P2Score = p2Score;
	}

	public static void setRoundCount(int roundCount) {
		GameController.roundCount = roundCount;
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

	public static boolean isSelect() {
		return isSelect;
	}

	public static void setSelect(boolean isSelect) {
		GameController.isSelect = isSelect;
	}

	public static boolean isChoose() {
		return isChoose;
	}

	public static void setChoose(boolean isChoose) {
		GameController.isChoose = isChoose;
	}

	public static boolean isWin() {
		return isWin;
	}

	public static void setWin(boolean isWin) {
		GameController.isWin = isWin;
	}

	public static void addRoundCount() {
		GameController.roundCount++;
	}

	public static void addTurnCount() {
		GameController.turnCount++;
	}

	public static void update() {
		while (GameScreen.board.getChildren().size() > 1) {
			GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
		}
		GameController.addTurnCount();
		GameController.setSelect(false);
		GameController.setChoose(false);
		GameController.setTurnDone(true);
		setP1(!isP1());
		gameBoard.resetReady();
		gameBoard.update();
		if (GameController.getTurnCount() == GameController.MAX_TURN_PER_PLAYER * 2 + 1) {
			checkWinnerAtTheEnd();
			GameController.setRoundOver(true);
		}
		if (isRoundOver()) {
			GameController.addRoundCount();
			setP1(GameController.getRoundCount() % 2 == 1);
			GameController.setTurnCount(1);
			GameController.getGameBoard().setDefault();
			GameController.setRoundDone(true); // finished round
		}
		if ((GameController.getRoundCount() > GameController.MAX_ROUND) || GameController.getP1Score() > 1
				|| GameController.getP2Score() > 1) {
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

	public static void checkWinnerAtTheEnd() {
		if (gameBoard.Player1Fighters.size() > gameBoard.Player2Fighters.size()) {
			P1Score++;
		} else if (gameBoard.Player2Fighters.size() > gameBoard.Player1Fighters.size()) {
			P2Score++;
		} else if (gameBoard.calculatePercentSumOfHitPointRemain(1) > gameBoard
				.calculatePercentSumOfHitPointRemain(2)) {
			P1Score++;
		} else if (gameBoard.calculatePercentSumOfHitPointRemain(2) > gameBoard
				.calculatePercentSumOfHitPointRemain(1)) {
			P2Score++;
		}
	}

	public static void checkWinner() {
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
}
