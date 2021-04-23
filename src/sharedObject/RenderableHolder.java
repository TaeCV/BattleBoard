package sharedObject;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RenderableHolder implements IRenderable {
	private ArrayList<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	private static final RenderableHolder instance = new RenderableHolder();

	// Image Holder
	// ==========================StartScreen====================================
	// Background Images
	public static Image start_bg_Image;
	public static Image howToPlay_bg_Image;

	// HowToPlay Images
	public static Image howToPlay_p1_Image;
	
	 // PlayerNameBar Images
	 public static Image check_Image;

	// ===========================GameScreen===================================
	// Background Images
	public static Image gameNameBar_bg_Image;
	public static Image brick_wall_Image;
	public static Image waterfall_Image;
	public static Image stone_wall_Image;
	
	// Floor
	public static Image floor_Image;
	

	// River
	public static Image river_Image;

	// Bridge
	public static Image bridge_Image;

	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ()) {
				return 1;
			}
			return -1;
		};
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	public static void loadResource() {
		String img = "image/";
		
		start_bg_Image = new Image(ClassLoader.getSystemResource(img + "start_bg.jpg").toString());
		howToPlay_bg_Image = new Image(ClassLoader.getSystemResource(img + "howtoplay_bg.jpg").toString());
		howToPlay_p1_Image = new Image(ClassLoader.getSystemResource(img + "howtoplay_p1.jpg").toString());
		gameNameBar_bg_Image = new Image(ClassLoader.getSystemResource(img + "gamenamebar_bg.jpg").toString());
		
		floor_Image = new Image(ClassLoader.getSystemResource(img + "board_floor.jpg").toString());
		river_Image = new Image(ClassLoader.getSystemResource(img + "river.jpg").toString());
		bridge_Image = new Image(ClassLoader.getSystemResource(img + "bridge.png").toString());
		stone_wall_Image = new Image(ClassLoader.getSystemResource(img + "stone_bg.jpg").toString());		
		brick_wall_Image = new Image(ClassLoader.getSystemResource(img + "brick_wall.png").toString());
		waterfall_Image = new Image(ClassLoader.getSystemResource(img + "waterfall.jpg").toString());
		
		check_Image = new Image(ClassLoader.getSystemResource(img + "check.png").toString());
		
	}

}
