package application;

import javafx.application.Application;
import javafx.stage.Stage;
import screen.StartScreen;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		new StartScreen(primaryStage);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
