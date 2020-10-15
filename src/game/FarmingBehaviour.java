package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Class representing Farmer's behaviour.
 * 
 * @author Lim Tzeyi 30521867
 *
 */
public class FarmingBehaviour implements Behaviour {

	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());			//get locations around the actor
		Collections.shuffle(exits); 														//randomize the locations the farmer sows
		
		List<Item> items = map.locationOf(actor).getItems();								//get the list of items at the actor's location
		for (Item item: items) {													
			if (item.hasCapability(CropCapability.UNRIPE)) {								//check if any of the items has the UNRIPE CropCapability
				return new FertilizeAction(item);											//fertilize the item at the actor's location
			}
		}
		
		for (Exit e: exits) {																
			if(e.getDestination().getDisplayChar() == '.') {								//check if the location has a dirt character
				return new SowAction(e.getDestination());									//call SowAction passing the location
			}
		}
		
		return null;
	}

}
