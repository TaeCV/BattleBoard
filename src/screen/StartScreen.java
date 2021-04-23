package screen;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;

public class StartScreen {
	private Stage primaryStage;
	private Canvas canvas;
	private GraphicsContext gc;
	public static MenuBar menu;
	private HowToPlay howToPlay;
	private PlayerNameBar playerNameBar;

	public static StackPane root;

	public StartScreen(Stage primaryStage) {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		canvas = new Canvas(1000, 800);
		gc = canvas.getGraphicsContext2D();
		menu = new MenuBar();
		howToPlay = new HowToPlay();
		playerNameBar = new PlayerNameBar();
		
		setUp();
	}

	public void setBackground(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.start_bg_Image, 0, 0, 1000, 800);
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.LIGHTGRAY);
		gc.setLineWidth(1);
		Font font = Font.font("Palatino Linotype", FontWeight.BOLD, 100);
		gc.setFont(font);
		gc.fillText("Battle Board!", 220, 150);
		gc.strokeText("Battle Board!", 220, 150);
	}

	public void draw(GraphicsContext gc) {
		root = new StackPane();
		root.getChildren().addAll(canvas, menu);

		Scene scene = new Scene(root, 1000, 800);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Battle Board");
	}

	public void setMenuAction() {
		menu.startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				root.getChildren().add(playerNameBar);
				menu.setButtonDisable();
			}
		});
		menu.howToPlayButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				root.getChildren().add(howToPlay);
				menu.setButtonDisable();
			}
		});
		menu.quitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Platform.exit();
			}
		});
	}

	public static void removeChildFromRoot() {
		int numOfChild = root.getChildren().size();
		root.getChildren().remove(numOfChild - 1);
		menu.setButtonUsable();
	}

	public void setUp() {
		setMenuAction();
		setBackground(gc);
		draw(gc);
	}

}
