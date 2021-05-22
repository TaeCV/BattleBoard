package gui;

import logic.GameConstant;
import logic.GameController;
import screen.GameScreen;

public class SimulationManager {
	private static PlayerPaneBattle P1PaneBattle;
	private static PlayerPaneBattle P2PaneBattle;
	private static PlayerPanePreBattle P1PanePreBattle;
	private static PlayerPanePreBattle P2PanePreBattle;
	private static BoardPane board;

	public static void initializeAllPane() {
		P1PaneBattle = new PlayerPaneBattle(GameConstant.TEAM_1);
		P2PaneBattle = new PlayerPaneBattle(GameConstant.TEAM_2);
		P1PanePreBattle = new PlayerPanePreBattle(GameConstant.TEAM_1);
		P2PanePreBattle = new PlayerPanePreBattle(GameConstant.TEAM_2);
		board = new BoardPane();
	}

	public static void updatePaneBattle() {
		board.update();
		P1PaneBattle.update();
		P2PaneBattle.update();
	}

	public static void updatePanePreBattle() {
		P1PanePreBattle.update();
		P2PanePreBattle.update();
	}

	public static PlayerPaneBattle getP1PaneBattle() {
		return P1PaneBattle;
	}

	public static PlayerPaneBattle getP2PaneBattle() {
		return P2PaneBattle;
	}

	public static PlayerPanePreBattle getP1PanePreBattle() {
		FighterBoxPreBattle selectedPreFighterP1 = (FighterBoxPreBattle) P1PanePreBattle.getChildren()
				.get(GameScreen.positionToSelectP1);
		selectedPreFighterP1.update();
		return P1PanePreBattle;
	}

	public static PlayerPanePreBattle getP2PanePreBattle() {
		FighterBoxPreBattle selectedPreFighterP2 = (FighterBoxPreBattle) P2PanePreBattle.getChildren()
				.get(GameScreen.positionToSelectP2);
		selectedPreFighterP2.update();
		return P2PanePreBattle;
	}

	public static BoardPane getBoard() {
		return board;
	}

	public static void fillUpPaneBattle() {
		P1PaneBattle.fillUp(P1PanePreBattle);
		P2PaneBattle.fillUp(P2PanePreBattle);
		GameController.getGameBoard().setPlayerFighters(P1PaneBattle.getFighters(), P2PaneBattle.getFighters());
	}
}