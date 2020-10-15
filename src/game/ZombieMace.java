package game;

import edu.monash.fit2099.engine.WeaponItem;
/**
 * Class representing mace
 *
 * @author Huang Yaoying 31044891
 *
 */
public class ZombieMace extends WeaponItem {
    public static final char MACE_DISPLAY_CHAR = 'E';

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     */
    public ZombieMace(String name, char displayChar, int damage, String verb) {
        super(name, displayChar, damage, verb);
    }
}
