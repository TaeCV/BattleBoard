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
		for (int i = 0; i < getWidth(); i += PIXEL) {
			if (i / PIXEL != 3) {
				gc.drawImage(RenderableHolder.brick_wall_Image, i, 0, PIXEL, PIXEL);
				gc.setFill(Color.CRIMSON);
				gc.beginPath();
				gc.moveTo(20 + i, 0);
				gc.lineTo(20 + i, 30);
				gc.lineTo(50 + i, 45);
				gc.lineTo(80 + i, 30);
				gc.lineTo(80 + i, 0);
				gc.closePath();
				gc.fill();
				gc.stroke();
			} else {
				gc.drawImage(RenderableHolder.waterfall_Image, i, 0, PIXEL, PIXEL);
			}

		}
		for (int i = 0; i < getHeight() - PIXEL; i += PIXEL) {
			for (int j = 0; j < getWidth(); j += PIXEL) {
				// Draw a floor
				if (j / PIXEL != 3) {
//					gc.setFill(Color.LIGHTGOLDENRODYELLOW);
//					gc.setStroke(Color.WHITE);
//					gc.fillRect(originY + j, originX + i, PIXEL, PIXEL);
//					gc.strokeRect(originY + j, originX + i, PIXEL, PIXEL);
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
		for (int i = 0; i < getWidth(); i += PIXEL) {
			if (i / PIXEL != 3) {
				gc.drawImage(RenderableHolder.stone_wall_Image, i, getHeight() - 50, PIXEL, PIXEL);
			} else {
				gc.drawImage(RenderableHolder.waterfall_Image, i - 5, getHeight() - 50, PIXEL + 10, PIXEL);
			}

		}
	}

}
