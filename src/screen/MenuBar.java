package screen;

import entity.base.ActionButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuBar extends VBox {
	public Button startButton;
	public Button howToPlayButton;
	public Button quitButton;

	public MenuBar() {
		setAlignment(Pos.BOTTOM_CENTER);
		setPrefHeight(300);
		setPrefWidth(180);
		setSpacing(30);
		setPadding(new Insets(200, 50, 200, 50));
		startButton = new ActionButton("START GAME");
		howToPlayButton = new ActionButton("HOW TO PLAY");
		quitButton = new ActionButton("QUIT");

		getChildren().addAll(startButton, howToPlayButton, quitButton);
	}

	public void setButtonDisable() {
		startButton.setDisable(true);
		howToPlayButton.setDisable(true);
		quitButton.setDisable(true);
	}

	public void setButtonUsable() {
		startButton.setDisable(false);
		howToPlayButton.setDisable(false);
		quitButton.setDisable(false);
	}
}