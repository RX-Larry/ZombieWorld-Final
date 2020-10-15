package game;

import edu.monash.fit2099.engine.Actor;

/**
 * Class to represent Hostile Mobs
 * 
 * @author Lim Tzeyi 30512867
 *
 */
public abstract class HostileMob extends Actor {
	
	/**
	 * Constructor for Hostile Mobs
	 * 
	 * @param name name of the hostile mobs
	 * @param displayChar display character for the hostile mob
	 * @param hitPoints initial health of the hostile mob
	 */
	public HostileMob(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(HostileMobCapability.KILLABLE); 				//make hostile mobs killable 
	}
	
	/**
	 * Method to get the hitpoints for hostile mob
	 * 
	 * @return current health of the hostile mob
	 */
	public int getHitPoints() {											//getter for hitpoints
		return hitPoints;
	}
	
	/**
	 * Method to set the hitpoints for hostile mob
	 * 
	 * @param health the health to set for the hostile mob
	 */
	public void setHitPoints(int health) { 								//setter got hitpoints
		this.hitPoints = health;
	}
	
	/**
	 * Method to allow the hostile mob to say
	 * something with a 100% probability.
	 * 
	 * @param actor actor who is saying
	 * @param word the word the actor will say
	 */
	public void saySomething(Actor actor, String word) {
		System.out.println(actor.toString() + "says: " + word);
	}
}
