package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class to represent Creeper
 * 
 * @author Lim Tzeyi 30512867
 *
 */
public class Creeper extends HostileMob {
	public static final char DISPLAYCHAR ='!';
	public static final int HITPOINTS = 20;
	public static final String NAME = "Creeper";
	private ArrayList<Behaviour> behaviours = new ArrayList<Behaviour>();										
	private WanderBehaviour wanderBehaviour = new WanderBehaviour();
	private ExplodeBehaviour explodeBehaviour = new ExplodeBehaviour();
	
	/**
	 * Constructor for the Creeper
	 */
	public Creeper() {
		super(NAME, DISPLAYCHAR, HITPOINTS);
		this.addCapability(CreeperCapability.EXPLODE);
	}
	
	/**
	 * Method to add behaviours for Creeper
	 */
	private void addBehaviours() {
		behaviours.add(explodeBehaviour);
		behaviours.add(wanderBehaviour);
	}
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		saySomething(this, "Imma Blow!!!!!");
		
		addBehaviours();																	//add farmingBehaviour and wanderBehaviour to Farmer
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new WanderBehaviour().getAction(this, map);
	}
		
}
