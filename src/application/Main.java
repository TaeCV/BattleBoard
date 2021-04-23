package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import screen.GameScreen;
import screen.StartScreen;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		StartScreen startScreen = new StartScreen(primaryStage);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
