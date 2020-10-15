package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Class representing a shotgun
 * @author Huang Yaoying 31044891
 */
public class Shotgun extends WeaponItem {
    public static final char SHOTGUN_DISPLAY_CHAR = 'S';

    /**
     * Constructor.
     */
    public Shotgun() {
        super("shotgun", 'S', 10, "shot");
    }

}
