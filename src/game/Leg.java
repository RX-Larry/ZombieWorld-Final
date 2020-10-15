package game;

import edu.monash.fit2099.engine.Item;

/**
 * Class representing a leg
 *
 * @author Huang Yaoying 31044891
 *
 */
public class Leg extends Item {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Leg(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }
}
