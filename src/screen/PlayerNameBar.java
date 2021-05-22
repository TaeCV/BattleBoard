package screen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.InvalidNameException;
import gui.base.ActionButton;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GameController;
import sharedObject.RenderableHolder;

public class PlayerNameBar extends VBox {
	private Stage primaryStage;
	private String Player1Name;
	private String Player2Name;

	private TextField Player1NameField;
	private TextField Player2NameField;
	private HBox nameConfirm1;
	private HBox nameConfirm2;
	private HBox buttonBox;
	private Button confirmButton1;
	private Button confirmButton2;
	private Button editButton1;
	private Button editButton2;

	private Button startButton;
	private Button backButton;

	private boolean isCheckP1;
	private boolean isCheckP2;

	public PlayerNameBar(Stage primaryStage) {
		this.primaryStage = primaryStage;
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		setAlignment(Pos.CENTER);
		setMaxSize(720, 300);
		setPadding(new Insets(100, 50, 100, 50));
		setSpacing(25);
		setBackground(new Background(new BackgroundFill(Color.TAN, CornerRadii.EMPTY, Insets.EMPTY)));
		setUpButtons();
		setUpNameConfirm();

		Text description = new Text("Put your name in TextFeild\n" + "1) It must contain 1 - 6 characters.\n"
				+ "2) It must contain only English alphabet and number.\n"
				+ "3) Player 1 and Player 2 should not use the same name.");
		description.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 14));
		StackPane groupText = new StackPane(description);
		groupText.setAlignment(Pos.CENTER_LEFT);

		Text p1 = new Text("Player 1");
		p1.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 28));
		Text p2 = new Text("Player 2");
		p2.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 28));

		getChildren().addAll(p1, nameConfirm1, p2, nameConfirm2, buttonBox, groupText);
	}

	private void setUpTextFields() {
		Player1NameField = new TextField();
		Player2NameField = new TextField();
		Player1NameField.setPrefSize(200, 50);
		Player1NameField.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		Player2NameField.setPrefSize(200, 50);
		Player2NameField.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
	}

	private void setUpNameConfirm() {
		setUpTextFields();
		nameConfirm1 = new HBox(30);
		nameConfirm2 = new HBox(30);
		nameConfirm1.getChildren().addAll(Player1NameField, confirmButton1);
		nameConfirm2.getChildren().addAll(Player2NameField, confirmButton2);
	}

	private void confirmName(String name) throws InvalidNameException { // found return false, not found return true
		if (name.isBlank()) {
			throw new InvalidNameException("Player's name can not be blank.");
		}
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(name);
		boolean b = m.find();
		if (b) {
			throw new InvalidNameException("Player's name must not have special characters.");
		}
		if (name.length() > 6) {
			throw new InvalidNameException("Player's name must not exceed 6 characters.");
		}
		if ((isCheckP1 && name.equals(Player1Name)) || (isCheckP2 && name.equals(Player2Name))) {
			throw new InvalidNameException("Player's name must not be the same.");
		}
	}

	private void setUpButtons() {

		confirmButton1 = new ActionButton("CONFIRM");
		confirmButton1.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		confirmButton1.setPrefSize(200, 50);
		confirmButton1.setMinSize(200, 50);
		confirmButton1.setOnMouseClicked(event -> {
			Thread checker = new Thread(() -> {
				try {
					confirmName(Player1NameField.getText().strip());
					Player1Name = Player1NameField.getText().strip();
					RenderableHolder.ButtonClick_Sound.play();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							if (!isCheckP1) {
								Player1NameField.setDisable(true);
								int numOfChild = nameConfirm1.getChildren().size();
								nameConfirm1.getChildren().remove(numOfChild - 1);
								nameConfirm1.getChildren().add(editButton1);
								nameConfirm1.getChildren().add(imageViewCheck());
								isCheckP1 = true;
							}

						}
					});
				} catch (InvalidNameException e) {
					RenderableHolder.Error_Sound.play();
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Please fill again!");
							alert.setContentText(e.getType());
							alert.setHeaderText(null);
							alert.showAndWait();
							Player1NameField.setText("");
							Player1Name = "";
						}

					});
				}
			});
			checker.start();
		});

		confirmButton2 = new ActionButton("CONFIRM");
		confirmButton2.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		confirmButton2.setPrefSize(200, 50);
		confirmButton2.setMinSize(200, 50);
		confirmButton2.setOnMouseClicked(event -> {
			Thread checker = new Thread(() -> {
				try {
					confirmName(Player2NameField.getText().strip());
					RenderableHolder.ButtonClick_Sound.play();
					Player2Name = Player2NameField.getText().strip();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							if (!isCheckP2) {
								Player2NameField.setDisable(true);
								int numOfChild = nameConfirm2.getChildren().size();
								nameConfirm2.getChildren().remove(numOfChild - 1);
								nameConfirm2.getChildren().add(editButton2);
								nameConfirm2.getChildren().add(imageViewCheck());
								isCheckP2 = true;
							}
						}
					});
				} catch (InvalidNameException e) {
					RenderableHolder.Error_Sound.play();
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Please fill again!");
							alert.setContentText(e.getType());
							alert.setHeaderText(null);
							alert.showAndWait();
							Player2NameField.setText("");

						}

					});
				}
			});
			checker.start();

		});

		editButton1 = new ActionButton("Edit");
		editButton1.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		editButton1.setPrefSize(200, 50);
		editButton1.setMinSize(200, 50);
		editButton1.setOnMouseClicked(event -> {
			RenderableHolder.ButtonClick_Sound.play();
			Player1NameField.setDisable(false);
			int numOfChild = nameConfirm1.getChildren().size();
			nameConfirm1.getChildren().remove(numOfChild - 1);
			nameConfirm1.getChildren().remove(numOfChild - 2);
			nameConfirm1.getChildren().add(confirmButton1);
			isCheckP1 = false;
		});

		editButton2 = new ActionButton("Edit");
		editButton2.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		editButton2.setPrefSize(200, 50);
		editButton2.setMinSize(200, 50);
		editButton2.setOnMouseClicked(event -> {
			RenderableHolder.ButtonClick_Sound.play();
			Player2NameField.setDisable(false);
			int numOfChild = nameConfirm2.getChildren().size();
			nameConfirm2.getChildren().remove(numOfChild - 1);
			nameConfirm2.getChildren().remove(numOfChild - 2);
			nameConfirm2.getChildren().add(confirmButton2);
			isCheckP2 = false;
		});

		buttonBox = new HBox(40);
		buttonBox.setPadding(new Insets(25));

		startButton = new ActionButton("START GAME!");
		startButton.setOnMouseClicked(event -> {
			if (isCheckP1 && isCheckP2) {
				RenderableHolder.ButtonClick_Sound.play();
				GameController.setDefault(Player1Name, Player2Name);
				RenderableHolder.StartScreen_Music.stop();
				StartScreen.startScreenSong.stop();
				new GameScreen(primaryStage, Player1Name, Player2Name);
			} else {
				RenderableHolder.Error_Sound.play();
			}
		});

		backButton = new ActionButton("Back");
		backButton.setOnMouseClicked(event -> {
			// TODO Auto-generated method stub
			RenderableHolder.ButtonClick_Sound.play();
			StartScreen.removeChildFromRoot();
		});
		buttonBox.getChildren().addAll(startButton, backButton);
	}

	private ImageView imageViewCheck() {
		ImageView checkImageView = new ImageView(RenderableHolder.check_Image);
		checkImageView.setPreserveRatio(true);
		checkImageView.setFitHeight(50);
		return checkImageView;
	}
}