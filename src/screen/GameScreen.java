package screen;

import gui.BoardPane;
import gui.SimulationManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;

public class GameScreen {
	private String p1Name;
	private String p2Name;

	private Stage primaryStage;
	private Canvas gameCanvas;
	private GraphicsContext gameGC;
	private BorderPane root;
	private StackPane namePane;
	private Canvas gameStatusCanvas;

	public GameScreen(Stage primaryStage, String p1Name, String p2Name) {
		this.primaryStage = primaryStage;
		this.p1Name = p1Name;
		this.p2Name = p2Name;
		gameCanvas = new Canvas(700, 800);
		gameGC = gameCanvas.getGraphicsContext2D();

		Text gameName = new Text("BATTLE BOARD");
		gameName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 36));
		gameName.setStroke(Color.SILVER);
		namePane = new StackPane();
		namePane.setAlignment(Pos.CENTER);
		namePane.setPrefSize(1000, 100);
		namePane.setMaxSize(1000, 100);
		namePane.getChildren().add(gameName);
		namePane.setBackground(new Background(new BackgroundImage(RenderableHolder.gameNameBar_bg_Image, null, null,
				null, new BackgroundSize(1000, 100, false, false, false, false))));

		
		gameStatusCanvas = new Canvas(1000, 100);
		GraphicsContext gameStatusGC = gameStatusCanvas.getGraphicsContext2D();
		drawStatusBar(gameStatusGC);
		
		setUp();
	}

	public void draw(GraphicsContext gc) {
		gc.drawImage(null, 0, 0);
	}
	
	public void drawStatusBar(GraphicsContext gc) {
		gc.setFill(Color.BEIGE);
		gc.fillRect(0, 0, 1000, 100);

		gc.setFill(Color.BLACK);
		gc.setStroke(Color.DARKGRAY);
		gc.strokeLine(150, 0, 150, 100);
		gc.strokeLine(850, 0, 850, 100);
		Font font = Font.font("Palatino Linotype", FontWeight.BOLD, 16);
		gc.setFont(font);
		gc.fillText("Player 1: " + p1Name, 20, 55);
		gc.fillText("Player 2: " + p2Name, 870, 55);
	}

	public void setScene() {
		root = new BorderPane();
		root.setTop(gameStatusCanvas); // Status Bar
		root.setCenter(SimulationManager.getBoard());
		root.setBottom(namePane); // Name Bar
		
		Scene scene = new Scene(root, 1000, 800);
		primaryStage.setTitle("Battle Board");
		primaryStage.setScene(scene);
	}

	public void setUp() {
		SimulationManager.initializeAllPane();
		draw(gameGC);
		setScene();
	}
}
