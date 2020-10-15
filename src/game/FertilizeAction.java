package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * An action that is used to Fertilize crop, to 
 * speed up growth.
 * 
 * @author Lim Tzeyi 30521867
 *
 */
public class FertilizeAction extends Action {
	
	private Item target;											//the item the Farmer wants to fertilize
	
	/**
	 * Constructor for FertilizeAction
	 * 
	 * @param item	The item we want to Fertilize on the map
	 */
	public FertilizeAction(Item item) {								
		this.target = item;						
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		Location actorLocation = map.locationOf(actor);				//get the actor's location
		
		for (int i = 0; i < 10; i++) {								//decrease time left to ripen by 10 turns
			target.tick(actorLocation);								//make the ground the crop is on to tick 10 turns
		}
		return target + " fertilized by 10 turns";					//tell the user the crop was fertilized by 10 turns
	}

	@Override
	public String menuDescription(Actor actor) {					//no need menu description 
		return null;
	}

}
