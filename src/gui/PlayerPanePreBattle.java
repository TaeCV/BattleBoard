package gui;

import entity.base.Fighter;
import gui.base.PlayerPane;
import input.InputUtility;
import javafx.scene.input.KeyCode;
import logic.LogicUtility;
import logic.Updatable;
import screen.GameScreen;
import sharedObject.RenderableHolder;

public class PlayerPanePreBattle extends PlayerPane implements Updatable {
	private Fighter[] fighters;
	private FighterBoxPreBattle unselectedPreFighter;
	private FighterBoxPreBattle selectedPreFighter;

	public PlayerPanePreBattle(int player) {
		super(player);
		this.fighters = LogicUtility.getRandomFighters(player);
		for (Fighter fighter : fighters) {
			addFighterBox(fighter);
		}
	}

	public void addFighterBox(Fighter fighter) {
		getChildren().add(new FighterBoxPreBattle(fighter, fighter.getSymbol()));
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		// USE KEY W OR KEY S TO MOVE CURSOR IN P1Pane and USE KEY F to select
		// USE KEY I OR KEY K TO MOVE CURSOR IN P2Pane and USE KEY SEMICOLON to select
		if (player == 1 && getChildren().size() > 3) {
			if (InputUtility.getKeyPressed(KeyCode.W) && GameScreen.positionToSelectP1 != 0) {
				unselectedPreFighter = (FighterBoxPreBattle) getChildren().get(GameScreen.positionToSelectP1);
				unselectedPreFighter.draw();
				GameScreen.positionToSelectP1--;
				selectedPreFighter = (FighterBoxPreBattle) getChildren().get(GameScreen.positionToSelectP1);
				selectedPreFighter.update();
			} else if (InputUtility.getKeyPressed(KeyCode.S)
					&& GameScreen.positionToSelectP1 != getChildren().size() - 1) {
				unselectedPreFighter = (FighterBoxPreBattle) getChildren().get(GameScreen.positionToSelectP1);
				unselectedPreFighter.draw();
				GameScreen.positionToSelectP1++;
				selectedPreFighter = (FighterBoxPreBattle) getChildren().get(GameScreen.positionToSelectP1);
				selectedPreFighter.update();
			} else if (InputUtility.getKeyPressed(KeyCode.F)) {
				RenderableHolder.Select_Sound.play();
				selectedPreFighter = (FighterBoxPreBattle) getChildren().get(GameScreen.positionToSelectP1);
				SimulationManager.getP1PaneBattle().addFighters(selectedPreFighter.getFighter());
				getChildren().remove(GameScreen.positionToSelectP1);
				GameScreen.positionToSelectP1 = 0;
				selectedPreFighter = (FighterBoxPreBattle) getChildren().get(GameScreen.positionToSelectP1);
				selectedPreFighter.update();
			}
		} else if (player == 2 && getChildren().size() > 3) {
			if (InputUtility.getKeyPressed(KeyCode.I) && GameScreen.positionToSelectP2 != 0) {
				unselectedPreFighter = (FighterBoxPreBattle) getChildren().get(GameScreen.positionToSelectP2);
				unselectedPreFighter.draw();
				GameScreen.positionToSelectP2--;
				selectedPreFighter = (FighterBoxPreBattle) getChildren().get(GameScreen.positionToSelectP2);
				selectedPreFighter.update();
			} else if (InputUtility.getKeyPressed(KeyCode.K)
					&& GameScreen.positionToSelectP2 != getChildren().size() - 1) {
				unselectedPreFighter = (FighterBoxPreBattle) getChildren().get(GameScreen.positionToSelectP2);
				unselectedPreFighter.draw();
				GameScreen.positionToSelectP2++;
				selectedPreFighter = (FighterBoxPreBattle) getChildren().get(GameScreen.positionToSelectP2);
				selectedPreFighter.update();
			} else if (InputUtility.getKeyPressed(KeyCode.SEMICOLON)) {
				RenderableHolder.Select_Sound.play();
				selectedPreFighter = (FighterBoxPreBattle) getChildren().get(GameScreen.positionToSelectP2);
				SimulationManager.getP2PaneBattle().addFighters(selectedPreFighter.getFighter());
				getChildren().remove(GameScreen.positionToSelectP2);
				GameScreen.positionToSelectP2 = 0;
				selectedPreFighter = (FighterBoxPreBattle) getChildren().get(GameScreen.positionToSelectP2);
				selectedPreFighter.update();
			}
		}
	}

	public Fighter[] getFightersLeft() {
		Fighter[] fightersLeft = new Fighter[getChildren().size() - 3];
		for (int i = 0; i < getChildren().size() - 3; i++) {
			fightersLeft[i] = ((FighterBoxPreBattle) getChildren().get(i)).getFighter();
		}
		return fightersLeft;
	}

}
