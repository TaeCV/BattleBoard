package gui;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class BattleBoard implements IRenderable {
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return -9999;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		clear(gc);
		gc.drawImage(RenderableHolder.board_bg_Image, 0, 0, 700, 600);
	}

	public void clear(GraphicsContext gc) {
		gc.clearRect(0, 0, 600, 700);
	}
	
	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}
}
