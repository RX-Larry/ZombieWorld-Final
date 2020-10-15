package game;

/**
 * Abstract class representing Food
 * 
 * @author Lim Tzeyi 30512867
 *
 */
public abstract class Food extends PortableItem {

	private int heal;
	
	/**
	 * Constructor for Food
	 * 
	 * @param name			Name of the food
	 * @param displayChar 	Display character chosen for the food
	 * @param heal 			The amount of heal points when consuming the food
	 */
	public Food(String name, char displayChar, int heal) {
		super(name, displayChar);
		this.heal = heal;											//this gives the food instance a heal value
		
	}
	
	/**
	 * A getter for heal attribute.
	 * 
	 * @return The amount of heal points the food provides
	 */
	public int getHeal() {
		return heal;		
	}

}
