package game;

import edu.monash.fit2099.engine.Actor;

/**
 * Class to represent animals
 * 
 * @author Lim Tzeyi 30512867
 *
 */
public abstract class Animal extends Actor {
	
	/**
	 * Constructor of animal where initialized animals
	 * would be given KILLABLE Animal Capability
	 * 
	 * @param name Name of the animal
	 * @param displayChar Display character representation of the animal
	 * @param hitPoints Health of the animal
	 */
	public Animal(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(AnimalCapability.KILLABLE);						//make animals killable
	}
	
	/**
	 * Method to get the hitpoints of the animal
	 * 
	 * @return the hitpoints of the animal
	 */
	public int getHitPoints() {												//getter for hitpoints
		return hitPoints;
	}
	
	/**
	 * Method to set the hitpoints of the animal
	 * 
	 * @param health the hitpoints of the animal
	 */
	public void setHitPoints(int health) {									//setter for hitpoints
		this.hitPoints = health;
	}
	
	/**
	 * Method to allow the animals to say something
	 * with a probability of 60%
	 * 
	 * @param actor actor who is performing the action
	 * @param word word which will be said by the actor
	 */
	public void saySomething(Actor actor, String word) {					//allows the animal to say something
		if (Math.random() <= 0.6) {
			System.out.println(actor.toString() + " says: " + word);
		}
	}
}
