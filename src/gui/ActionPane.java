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
	private Text moveText;
	private Text attackText;
	private Text healText;
	private Text blankActionText;
	private Text cancelText;

	public ActionPane(int symbol, int moveCount, int attackCount, int healCount) {
		setMaxSize(400, 200);
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.TAN, null, getInsets())));
		setSpacing(15);
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		if (GameController.isP1()) {
			cancelText = new Text("ESC) Cancel select!");
			cancelText.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 30));
			addition = 0;
		} else {
			cancelText = new Text("BACKSPACE) Cancel select!");
			cancelText.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 30));
			addition = 6;
		}
		moveText = new Text(Integer.toString(1 + addition) + ") MOVE");
		moveText.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 30));
		attackText = new Text(Integer.toString(2 + addition) + ") ATTACK");
		attackText.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 30));
		healText = new Text(Integer.toString(3 + addition) + ") HEAL	");
		healText.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 30));

		if (moveCount != 0) {
			getChildren().add(moveText);
		}
		if (attackCount != 0) {
			getChildren().add(attackText);
		}
		if ((symbol == 2 || symbol == 7 || symbol == 12 || symbol == 17) && healCount != 0) {
			getChildren().add(healText);
		}

		blankActionText = new Text("SPACE) Do nothing, skip!");
		blankActionText.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 30));
		getChildren().add(blankActionText);
		getChildren().add(cancelText);
	}
}
