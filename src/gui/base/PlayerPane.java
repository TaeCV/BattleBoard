package gui.base;

import entity.base.Fighter;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class PlayerPane extends VBox {
	protected int player;

	public PlayerPane(int player) {
		this.player = player;
		setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		setPrefSize(150, 600);
	}

	public abstract void addFighterBox(Fighter fighter);
}
