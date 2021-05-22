package screen;

import gui.base.ActionButton;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GameController;
import sharedObject.RenderableHolder;

public class EndScreen {
	private Stage primaryStage;
	private Pane root;
	private VBox textGroup;
	private HBox buttonGroup;
	private AnimationTimer endScreenSong;
	private Text congratulationText;
	private Text playerName;
	private Text tieText;
	private Button backButton;
	private Button quitButton;

	public EndScreen(Stage primaryStage) {
		this.primaryStage = primaryStage;
		root = new Pane();
		root.setMaxSize(1000, 800);

		setUpSongAndTextGroup();
		setUpButtonGroup();

		root.getChildren().addAll(textGroup, buttonGroup);

		Scene scene = new Scene(root, 1000, 800);
		primaryStage.setScene(scene);
	}

	private void setUpSongAndTextGroup() {
		if (GameController.isWin()) {
			endScreenSong = new AnimationTimer() {

				@Override
				public void handle(long arg0) {
					// TODO Auto-generated method stub
					if (!RenderableHolder.GameWin_Sound.isPlaying())
						RenderableHolder.GameWin_Sound.play();
				}
			};
			endScreenSong.start();
			root.setBackground(new Background(new BackgroundImage(RenderableHolder.end_bg_Image, null, null, null,
					new BackgroundSize(1000, 800, false, false, false, false))));

			textGroup = new VBox(60);
			textGroup.setPadding(new Insets(0, 0, 400, 400));
			textGroup.setAlignment(Pos.CENTER);
			textGroup.setLayoutX(100);
			textGroup.setLayoutY(170);
			congratulationText = new Text("CONGRATULATION");
			congratulationText.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 40));
			congratulationText.setFill(Color.WHITE);
			playerName = new Text(GameController.getWinner());
			playerName.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 65));
			playerName.setFill(Color.WHITE);
			textGroup.getChildren().addAll(congratulationText, playerName);

			primaryStage.setTitle("Congratulation!!");
		} else {
			endScreenSong = new AnimationTimer() {

				@Override
				public void handle(long arg0) {
					// TODO Auto-generated method stub
					if (!RenderableHolder.GameTie_Sound.isPlaying())
						RenderableHolder.GameTie_Sound.play();
				}
			};
			endScreenSong.start();
			root.setBackground(new Background(new BackgroundImage(RenderableHolder.tie_end_bg_Image, null, null, null,
					new BackgroundSize(1000, 800, false, false, false, false))));
			textGroup = new VBox(30);
			textGroup.setPadding(new Insets(0, 0, 400, 400));
			textGroup.setAlignment(Pos.CENTER);
			textGroup.setLayoutX(-90);
			textGroup.setLayoutY(80);
			tieText = new Text("TIE GAME");
			tieText.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 80));
			tieText.setFill(Color.WHITE);
			textGroup.getChildren().addAll(tieText);

			primaryStage.setTitle("TIE GAME!!");
		}
	}

	private void setUpButtonGroup() {
		buttonGroup = new HBox(20);
		buttonGroup.setPadding(new Insets(100));
		buttonGroup.setLayoutX(370);
		buttonGroup.setLayoutY(550);
		backButton = new ActionButton("Main Menu");
		backButton.setPrefSize(200, 50);
		backButton.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		backButton.setOnMouseClicked(event -> {
			// TODO Auto-generated method stub
			RenderableHolder.ButtonClick_Sound.play();
			RenderableHolder.GameWin_Sound.stop();
			endScreenSong.stop();
			new StartScreen(primaryStage);
		});

		quitButton = new ActionButton("QUIT");
		quitButton.setPrefSize(200, 50);
		quitButton.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		quitButton.setOnMouseClicked(event -> {
			// TODO Auto-generated method stub
			RenderableHolder.ButtonClick_Sound.play();
			RenderableHolder.GameWin_Sound.stop();
			endScreenSong.stop();
			Platform.exit();
		});

		buttonGroup.getChildren().addAll(backButton, quitButton);
	}
}
