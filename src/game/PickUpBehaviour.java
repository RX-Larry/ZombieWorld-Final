package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing a behaviour that check whether the place the actor standing has a weapon, if yes, pick it up
 *
 * @author Huang Yaoying 31044891
 *
 */
public class PickUpBehaviour implements Behaviour {

    /**
     * create a new PickUpItemAction if there is a weapon on the ground where the actor is standing
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return new PickUpItemAction if there is a weapon on the ground where the actor is standing
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.locationOf(actor).getItems().isEmpty())
            for (Item item: map.locationOf(actor).getItems()) {
                if (item.getDisplayChar() == Plank.PLANK_DISPLAY_CHART ||
                        (item.getDisplayChar() == SimpleClub.SIMPLE_CLUB) ||
                        item.getDisplayChar() == SimpleClub.SIMPLE_MACE) {
                    if (actor.getInventory().contains(item)) //zombie can only have one weapon
                        return null;
                    return new PickUpItemAction(item);
                }
            }
        return null;
    }
}
