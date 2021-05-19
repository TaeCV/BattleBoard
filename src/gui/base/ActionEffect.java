package gui.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import sharedObject.IRenderable;

public class ActionEffect implements IRenderable{
	private Image image;
	
	private int z;
	
	private boolean isDestroyed, isVisible;
	
	public ActionEffect(Image image) {
		this.image = image;
		setZ(0);
		
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}
	
	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for (int i = 0 ; i < 10; i++) {
            final double opacity = 1 - ((double) i) / 10;
            System.out.println(opacity);
            gc.setGlobalAlpha(opacity);
            gc.setEffect(new BoxBlur(i * 2, i * 2, 3));

            gc.drawImage(image, i, 0);
        }
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return isDestroyed;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}
	
	
}
