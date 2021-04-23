package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;

public class BoardPane extends Canvas {
	private GraphicsContext gc;
	public final static int originX = 50;
	public final static int originY = 0;
	public final static int PIXEL = 100;

	public BoardPane() {
		setWidth(700);
		setHeight(600);
		gc = getGraphicsContext2D();

		gc.drawImage(RenderableHolder.wall_Image, 0, 0, getWidth(), 50);
		gc.drawImage(RenderableHolder.waterfall_Image, 300, 0, PIXEL, 50);

		for (int i = 0; i < getHeight() - PIXEL; i += PIXEL) {
			for (int j = 0; j < getWidth(); j += PIXEL) {
				// Draw a floor
				if (j / PIXEL != 3) {
					gc.drawImage(RenderableHolder.floor_Image, originY + j, originX + i, PIXEL, PIXEL);
				} else {
					gc.drawImage(RenderableHolder.river_Image, originY + j, originX + i, PIXEL, PIXEL);
					if (i / PIXEL == 1 | i / 100 == 3) {
						// Draw bridge in row 1 and row 3
						gc.drawImage(RenderableHolder.bridge_Image, originY + j - 5, originX + i - 10, PIXEL + 10,
								PIXEL + 20);
					}
				}

			}
		}
		gc.drawImage(RenderableHolder.stone_wall_Image, 0, getHeight() - 50, getWidth(), PIXEL);
		gc.drawImage(RenderableHolder.waterfall_Image,  295, getHeight() - 50, PIXEL + 10, PIXEL);
	}

}
