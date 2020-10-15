package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class used to represent Pigs
 * 
 * @author Lim Tzeyi 30521867
 *
 */
public class Pig extends Animal {
	private static final char DISPLAYCHAR = 'P';
	private static final int HITPOINTS = 20;
	private static final String NAME = "Pig";
	private ArrayList<Behaviour> behaviours = new ArrayList<Behaviour>();										
	private WanderBehaviour wanderBehaviour = new WanderBehaviour();
	
	/**
	 * Constructor of Pig which gives it an oink capability
	 */
	public Pig() {
		super(NAME, DISPLAYCHAR, HITPOINTS);
		this.addCapability(PigCapability.OINK);							
	}
	
	/**
	 * Method to add behaivours to Pigs
	 */
	private void addBehaviours() {
		behaviours.add(wanderBehaviour);									//adds behaviour into behaviour arraylist of chicken
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
		saySomething(this, "Oink");											//allow pig to say "Oink"
		
		addBehaviours();													//add wander behaviour to Chickens
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new WanderBehaviour().getAction(this, map);
	}

}
