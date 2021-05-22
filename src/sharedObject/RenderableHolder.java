package sharedObject;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder {
	// Image Holder
	// ==========================StartScreen====================================
	// Background Images
	public static Image start_bg_Image;
	public static Image howToPlay_bg_Image;
	public static Image title_bg_Image;

	// HowToPlay Images

	// PlayerNameBar Images
	public static Image check_Image;

	// ===========================GameScreen===================================
	// Background Images
	public static Image gameNameBar_bg_Image;
	public static Image board_bg_Image;

	// Sword
	public static Image sword_Image;

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

	// Actions
	public static Image heal_Image;
	public static Image meleeAttack1_Image; // team 1
	public static Image meleeAttack2_Image; // team 2
	public static Image rangeAttack_Image;
	public static Image ducked1_Image; // team 1
	public static Image ducked2_Image; // team 2

	// =============================EndScreen====================================
	// Background Images
	public static Image end_bg_Image;
	public static Image tie_end_bg_Image;

	// Audio Holder
	// ==========================StartScreen==================================
	public static AudioClip StartScreen_Music;

	public static AudioClip ButtonClick_Sound;
	public static AudioClip Error_Sound;

	// ===========================GameScreen===================================
	public static AudioClip GameScreen_Music;

	public static AudioClip Select_Sound; // when choose Fighters
	public static AudioClip Select_Sound_2; // when select Fighter and select actions

	public static AudioClip MeleeAttack_Sound;
	public static AudioClip RangeAttack_Sound;
	public static AudioClip Heal_Sound;
	public static AudioClip DodgeMelee_Sound;
	public static AudioClip DodgeRange_Sound;

	public static AudioClip RoundWin_Sound;

	// =============================EndScreen====================================
	public static AudioClip GameWin_Sound;
	public static AudioClip GameTie_Sound;

	static {
		loadResource();
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

	public static Image getEffectImage(int symbol) {
		Image image = null;
		switch (symbol) {
		case 23:
			image = RenderableHolder.meleeAttack1_Image;
			break;
		case 24:
			image = RenderableHolder.meleeAttack2_Image;
			break;
		case 25:
			image = RenderableHolder.rangeAttack_Image;
			break;
		case 26:
			image = RenderableHolder.heal_Image;
			break;
		case 27:
			image = RenderableHolder.ducked1_Image;
			break;
		case 28:
			image = RenderableHolder.ducked2_Image;
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

		check_Image = new Image(ClassLoader.getSystemResource(img + "check.png").toString());
		sword_Image = new Image(ClassLoader.getSystemResource(img + "sword.png").toString());

		duckmelee1_Image = new Image(ClassLoader.getSystemResource(img + "duckmelee1.png").toString());
		duckmelee2_Image = new Image(ClassLoader.getSystemResource(img + "duckmelee2.png").toString());
		duckrange1_Image = new Image(ClassLoader.getSystemResource(img + "duckrange1.png").toString());
		duckrange2_Image = new Image(ClassLoader.getSystemResource(img + "duckrange2.png").toString());
		duckmeleehead_Image = new Image(ClassLoader.getSystemResource(img + "duckmeleehead.png").toString());
		duckrangehead_Image = new Image(ClassLoader.getSystemResource(img + "duckrangehead.png").toString());

		healermelee1_Image = new Image(ClassLoader.getSystemResource(img + "healermelee1.png").toString());
		healermelee2_Image = new Image(ClassLoader.getSystemResource(img + "healermelee2.png").toString());
		healerrange1_Image = new Image(ClassLoader.getSystemResource(img + "healerrange1.png").toString());
		healerrange2_Image = new Image(ClassLoader.getSystemResource(img + "healerrange2.png").toString());
		healermeleehead_Image = new Image(ClassLoader.getSystemResource(img + "healermeleehead.png").toString());
		healerrangehead_Image = new Image(ClassLoader.getSystemResource(img + "healerrangehead.png").toString());

		speedymelee1_Image = new Image(ClassLoader.getSystemResource(img + "speedymelee1.png").toString());
		speedymelee2_Image = new Image(ClassLoader.getSystemResource(img + "speedymelee2.png").toString());
		speedyrange1_Image = new Image(ClassLoader.getSystemResource(img + "speedyrange1.png").toString());
		speedyrange2_Image = new Image(ClassLoader.getSystemResource(img + "speedyrange2.png").toString());
		speedymeleehead_Image = new Image(ClassLoader.getSystemResource(img + "speedymeleehead.png").toString());
		speedyrangehead_Image = new Image(ClassLoader.getSystemResource(img + "speedyrangehead.png").toString());

		toughmelee1_Image = new Image(ClassLoader.getSystemResource(img + "toughmelee1.png").toString());
		toughmelee2_Image = new Image(ClassLoader.getSystemResource(img + "toughmelee2.png").toString());
		toughrange1_Image = new Image(ClassLoader.getSystemResource(img + "toughrange1.png").toString());
		toughrange2_Image = new Image(ClassLoader.getSystemResource(img + "toughrange2.png").toString());
		toughmeleehead_Image = new Image(ClassLoader.getSystemResource(img + "toughmeleehead.png").toString());
		toughrangehead_Image = new Image(ClassLoader.getSystemResource(img + "toughrangehead.png").toString());

		wildmelee1_Image = new Image(ClassLoader.getSystemResource(img + "wildmelee1.png").toString());
		wildmelee2_Image = new Image(ClassLoader.getSystemResource(img + "wildmelee2.png").toString());
		wildrange1_Image = new Image(ClassLoader.getSystemResource(img + "wildrange1.png").toString());
		wildrange2_Image = new Image(ClassLoader.getSystemResource(img + "wildrange2.png").toString());
		wildmeleehead_Image = new Image(ClassLoader.getSystemResource(img + "wildmeleehead.png").toString());
		wildrangehead_Image = new Image(ClassLoader.getSystemResource(img + "wildrangehead.png").toString());

		heal_Image = new Image(ClassLoader.getSystemResource(img + "heal.png").toString());
		meleeAttack1_Image = new Image(ClassLoader.getSystemResource(img + "meleeattack1.png").toString());
		meleeAttack2_Image = new Image(ClassLoader.getSystemResource(img + "meleeattack2.png").toString());
		rangeAttack_Image = new Image(ClassLoader.getSystemResource(img + "rangeattack.png").toString());
		ducked1_Image = new Image(ClassLoader.getSystemResource(img + "ducked1_alt.png").toString());
		ducked2_Image = new Image(ClassLoader.getSystemResource(img + "ducked2_alt.png").toString());

		String audio = "audio/";

		ButtonClick_Sound = new AudioClip(ClassLoader.getSystemResource(audio + "click1.mp3").toString());
		Error_Sound = new AudioClip(ClassLoader.getSystemResource(audio + "clickerror.mp3").toString());
		Select_Sound = new AudioClip(ClassLoader.getSystemResource(audio + "click2.mp3").toString());
		Select_Sound_2 = new AudioClip(ClassLoader.getSystemResource(audio + "click4.mp3").toString());

		MeleeAttack_Sound = new AudioClip(ClassLoader.getSystemResource(audio + "melee_attack.mp3").toString());
		RangeAttack_Sound = new AudioClip(ClassLoader.getSystemResource(audio + "range_attack.mp3").toString());
		Heal_Sound = new AudioClip(ClassLoader.getSystemResource(audio + "heal.mp3").toString());
		DodgeMelee_Sound = new AudioClip(ClassLoader.getSystemResource(audio + "dodgemelee1.mp3").toString());
		DodgeRange_Sound = new AudioClip(ClassLoader.getSystemResource(audio + "dodgerange.mp3").toString());

		RoundWin_Sound = new AudioClip(ClassLoader.getSystemResource(audio + "roundwin.mp3").toString());
		GameWin_Sound = new AudioClip(ClassLoader.getSystemResource(audio + "gamewin1.mp3").toString());
		GameTie_Sound = new AudioClip(ClassLoader.getSystemResource(audio + "gametie1.mp3").toString());

		StartScreen_Music = new AudioClip(ClassLoader.getSystemResource(audio + "startscreen_music.mp3").toString());
		GameScreen_Music = new AudioClip(ClassLoader.getSystemResource(audio + "gamescreen_music.mp3").toString());
	}
}
