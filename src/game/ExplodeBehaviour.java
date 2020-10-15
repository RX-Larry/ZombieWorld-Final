package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class that makes an actor explodes and inflicts 
 * damage to living things around it.
 * 
 * @author Lim Tzeyi 30512867
 *
 */
public class ExplodeBehaviour implements Behaviour {
	
	/**
	 * Constructor for ExplodeBehaviour
	 */
	public ExplodeBehaviour() {
		
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		for (Exit e: exits) {
			try {
				Actor target = e.getDestination().getActor();				//get target within the blast radius
				//check if there is living things around the actor
				if ((target.hasCapability(AnimalCapability.KILLABLE)) || (target.hasCapability(ZombieCapability.ALIVE)) || (target.hasCapability(ZombieCapability.UNDEAD))) {
					return new ExplodeAction(e.getDestination(), target);
				}
			}
			catch (java.lang.NullPointerException exception){
				continue;		
			}
		}
		return null;
	}

}
