package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.HealerFighter;
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
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class BoardPane extends Canvas implements Updatable {
	private GraphicsContext gc;
	private int fighterNo = 0;
	private int coordinatesIndex = 0;
	private int[] pixel;
	private int key;
	private ArrayList<Coordinate> coordinates;
	private Coordinate movingToCoordinate;
	private Coordinate currentCoordinate;
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
					currentCoordinate = coordinates.get(fighterNo);
					System.out.println("Action!");
					System.out.println(currentCoordinate.toString());
					fighterNo = 0;
					actionPane = new ActionPane(gameBoard.map[currentCoordinate.getI()][currentCoordinate.getJ()],
							gameBoard.getAllPossibleToMoveCoordinate(currentCoordinate).size(),
							gameBoard.getAllPossibleTargetsToAttack(currentCoordinate).size(),
							gameBoard.getAllPossibleAlliesToHeal(currentCoordinate).size());
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
						&& gameBoard.getAllPossibleToMoveCoordinate(currentCoordinate).size() != 0) {
					System.out.println("Move!");
					System.out.println(gameBoard.getAllPossibleToMoveCoordinate(currentCoordinate).size());
					key = 1;
					coordinates = gameBoard.getAllPossibleToMoveCoordinate(currentCoordinate);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.DIGIT2)
						&& gameBoard.getAllPossibleTargetsToAttack(currentCoordinate).size() != 0) {
					System.out.println("Attack!");
					System.out.println(gameBoard.getAllPossibleTargetsToAttack(currentCoordinate).size());
					key = 2;
					coordinates = gameBoard.getAllPossibleTargetsToAttack(currentCoordinate);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.DIGIT3)
						&& (currentCoordinate.getFighter() instanceof HealerFighter)
						&& gameBoard.getAllPossibleAlliesToHeal(currentCoordinate).size() != 0) {
					System.out.println("Heal!");
					System.out.println(gameBoard.getAllPossibleAlliesToHeal(currentCoordinate).size());
					key = 3;
					coordinates = gameBoard.getAllPossibleAlliesToHeal(currentCoordinate);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
					GameController.setSelect(false);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.SPACE)) {
					GameController.setSelect(false);
					currentCoordinate.getFighter().setReady(false);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				}

			} else if (coordinates.size() != 0) {
				for (Coordinate coordinate : coordinates) {
					gc.setStroke(Color.LIGHTYELLOW);
					gc.strokeRect(coordinate.coordinate2Pixel()[0], coordinate.coordinate2Pixel()[1],
							GameController.PIXEL_X, GameController.PIXEL_Y);
				}
				if (key == 1) {
					pixel = currentCoordinate.coordinate2Pixel(); // {x, y}
					gc.setStroke(Color.BLUE);
					gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
					if (InputUtility.getKeyPressed(KeyCode.W)) { 
						doMove(getMovingToCoordinate("top"));
					} else if (InputUtility.getKeyPressed(KeyCode.A)) {
						doMove(getMovingToCoordinate("left"));
					} else if (InputUtility.getKeyPressed(KeyCode.S)) {
						doMove(getMovingToCoordinate("bottom"));
					} else if (InputUtility.getKeyPressed(KeyCode.D)) {
						doMove(getMovingToCoordinate("right"));
					} else if (InputUtility.getKeyPressed(KeyCode.F)) {
						System.out.println("Done!");
						System.out.println(currentCoordinate.toString());
						currentCoordinate.getFighter().setReady(false);
						GameController.setSelect(false);
						GameController.setChoose(false);
					} else if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
						GameController.setSelect(false);
						GameController.setChoose(false);
					}
				} else if (key == 2 || key == 3){
					pixel = coordinates.get(coordinatesIndex).coordinate2Pixel(); // {x, y}
					gc.setStroke(Color.BLUE);
					gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
					if (InputUtility.getKeyPressed(KeyCode.W) && coordinatesIndex != 0) {
						draw();
						coordinatesIndex--;
						pixel = coordinates.get(coordinatesIndex).coordinate2Pixel();
						gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
					} else if (InputUtility.getKeyPressed(KeyCode.S) && coordinatesIndex != coordinates.size() - 1) {
						draw();
						coordinatesIndex++;
						pixel = coordinates.get(coordinatesIndex).coordinate2Pixel();
						gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
					} else if (InputUtility.getKeyPressed(KeyCode.F)) {
						draw();
						System.out.println("Done!");
						System.out.println(coordinates.get(coordinatesIndex).toString());
						if (key == 2) {
							gameBoard.takeAttack(currentCoordinate, coordinates.get(coordinatesIndex));
						} else if (key == 3) {
							gameBoard.takeHeal(currentCoordinate, coordinates.get(coordinatesIndex));
						}
						GameController.setSelect(false);
						GameController.setChoose(false);
						coordinatesIndex = 0;
					} else if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
						GameController.setSelect(false);
						GameController.setChoose(false);
						coordinatesIndex = 0;
					}
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
					currentCoordinate = coordinates.get(fighterNo);
					System.out.println("Action!");
					System.out.println(currentCoordinate.toString());
					fighterNo = 0;
					actionPane = new ActionPane(gameBoard.map[currentCoordinate.getI()][currentCoordinate.getJ()],
							gameBoard.getAllPossibleToMoveCoordinate(currentCoordinate).size(),
							gameBoard.getAllPossibleTargetsToAttack(currentCoordinate).size(),
							gameBoard.getAllPossibleAlliesToHeal(currentCoordinate).size());
					GameScreen.board.getChildren().add(actionPane);
					GameController.setSelect(true);
					System.out.println(GameController.isSelect());
					System.out.println(GameController.isChoose());
				}

			} else if (GameController.isSelect() && !GameController.isChoose()) {
				// use 7,8,9 to select action or space to skip
				// 1) move, 2) attack, 3) heal (only healer can do this action)
				if (InputUtility.getKeyPressed(KeyCode.DIGIT7)
						&& gameBoard.getAllPossibleToMoveCoordinate(currentCoordinate).size() != 0) {
					System.out.println("Move!");
					System.out.println(gameBoard.getAllPossibleToMoveCoordinate(currentCoordinate).size());
					key = 1;
					coordinates = gameBoard.getAllPossibleToMoveCoordinate(currentCoordinate);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.DIGIT8)
						&& gameBoard.getAllPossibleTargetsToAttack(currentCoordinate).size() != 0) {
					System.out.println("Attack!");
					System.out.println(gameBoard.getAllPossibleTargetsToAttack(currentCoordinate).size());
					key = 2;
					coordinates = gameBoard.getAllPossibleTargetsToAttack(currentCoordinate);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.DIGIT9)
						&& (currentCoordinate.getFighter() instanceof HealerFighter)
						&& gameBoard.getAllPossibleAlliesToHeal(currentCoordinate).size() != 0) {
					System.out.println("Heal!");
					System.out.println(gameBoard.getAllPossibleAlliesToHeal(currentCoordinate).size());
					key = 3;
					coordinates = gameBoard.getAllPossibleAlliesToHeal(currentCoordinate);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.BACK_SPACE)) {
					GameController.setSelect(false);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.SPACE)) {
					GameController.setSelect(false);
					currentCoordinate.getFighter().setReady(false);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				}

			} else if (coordinates.size() != 0) {
				for (Coordinate coordinate : coordinates) {
					gc.setStroke(Color.LIGHTYELLOW);
					gc.strokeRect(coordinate.coordinate2Pixel()[0], coordinate.coordinate2Pixel()[1],
							GameController.PIXEL_X, GameController.PIXEL_Y);
				}
				if (key == 1) {
					pixel = currentCoordinate.coordinate2Pixel(); // {x, y}
					gc.setStroke(Color.RED);
					gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
					if (InputUtility.getKeyPressed(KeyCode.I)) { 
						doMove(getMovingToCoordinate("top"));
					} else if (InputUtility.getKeyPressed(KeyCode.J)) {
						doMove(getMovingToCoordinate("left"));
					} else if (InputUtility.getKeyPressed(KeyCode.K)) {
						doMove(getMovingToCoordinate("bottom"));
					} else if (InputUtility.getKeyPressed(KeyCode.L)) {
						doMove(getMovingToCoordinate("right"));
					} else if (InputUtility.getKeyPressed(KeyCode.SEMICOLON)) {
						System.out.println("Done!");
						System.out.println(currentCoordinate.toString());
						currentCoordinate.getFighter().setReady(false);
						GameController.setSelect(false);
						GameController.setChoose(false);
					} else if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
						GameController.setSelect(false);
						GameController.setChoose(false);
					}
				} else if (key == 2 || key == 3){
					pixel = coordinates.get(coordinatesIndex).coordinate2Pixel(); // {x, y}
					gc.setStroke(Color.RED);
					gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
					if (InputUtility.getKeyPressed(KeyCode.I) && coordinatesIndex != 0) {
						draw();
						coordinatesIndex--;
						pixel = coordinates.get(coordinatesIndex).coordinate2Pixel();
						gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
					} else if (InputUtility.getKeyPressed(KeyCode.K) && coordinatesIndex != coordinates.size() - 1) {
						draw();
						coordinatesIndex++;
						pixel = coordinates.get(coordinatesIndex).coordinate2Pixel();
						gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
					} else if (InputUtility.getKeyPressed(KeyCode.SEMICOLON)) {
						draw();
						System.out.println("Done!");
						System.out.println(coordinates.get(coordinatesIndex).toString());
						if (key == 2) {
							gameBoard.takeAttack(currentCoordinate, coordinates.get(coordinatesIndex));
						} else if (key == 3) {
							gameBoard.takeHeal(currentCoordinate, coordinates.get(coordinatesIndex));
						}
						GameController.setSelect(false);
						GameController.setChoose(false);
						coordinatesIndex = 0;
					} else if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
						GameController.setSelect(false);
						GameController.setChoose(false);
						coordinatesIndex = 0;
					}
				}
			}
		}
	}
	
	public Coordinate getMovingToCoordinate(String key) {
		int currentI = currentCoordinate.getI();
		int currentJ = currentCoordinate.getJ();
		for (Coordinate coordinate: coordinates) {
			int i = coordinate.getI();
			int j = coordinate.getJ();
			if (key.equals("left")) {
				if (currentI == i && currentJ - 1 == j) {
					return coordinate;
				}
			} else if (key.equals("top")) {
				if (currentI - 1 == i && currentJ == j) {
					return coordinate;
				}
			} else if (key.equals("right")) {
				if (currentI == i && currentJ + 1 == j) {
					return coordinate;
				}
			} else if (key.equals("bottom")) {
				if (currentI + 1 == i && currentJ == j) {
					return coordinate;
				}
			}
		}
		return null;
	}
	
	public void doMove(Coordinate movingToCoordinate) {
		if (movingToCoordinate != null) {
			draw();
			gameBoard.takeMove(currentCoordinate, movingToCoordinate);
			currentCoordinate = movingToCoordinate;
			pixel = currentCoordinate.coordinate2Pixel();
			gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);	
		}
	}
	
	public void setDefault() {
		System.out.println("in BoardPane.setDefault()");
		gameBoard = GameController.getGameBoard();
		draw();
		GameController.setSelect(false);
		GameController.setChoose(false);
		coordinatesIndex = 0;
		fighterNo = 0;
	}

}