package gui;

import entity.base.Fighter;
import gui.base.FighterBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.Updatable;
import sharedObject.RenderableHolder;

public class FighterBoxBattle extends FighterBox implements Updatable {

	public FighterBoxBattle(Fighter fighter, int symbol) {
		super(fighter, symbol);
		setWidth(150);
		setHeight(120);
		setImage();
		draw();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		gc.setFill(Color.LIGHTGREY);
		gc.fillRect(0, 0, 150, 120);
		gc.drawImage(image, 5, 5, 70, 70);
		gc.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		gc.strokeText(fighter.getName(), 80, 45);
		gc.setFill(Color.GREEN);
		gc.fillRect(25, 90, 100, 20);
		gc.strokeRect(0, 0, 150, 120);
	}

	@Override
	public void setImage() {
		image = RenderableHolder.getHeadImage(symbol);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		draw();
		gc.setFill(Color.BLACK);
		gc.fillRect(25 + (fighter.getHitPoint() / fighter.getMaxHitPoint()) * 100, 90,
				100 - (fighter.getHitPoint() / fighter.getMaxHitPoint()) * 100, 20);
		gc.strokeRect(0, 0, 150, 120);

	}

}
