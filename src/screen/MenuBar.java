package screen;

import gui.base.ActionButton;
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
}