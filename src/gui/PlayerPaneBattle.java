package gui;

import java.util.ArrayList;

import entity.base.Fighter;
import gui.base.PlayerPane;
import javafx.scene.Node;
import logic.GameController;
import logic.Updatable;

public class PlayerPaneBattle extends PlayerPane implements Updatable {
	private ArrayList<Fighter> fighters;

	public PlayerPaneBattle(int player) {
		super(player);
		fighters = new ArrayList<Fighter>();
	}

	public void addFighters(Fighter fighter) {
		if (fighters.size() < 5) {
			if (player == 1) {
				GameController.getGameBoard().map[fighters.size()][0] = fighter.getSymbol();
			} else if (player == 2) {
				GameController.getGameBoard().map[fighters.size()][6] = fighter.getSymbol();
			}
			fighters.add(fighter);
			addFighterBox(fighter);
		}
	}

	public void addFighterBox(Fighter fighter) {
		getChildren().add(new FighterBoxBattle(fighter, fighter.getSymbol()));
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		// update each FighterBox in its
		for (Node node : getChildren()) {
			((FighterBoxBattle) node).update();
		}
	}

	public void fillUp(PlayerPanePreBattle playerPreBattle) {
		for (Fighter fighter : playerPreBattle.getFightersLeft()) {
			addFighters(fighter);
		}
	}

	public ArrayList<Fighter> getFighters() {
		return fighters;
	}

}
