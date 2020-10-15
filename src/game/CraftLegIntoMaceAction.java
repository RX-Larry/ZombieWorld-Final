package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
/**
 * Class representing an action that craft a zombie's leg into a mace.
 *
 * @author Huang Yaoying 31044891
 *
 */
public class CraftLegIntoMaceAction extends Action {

    /**
     * remove the SimpleClub from player's inventory
     * create a new ZombieMace object and put it into te player's inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String showing a zombieMACE is created
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (Item e : actor.getInventory()) {
            if (e.getDisplayChar() == SimpleClub.SIMPLE_MACE) {
                ZombieMace mace = new ZombieMace("ZombieMace", ZombieMace.MACE_DISPLAY_CHAR, 30, "zaps");
                actor.removeItemFromInventory(e);
                actor.addItemToInventory(mace);
                return menuDescription(actor);
            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " craft the leg into a mace";
    }
}

