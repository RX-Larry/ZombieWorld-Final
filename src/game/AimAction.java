package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class representing an action that actor aim
 * @author Huang Yaoying 31044891
 */
public class AimAction extends Action {

    /**
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String shows actor is aiming
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        player.aimRoundPlus();
        return actor.toString() + " aim for one turn";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
