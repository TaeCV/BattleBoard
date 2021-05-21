package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class EffectPane extends Canvas {
	private GraphicsContext gc;

	public EffectPane() {
		setWidth(700);
		setHeight(600);
		gc = getGraphicsContext2D();
	}

	public void clear() {
		gc.clearRect(0, 0, 600, 700);
	}
}
