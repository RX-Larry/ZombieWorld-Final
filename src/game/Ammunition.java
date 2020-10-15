package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Class representing an ammunition
 * @author Huang Yaoying 31044891
 */
public class Ammunition extends PortableItem {
    public static final char ammunition = '$';

    public Ammunition() {
        super("ammunition", Ammunition.ammunition);
    }
}
