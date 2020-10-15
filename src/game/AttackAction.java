package game;

import java.util.Random;

import edu.monash.fit2099.engine.*;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	private int changeDamage = 0;

	public void setDamage(int damage) {
		this.changeDamage = damage;
	}

	public int getDamage() {
		return changeDamage;
	}

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

//		if (rand.nextBoolean()) {
//			return actor + " misses " + target + ".";
//		}
		
		if (weapon.verb().equals(Zombie.BITES) || weapon.verb().equals(Zombie.BITES_AND_PUNCHES)){
			actor.heal(5);
			System.out.println(actor.toString() + " bites the target, restore 5 health points");
		}

		if (target.getDisplayChar() == Zombie.ZOMBIE_DISPLAY_CHAR){
			Zombie z = (Zombie) target;
			z.dropLimb(actor, map);
		}
		int damage = weapon.damage();

		if (changeDamage != 0){
			damage = changeDamage;
		}
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);

		//original attack action
//		if (!target.isConscious()) {
//			Corpse corpse = new Corpse(target.toString() + target, Corpse.CORPSE_DISPLAY_CHAR);
//			map.locationOf(target).addItem(corpse);
//			Actions dropActions = new Actions();
//			for (Item item : target.getInventory())
//				dropActions.add(item.getDropAction());
//			for (Action drop : dropActions)
//				drop.execute(target, map);
//			map.removeActor(target);
//			result += System.lineSeparator() + target + " is killed.";
//		}
		
		//modified attack action
		if (!target.isConscious()) {			
			if (target.hasCapability(AnimalCapability.KILLABLE)) {				//check if the target is KILLABLE		
				if (target.hasCapability(CowCapability.MOO)) {					//check if the target is a cow
					map.locationOf(target).addItem(new Steak());				//if yes then place a steak at target location
				}
				
				if (target.hasCapability(PigCapability.OINK)) {					//check if the target is a pig	
					map.locationOf(target).addItem(new Pork());					//if yes then place a pork at target location
				}
				
				if (target.hasCapability(ChickenCapability.CLUCK)) {			//check if the target is a chicken
					map.locationOf(target).addItem(new Drumstick());			//if yes then place a drumstick at target location
				}
			}
			
			else if (target.hasCapability(HostileMobCapability.KILLABLE)) {		//check if there is a killable mob
				if (target.hasCapability(CreeperCapability.EXPLODE)) {			//check if it is a creeper
					map.removeActor(target);									//the target is killed
				}
			}
			
			else {
				if (target.getDisplayChar() == Human.HUMAN_DISPLAY_CHAR) {
					Corpse corpse = new Corpse(target.toString() + target, Corpse.CORPSE_DISPLAY_CHAR);
					map.locationOf(target).addItem(corpse);
				}
				//========================================
					Actions dropActions = new Actions();
					for (Item item : target.getInventory()) {
						dropActions.add(item.getDropAction());
					}
					for (Action drop : dropActions)
						System.out.println(drop.execute(target, map));
				//=========================================
			}
			map.removeActor(target);
			result += System.lineSeparator() + target + " is killed.";
			if (target.getDisplayChar() == Zombie.ZOMBIE_DISPLAY_CHAR && actor.getDisplayChar() == '@'){
				target.heal(10);
				result += "\n" + actor.toString() + " killed " + target.toString() + " restore 10 health points";
			}
		}
 		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
