package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.base.Updatable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder implements IRenderable {
	private ArrayList<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	private static final RenderableHolder instance = new RenderableHolder();

	// Image Holder
	// ==========================StartScreen====================================
	// Background Images
	public static Image start_bg_Image;
	public static Image howToPlay_bg_Image;
	public static Image title_bg_Image;

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
	public static Image wall_Image;
	public static Image board_bg_Image;

	// Floor
	public static Image floor_Image;

	// River
	public static Image river_Image;

	// Bridge
	public static Image bridge_Image;

	// Sword
	public static Image sword_Image;
	
	// Fighter
	public static Image example_Image;
	
	// =============================EndScreen====================================
	//Background Images
	public static Image end_bg_Image;
	public static Image tie_end_bg_Image;

	// Audio Holder
	// ==========================StartScreen==================================
	public static AudioClip Button_Click;
	public static AudioClip Error;
	public static AudioClip Start_Sound;

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

	public void add(IRenderable entity) {
		entities.add(entity);

		// System.out.println("Suscessfully added "+entity.toString()+"\nto
		// entites....");

		Collections.sort(entities, comparator);
	}

	public void update() {
		Collections.sort(entities, comparator);
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i) instanceof Updatable) {
				((Updatable) entities.get(i)).update();
			}
		}
		for (int i1 = entities.size() - 1; i1 >= 0; i1--) {
			if (!entities.get(i1).isVisible()) {
				entities.remove(i1);
			}
		}
	}

	public static void loadResource() {
		String img = "image/";

		start_bg_Image = new Image(ClassLoader.getSystemResource(img + "start_bg.jpg").toString());
		title_bg_Image = new Image(ClassLoader.getSystemResource(img + "title_bg.png").toString());
		gameNameBar_bg_Image = new Image(ClassLoader.getSystemResource(img + "gamenamebar_bg.jpg").toString());
		board_bg_Image = new Image(ClassLoader.getSystemResource(img + "image0.jpg").toString());
		end_bg_Image = new Image(ClassLoader.getSystemResource(img + "end_bg.jpg").toString());
		tie_end_bg_Image = new Image(ClassLoader.getSystemResource(img + "tie_end_bg.jpg").toString());
		
		howToPlay_bg_Image = new Image(ClassLoader.getSystemResource(img + "howtoplay_bg.jpg").toString());
		howToPlay_p1_Image = new Image(ClassLoader.getSystemResource(img + "howtoplay_p1.jpg").toString());

		floor_Image = new Image(ClassLoader.getSystemResource(img + "board_floor.jpg").toString());
		river_Image = new Image(ClassLoader.getSystemResource(img + "river.jpg").toString());
		bridge_Image = new Image(ClassLoader.getSystemResource(img + "bridge.png").toString());
		stone_wall_Image = new Image(ClassLoader.getSystemResource(img + "stone_bg.png").toString());
		brick_wall_Image = new Image(ClassLoader.getSystemResource(img + "brick_wall.png").toString());
		waterfall_Image = new Image(ClassLoader.getSystemResource(img + "waterfall.jpg").toString());
		wall_Image = new Image(ClassLoader.getSystemResource(img + "wall.jpg").toString());
		check_Image = new Image(ClassLoader.getSystemResource(img + "check.png").toString());
		sword_Image = new Image(ClassLoader.getSystemResource(img + "sword.png").toString());
		example_Image = new Image(ClassLoader.getSystemResource(img + "example.png").toString());
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public ArrayList<IRenderable> getEntities() {
		return entities;
	}
}
