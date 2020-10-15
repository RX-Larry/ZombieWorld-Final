package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Class which allows zombie to destroy crops
 * 
 * @author Lim Tzeyi 30512867
 *
 */
public class DestroyCropAction extends Action {
	
	private Item target;
	
	/**
	 * Constructor for DestroyCropAction
	 * 
	 * @param item item to be destroyed
	 */
	public DestroyCropAction(Item item) {
		this.target = item;												//set target as the item we want to destroy
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		Location actorLocation = map.locationOf(actor);					//get the location of the actor
		map.locationOf(actor).removeItem(target);						//remove the destroyed item a location
		return actor.toString() + " destroyed a " + target.toString();	
	}

	@Override
	public String menuDescription(Actor actor) {						//no need menu description
		return null;	
	}

}
