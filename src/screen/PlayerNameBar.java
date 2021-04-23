package screen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.base.ActionButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;
import exception.InvalidNameException;

public class PlayerNameBar extends VBox {
	private String Player1Name;
	private String Player2Name;

	private TextField Player1NameField;
	private TextField Player2NameField;
	private HBox nameConfirm1;
	private HBox nameConfirm2;

	private Button confirmButton1;
	private Button confirmButton2;
	private Button startButton;

	private boolean isCheckP1;
	private boolean isCheckP2;

	public PlayerNameBar() {
		setAlignment(Pos.CENTER);
		setPrefSize(700, 300);
		setMaxSize(700, 300);
		setPadding(new Insets(200, 150, 200, 150));
		setSpacing(20);
		setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
		setUpButtons();
		setUpNameConfirm();
		getChildren().addAll(nameConfirm1, nameConfirm2, startButton);
	}

	public void setUpTextFields() {
		Player1NameField = new TextField();
		Player2NameField = new TextField();
		Player1NameField.setPrefSize(300, 75);
		Player1NameField.setMinSize(300, 75);
		Player2NameField.setPrefSize(300, 75);
		Player2NameField.setMinSize(300, 75);
	}

	public void setUpNameConfirm() {
		setUpTextFields();
		nameConfirm1 = new HBox();
		nameConfirm2 = new HBox();
		nameConfirm1.setSpacing(15);
		nameConfirm2.setSpacing(15);
		nameConfirm1.getChildren().addAll(new Text("Player 1 Name: "), Player1NameField, confirmButton1);
		nameConfirm2.getChildren().addAll(new Text("Player 2 Name: "), Player2NameField, confirmButton2);
	}

	public void confirmName(String name) throws InvalidNameException { // found return false, not found return true
		if (name.isBlank()) {
			throw new InvalidNameException("Player's name can not be blank");
		}
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(name);
		boolean b = m.find();
		if (b) {
			throw new InvalidNameException("Player's name must not have special characters");
		}
		if (name.length() > 6) {
			throw new InvalidNameException("Player's name must not exceed 6 characters");
		}
	}

	public void setUpButtons() {
		confirmButton1 = new ActionButton("CONFIRM");
		confirmButton1.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		confirmButton1.setPrefSize(200, 50);
		confirmButton1.setMinSize(200, 50);
		confirmButton1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Thread checker = new Thread(() -> {
					try {
						confirmName(Player1NameField.getText());
						Player1Name = Player1NameField.getText();
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (!isCheckP1) {
									nameConfirm1.getChildren().add(imageViewCheck());
									isCheckP1 = true;
								}

							}
						});
					} catch (InvalidNameException e) {

					}
				});
				checker.start();
			}
		});
		confirmButton2 = new ActionButton("CONFIRM");
		confirmButton2.setFont(Font.font("Palatino Linotype", FontWeight.SEMI_BOLD, 20));
		confirmButton2.setPrefSize(200, 50);
		confirmButton2.setMinSize(200, 50);
		confirmButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Thread checker = new Thread(() -> {
					try {
						confirmName(Player2NameField.getText());
						Player2Name = Player2NameField.getText();
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (!isCheckP2) {
									nameConfirm2.getChildren().add(imageViewCheck());
									isCheckP2 = true;
								}
							}
						});
					} catch (InvalidNameException e) {

					}
				});
				checker.start();
			}
		});
		startButton = new ActionButton("START GAME!");

	}

	public ImageView imageViewCheck() {
		ImageView checkImageView = new ImageView(RenderableHolder.check_Image);
		checkImageView.setPreserveRatio(true);
		checkImageView.setFitHeight(75);
		return checkImageView;
	}
}