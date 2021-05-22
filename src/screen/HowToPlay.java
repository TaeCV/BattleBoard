package screen;

import gui.base.ActionButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sharedObject.RenderableHolder;

public class HowToPlay extends VBox {
	private Text title;
	private GridPane description;

	public HowToPlay() {
		setAlignment(Pos.CENTER);
		setSpacing(50);
		setMaxSize(500, 750);
		setBackground(new Background(new BackgroundImage(RenderableHolder.howToPlay_bg_Image, null, null, null,
				new BackgroundSize(500, 750, isFocusTraversable(), isDisabled(), isDisable(), isCache()))));

		// Text title
		title = new Text("HOW TO PLAY");
		title.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 48));
		title.setStroke(Color.SILVER);

		// Describe the player button and character's ability
		description = new GridPane();
		description.setHgap(10);
		description.setVgap(10);
		description.setPadding(new Insets(15));
		int row_idx = 0;
		Text howToPlay_text = new Text("Rules :\n1) The game is best of 3 rounds.\n"
				+ "2) Each round, both players pick 5 fighters from 8 random fighters.\n"
				+ "3) Each round, player can play up to 9 turns.\n"
				+ "4) Each turn, a player has 60 seconds to pick each fighter to do an action ( move, attack, or heal (healer only) ).\n"
				+ "5) The round will end when\n 5.1) after each players played 9 turns\n 5.2) one player is out of fighters.\n");
		howToPlay_text.setWrappingWidth(400);
		howToPlay_text.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 25));
		description.add(howToPlay_text, 0, row_idx++, 2, 1);
		Text board_topic = new Text("Board description:");
		board_topic.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 30));
		description.add(board_topic, 0, row_idx++, 2, 1);
		ImageView board_img = new ImageView(RenderableHolder.board_bg_Image);
		board_img.setFitWidth(175);
		board_img.setPreserveRatio(true);
		Text gameboard_description = new Text("The board has 3 components, including floor, river, and bridge.");
		gameboard_description.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		gameboard_description.setWrappingWidth(220);
		description.addRow(row_idx++, board_img, gameboard_description);

		// Describe each fighter's ability
		Text player_topic = new Text("Fighters description:");
		player_topic.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 30));
		description.add(player_topic, 0, row_idx++, 2, 1);
		ImageView duck_img = new ImageView(RenderableHolder.duckmelee1_Image);
		duck_img.setFitWidth(175);
		duck_img.setPreserveRatio(true);
		Text duck_description = new Text("Duck Fighter :\n" + "This fighter has a chance to dodge the enemy's attack.");
		duck_description.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		duck_description.setWrappingWidth(220);
		description.addRow(row_idx++, duck_img, duck_description);

		ImageView healer_img = new ImageView(RenderableHolder.healermelee1_Image);
		healer_img.setFitWidth(175);
		healer_img.setPreserveRatio(true);
		Text healer_description = new Text("Healer Fighter :\n"
				+ "This fighter has an ability to heal his teammate.\n Heal is a special action in addition to move and attack");
		healer_description.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		healer_description.setWrappingWidth(220);
		description.addRow(row_idx++, healer_img, healer_description);

		ImageView speedy_img = new ImageView(RenderableHolder.speedymelee1_Image);
		speedy_img.setFitWidth(175);
		speedy_img.setPreserveRatio(true);
		Text speedy_description = new Text("Speedy Fighter :\n" + "This fighter can move 2 blocks");
		speedy_description.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		speedy_description.setWrappingWidth(220);
		description.addRow(row_idx++, speedy_img, speedy_description);

		ImageView tough_img = new ImageView(RenderableHolder.toughmelee1_Image);
		tough_img.setFitWidth(175);
		tough_img.setPreserveRatio(true);
		Text tough_description = new Text(
				"Tough Fighter :\n" + "This fighter has greater HP and reduce more damage income");
		tough_description.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		tough_description.setWrappingWidth(220);
		description.addRow(row_idx++, tough_img, tough_description);

		ImageView wild_img = new ImageView(RenderableHolder.wildmelee1_Image);
		wild_img.setFitWidth(175);
		wild_img.setPreserveRatio(true);
		Text wild_description = new Text("Wild Fighter :\n" + "This fighter attack with additional damage.");
		wild_description.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		wild_description.setWrappingWidth(220);
		description.addRow(row_idx++, wild_img, wild_description);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(description);
		scrollPane.setMaxSize(450, 450);

		// Back Button
		ActionButton backButton = new ActionButton("Back");
		backButton.setPrefSize(200, 50);
		backButton.setOnMouseClicked(event -> {
			// TODO Auto-generated method stub
			RenderableHolder.ButtonClick_Sound.play();
			StartScreen.removeChildFromRoot();
		});

		getChildren().addAll(title, scrollPane, backButton);

	}
}
