package screen;

import gui.SimulationManager;
import gui.base.PlayerPane;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GameBoard;
import logic.GameConstant;
import logic.GameController;
import sharedObject.RenderableHolder;

public class GameScreen {
	private String P1Name;
	private String P2Name;

	private Stage primaryStage;
	private Scene scene;
	private BorderPane root;

	private Canvas gameCanvas;
	private Canvas statusCanvas;

	private HBox statusPane;
	private StackPane namePane;
	private PlayerPane P1Pane;
	private PlayerPane P2Pane;

	private GameBoard gameBoard;
	private AnimationTimer gameScreenSong;

	public static GraphicsContext gameGC;
	public static GraphicsContext statusGC;
	public static GraphicsContext effectGC;

	public static int gameTime;
	public static int positionToSelectP1 = 0;
	public static int positionToSelectP2 = 0;

	public static int timeToDrawAnimation;
	public static int[] selectedPixel;
	public static boolean P1;
	public static String selectedFighterType;

	public static int effectSymbol;

	public static StackPane board;

	public GameScreen(Stage primaryStage, String P1Name, String P2Name) {
		this.primaryStage = primaryStage;
		this.P1Name = P1Name;
		this.P2Name = P2Name;
		gameBoard = GameController.getGameBoard();
		board = new StackPane();
		gameScreenSong = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				if (!RenderableHolder.GameScreen_Music.isPlaying())
					RenderableHolder.GameScreen_Music.play();
			}
		};
		gameScreenSong.start();
		initializeGame();
	}

	private void paintGameScreenComponent() {
		// TODO Auto-generated method stub
		for (int i = 0; i < GameConstant.N_ROWS; i++) {
			for (int j = 0; j < GameConstant.N_COLS; j++) {
				if (gameBoard.map[i][j] <= 20) {
					gameGC.drawImage(RenderableHolder.getFullBodyImage(gameBoard.map[i][j]),
							GameConstant.ORIGIN_X + (j * GameConstant.BOX_WIDTH),
							GameConstant.ORIGIN_Y + (i * GameConstant.BOX_HEIGHT) - 56, 100, 100);
				}
			}
		}
	}

	private void drawAnimation() {
		if (timeToDrawAnimation > 0 && effectSymbol != 0) {
			gameGC.drawImage(RenderableHolder.getEffectImage(effectSymbol), selectedPixel[0], selectedPixel[1] - 56,
					100, 100);
			timeToDrawAnimation--;
		} else {
			effectSymbol = 0;
		}
	}

	private void drawNamePane() {
		Text gameName = new Text("BATTLE BOARD");
		gameName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 36));
		gameName.setStroke(Color.SILVER);
		namePane = new StackPane();
		namePane.setAlignment(Pos.CENTER);
		namePane.setPrefSize(1000, 100);
		namePane.getChildren().add(gameName);
		namePane.setBackground(new Background(new BackgroundImage(RenderableHolder.gameNameBar_bg_Image, null, null,
				null, new BackgroundSize(1000, 100, false, false, false, false))));
	}

	// Set up Header pane to show time, round and win count
	private void drawStatusPane() {
		statusPane = new HBox();
		statusPane.setMaxSize(1000, 100);
		// Including player 1 tag, time and round , player 2 tag

		StackPane P1Tag = new StackPane();
		P1Tag.setBorder(new Border(new BorderStroke(Color.SILVER, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		P1Tag.setPrefSize(100, 100);
		P1Tag.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Text P1NameText = new Text("Player 1:\n" + P1Name);
		P1NameText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		P1NameText.setFill(Color.WHITE);
		P1Tag.getChildren().add(P1NameText);

		statusCanvas = new Canvas(800, 100);
		statusGC = statusCanvas.getGraphicsContext2D();

		StackPane P2Tag = new StackPane();
		P2Tag.setBorder(new Border(new BorderStroke(Color.SILVER, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		P2Tag.setPrefSize(100, 100);
		P2Tag.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		Text P2NameText = new Text("Player 2:\n" + P2Name);
		P2NameText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		P2NameText.setFill(Color.WHITE);
		P2Tag.getChildren().add(P2NameText);

		statusPane.getChildren().addAll(P1Tag, statusCanvas, P2Tag);

	}

	// Receive input from user
	private void addListener(Scene scene) {
		scene.setOnKeyPressed((KeyEvent e) -> {
			InputUtility.setKeyPressed(e.getCode(), true);
		});

		scene.setOnKeyReleased((KeyEvent e) -> {
			InputUtility.setKeyPressed(e.getCode(), false);
		});
	}

	// Add all nodes to root and set new scene
	private void setScene() {
		root = new BorderPane();
		root.setTop(statusPane); // Status Pane
		root.setLeft(P1Pane);
		root.setCenter(board); // Set Board Bar
		root.setRight(P2Pane);
		root.setBottom(namePane); // Name Pane

		scene = new Scene(root, 1000, 800);
		addListener(scene);
		primaryStage.setTitle("Battle Board");
		primaryStage.setScene(scene);
	}

	// Draw time and round each fame
	private void drawTimeAndRound() {
		statusGC.clearRect(0, 0, 800, 100);
		statusGC.setFill(Color.LIGHTGREY);
		statusGC.fillRect(0, 0, 800, 100);

		statusGC.drawImage(RenderableHolder.sword_Image, 50, 15, 65, 65);
		statusGC.drawImage(RenderableHolder.sword_Image, 680, 15, 65, 65);
		statusGC.setFill(Color.BLUE);
		Font font1 = Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 40);
		statusGC.setFont(font1);
		statusGC.fillText(Integer.toString(GameController.getP1Score()), 10, 65);
		statusGC.setFill(Color.RED);
		statusGC.fillText(Integer.toString(GameController.getP2Score()), 770, 65);
		Font font2 = Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 48);
		statusGC.setFont(font2);
		statusGC.setFill(Color.BLACK);
		statusGC.fillText("ROUND " + GameController.getRoundCount() + " - " + (GameController.getTurnCount() + 1) / 2,
				150, 65);
		statusGC.fillText("TIME ", 450, 65);

		if (10 <= gameTime) {
			statusGC.fillText(Integer.toString(gameTime), 600, 65);
		} else {
			statusGC.setFill(Color.RED);
			statusGC.fillText(Integer.toString(gameTime), 615, 65);
		}

	}

	private void initializeGame() {
		GameScreen.timeToDrawAnimation = 0;
		SimulationManager.initializeAllPane();
		gameCanvas = SimulationManager.getBoard();
		gameGC = gameCanvas.getGraphicsContext2D(); // Get the starter Board
		board.getChildren().addAll(gameCanvas);
		drawNamePane(); // Set namePane
		drawStatusPane(); // Set default statusPane without time and round
		P1Pane = SimulationManager.getP1PanePreBattle();
		P2Pane = SimulationManager.getP2PanePreBattle();
		GameController.setEndPreBattle(false);

		final long startNanoTime = System.nanoTime();
		AnimationTimer timerPreBattle = new AnimationTimer() {
			public void handle(long currentNanoTime) {
				double t = ((currentNanoTime - startNanoTime) / 1000000000.0);
				gameTime = (int) (GameConstant.PRE_BATTLE_PHASE_TIME - t + 1);
				drawTimeAndRound();
				if (gameTime <= 0 || (P1Pane.getChildren().size() == 3 && P2Pane.getChildren().size() == 3)) {
					GameController.setEndPreBattle(true);
					SimulationManager.fillUpPaneBattle();
					this.stop();
					initializeBattle();
				}
			}
		};

		AnimationTimer animationTimer = new AnimationTimer() {
			public void handle(long now) {
				SimulationManager.updatePanePreBattle();
				InputUtility.removeKeyPressed();
				if (GameController.isEndPreBattle()) {
					this.stop();
				}
			}
		};

		timerPreBattle.start();
		animationTimer.start();
		setScene();
	}

	private void initializeBattle() {
		GameController.setTurnDone(false);
		GameController.setRoundDone(false);
		P1Pane = SimulationManager.getP1PaneBattle();
		P2Pane = SimulationManager.getP2PaneBattle();
		final long startNanoTime = System.nanoTime();
		AnimationTimer timerPerTurn = new AnimationTimer() {
			public void handle(long currentNanoTime) {
				double t = ((currentNanoTime - startNanoTime) / 1000000000.0);
				gameTime = (int) (GameConstant.BATTLE_PHASE_TIME_PER_TURN - t + 1);
				drawTimeAndRound();
				if (gameTime <= 0
						|| (GameController.isP1() && gameBoard.getAllReadyPlayerFightersCoordinate(1).size() == 0)
						|| (!GameController.isP1() && gameBoard.getAllReadyPlayerFightersCoordinate(2).size() == 0)
						|| GameController.checkRoundOver()) {
					this.stop();
					while (GameScreen.board.getChildren().size() > 1) {
						GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
					}
					GameController.update();
				}
			}
		};

		AnimationTimer animationTimer = new AnimationTimer() {
			public void handle(long now) {
				SimulationManager.updatePaneBattle();
				paintGameScreenComponent();
				drawAnimation();
				InputUtility.removeKeyPressed();
				if (GameController.isTurnDone()) {
					System.out.println("====================================");
					SimulationManager.getBoard().resetActorCoordinate();
					System.out.println("====================================");
					this.stop();
					if (!GameController.isRoundDone()) {
						initializeBattle();
					} else if (GameController.isRoundDone() && !GameController.isGame()) {
						board.getChildren().clear();
						initializeGame();
					} else {
						RenderableHolder.GameScreen_Music.stop();
						gameScreenSong.stop();
						new EndScreen(primaryStage);
					}
				}
			}
		};

		timerPerTurn.start();
		animationTimer.start();
		setScene();
	}
}
