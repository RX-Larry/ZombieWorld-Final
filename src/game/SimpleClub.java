package game;

import edu.monash.fit2099.engine.WeaponItem;
/**
 * Class representing a simple club
 *
 * @author Huang Yaoying 31044891
 *
 */
public class SimpleClub extends WeaponItem {
    public static final char SIMPLE_CLUB = 'b';
    public static final char SIMPLE_MACE = 'a';

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     */
    public SimpleClub(String name, char displayChar, int damage, String verb) {
        super(name, displayChar, damage, verb);
    }  
}
