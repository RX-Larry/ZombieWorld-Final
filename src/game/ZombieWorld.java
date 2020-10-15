package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * Class representing ZombieWorld which checks 
 * whether the player won or lost.
 * 
 * @author Lim Tzeyi 30512867
 *
 */
public class ZombieWorld extends World {
	
	/**
	 * Constructor for ZombieWorld
	 * 
	 * @param display the Display that will display this world
	 */
	public ZombieWorld(Display display) {
		super(display);
	}
	
	@Override
	public void run() {
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}

		// This loop is basically the whole game
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			
			ArrayList<Character> charList = new ArrayList(); 	//array list used to store the actors in the map
			for (Actor actor: actorLocations) {
				charList.add(actor.getDisplayChar());  			//add all the actors char in the map into the array list
			}
			
			//test to see if charList works
//			System.out.println("before clear: " + charList);
			
			if((charList.contains('H')) == false) {				//check if all humans are dead
				System.out.println("player loses");
				break;
			}
			
			else if (charList.contains('Z') == false) {			//check if all zombies are dead
				if (charList.contains('M') == false) {			//check if mambo marie is dead
					System.out.println("player wins");
					break;
				}
				else {
					continue;
				}
			}
			
			charList.clear(); 									//reset the contents of charList
			
			//testing to see if the array list clears
//			System.out.println(charList);

			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
			}
			
			if (stillRunning() == false) { 						//check if the player is dead
				System.out.println("player loses"); 
				break;
			}
		}
		display.println(endGameMessage());
	}
}
