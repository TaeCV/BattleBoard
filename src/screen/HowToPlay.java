package screen;

import gui.base.ActionButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.Stage;
import sharedObject.RenderableHolder;

public class HowToPlay extends VBox {

	public HowToPlay() {
		setAlignment(Pos.CENTER);
		setSpacing(50);
		setMaxSize(500, 750);
		setBackground(new Background(new BackgroundImage(RenderableHolder.howToPlay_bg_Image, null, null, null,
				new BackgroundSize(500, 750, isFocusTraversable(), isDisabled(), isDisable(), isCache()))));

		// Text title
		Text title = new Text("HOW TO PLAY");
		title.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 48));
		title.setStroke(Color.SILVER);

		// Describe the player button and character's ability
		GridPane description = new GridPane();
		description.setHgap(10);
		description.setVgap(10);
		description.setPadding(new Insets(15));
		ImageView howToPlay_p1_Image = new ImageView(RenderableHolder.howToPlay_p1_Image);
		Text howToPlay_p1_Text = new Text("Player 1:\n" + "1) Use 'A','W','S','D' to move the cursor\n"
				+ "2) Click ctrl button to choose the selected box");
		howToPlay_p1_Text.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 24));
		howToPlay_p1_Text.setWrappingWidth(230);

		description.addRow(0, howToPlay_p1_Image, howToPlay_p1_Text);
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(description);
		scrollPane.setPrefHeight(400);
		scrollPane.setMaxSize(450, 500);

		// Back Button
		ActionButton backButton = new ActionButton("Back");
		backButton.setPrefSize(200, 50);
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.ButtonClick_Sound.play();
				StartScreen.removeChildFromRoot();
			}
		});

		getChildren().addAll(title, scrollPane, backButton);

	}
}
