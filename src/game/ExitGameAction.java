package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class representing quit game action
 * 
 * @author Lim Tzeyi 30512867
 *
 */

public class ExitGameAction extends Action {

	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(actor);							//remove the player from the gamemap 
														//which ends the game
		return "Quit game sucessful";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "quit game";								//gives player option to quit the game
	}

}
