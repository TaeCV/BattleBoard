package gui;

import java.util.ArrayList;
import java.util.Arrays;

import entity.HealerFighter;
import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import logic.Coordinate;
import logic.EffectUtility;
import logic.GameBoard;
import logic.GameController;
import logic.LogicUtility;
import logic.Updatable;
import screen.GameScreen;
import sharedObject.RenderableHolder;

public class BoardPane extends Canvas implements Updatable {
	private GraphicsContext gc;
	private int[] pixel;
	private char key;
	private ArrayList<Coordinate> coordinates;
	private Coordinate beforeActionCoordinate;
	private Coordinate targetCoordinate; // where the target at
	private Coordinate actorCoordinate; // where the actor at
	private GameBoard gameBoard;
	private ActionPane actionPane;

	public BoardPane() {
		setWidth(700);
		setHeight(600);
		gc = getGraphicsContext2D();
		gameBoard = GameController.getGameBoard();
		draw();
	}

	private void draw() {
		gc.clearRect(0, 0, 600, 700);
		gc.drawImage(RenderableHolder.board_bg_Image, 0, 0, getWidth(), getHeight());
	}

	public void update() {
		if (GameController.isP1() && gameBoard.getAllReadyPlayerFightersCoordinate(1).size() != 0) {
			if (!GameController.isSelect()) {
				draw();
				coordinates = gameBoard.getAllReadyPlayerFightersCoordinate(1);
				if (actorCoordinate == null) {
					actorCoordinate = coordinates.get(0);
				}
				pixel = actorCoordinate.coordinate2Pixel();
				gc.setStroke(Color.BLUE);
				gc.setLineWidth(3);
				gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
				// use WASD to select fighter to do action
				if (InputUtility.getKeyPressed(KeyCode.W)) {
					if (getTargetCoordinate("top", actorCoordinate) != null) {
						actorCoordinate = getTargetCoordinate("top", actorCoordinate);
					}
					select(actorCoordinate);
				} else if (InputUtility.getKeyPressed(KeyCode.A)) {
					if (getTargetCoordinate("left", actorCoordinate) != null) {
						actorCoordinate = getTargetCoordinate("left", actorCoordinate);
					}
					select(actorCoordinate);
				} else if (InputUtility.getKeyPressed(KeyCode.S)) {
					if (getTargetCoordinate("bottom", actorCoordinate) != null) {
						actorCoordinate = getTargetCoordinate("bottom", actorCoordinate);
					}
					select(actorCoordinate);
				} else if (InputUtility.getKeyPressed(KeyCode.D)) {
					if (getTargetCoordinate("right", actorCoordinate) != null) {
						actorCoordinate = getTargetCoordinate("right", actorCoordinate);
					}
					select(actorCoordinate);
				} else if (InputUtility.getKeyPressed(KeyCode.F)) {
					draw();
					System.out.println("Action!");
					System.out.println(actorCoordinate.toString());
					beforeActionCoordinate = actorCoordinate;
					actionPane = new ActionPane(actorCoordinate.getFighter() instanceof HealerFighter,
							gameBoard.getAllPossibleToMoveCoordinate(actorCoordinate).size(),
							gameBoard.getAllPossibleTargetsToAttack(actorCoordinate).size(),
							gameBoard.getAllPossibleAlliesToHeal(actorCoordinate).size());
					GameScreen.board.getChildren().add(actionPane);
					GameController.setSelect(true);
				}

			} else if (GameController.isSelect() && !GameController.isChoose()) {
				// use 1,2,3 to select action or space to skip
				// 1) move, 2) attack, 3) heal (only healer can do this action), and
				// 4) can't do anything action
				if (InputUtility.getKeyPressed(KeyCode.DIGIT1)
						&& gameBoard.getAllPossibleToMoveCoordinate(actorCoordinate).size() > 1) {
					System.out.println("Move!");
					System.out.println(gameBoard.getAllPossibleToMoveCoordinate(actorCoordinate).size());
					key = GameController.MOVE_KEY;
					coordinates = gameBoard.getAllPossibleToMoveCoordinate(actorCoordinate);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.DIGIT2)
						&& gameBoard.getAllPossibleTargetsToAttack(actorCoordinate).size() != 0) {
					System.out.println("Attack!");
					System.out.println(gameBoard.getAllPossibleTargetsToAttack(actorCoordinate).size());
					key = GameController.ATTACK_KEY;
					coordinates = gameBoard.getAllPossibleTargetsToAttack(actorCoordinate);
					targetCoordinate = coordinates.get(0);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.DIGIT3)
						&& (actorCoordinate.getFighter() instanceof HealerFighter)
						&& gameBoard.getAllPossibleAlliesToHeal(actorCoordinate).size() != 0) {
					System.out.println("Heal!");
					System.out.println(gameBoard.getAllPossibleAlliesToHeal(actorCoordinate).size());
					key = GameController.HEAL_KEY;
					coordinates = gameBoard.getAllPossibleAlliesToHeal(actorCoordinate);
					targetCoordinate = coordinates.get(0);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
					System.out.println("Cancel!");
					GameController.setSelect(false);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.SPACE)) {
					System.out.println("Skip!");
					GameController.setSelect(false);
					actorCoordinate.getFighter().setReady(false);
					actorCoordinate = null;
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				}

			} else if (coordinates.size() != 0) {
				for (Coordinate coordinate : coordinates) {
					gc.setStroke(Color.LIGHTYELLOW);
					gc.strokeRect(coordinate.coordinate2Pixel()[0], coordinate.coordinate2Pixel()[1],
							GameController.PIXEL_X, GameController.PIXEL_Y);
				}
				if (key == GameController.MOVE_KEY) {
					pixel = actorCoordinate.coordinate2Pixel(); // {x, y}
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
						if (!actorCoordinate.equals(beforeActionCoordinate)) {
							System.out.println("Done!");
							System.out.println(actorCoordinate.toString());
							actorCoordinate.getFighter().setReady(false);
							actorCoordinate = null;
						}
						GameController.setSelect(false);
						GameController.setChoose(false);
					} else if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
						gameBoard.takeMove(actorCoordinate, beforeActionCoordinate);
						actorCoordinate = beforeActionCoordinate;
						GameController.setSelect(false);
						GameController.setChoose(false);
					}
				} else if (key == GameController.ATTACK_KEY || key == GameController.HEAL_KEY) {
					pixel = targetCoordinate.coordinate2Pixel(); // {x, y}
					gc.setStroke(Color.BLUE);
					gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
					if (InputUtility.getKeyPressed(KeyCode.W)) {
						if (getTargetCoordinate("top", targetCoordinate) != null) {
							targetCoordinate = getTargetCoordinate("top", targetCoordinate);
						}
						select(targetCoordinate);
					} else if (InputUtility.getKeyPressed(KeyCode.A)) {
						if (getTargetCoordinate("left", targetCoordinate) != null) {
							targetCoordinate = getTargetCoordinate("left", targetCoordinate);
						}
						select(targetCoordinate);
					} else if (InputUtility.getKeyPressed(KeyCode.S)) {
						if (getTargetCoordinate("bottom", targetCoordinate) != null) {
							targetCoordinate = getTargetCoordinate("bottom", targetCoordinate);
						}
						select(targetCoordinate);
					} else if (InputUtility.getKeyPressed(KeyCode.D)) {
						if (getTargetCoordinate("right", targetCoordinate) != null) {
							targetCoordinate = getTargetCoordinate("right", targetCoordinate);
						}
						select(targetCoordinate);
					} else if (InputUtility.getKeyPressed(KeyCode.F)) {
						draw();
						GameScreen.timeToDrawAnimation = 20;
						GameScreen.P1 = true;
						GameScreen.selectedFighterType = actorCoordinate.getFighter().getType();
						GameScreen.selectedPixel = Arrays.copyOf(pixel, pixel.length);
						System.out.println("Done!");
						System.out.println(targetCoordinate.toString());
						if (key == GameController.ATTACK_KEY) {
							double damageDone = gameBoard.takeAttack(actorCoordinate, targetCoordinate);
							System.out.println("damage done: " + damageDone);
							GameScreen.effectSymbol = EffectUtility.getEffectSymbol(key, actorCoordinate, damageDone);
							EffectUtility.playSoundEffect(key, actorCoordinate, damageDone);
						} else if (key == GameController.HEAL_KEY) {
							gameBoard.takeHeal(actorCoordinate, targetCoordinate);
							GameScreen.effectSymbol = EffectUtility.getEffectSymbol(key, actorCoordinate, 0);
							EffectUtility.playSoundEffect(key, actorCoordinate, 0);
						}
						actorCoordinate = null;
						GameController.setSelect(false);
						GameController.setChoose(false);
					} else if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
						actorCoordinate = beforeActionCoordinate;
						GameController.setSelect(false);
						GameController.setChoose(false);
					}
				}
			}

		} else if (!GameController.isP1() && gameBoard.getAllReadyPlayerFightersCoordinate(2).size() != 0) {
			if (!GameController.isSelect()) {
				draw();
				coordinates = gameBoard.getAllReadyPlayerFightersCoordinate(2);
				if (actorCoordinate == null) {
					actorCoordinate = coordinates.get(0);
				}
				pixel = actorCoordinate.coordinate2Pixel();
				gc.setStroke(Color.RED);
				gc.setLineWidth(3);
				gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
				// use WASD to select fighter to do action
				if (InputUtility.getKeyPressed(KeyCode.I)) {
					if (getTargetCoordinate("top", actorCoordinate) != null) {
						actorCoordinate = getTargetCoordinate("top", actorCoordinate);
					}
					select(actorCoordinate);
				} else if (InputUtility.getKeyPressed(KeyCode.J)) {
					if (getTargetCoordinate("left", actorCoordinate) != null) {
						actorCoordinate = getTargetCoordinate("left", actorCoordinate);
					}
					select(actorCoordinate);
				} else if (InputUtility.getKeyPressed(KeyCode.K)) {
					if (getTargetCoordinate("bottom", actorCoordinate) != null) {
						actorCoordinate = getTargetCoordinate("bottom", actorCoordinate);
					}
					select(actorCoordinate);
				} else if (InputUtility.getKeyPressed(KeyCode.L)) {
					if (getTargetCoordinate("right", actorCoordinate) != null) {
						actorCoordinate = getTargetCoordinate("right", actorCoordinate);
					}
					select(actorCoordinate);
				} else if (InputUtility.getKeyPressed(KeyCode.SEMICOLON)) {
					draw();
					System.out.println("Action!");
					System.out.println(actorCoordinate.toString());
					beforeActionCoordinate = actorCoordinate;
					actionPane = new ActionPane(actorCoordinate.getFighter() instanceof HealerFighter,
							gameBoard.getAllPossibleToMoveCoordinate(actorCoordinate).size(),
							gameBoard.getAllPossibleTargetsToAttack(actorCoordinate).size(),
							gameBoard.getAllPossibleAlliesToHeal(actorCoordinate).size());
					GameScreen.board.getChildren().add(actionPane);
					GameController.setSelect(true);
				}

			} else if (GameController.isSelect() && !GameController.isChoose()) {
				// use 7,8,9 to select action or space to skip
				// 1) move, 2) attack, 3) heal (only healer can do this action)
				if (InputUtility.getKeyPressed(KeyCode.DIGIT7)
						&& gameBoard.getAllPossibleToMoveCoordinate(actorCoordinate).size() > 1) {
					System.out.println("Move!");
					System.out.println(gameBoard.getAllPossibleToMoveCoordinate(actorCoordinate).size());
					key = GameController.MOVE_KEY;
					coordinates = gameBoard.getAllPossibleToMoveCoordinate(actorCoordinate);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.DIGIT8)
						&& gameBoard.getAllPossibleTargetsToAttack(actorCoordinate).size() != 0) {
					System.out.println("Attack!");
					System.out.println(gameBoard.getAllPossibleTargetsToAttack(actorCoordinate).size());
					key = GameController.ATTACK_KEY;
					coordinates = gameBoard.getAllPossibleTargetsToAttack(actorCoordinate);
					targetCoordinate = coordinates.get(0);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.DIGIT9)
						&& (actorCoordinate.getFighter() instanceof HealerFighter)
						&& gameBoard.getAllPossibleAlliesToHeal(actorCoordinate).size() != 0) {
					System.out.println("Heal!");
					System.out.println(gameBoard.getAllPossibleAlliesToHeal(actorCoordinate).size());
					key = GameController.HEAL_KEY;
					coordinates = gameBoard.getAllPossibleAlliesToHeal(actorCoordinate);
					targetCoordinate = coordinates.get(0);
					GameController.setChoose(true);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.BACK_SPACE)) {
					System.out.println("Cancel!");
					GameController.setSelect(false);
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				} else if (InputUtility.getKeyPressed(KeyCode.SPACE)) {
					System.out.println("Skip!");
					GameController.setSelect(false);
					actorCoordinate.getFighter().setReady(false);
					actorCoordinate = null;
					GameScreen.board.getChildren().remove(GameScreen.board.getChildren().size() - 1);
				}

			} else if (coordinates.size() != 0) {
				for (Coordinate coordinate : coordinates) {
					gc.setStroke(Color.LIGHTYELLOW);
					gc.strokeRect(coordinate.coordinate2Pixel()[0], coordinate.coordinate2Pixel()[1],
							GameController.PIXEL_X, GameController.PIXEL_Y);
				}
				if (key == GameController.MOVE_KEY) {
					pixel = actorCoordinate.coordinate2Pixel(); // {x, y}
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
						if (!actorCoordinate.equals(beforeActionCoordinate)) {
							System.out.println("Done!");
							System.out.println(actorCoordinate.toString());
							actorCoordinate.getFighter().setReady(false);
							actorCoordinate = null;
						}
						GameController.setSelect(false);
						GameController.setChoose(false);
					} else if (InputUtility.getKeyPressed(KeyCode.BACK_SPACE)) {
						gameBoard.takeMove(actorCoordinate, beforeActionCoordinate);
						actorCoordinate = beforeActionCoordinate;
						GameController.setSelect(false);
						GameController.setChoose(false);
					}
				} else if (key == GameController.ATTACK_KEY || key == GameController.HEAL_KEY) {
					pixel = targetCoordinate.coordinate2Pixel(); // {x, y}
					gc.setStroke(Color.RED);
					gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
					if (InputUtility.getKeyPressed(KeyCode.I)) {
						if (getTargetCoordinate("top", targetCoordinate) != null) {
							targetCoordinate = getTargetCoordinate("top", targetCoordinate);
						}
						select(targetCoordinate);
					} else if (InputUtility.getKeyPressed(KeyCode.J)) {
						if (getTargetCoordinate("left", targetCoordinate) != null) {
							targetCoordinate = getTargetCoordinate("left", targetCoordinate);
						}
						select(targetCoordinate);
					} else if (InputUtility.getKeyPressed(KeyCode.K)) {
						if (getTargetCoordinate("bottom", targetCoordinate) != null) {
							targetCoordinate = getTargetCoordinate("bottom", targetCoordinate);
						}
						select(targetCoordinate);
					} else if (InputUtility.getKeyPressed(KeyCode.L)) {
						if (getTargetCoordinate("right", targetCoordinate) != null) {
							targetCoordinate = getTargetCoordinate("right", targetCoordinate);
						}
						select(targetCoordinate);
					} else if (InputUtility.getKeyPressed(KeyCode.SEMICOLON)) {
						draw();
						GameScreen.timeToDrawAnimation = 20;
						GameScreen.P1 = true;
						GameScreen.selectedFighterType = actorCoordinate.getFighter().getType();
						GameScreen.selectedPixel = Arrays.copyOf(pixel, pixel.length);
						System.out.println("Done!");
						System.out.println(targetCoordinate.toString());
						if (key == GameController.ATTACK_KEY) {
							double damageDone = gameBoard.takeAttack(actorCoordinate, targetCoordinate);
							GameScreen.effectSymbol = EffectUtility.getEffectSymbol(key, actorCoordinate, damageDone);
							EffectUtility.playSoundEffect(key, actorCoordinate, damageDone);
						} else if (key == GameController.HEAL_KEY) {
							gameBoard.takeHeal(actorCoordinate, targetCoordinate);
							GameScreen.effectSymbol = EffectUtility.getEffectSymbol(key, actorCoordinate, 0);
							EffectUtility.playSoundEffect(key, actorCoordinate, 0);
						}
						actorCoordinate = null;
						GameController.setSelect(false);
						GameController.setChoose(false);
					} else if (InputUtility.getKeyPressed(KeyCode.BACK_SPACE)) {
						actorCoordinate = beforeActionCoordinate;
						GameController.setSelect(false);
						GameController.setChoose(false);
					}
				}
			}
		}
	}

	private Coordinate getMovingToCoordinate(String key) {
		int currentI = actorCoordinate.getI();
		int currentJ = actorCoordinate.getJ();
		for (Coordinate coordinate : coordinates) {
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

	private Coordinate getTargetCoordinate(String key, Coordinate currentCoordinate) {
		sortCoordinatesByStraight(currentCoordinate);
		int currentI = currentCoordinate.getI();
		int currentJ = currentCoordinate.getJ();
		for (Coordinate coordinate : coordinates) {
			int i = coordinate.getI();
			int j = coordinate.getJ();
			if (key.equals("left")) {
				if (j < currentJ) {
					return coordinate;
				}
			} else if (key.equals("top")) {
				if (i < currentI) {
					return coordinate;
				}
			} else if (key.equals("right")) {
				if (j > currentJ) {
					return coordinate;
				}
			} else if (key.equals("bottom")) {
				if (i > currentI) {
					return coordinate;
				}
			}
		}
		return null;
	}

	private void doMove(Coordinate movingToCoordinate) {
		if (movingToCoordinate != null) {
			draw();
			gameBoard.takeMove(actorCoordinate, movingToCoordinate);
			actorCoordinate = movingToCoordinate;
			pixel = actorCoordinate.coordinate2Pixel();
			gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
		}
	}

	private void select(Coordinate currentCoordinate) {
		if (currentCoordinate != null) {
			System.out.println("selecting " + currentCoordinate.toString());
			draw();
			pixel = currentCoordinate.coordinate2Pixel();
			gc.strokeRect(pixel[0], pixel[1], GameController.PIXEL_X, GameController.PIXEL_Y);
		}
	}

	private void sortCoordinatesByStraight(Coordinate currentCoordinate) {
		// straight come first
		int currentI = currentCoordinate.getI();
		int currentJ = currentCoordinate.getJ();
		ArrayList<Coordinate> straight = new ArrayList<>();
		ArrayList<Coordinate> notStraight = new ArrayList<>();
		ArrayList<Coordinate> sortedCoordinates = new ArrayList<>();
		for (Coordinate coordinate : coordinates) {
			int i = coordinate.getI();
			int j = coordinate.getJ();
			if (currentI == i || currentJ == j) {
				straight.add(coordinate);
			} else {
				notStraight.add(coordinate);
			}
		}
		sortedCoordinates.addAll(LogicUtility.sortByDistance(straight, currentCoordinate));
		sortedCoordinates.addAll(LogicUtility.sortByDistance(notStraight, currentCoordinate));
		coordinates = sortedCoordinates;
	}

	public void resetActorCoordinate() {
		actorCoordinate = null;
	}
}