package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * An Action that Harvest ripe crop from the ground.
 * 
 * @author Lim Tzeyi 30512867
 *
 */
public class HarvestAction extends Action {
	
	private Location target;						
	private Item item;					
	
	/**
	 * Constructor for HarvestAction
	 * 
	 * @param location 	The location to harvest on the map
	 * @param item 		The item on the map 
	 */
	public HarvestAction(Location location, Item item) {
		this.target = location;						
		this.item = item;						
	}

	@Override
	public String execute(Actor actor, GameMap map) {		
		
		if (actor.hasCapability(HumanCapability.HARVEST)) {						//check if the actor has Harvest HumanCapability
			map.at(target.x(), target.y()).removeItem(item);					//remove item at location
			actor.addItemToInventory(new CropFood());							//add CropFood object to the actor's Inventory
			return actor + " harvest a crop and stored as food in inventory";	
		}
		
		else if(actor.hasCapability(FarmerCapability.HARVEST)){					//check if the actor has Harvest FarmerCapability
			map.at(target.x(), target.y()).removeItem(item);					//remove item at location
			map.at(target.x(), target.y()).addItem(new CropFood());				//add CropFood object on the map
			return actor + " harvested a crop and dropped a food on the ground";
		}
		
		return "No crop can be harvested by " + actor;																
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " harvest crop";																				
	}

}
