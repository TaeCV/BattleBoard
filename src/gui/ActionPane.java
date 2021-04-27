package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.GameController;

public class ActionPane extends VBox {
	private int addition;

	public ActionPane(String symbol) {
		setMaxSize(300, 200);
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.TAN, null, getInsets())));
		setSpacing(15);
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		if (GameController.isP1()) {
			addition = 0;
		} else {
			addition = 6;
		}
		Text moveText = new Text(Integer.toString(1 + addition) + ") MOVE");
		moveText.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 30));
		Text attackText = new Text(Integer.toString(2 + addition) + ") ATTACK");
		attackText.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 30));
		Text healText = new Text(Integer.toString(3 + addition) + ") HEAL	");
		healText.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 30));

		getChildren().addAll(moveText, attackText);
		if (symbol.equals("HR") || symbol.equals("HM")) {
			getChildren().add(healText);
		}

	}
}
