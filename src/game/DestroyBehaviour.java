package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class that allows actor to destroy crops on actor's
 * location
 * 
 * @author Lim Tzeyi 30512867
 *
 */
public class DestroyBehaviour implements Behaviour {

	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Item> items = map.locationOf(actor).getItems();													//get the list of item at actor's location
		for (Item item: items) {
			if ((item.hasCapability(CropCapability.UNRIPE)) || (item.hasCapability(CropCapability.RIPE))) {		//check if there is crop at location
				return new DestroyCropAction(item);																//if yes, destroy the crop
			}
		}
		return null;
	}

}
