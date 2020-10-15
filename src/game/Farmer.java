package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing a Farmer
 * 
 * @author Lim Tzeyi 30512867
 *
 */
public class Farmer extends Human {

	private ArrayList<Behaviour> behaviours = new ArrayList<Behaviour>();					
	private FarmingBehaviour farmingBehaviour = new FarmingBehaviour();						
	private WanderBehaviour wanderBehaviour = new WanderBehaviour();						
	
	/**
	 * The Farmer constructor
	 * 
	 * @param name	Name to call the Farmer in the UI
	 */
	public Farmer(String name) {
		super(name, 'F', 100);	
		if (name.length() > 20) {
			throw new IllegalArgumentException("Farmer name length cannot be more than 20");
		}
//		super(name, 'F', 100);																//Farmer is given a 'F' displayChar and have 100 health points
		this.addCapability(FarmerCapability.HARVEST); 										//give farmer FarmerCapability to Harvest
		this.removeCapability(HumanCapability.EAT); 										//remove eat HumanCapability from farmer
		this.removeCapability(HumanCapability.HARVEST);   									//remove harvest HumanCapability from farmer
	}
	
	/**
	 * A method to add Farmer behaviours
	 */
	private void addBehaviours() {
		behaviours.add(farmingBehaviour);													//add farmingBehaviour to Farmer
		behaviours.add(wanderBehaviour);													//add wanderBehaviour to Farmer
	}
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
		//check if there is harvestable crop around the Farmer
		ArrayList<Exit> exits = new ArrayList<Exit>(map.locationOf(this).getExits());		//get location around Farmer
		for(Exit e: exits) {
			List<Item> itemsOnGround = e.getDestination().getItems();						//get the list of items at those locations
			for(Item item: itemsOnGround) {
				if (item.hasCapability(CropCapability.RIPE)) {								//check if any items have RIPE Crop Capability
					return new HarvestAction(e.getDestination(), item);						//perform HarvestAction
				}
			}
		}
		
		//check if there is harvestable crop at Farmer location
		List<Item> itemsAtFarmer = map.locationOf(this).getItems();							//get location of Farmer
		for (Item item: itemsAtFarmer) {
			if (item.hasCapability(CropCapability.RIPE)) {									//check if any items have RIPE Crop Capability
				return new HarvestAction(map.locationOf(this), item);						//perform HarvestAction
			}
		}
		
	
		addBehaviours();																	//add farmingBehaviour and wanderBehaviour to Farmer
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new WanderBehaviour().getAction(this, map);									//if no suitable action, Farmer wanders around
		
	}

}
