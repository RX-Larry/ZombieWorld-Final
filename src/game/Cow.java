package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class used to represent Cows
 *
 * @author Lim Tzeyi 30521867
 *
 */
public class Cow extends Animal {
	private static final char DISPLAYCHAR = 'W';
	private static final int HITPOINTS = 60;
	private static final String NAME = "Cow";
	private ArrayList<Behaviour> behaviours = new ArrayList<Behaviour>();										
	private WanderBehaviour wanderBehaviour = new WanderBehaviour();
	
	/**
	 * Constructor of Cow which gives it a moo capability
	 */
	public Cow() {
		super(NAME, DISPLAYCHAR, HITPOINTS);
		this.addCapability(CowCapability.MOO);
	}
	
	/**
	 * Method to add behaviours to Chickens
	 */
	private void addBehaviours() {
		behaviours.add(wanderBehaviour);									//adds behaviour into behaviour arraylist of Cow
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
		saySomething(this, "Moo");											//allow cow to say "Moo"
		
		addBehaviours();													//add wander behaviour to Cows
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new WanderBehaviour().getAction(this, map);
	}

}
