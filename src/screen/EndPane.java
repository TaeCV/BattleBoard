package screen;

import entity.base.ActionButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EndPane extends VBox {
	public Text winnerText;
	public Button quitButton;

	public EndPane() {
		setAlignment(Pos.CENTER);
		setPrefHeight(500);
		setPrefWidth(650);
		setSpacing(100);
		
		winnerText = new Text("Player " + ".getWinner()" +" Win!!");
		winnerText.setFont(new Font(100));
		
		quitButton = new ActionButton("QUIT");
		
		getChildren().addAll(winnerText, quitButton);		
	}
}
