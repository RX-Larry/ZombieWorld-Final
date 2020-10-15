package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class representing an ordinary human.
 * 
 * @author ram
 *
 */
public class Human extends ZombieActor {
	private Behaviour behaviour = new WanderBehaviour();
	public static final char HUMAN_DISPLAY_CHAR = 'H';

	/**
	 * The default constructor creates default Humans
	 * 
	 * @param name the human's display name
	 */
	public Human(String name) {
		super(name, HUMAN_DISPLAY_CHAR, 50, ZombieCapability.ALIVE);
		if (name.length() > 20) {
			throw new IllegalArgumentException("Human name length cannot be more than 20");
		}
		this.addCapability(HumanCapability.HARVEST); 									//give default humans capability to harvest
		this.addCapability(HumanCapability.EAT);										//give default humans capability to eat
	}


	/**
	 * The protected constructor can be used to create subtypes
	 * of Human, such as the Player
	 * 
	 * @param name the human's display name
	 * @param displayChar character that will represent the Human in the map 
	 * @param hitPoints amount of damage that the Human can take before it dies
	 */
	protected Human(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE);
		this.addCapability(HumanCapability.EAT);											//give subtypes of humans EAT HumanCapability
		this.addCapability(HumanCapability.HARVEST); 										//give subtypes of humans HARVEST HumanCapability
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
		//for HarvestAction
		ArrayList<Exit> exits = new ArrayList<Exit>(map.locationOf(this).getExits());		//get locations around the Human
		for(Exit e: exits) {
			List<Item> itemsOnGround = e.getDestination().getItems();						//get items at locations
			for(Item item: itemsOnGround) {
				if (item.hasCapability(CropCapability.RIPE)) {								//check if the item has RIPE CropCapability
					actions.add(new HarvestAction(e.getDestination(), item));				//Harvest the item
				}
			}
		}
		
		//for HarvestAction
		List<Item> itemsAtHuman = map.locationOf(this).getItems();							//get items at Human location
		for (Item item: itemsAtHuman) {
			if (item.hasCapability(CropCapability.RIPE)) {									//check if the item has RIPE CropCapability
				actions.add(new HarvestAction(map.locationOf(this), item));					//Harvest the item
			}
		}
		
		//for EatAction
		List<Item> humanInventory = this.getInventory();									//get the Human's inventory
		for (Item item: humanInventory) {														
			if (item instanceof Food) {														//check if the item is an instance of Food			
				actions.add(new EatAction(item));											//perform EatAction
			}
		}
		
		//try and except method to prevent getActor() at location to return nullpointerexception
		//used to attack animals
		for (Exit e: exits) {
			try {
				Actor animal = e.getDestination().getActor();
				if (animal.hasCapability(AnimalCapability.KILLABLE)) {
					actions.add(new AttackAction(animal));
				}
			}
			catch (java.lang.NullPointerException exception){
				continue;
			}
		}
		
		//try and except method to prevent getActor() at location to return nullpointer exception
		//used to attack creeper
		for (Exit e: exits) {
			try {
				Actor hostileMob = e.getDestination().getActor();
				if (hostileMob.hasCapability(HostileMobCapability.KILLABLE)) {
					actions.add(new AttackAction(hostileMob));
				}
			}
			catch(java.lang.NullPointerException exception) {
				continue;
			}
		}
		
		
		// FIXME humans are pretty dumb, maybe they should at least run away from zombies?
		return behaviour.getAction(this, map);
	}

}
