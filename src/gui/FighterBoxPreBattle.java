package gui;

import entity.base.Fighter;
import entity.base.Updatable;
import gui.base.FighterBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sharedObject.RenderableHolder;

public class FighterBoxPreBattle extends FighterBox implements Updatable{

	public FighterBoxPreBattle(Fighter fighter, int symbol) {
		super(fighter, symbol);
		setWidth(150);
		setHeight(600 / 8);
		setImage();
		draw();
	}

	@Override
	public void draw(){
		gc.setFill(Color.CORNSILK);
		gc.fillRect(0, 0, 150, 600 / 8);
		gc.drawImage(image, 5, 5, 70, 70);
		gc.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		gc.strokeText(Integer.toString(fighter.getSymbol()), 90, 45);
		gc.strokeRect(0, 0, 150, 600 / 8);
	}

	@Override
	public void setImage() {
		// TODO Auto-generated method stub
		image = RenderableHolder.getHeadImage(symbol);	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		gc.setStroke(Color.RED);
		gc.setLineWidth(5);
		gc.strokeRect(0, 0, 150, 600 / 8);
	}
	
	public void resetStroke() {
		gc.clearRect(0, 0, 150, 600/8);
		gc.setFill(Color.CORNSILK);
		gc.fillRect(0, 0, 150, 600 / 8);
		gc.drawImage(image, 5, 5, 70, 70);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		gc.strokeText(Integer.toString(fighter.getSymbol()), 90, 45);
		gc.strokeRect(0, 0, 150, 600 / 8);
	}

}
