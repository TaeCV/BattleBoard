package screen;

import gui.base.ActionButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GameController;
import sharedObject.RenderableHolder;

public class EndScreen {
	private Pane root;
	private VBox textGroup;

	public EndScreen(Stage primaryStage) {
		root = new Pane();		
		root.setMaxSize(1000, 800);		

		if (GameController.isWin()) {
			root.setBackground(new Background(new BackgroundImage(RenderableHolder.end_bg_Image, null, null, null,
					new BackgroundSize(1000, 800, false, false, false, false))));
			
			textGroup = new VBox(60);
			textGroup.setPadding(new Insets(0, 0, 400, 400));
			textGroup.setAlignment(Pos.CENTER);
			textGroup.setLayoutX(100);
			textGroup.setLayoutY(170);
			Text congratulationText = new Text("CONGRATULATION");
			congratulationText.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 40));
			congratulationText.setFill(Color.WHITE);
			Text playerName = new Text(GameController.getWinner());
			playerName.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 65));
			playerName.setFill(Color.WHITE);
			textGroup.getChildren().addAll(congratulationText, playerName);
		} else {
			root.setBackground(new Background(new BackgroundImage(RenderableHolder.tie_end_bg_Image, null, null, null,
					new BackgroundSize(1000, 800, false, false, false, false))));
			textGroup = new VBox(30);
			textGroup.setPadding(new Insets(0, 0, 400, 400));
			textGroup.setAlignment(Pos.CENTER);
			textGroup.setLayoutX(-90);
			textGroup.setLayoutY(80);
			Text tieText = new Text("TIE GAME");
			tieText.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 80));
			tieText.setFill(Color.WHITE);
			textGroup.getChildren().addAll(tieText);
		}				

		HBox buttonGroup = new HBox(20);
		buttonGroup.setPadding(new Insets(100));
		buttonGroup.setLayoutX(370);
		buttonGroup.setLayoutY(550);
		Button backButton = new ActionButton("Main Menu");
		backButton.setPrefSize(200, 50);
		backButton.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				new StartScreen(primaryStage);
			}
		});

		Button quitButton = new ActionButton("QUIT");
		quitButton.setPrefSize(200, 50);
		quitButton.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		quitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Platform.exit();
			}
		});

		buttonGroup.getChildren().addAll(backButton, quitButton);
		root.getChildren().addAll(textGroup, buttonGroup);

		Scene scene = new Scene(root, 1000, 800);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Congratulation!!");
	}
}
