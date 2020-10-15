package game;

import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Action that is used to Sow crops on the ground.
 * 
 * @author Lim Tzeyi 30521867
 *
 */
public class SowAction extends Action {

	protected Random rand = new Random();																	
	private Location target;															
	
	/**
	 * Constructor for SowAction which takes in a location
	 * 
	 * @param location	The location of the map we want to sow
	 */
	public SowAction(Location location) {
		this.target = location;																						
	}	
	
	@Override
	public String execute(Actor actor, GameMap map) {
		char locationChar = map.at(target.x(), target.y()).getDisplayChar();			//Get the display character of the location
																	
		if (locationChar == 'o' || locationChar == 'c' || locationChar == 'C') {		//check if the location contains a Crop character
			return "";								
		}
		else {																			//if not then plant a crop
			if (Math.random() < 0.33) {													//sowing crop has a probability of 33%
				map.at(target.x(), target.y()).addItem(new Crop());						//sow the location with crop
				return actor + " planted a crop.";								
			}
			else {
				return actor + " can't plant a crop";									//show on UI if unsucessful
			}
		}
	}

	@Override
	public String menuDescription(Actor actor) {										//menu is not needed
		return null;
	}

}
