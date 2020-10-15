package game;

import edu.monash.fit2099.engine.WeaponItem;
/**
 * Class representing a sniper rifle
 * @author Huang Yaoying 31044891
 */

public class SniperRifle extends WeaponItem {
    public static final char SNIPER_RIFLE_DISPLAY_CHAR = 'R';

    /**
     * Constructor.
     *
     */
    public SniperRifle() {
        super("sniper rifle", SNIPER_RIFLE_DISPLAY_CHAR, 10, "snipe");
    }
}
