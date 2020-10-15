package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class used to represent Chickens
 * 
 * @author Lim Tzeyi 30521867
 *
 */
public class Chicken extends Animal {
	private static final char DISPLAYCHAR = 'N';
	private static final int HITPOINTS = 20;
	private static final String NAME = "Chicken";
	private ArrayList<Behaviour> behaviours = new ArrayList<Behaviour>();										
	private WanderBehaviour wanderBehaviour = new WanderBehaviour();
	
	/**
	 * Constructor of Chicken which gives it a cluck capability
	 */
	public Chicken() {
		super(NAME, DISPLAYCHAR, HITPOINTS);
		this.addCapability(ChickenCapability.CLUCK);
	}
	
	/**
	 * Method to add behaviours to Chickens
	 */
	private void addBehaviours() {
		behaviours.add(wanderBehaviour);									//adds behaviour into behaviour arraylist of chicken
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
		saySomething(this, "Cluck");										//allow chicken to say "Cluck"
		
		addBehaviours();													//add wander behaviour to Chickens
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new WanderBehaviour().getAction(this, map);
	}

}
