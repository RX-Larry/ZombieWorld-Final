package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * An action that is used by humans to 
 * eat food.
 * 
 * @author Lim Tzeyi 30512867
 *
 */
public class EatAction extends Action {
	
	private Food food;
	
	/**
	 * Constructor
	 * 
	 * @param food item in inventory to eat
	 */
	public EatAction(Item food) {
		this.food = (Food) food;									//downcast item e to Food class to 
																	//call getHeal() method in Food class 
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		if (actor.hasCapability(HumanCapability.EAT)) {				//check if actor has Eat HumanCapability 
			actor.heal(food.getHeal());								//player health increase after eating food
			actor.removeItemFromInventory(food);					//food is removed from inventory
		}
		return actor + " ate " + food.toString() + " restore " + food.getHeal() + " health points.";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " eats " + food.toString();
	}

}
