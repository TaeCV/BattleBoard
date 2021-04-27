package gui;

import entity.base.Fighter;
import entity.base.Updatable;
import gui.base.FighterBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sharedObject.RenderableHolder;

public class FighterBoxBattle extends FighterBox implements Updatable {

	public FighterBoxBattle(Fighter fighter, String symbol) {
		super(fighter, symbol);
		setWidth(150);
		setHeight(120);
		setImage();
		draw();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		gc.setFill(Color.CORNSILK);
		gc.fillRect(0, 0, 150, 120);
		gc.drawImage(image, 5, 5, 70, 70);
		gc.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		gc.strokeText(fighter.getSymbol(), 90, 45);
		gc.setFill(Color.GREEN);
		gc.fillRect(25, 90, 100, 20);
		gc.strokeRect(0, 0, 150, 120);
	}

	@Override
	public void setImage() {
//		if (symbol.equals("DM")) {
//			image = RenderableHolder.duck_melee_head.png;
//		} else if (symbol.equals("DR")) {
//			image = RenderableHolder.duck_range_head.png;
//		} else if (symbol.equals("HM")) {
//			image = RenderableHolder.healer_melee_head.png;
//		} else if (symbol.equals("HR")) {
//			image = RenderableHolder.healer_range_head.png;
//		} else if (symbol.equals("SM")) {
//			image = RenderableHolder.speedy_head.png;
//		} else if (symbol.equals("SR")) {
//			image = RenderableHolder.speedy_range_head.png;
//		} else if (symbol.equals("TM")) {
//			image = RenderableHolder.tough_melee_head.png;
//		} else if (symbol.equals("TR")) {
//			image = RenderableHolder.tough_range_head.png;
//		} else if (symbol.equals("WM")) {
//			image = RenderableHolder.wild_melee_head.png;
//		} else if (symbol.equals("WR")) {
//			image = RenderableHolder.wild_range_head.png;
//		}
		image = RenderableHolder.sword_Image;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		gc.clearRect(0, 0, 150, 120);
		gc.setFill(Color.CORNSILK);
		gc.fillRect(0, 0, 150, 120);
		gc.drawImage(image, 5, 5, 70, 70);
		gc.strokeText(fighter.getSymbol(), 90, 45);
		gc.setFill(Color.GREEN);
		gc.fillRect(25, 90, (fighter.getHitPoint() / fighter.getMaxHitPoint()) * 100, 20);
		gc.setFill(Color.BLACK);
		gc.fillRect(25 + (fighter.getHitPoint() / fighter.getMaxHitPoint()) * 100, 90,
				100 - (fighter.getHitPoint() / fighter.getMaxHitPoint()) * 100, 20);
		gc.strokeRect(0, 0, 150, 120);

	}

}
