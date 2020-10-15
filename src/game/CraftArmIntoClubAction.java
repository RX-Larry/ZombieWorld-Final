package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing an action that craft a zombie's arm into a club.
 *
 * @author Huang Yaoying 31044891
 *
 */
public class CraftArmIntoClubAction extends Action {

    /**
     * remove the SimpleClub from player's inventory
     * create a new ZombieClub object and put it into te player's inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String showing a zombieClub is created
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (Item e : actor.getInventory()) {
            if (e.getDisplayChar() == SimpleClub.SIMPLE_CLUB){
                ZombieClub club = new ZombieClub("ZombieClub", ZombieClub.CLUB_DISPLAY_CHAR, 20, "hits");
                actor.removeItemFromInventory(e);
                actor.addItemToInventory(club);
                return menuDescription(actor);
            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
            return actor + " craft the arm into a club";
        }
}
