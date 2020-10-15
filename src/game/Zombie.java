package game;


//import com.sun.tools.attach.AgentInitializationException;
import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * A Zombie.
 *
 * This Zombie is pretty boring.  It needs to be made more interesting.
 *
 * @author ram
 *
 */
public class Zombie extends ZombieActor {

	private int turnForPunch;
	private int turnForWalk;
	public static final String BITES = "bites";
	public static final String PUNCHES = "punches";
	public static final String BITES_AND_PUNCHES = "bites and punches";
	public static final char ZOMBIE_DISPLAY_CHAR = 'Z';
	public static final char ARM_DISPLAY_CHAR = 'A';
	public static final char LEG_DISPLAY_CHAR = 'L';
	private AttackBehaviour attackBehaviour = new AttackBehaviour(ZombieCapability.ALIVE);
	private HuntBehaviour huntBehaviour = new HuntBehaviour(Human.class, 10);
	private WanderBehaviour wanderBehaviour = new WanderBehaviour();
	private PickUpBehaviour pickUpBehaviour = new PickUpBehaviour();
	private DestroyBehaviour destroyBehaviour = new DestroyBehaviour();
	Item arm;
	Item leg;

	/**
	 * constructor
	 * when creating a zombie, it has two arms and two legs in its inventory
	 * @param name zombie's name
	 */
	public Zombie(String name) {
		super(name, ZOMBIE_DISPLAY_CHAR, 100, ZombieCapability.UNDEAD);
		addBehaviours();
		arm = new Arm("arm", Zombie.ARM_DISPLAY_CHAR, true);
		addItemToInventory(arm);
		addItemToInventory(arm);
		leg = new Leg("leg", Zombie.LEG_DISPLAY_CHAR, true);
		addItemToInventory(leg);
		addItemToInventory(leg);
		turnForPunch = 0;
		turnForWalk = 0;
	}

	private ArrayList<Behaviour> behaviours = new ArrayList<Behaviour>();

	/**
	 * add the behaviour the zombie should have for the zombie
	 */
	public void addBehaviours() {
		behaviours.add(destroyBehaviour);				//give zombies the ability to destroy crops
		behaviours.add(pickUpBehaviour);
		behaviours.add(attackBehaviour);
		behaviours.add(huntBehaviour);
		behaviours.add(wanderBehaviour);
	}

	/**
	 * zombie has a 25% chance to drop its limbs,
	 * if it drop its first arm, it has a 50% chance to drop the weapon it is holding
	 * if he has no arm after losing this arm, it will drop all the weapon it has
	 * after it drop its arm, create a new SimpleClub on where it is standing
	 * if it drop its leg, it create a new SimpleClub also
	 * lost leg has more damage than lost arm
	 * @param actor zombie
	 * @param map the ground that the actor is standing on
	 */
	public void dropLimb(Actor actor, GameMap map){
		if (Math.random() <= 0.25){
//		 for (Item e: getInventory()){
//			 System.out.println(e.getDisplayChar());
//		 }
			if (getInventory().contains(arm) && Math.random() <= 0.5) {
				removeItemFromInventory(arm);
				addTurnForPunch();
				System.out.println(name + " lose an arm");
				SimpleClub simpleClub = new SimpleClub("zombie's arm", SimpleClub.SIMPLE_CLUB, 10, "wield");
				dropItem(actor, map, simpleClub);
				if (!getInventory().contains(arm) || Math.random() <= 0.5) {
					for (Item e : getInventory()) {
						if (e.getDisplayChar() == Plank.PLANK_DISPLAY_CHART) {
							removeItemFromInventory(e);
							dropItem(actor, map, e);
						}
					}
				}
			}
			else if (getInventory().contains(leg)){
				removeItemFromInventory(leg);
				addTurnForWalk();
				System.out.println(name + " lose a lag");
				SimpleClub simpleMace = new SimpleClub("zombie's leg", SimpleClub.SIMPLE_MACE, 15, "wield");
				dropItem(actor, map, simpleMace);
			}
//		for (Item e: getInventory()){
//			System.out.println(e.getDisplayChar());
		}
//		}
	}

	/**
	 * create a new DropItemAction and drop the item
	 * @param actor zombie
	 * @param map the ground where the zombie is standing
	 * @param item the item which is going to drop
	 */
	public void dropItem(Actor actor, GameMap map, Item item){
		DropItemAction dropItemAction = new DropItemAction(item);
		dropItemAction.execute(actor, map);
	}

	/**
	 * getter
	 * @return turnForPunch
	 */
	public int getTurnForPunch() {
		return turnForPunch;
	}

	/**
	 * getter
	 * @return turnForWalk
	 */
	public int getTurnForWalk() {
		return turnForWalk;
	}

	/**
	 * turnForPunch + 1
	 */
	public void addTurnForPunch(){
		turnForPunch += 1;
	}

	/**
	 * turnForWalk + 1
	 */
	public void addTurnForWalk(){
		turnForWalk += 1;
	}


	/**
	 * create a intrinsic weapon for zombie
	 * @return the IntrinsicWeapon that the zombie is going to use
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		double random = Math.random();
		if (inventory.contains(arm) && getTurnForPunch() % 2 == 0){
			if (random <= 0.5)
				return new IntrinsicWeapon(10, PUNCHES);
//				return new IntrinsicWeapon(1, PUNCHES);
			else if (random <= 0.75)
				return new IntrinsicWeapon(20, BITES_AND_PUNCHES);
//				return new IntrinsicWeapon(1, BITES_AND_PUNCHES);
		}
			return new IntrinsicWeapon(15,  BITES);
//		return new IntrinsicWeapon(1,  BITES);
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.
	 * If no humans are close enough it will wander randomly.
	 *
	 * @param actions    list of possible Actions
	 * @param lastAction previous Action, if it was a multi-turn action
	 * @param map        the map where the current Zombie is
	 * @param display    the Display where the Zombie's utterances will be displayed
	 */


	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		saySomething(this);
		if (getTurnForPunch() > 0)
			addTurnForPunch();
			//System.out.println(turnForPunch);
		if (turnForWalk > 0){
			if (getInventory().contains(leg) && !behaviours.contains(huntBehaviour) &&
					!behaviours.contains(wanderBehaviour) && getTurnForWalk() % 2 == 0) {
				behaviours.add(huntBehaviour);
				behaviours.add(wanderBehaviour);
			}
			if (!getInventory().contains(leg) || getTurnForWalk() % 2 == 1){
				behaviours.remove(huntBehaviour);
				behaviours.remove(wanderBehaviour);
			}
			addTurnForWalk();
			//System.out.println(turnForWalk);
		}
		if (hitPoints > maxHitPoints){
			hitPoints = maxHitPoints;
		}
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

	/**
	 * print the zombie is saying something
	 * @param actor zombie
	 */
	public void saySomething(Actor actor) {
		if (Math.random() <= 0.1) {
			System.out.println(actor.toString() + " says: Braaaaains");
		}
	}
}
