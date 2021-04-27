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

	// Duck
	public static Image duckmelee1_Image;
	public static Image duckmelee2_Image;
	public static Image duckrange1_Image;
	public static Image duckrange2_Image;
	public static Image duckmeleehead_Image;
	public static Image duckrangehead_Image;

	// Healer
	public static Image healermelee1_Image;
	public static Image healermelee2_Image;
	public static Image healerrange1_Image;
	public static Image healerrange2_Image;
	public static Image healermeleehead_Image;
	public static Image healerrangehead_Image;

	// Speedy
	public static Image speedymelee1_Image;
	public static Image speedymelee2_Image;
	public static Image speedyrange1_Image;
	public static Image speedyrange2_Image;
	public static Image speedymeleehead_Image;
	public static Image speedyrangehead_Image;

	// Tough
	public static Image toughmelee1_Image;
	public static Image toughmelee2_Image;
	public static Image toughrange1_Image;
	public static Image toughrange2_Image;
	public static Image toughmeleehead_Image;
	public static Image toughrangehead_Image;

	// Wild
	public static Image wildmelee1_Image;
	public static Image wildmelee2_Image;
	public static Image wildrange1_Image;
	public static Image wildrange2_Image;
	public static Image wildmeleehead_Image;
	public static Image wildrangehead_Image;

	// =============================EndScreen====================================
	// Background Images
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

	public static Image getHeadImage(int symbol) {
		if (symbol > 10) {
			symbol -= 10;
		}
		Image image = null;
		switch (symbol) {
		case 1:
			image = RenderableHolder.duckmeleehead_Image;
			break;
		case 2:
			image = RenderableHolder.healermeleehead_Image;
			break;
		case 3:
			image = RenderableHolder.speedymeleehead_Image;
			break;
		case 4:
			image = RenderableHolder.toughmeleehead_Image;
			break;
		case 5:
			image = RenderableHolder.wildmeleehead_Image;
			break;
		case 6:
			image = RenderableHolder.duckrangehead_Image;
			break;
		case 7:
			image = RenderableHolder.healerrangehead_Image;
			break;
		case 8:
			image = RenderableHolder.speedyrangehead_Image;
			break;
		case 9:
			image = RenderableHolder.toughrangehead_Image;
			break;
		case 10:
			image = RenderableHolder.wildrangehead_Image;
			break;
		}
		return image;
	}

	public static Image getFullBodyImage(int symbol) {
		Image image = null;
		switch (symbol) {
		case 1:
			image = RenderableHolder.duckmelee1_Image;
			break;
		case 2:
			image = RenderableHolder.healermelee1_Image;
			break;
		case 3:
			image = RenderableHolder.speedymelee1_Image;
			break;
		case 4:
			image = RenderableHolder.toughmelee1_Image;
			break;
		case 5:
			image = RenderableHolder.wildmelee1_Image;
			break;
		case 6:
			image = RenderableHolder.duckrange1_Image;
			break;
		case 7:
			image = RenderableHolder.healerrange1_Image;
			break;
		case 8:
			image = RenderableHolder.speedyrange1_Image;
			break;
		case 9:
			image = RenderableHolder.toughrange1_Image;
			break;
		case 10:
			image = RenderableHolder.wildrange1_Image;
			break;
		case 11:
			image = RenderableHolder.duckmelee2_Image;
			break;
		case 12:
			image = RenderableHolder.healermelee2_Image;
			break;
		case 13:
			image = RenderableHolder.speedymelee2_Image;
			break;
		case 14:
			image = RenderableHolder.toughmelee2_Image;
			break;
		case 15:
			image = RenderableHolder.wildmelee2_Image;
			break;
		case 16:
			image = RenderableHolder.duckrange2_Image;
			break;
		case 17:
			image = RenderableHolder.healerrange2_Image;
			break;
		case 18:
			image = RenderableHolder.speedyrange2_Image;
			break;
		case 19:
			image = RenderableHolder.toughrange2_Image;
			break;
		case 20:
			image = RenderableHolder.wildrange2_Image;
			break;
		}
		return image;
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

		duckmelee1_Image = new Image(ClassLoader.getSystemResource(img + "duckmelee1.png").toString());
		duckmelee2_Image = new Image(ClassLoader.getSystemResource(img + "duckmelee2.png").toString());
		duckrange1_Image = new Image(ClassLoader.getSystemResource(img + "duckrange1.png").toString());
		duckrange2_Image = new Image(ClassLoader.getSystemResource(img + "duckrange1.png").toString());
		duckmeleehead_Image = new Image(ClassLoader.getSystemResource(img + "duckmeleehead.png").toString());
		duckrangehead_Image = new Image(ClassLoader.getSystemResource(img + "duckrangehead.png").toString());

		healermelee1_Image = new Image(ClassLoader.getSystemResource(img + "healermelee1.png").toString());
		healermelee2_Image = new Image(ClassLoader.getSystemResource(img + "healerrange1.png").toString());
		healerrange1_Image = new Image(ClassLoader.getSystemResource(img + "healermelee2.png").toString());
		healerrange2_Image = new Image(ClassLoader.getSystemResource(img + "healerrange2.png").toString());
		healermeleehead_Image = new Image(ClassLoader.getSystemResource(img + "healermeleehead.png").toString());
		healerrangehead_Image = new Image(ClassLoader.getSystemResource(img + "healerrangehead.png").toString());

		speedymelee1_Image = new Image(ClassLoader.getSystemResource(img + "speedymelee1.png").toString());
		speedymelee2_Image = new Image(ClassLoader.getSystemResource(img + "speedyrange1.png").toString());
		speedyrange1_Image = new Image(ClassLoader.getSystemResource(img + "speedymelee2.png").toString());
		speedyrange2_Image = new Image(ClassLoader.getSystemResource(img + "speedyrange2.png").toString());
		speedymeleehead_Image = new Image(ClassLoader.getSystemResource(img + "speedymeleehead.png").toString());
		speedyrangehead_Image = new Image(ClassLoader.getSystemResource(img + "speedyrangehead.png").toString());

		toughmelee1_Image = new Image(ClassLoader.getSystemResource(img + "toughmelee1.png").toString());
		toughmelee2_Image = new Image(ClassLoader.getSystemResource(img + "toughrange1.png").toString());
		toughrange1_Image = new Image(ClassLoader.getSystemResource(img + "toughmelee2.png").toString());
		toughrange2_Image = new Image(ClassLoader.getSystemResource(img + "toughrange2.png").toString());
		toughmeleehead_Image = new Image(ClassLoader.getSystemResource(img + "toughmeleehead.png").toString());
		toughrangehead_Image = new Image(ClassLoader.getSystemResource(img + "toughrangehead.png").toString());

		wildmelee1_Image = new Image(ClassLoader.getSystemResource(img + "wildmelee1.png").toString());
		wildmelee2_Image = new Image(ClassLoader.getSystemResource(img + "wildmelee2.png").toString());
		wildrange1_Image = new Image(ClassLoader.getSystemResource(img + "wildrange1.png").toString());
		wildrange2_Image = new Image(ClassLoader.getSystemResource(img + "wildrange2.png").toString());
		wildmeleehead_Image = new Image(ClassLoader.getSystemResource(img + "wildmeleehead.png").toString());
		wildrangehead_Image = new Image(ClassLoader.getSystemResource(img + "wildrangehead.png").toString());

	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public ArrayList<IRenderable> getEntities() {
		return entities;
	}
}
