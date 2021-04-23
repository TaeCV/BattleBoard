package gui;

public class SimulationManager {
	private static Player1Pane p1Pane;
	private static Player2Pane p2Pane;
	private static BoardPane board;
	
	public static void initializeAllPane() {
		p1Pane = new Player1Pane();
		p2Pane = new Player2Pane();
		board = new BoardPane();
	}
	
	public static void updatePane() {
		//p1Pane.update();
		//p2Pane.update();
	}

	public static Player1Pane getP1Pane() {
		return p1Pane;
	}

	public static Player2Pane getP2Pane() {
		return p2Pane;
	}
	
	public static BoardPane getBoard() {
		return board;
	}
}
