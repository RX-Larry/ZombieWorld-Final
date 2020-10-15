package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;
/**
 * Class representing a chant action
 * @author Huang Yaoying 31044891
 */

public class ChantAction extends Action {
    private int numberOfZombies;

    public void setNumberOfZombies(int numberOfZombies) {
        this.numberOfZombies = numberOfZombies;
    }

    public int getNumberOfZombies() {
        return numberOfZombies;
    }

    /**
     * create 5 zombies at random location
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String shows zombies are created
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        NumberRange weight = map.getXRange();
        NumberRange height = map.getYRange();
        String result = "Mambo Marie chants";
        int i = 0;
        while (i < 5){
            Location randomLocation = map.at((int)(Math.random() * (weight.max() - weight.min())), (int)(Math.random()*(height.max() - height.min())));
            if (!randomLocation.containsAnActor()){
                Zombie zombie = new Zombie("zombie" + getNumberOfZombies());
                setNumberOfZombies(getNumberOfZombies()+1);
                randomLocation.addActor(zombie);
                result += "\n" + zombie.toString() + " is created";
                i++;
            }
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
