package gui;

import java.util.ArrayList;

import entity.base.Updatable;
import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import logic.Coordinate;
import logic.GameBoard;
import logic.GameController;
import screen.GameScreen;
import sharedObject.RenderableHolder;

public class BoardPane extends Canvas implements Updatable {
	private GraphicsContext gc;
	private int fighterNo = 0;
	private int pixelNo = 0;
	private int[] pixel;
	private int key;
	private ArrayList<Coordinate> coordinates;
	private Coordinate coordinate1;
	private GameBoard gameBoard;
	private ActionPane actionPane;

	public BoardPane() {
		setWidth(700);
		setHeight(600);
		gc = getGraphicsContext2D();
		gameBoard = GameController.getGameBoard();
		draw();
	}

	public void draw() {
		clear();
		gc.drawImage(RenderableHolder.board_bg_Image, 0, 0, getWidth(), getHeight());
	}

	public void clear() {
		gc.clearRect(0, 0, 600, 700);
	}

	public void update() {
		if (GameController.isP1() && gameBoard.getAllReadyPlayerFightersCoordinate(1).size() != 0) {
			if (!GameController.isSelect()) {
				draw();
				coordinates = gameBoard.getAllReadyPlayerFightersCoordinate(1);
				pixel = coordinates.get(fighterNo).coordinate2Pixel();
				gc.setStroke(Color.BLUE);
				gc.setLineWidth(3);
				gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
				// use W,S to select fighter to do action
				if (InputUtility.getKeyPressed(KeyCode.W) && fighterNo != 0) {
					draw();
					fighterNo--;
					pixel = coordinates.get(fighterNo).coordinate2Pixel();
					gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
				} else if (InputUtility.getKeyPressed(KeyCode.S) && fighterNo != coordinates.size() - 1) {
					draw();
					fighterNo++;
					pixel = coordinates.get(fighterNo).coordinate2Pixel();
					gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
				} else if (InputUtility.getKeyPressed(KeyCode.F)) {
					draw();
					coordinate1 = coordinates.get(fighterNo);
					System.out.println("Action!");
					System.out.println(coordinate1.toString());
					fighterNo = 0;
					actionPane = new ActionPane(gameBoard.map[coordinate1.getI()][coordinate1.getJ()],
							gameBoard.getAllPossibleToMoveCoordinate(coordinate1).size(),
							gameBoard.getAllPossibleTargetsToAttack(coordinate1).size(),
							gameBoard.getAllPossibleAlliesToHeal(coordinate1).size());
					GameScreen.board.getChildren().add(actionPane);
					GameController.setSelect(true);
					System.out.println(GameController.isSelect());
					System.out.println(GameController.isChoose());
				}

			} else if (GameController.isSelect() && !GameController.isChoose()) {
				// use 1,2,3 to select action or space to skip
				// 1) move, 2) attack, 3) heal (only healer can do this action), and
				// 4) can't do anything action
				if (InputUtility.getKeyPressed(KeyCode.DIGIT1)
						&& gameBoard.getAllPossibleToMoveCoordinate(coordinate1).size() != 0) {
					System.out.println("Move!");
					System.out.println(gameBoard.getAllPossibleToMoveCoordinate(coordinate1).size());
					key = 1;
					coordinates = gameBoard.getAllPossibleToMoveCoordinate(coordinate1);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.DIGIT2)
						&& gameBoard.getAllPossibleTargetsToAttack(coordinate1).size() != 0) {
					System.out.println("Attack!");
					System.out.println(gameBoard.getAllPossibleTargetsToAttack(coordinate1).size());
					key = 2;
					coordinates = gameBoard.getAllPossibleTargetsToAttack(coordinate1);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.DIGIT3)
						&& (gameBoard.map[coordinate1.getI()][coordinate1.getJ()] == 2
								|| gameBoard.map[coordinate1.getI()][coordinate1.getJ()] == 7)
						&& gameBoard.getAllPossibleAlliesToHeal(coordinate1).size() != 0) {
					System.out.println("Heal!");
					System.out.println(gameBoard.getAllPossibleAlliesToHeal(coordinate1).size());
					key = 3;
					coordinates = gameBoard.getAllPossibleAlliesToHeal(coordinate1);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
					GameController.setSelect(false);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.SPACE)) {
					GameController.setSelect(false);
					coordinate1.getFighter().setReady(false);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				}

			} else if (coordinates.size() != 0) {
				for (Coordinate coordinate : coordinates) {
					gc.setStroke(Color.LIGHTYELLOW);
					gc.strokeRect(coordinate.coordinate2Pixel()[0], coordinate.coordinate2Pixel()[1],
							GameController.PIXEL_X, GameController.PIXEL_Y);
				}
				pixel = coordinates.get(pixelNo).coordinate2Pixel();
				gc.setStroke(Color.BLUE);
				gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
				if (InputUtility.getKeyPressed(KeyCode.W) && pixelNo != 0) {
					draw();
					pixelNo--;
					pixel = coordinates.get(pixelNo).coordinate2Pixel();
					gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
				} else if (InputUtility.getKeyPressed(KeyCode.S) && pixelNo != coordinates.size() - 1) {
					draw();
					pixelNo++;
					pixel = coordinates.get(pixelNo).coordinate2Pixel();
					gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
				} else if (InputUtility.getKeyPressed(KeyCode.F)) {
					draw();
					System.out.println("Done!");
					System.out.println(coordinates.get(pixelNo).toString());
					if (key == 1) {
						gameBoard.takeMove(coordinate1, coordinates.get(pixelNo));
					} else if (key == 2) {
						gameBoard.takeAttack(coordinate1, coordinates.get(pixelNo));
					} else if (key == 3) {
						gameBoard.takeHeal(coordinate1, coordinates.get(pixelNo));
					}
					GameController.setSelect(false);
					GameController.setChoose(false);
					pixelNo = 0;
				} else if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
					GameController.setSelect(false);
					GameController.setChoose(false);
					pixelNo = 0;
				}

			}

		} else if (gameBoard.getAllReadyPlayerFightersCoordinate(2).size() != 0) {
			if (!GameController.isSelect()) {
				draw();
				coordinates = gameBoard.getAllReadyPlayerFightersCoordinate(2);
				pixel = coordinates.get(fighterNo).coordinate2Pixel();
				gc.setStroke(Color.RED);
				gc.setLineWidth(3);
				gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
				// use I,K to select fighter to do action
				if (InputUtility.getKeyPressed(KeyCode.I) && fighterNo != 0) {
					draw();
					fighterNo--;
					pixel = coordinates.get(fighterNo).coordinate2Pixel();
					gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
				} else if (InputUtility.getKeyPressed(KeyCode.K) && fighterNo != coordinates.size() - 1) {
					draw();
					fighterNo++;
					pixel = coordinates.get(fighterNo).coordinate2Pixel();
					gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
				} else if (InputUtility.getKeyPressed(KeyCode.SEMICOLON)) {
					draw();
					coordinate1 = coordinates.get(fighterNo);
					System.out.println("Action!");
					System.out.println(coordinate1.toString());
					fighterNo = 0;
					actionPane = new ActionPane(gameBoard.map[coordinate1.getI()][coordinate1.getJ()],
							gameBoard.getAllPossibleToMoveCoordinate(coordinate1).size(),
							gameBoard.getAllPossibleTargetsToAttack(coordinate1).size(),
							gameBoard.getAllPossibleAlliesToHeal(coordinate1).size());
					GameScreen.board.getChildren().add(actionPane);
					GameController.setSelect(true);
					System.out.println(GameController.isSelect());
					System.out.println(GameController.isChoose());
				}

			} else if (GameController.isSelect() && !GameController.isChoose()) {
				// use 7,8,9 to select action or space to skip
				// 1) move, 2) attack, 3) heal (only healer can do this action)
				if (InputUtility.getKeyPressed(KeyCode.DIGIT7)
						&& gameBoard.getAllPossibleToMoveCoordinate(coordinate1).size() != 0) {
					System.out.println("Move!");
					System.out.println(gameBoard.getAllPossibleToMoveCoordinate(coordinate1).size());
					key = 1;
					coordinates = gameBoard.getAllPossibleToMoveCoordinate(coordinate1);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.DIGIT8)
						&& gameBoard.getAllPossibleTargetsToAttack(coordinate1).size() != 0) {
					System.out.println("Attack!");
					System.out.println(gameBoard.getAllPossibleTargetsToAttack(coordinate1).size());
					key = 2;
					coordinates = gameBoard.getAllPossibleTargetsToAttack(coordinate1);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.DIGIT9)
						&& (gameBoard.map[coordinate1.getI()][coordinate1.getJ()] == 12
								|| gameBoard.map[coordinate1.getI()][coordinate1.getJ()] == 17)
						&& gameBoard.getAllPossibleAlliesToHeal(coordinate1).size() != 0) {
					System.out.println("Heal!");
					System.out.println(gameBoard.getAllPossibleAlliesToHeal(coordinate1).size());
					key = 3;
					coordinates = gameBoard.getAllPossibleAlliesToHeal(coordinate1);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.BACK_SPACE)) {
					GameController.setSelect(false);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.SPACE)) {
					GameController.setSelect(false);
					coordinate1.getFighter().setReady(false);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				}

			} else if (coordinates.size() != 0) {
				for (Coordinate coordinate : coordinates) {
					gc.setStroke(Color.LIGHTYELLOW);
					gc.strokeRect(coordinate.coordinate2Pixel()[0], coordinate.coordinate2Pixel()[1],
							GameController.PIXEL_X, GameController.PIXEL_Y);
				}
				pixel = coordinates.get(pixelNo).coordinate2Pixel();
				gc.setStroke(Color.RED);
				gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
				if (InputUtility.getKeyPressed(KeyCode.I) && pixelNo != 0) {
					draw();
					pixelNo--;
					pixel = coordinates.get(pixelNo).coordinate2Pixel();
					gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
				} else if (InputUtility.getKeyPressed(KeyCode.K) && pixelNo != coordinates.size() - 1) {
					draw();
					pixelNo++;
					pixel = coordinates.get(pixelNo).coordinate2Pixel();
					gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
				} else if (InputUtility.getKeyPressed(KeyCode.SEMICOLON)) {
					draw();
					System.out.println("Done!");
					System.out.println(coordinates.get(pixelNo).toString());
					if (key == 1) {
						gameBoard.takeMove(coordinate1, coordinates.get(pixelNo));
					} else if (key == 2) {
						gameBoard.takeAttack(coordinate1, coordinates.get(pixelNo));
					} else if (key == 3) {
						gameBoard.takeHeal(coordinate1, coordinates.get(pixelNo));
					}
					GameController.setSelect(false);
					GameController.setChoose(false);
					pixelNo = 0;
				} else if (InputUtility.getKeyPressed(KeyCode.BACK_SPACE)) {
					GameController.setSelect(false);
					GameController.setChoose(false);
					pixelNo = 0;
				}
			}
		}

	}

	public void setDefault() {
		System.out.println("in BoardPane.setDefault()");
		gameBoard = GameController.getGameBoard();
		draw();
		GameController.setSelect(false);
		GameController.setChoose(false);
		pixelNo = 0;
		fighterNo = 0;
	}

}