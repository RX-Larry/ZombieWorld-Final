package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * Class representing the Player.
 */
public class Player extends Human {
	public static final char PLAYER_DISPLAY_CHAR = '@';
	private Menu menu = new Menu();
	private int turnForMamboMarieVanish = 0;
	private int mamboMarieHitPoints = 50;
	private int aimRound = 0;
	private String snipeTarget;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, PLAYER_DISPLAY_CHAR, hitPoints);
	}

	MamboMarie mamboMarie = new MamboMarie("Mambo Marie", mamboMarieHitPoints);

	public void setTurnForMamboMarieVanish(int turnForMamboMarieVanish) {
		this.turnForMamboMarieVanish = turnForMamboMarieVanish;
	}

	public int getTurnForMamboMarieVanish() {
		return turnForMamboMarieVanish;
	}

	public void aimRoundPlus() {
		aimRound++;
	}

	public int getAimRound() {
		return aimRound;
	}

	public void setAimRound(int aimRound) {
		this.aimRound = aimRound;
	}

	public String getSnipeTarget() {
		return snipeTarget;
	}

	public void setSnipeTarget(String snipeTarget) {
		this.snipeTarget = snipeTarget;
	}

	public void createActor(Location location, Actor actor) {
		location.addActor(actor);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		//for user to quit the game
		actions.add(new ExitGameAction());

		// mambo marie has 5% chance to appear, start at the edge of the map
		if (!map.contains(mamboMarie) && mamboMarieHitPoints > 0) {
			//==============================
//			if (Math.random() <= 0.999) {
			if (Math.random() <= 0.05){
				//==============================
				NumberRange weight = map.getXRange();
				NumberRange height = map.getYRange();
				int randomX = (int) (Math.random() * (weight.max() - weight.min()));
				int randomY = (int) (Math.random() * (height.max() - height.min()));
				double r = Math.random();
				if (r < 0.25) {
					Location mamboMarieLocation = map.at(weight.min(), randomY);    // the location of new mambo marie (if there is no any other actor on this location)
					while (mamboMarieLocation.containsAnActor()) {        //if the location contains other actor
						mamboMarieLocation = map.at(weight.max(), (int) (Math.random() * (height.max() - height.min())));        //create a new random location
					}
					createActor(mamboMarieLocation, mamboMarie);
				} else if (r < 0.5) {
					Location mamboMarieLocation = map.at(randomX, height.max());
					while (mamboMarieLocation.containsAnActor()) {
						mamboMarieLocation = map.at((int) (Math.random() * (weight.max() - weight.min())), height.max());
					}
					createActor(mamboMarieLocation, mamboMarie);
				} else if (r < 0.75) {
					Location mamboMarieLocation = map.at(weight.max(), randomY);
					while (mamboMarieLocation.containsAnActor()) {
						mamboMarieLocation = map.at(weight.max(), (int) (Math.random() * (height.max() - height.min())));
					}
					createActor(mamboMarieLocation, mamboMarie);
				} else {
					Location mamboMarieLocation = map.at(randomX, height.min());
					while (mamboMarieLocation.containsAnActor()) {
						mamboMarieLocation = map.at((int) (Math.random() * (weight.max() - weight.min())), height.min());
					}
					mamboMarieLocation.addActor(mamboMarie);
				}
			}
		} else {
			// turnForMamboMarieVanish keeps adding until turnForMamboMarieVanish reach 30
			setTurnForMamboMarieVanish(turnForMamboMarieVanish + 1);
			if (getTurnForMamboMarieVanish() == 30) {
				setTurnForMamboMarieVanish(0);
				map.removeActor(mamboMarie);
			}
		}

		//for HarvestAction
		ArrayList<Exit> exits = new ArrayList<Exit>(map.locationOf(this).getExits());        //get locations around the Player
		for (Exit e : exits) {
			List<Item> itemsOnGround = e.getDestination().getItems();                        //get the items at location
			for (Item item : itemsOnGround) {
				if (item.hasCapability(CropCapability.RIPE)) {                                //check if the item has RIPE CropCapability
					actions.add(new HarvestAction(e.getDestination(), item));                //Harvest the item
				}
			}
		}

		//for HarvestAction
		List<Item> itemsAtPlayer = map.locationOf(this).getItems();                            //get the items at Player's location
		for (Item item : itemsAtPlayer) {
			if (item.hasCapability(CropCapability.RIPE)) {                                    //check if the item has RIPE CropCapability
				actions.add(new HarvestAction(map.locationOf(this), item));                    //Harvest the item
			}
		}

		//for EatAction
		List<Item> playerInventory = this.getInventory();                                    //get the Player's inventory
		for (Item item : playerInventory) {
			if (item instanceof Food) {                                                        //check if the item is an instance of Food
				actions.add(new EatAction(item));                                            //perform EatAction
			}
		}

		//try and except method to prevent getActor() at location to return nullpointerexception
		//used to attack animals
		for (Exit e : exits) {
			try {
				Actor animal = e.getDestination().getActor();
				if (animal.hasCapability(AnimalCapability.KILLABLE)) {                //check if the animal is killable
					actions.add(new AttackAction(animal));
				}
			} catch (java.lang.NullPointerException exception) {
				continue;
			}
		}

		//try and except method to prevent getActor() at location to return nullpointer exception
		//used to attack creeper
		for (Exit e : exits) {
			try {
				Actor hostileMob = e.getDestination().getActor();
				if (hostileMob.hasCapability(HostileMobCapability.KILLABLE)) {        //check if the HostileMob if killable
					actions.add(new AttackAction(hostileMob));
				}
			} catch (java.lang.NullPointerException exception) {
				continue;
			}
		}

		// Handle multi-turn Actions
		int a = 0;
		while (a < getInventory().size()) {
			if (getInventory().get(a).getDisplayChar() == SimpleClub.SIMPLE_CLUB) {
				actions.add(new CraftArmIntoClubAction());
			}
			if (getInventory().get(a).getDisplayChar() == SimpleClub.SIMPLE_MACE) {
				actions.add(new CraftLegIntoMaceAction());
			}
			if (getInventory().get(a).getDisplayChar() == Shotgun.SHOTGUN_DISPLAY_CHAR || getInventory().get(a).getDisplayChar() == SniperRifle.SNIPER_RIFLE_DISPLAY_CHAR) {
				int b = 0;
				while (b < getInventory().size()) {
					if (getInventory().get(b).getDisplayChar() == Ammunition.ammunition) {
						if (getInventory().get(a).getDisplayChar() == Shotgun.SHOTGUN_DISPLAY_CHAR) {
							actions.add(new ShotAction());
						}
						if (getInventory().get(a).getDisplayChar() == SniperRifle.SNIPER_RIFLE_DISPLAY_CHAR) {
							SnipeAction snipeAction = new SnipeAction();
							if (aimRound == 1){
								snipeAction.setProbabilityOfHit(0.9);
								snipeAction.setDamage(80);
							}
							else if (aimRound == 2){
								snipeAction.setProbabilityOfHit(1);
								snipeAction.setDamage(100);
							}
							actions.add(snipeAction);
						}
					}
					b++;
				}
			}
			//actions.add(new SnipeAction(this));
			//actions.add(new AimAction());
			a++;
		}

		return menu.showMenu(this, actions, display);
	}
}