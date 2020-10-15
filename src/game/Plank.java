package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A primitive weapon.
 * 
 * @author ram
 *
 */
public class Plank extends WeaponItem {
	public static final char PLANK_DISPLAY_CHART = ')';

	public Plank() {
		super("plank", PLANK_DISPLAY_CHART, 20, "whacks");
		// TODO Auto-generated constructor stub
	}

}
