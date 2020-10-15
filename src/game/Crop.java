package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Class to represent Crop
 * 
 * @author Lim Tzeyi 30512867
 *
 */
public class Crop extends Item {

	private int age = 0;									//used to track the crop's growth
	
	/**
	 * Constructor for Crop
	 * 
	 */
	public Crop() {
		super("Crop", 'o', false);							//Crop can't be picked up or dropped
		this.addCapability(CropCapability.UNRIPE);			//Initially Crop is UNRIPE
	}
	
	@Override
	public void tick(Location location) {
		super.tick(location);
		
		age++;												//increment as tick increases
		if (age == 10) {
			displayChar = 'c';								//when the age is 10, the crop is 
															//half ripe. displayChar changes to 'c'.
		}
		if(age == 20) {
			displayChar = 'C';								//when age is 20, displayChar changes to 'C'.
			this.removeCapability(CropCapability.UNRIPE);	//remove UNRIPE capability
			this.addCapability(CropCapability.RIPE);		//add RIPE capability
		}
	}

}
