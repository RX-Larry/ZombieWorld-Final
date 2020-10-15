package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Class to represent Explode action
 * 
 * @author Lim Tzeyi 30512867
 *
 */
public class ExplodeAction extends Action {
	
	private Location location;							
	private Actor target;
	protected Random rand = new Random();
	
	/**
	 * Constructor for ExplodeAction
	 * 
	 * @param location blast radius
	 * @param target target which gets damages within the blast radius
	 */
	public ExplodeAction(Location location, Actor target) {
		this.location = location;
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		if (rand.nextBoolean()) {								
			return actor.toString() + " doesn't blow up";
		}
			
		target.hurt(30);							//explode action damages health by 30 hitpoints
//		target.hurt(100); 							//for testing
		
		if(!target.isConscious()) {					//check if the target is alive
			map.removeActor(target);				//if dead then remove the target
			map.removeActor(actor);					//since the actor explodes so the actor dies
			return actor.toString() + " explosion killed " + target.toString();
		}
		map.removeActor(actor);						//actor dies as it explodes
		return actor.toString() + " exploded and " + target.toString() + " suffered 30 damage";
	}

	@Override
	public String menuDescription(Actor actor) {							//menu not needed
		return null;
	}

}
