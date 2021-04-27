package gui.base;

import entity.base.Fighter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sharedObject.RenderableHolder;

public abstract class FighterBox extends Canvas {
	protected String symbol;
	protected GraphicsContext gc;
	protected Image image;
	protected Fighter fighter;

	public FighterBox(Fighter fighter, String symbol) {
		this.symbol = symbol;
		this.fighter = fighter;
		gc = getGraphicsContext2D();
	}

	public abstract void draw();

	public abstract void setImage();

	public Image getImage() {
		return image;
	}

	public Fighter getFighter() {
		return fighter;
	}

}
