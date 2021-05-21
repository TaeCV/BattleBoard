package screen;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
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
	private MenuBar menu;
	private HowToPlay howToPlay;
	public static StackPane root;
	public static AnimationTimer startScreenSong;

	public StartScreen(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.canvas = new Canvas(1000, 800);
		this.gc = canvas.getGraphicsContext2D();
		this.menu = new MenuBar();
		this.howToPlay = new HowToPlay();

		setUp();
	}

	public void setBackground(GraphicsContext gc) {
		// Draw background picture
		gc.drawImage(RenderableHolder.start_bg_Image, 0, 0, 1000, 800);
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.LIGHTGRAY);
		gc.setLineWidth(1.5);

		// Draw title bar with game's name
		gc.drawImage(RenderableHolder.title_bg_Image, 130, 25, 765, 175);
		gc.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 100));
		gc.fillText("Battle Board!", 220, 150);
		gc.strokeText("Battle Board!", 220, 150);
	}

	public void drawScene(GraphicsContext gc) {
		root = new StackPane();
		root.getChildren().addAll(canvas, menu);
		Scene scene = new Scene(root, 1000, 800);
		startScreenSong = new AnimationTimer() {
			public void handle(long now) {
				if (!RenderableHolder.StartScreen_Music.isPlaying())
					RenderableHolder.StartScreen_Music.play();
			}
		};
		startScreenSong.start();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Battle Board");
	}

	public void setMenuAction() {
		menu.startButton.setOnMouseClicked(event -> {
			RenderableHolder.ButtonClick_Sound.play();
			root.getChildren().add(new PlayerNameBar(primaryStage));
		});
		menu.howToPlayButton.setOnMouseClicked(event -> {
			RenderableHolder.ButtonClick_Sound.play();
			root.getChildren().add(howToPlay);
		});
		menu.quitButton.setOnMouseClicked(event -> {
			RenderableHolder.ButtonClick_Sound.play();
			Platform.exit();
		});
	}

	public static void removeChildFromRoot() {
		int numOfChild = root.getChildren().size();
		root.getChildren().remove(numOfChild - 1);
	}

	public void setUp() {
		setMenuAction();
		setBackground(gc);
		drawScene(gc);
	}

}
