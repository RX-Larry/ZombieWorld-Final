package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Class representing a corpse.
 *
 * @author Huang Yaoying 31044891
 *
 */
public class Corpse extends Item {
    private int tick = 0;
    public static final char CORPSE_DISPLAY_CHAR = '%';

    public Corpse(String name, char displayChar) {
        super(name, CORPSE_DISPLAY_CHAR,false);
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        tick++;
        if ((tick >= 5 && Math.random() <= 0.2) || tick == 10){
            createZombie(currentLocation);
            System.out.println(description());
            currentLocation.removeItem(this);
        }
    }

    public void createZombie(Location currentLocation){
        currentLocation.addActor(new Zombie(name));
    }

    public String description(){
        return name + " rise from the dead";
    }
}
