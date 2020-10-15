package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
//		World world = new World(new Display());
		ZombieWorld world = new ZombieWorld(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		List<String> townMap = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"............++..................................................................",
		"..............++++..............................................................",
		".............+++...+++..........................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"............+++.................................................................",
		".............+++++..............................................................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		
		GameMap town = new GameMap(groundFactory, townMap);
		world.addGameMap(town);
		
		//place vehicle in compound to get to town
		TownItem car1 = new TownItem("Car", '^', false);
		car1.addAction(new MoveActorAction(town.at(45, 15), "to Town!"));
		gameMap.at(60, 2).addItem(car1);

		//unsure, but place vehicle in town to get to compound
		TownItem car2 = new TownItem("Car", '^', false);
		car2.addAction(new MoveActorAction(gameMap.at(60, 2), "to Compound!"));
		town.at(60, 2).addItem(car2);

		//place random humans in the town map
		String[] townHumans = {"Carlton", "May", "Vicente", "Andrea"};
		int x1, y1;
		for (String name : townHumans) {
			do {
				x1 = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y1 = (int) Math.floor(Math.random() * 7.0 + 5.0);
			}
			while (town.at(x1, y1).containsAnActor());
			town.at(x1,  y1).addActor(new Human(name));
		}

		//place random zombies in the town map
		town.at(30, 20).addActor(new Zombie("Groan"));
		town.at(30,  18).addActor(new Zombie("Boo"));
		town.at(10,  4).addActor(new Zombie("Uuuurgh"));
		town.at(50, 18).addActor(new Zombie("Mortalis"));
		town.at(1, 10).addActor(new Zombie("Gaaaah"));
		town.at(62, 12).addActor(new Zombie("Aaargh"));

		//place random farmers around the town
		town.at(35, 18).addActor(new Farmer("Farmer Larry"));
		town.at(1, 15).addActor(new Farmer("Farmer Ming"));
		town.at(65, 12).addActor(new Farmer("Farmer Tom"));

		//place random animals around the town
		town.at(20, 11).addActor(new Pig());
		town.at(1, 19).addActor(new Cow());
		town.at(3, 11).addActor(new Chicken());

		//place random animals in the compound map
		gameMap.at(20, 11).addActor(new Pig());
		gameMap.at(1, 19).addActor(new Cow());
		gameMap.at(3, 11).addActor(new Chicken());

		//place sniper rifle around the town
		town.at(10, 19).addItem(new SniperRifle());

		//place shotgun around the town
		town.at(35, 10).addItem(new Shotgun());

		//place ammunition around the town
		town.at(35, 9).addItem(new Ammunition());
		town.at(10, 10).addItem(new Ammunition());
		gameMap.at(70, 10).addItem(new Ammunition());
		gameMap.at(30, 18).addItem(new Ammunition());
		gameMap.at(15, 10).addItem(new Ammunition());
		
		//place human to go to town
//		Actor player = new Player("Player", '@', 100);
//		world.addPlayer(player, gameMap.at(60, 3));
		
	    // Place some random humans
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			}
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));
		}

		// place a simple weapon
		gameMap.at(74, 20).addItem(new Plank());

		//Huang Yaoying test
		// player standing besides a zombie
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(29, 20));  //player beside zombie
//
////		gameMap.at(29,19).addActor(new Zombie("zombie"));
////		gameMap.at(29, 20).addItem(new ShotgunAmmunition());
//
//
//		// test shotAction ==================================
////		gameMap.at(29, 20).addItem(new ShotgunAmmunition());
////		for (int i = 25; i <= 33; i++){
////			for (int j = 16; j <= 24; j++){
////				if (!gameMap.at(i, j).containsAnActor()){
////					gameMap.at(i, j).addActor(new Zombie("zombie"));
////				}
////			}
////		}
		// test sniper rifle
//		gameMap.at(29,20).addItem(new SniperRifle());
//		gameMap.at(29,20).addItem(new Ammunition());

//
//		// test zombie pick up weapon
//		// mortails
////		gameMap.at(49, 19).addItem(new Plank());
////		gameMap.at(49, 18).addItem(new Plank());
////		gameMap.at(49, 17).addItem(new Plank());
////		gameMap.at(50, 19).addItem(new Plank());
////		gameMap.at(50, 17).addItem(new Plank());
////		gameMap.at(51, 19).addItem(new Plank());
////		gameMap.at(51, 18).addItem(new Plank());
////		Actor player = new Player("Player", '@', 100);
////		world.addPlayer(player, gameMap.at(51, 17));
//		//gameMap.at(51, 17).addActor(new Human("aHuman"));
//
//		//test human rising from dead
//		gameMap.at(61, 12).addActor(new Human("aHuman")); //beside Aaargh
//		gameMap.at(2, 10).addActor(new Human("bHuman")); //beside Gaaaah
//		//gameMap.at(49, 18).addActor(new Human("cHuman")); //beside Mortalis
//
//
//
		//Lim Tzeyi tests
		//add creeper to show it explodes
//		Actor creeper = new Creeper();
//		gameMap.at(60, 8).addActor(creeper);
//
		//placing creeper next to a zombie to show it explodes and kills
//		gameMap.at(60, 7).addActor(new Zombie("Groan"));

		//test killing animals and they drop food
//		Actor player = new Player("Player", '@', 100);
//		world.addPlayer(player, gameMap.at(61, 10));
//		gameMap.at(61, 11).addActor(new Pig());
//		gameMap.at(61, 9).addActor(new Cow());
//		gameMap.at(61, 8).addActor(new Chicken());

		//testing zombies destroying crops
		Crop cropTest = new Crop();
		gameMap.at(60, 0).addActor(new Zombie("ZomZom"));
		gameMap.at(60, 0).addItem(cropTest);

		//test Farmer growing crops and harvesting
//		Actor farmerTest = new Farmer("Farmer Bob");
//		gameMap.at(56, 1).addActor(farmerTest);

		//test exception handling for Farmer
//		try {
//			Actor farmerTest = new Farmer("Farmer Tim Lee Ming Hoo");
//			gameMap.at(56, 1).addActor(farmerTest);
//		} catch (Exception e1){
////			e1.printStackTrace();
//			System.out.println("Farmer name length cannot be more then 20");
//		}

		//test exception handling for Human
//		try {
//			Actor humanTest = new Human("Elizabeth Ribeiro McNamara");
//			gameMap.at(56, 1).addActor(humanTest);
//		} catch (Exception e1){
////			e1.printStackTrace();
//			System.out.println("Human name length cannot be more then 20");
//		}


//		//place a ZombieClub and see if it can be picked up
//		gameMap.at(43, 15).addItem(new ZombieClub());
//
//		//place a ZombieMace and see if it can be picked up
//		gameMap.at(43, 16).addItem(new ZombieMace());

		// FIXME: Add more zombies!
		gameMap.at(30, 20).addActor(new Zombie("Groan"));
		gameMap.at(30,  18).addActor(new Zombie("Boo"));
		gameMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
		gameMap.at(50, 18).addActor(new Zombie("Mortalis"));
		gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
		gameMap.at(62, 12).addActor(new Zombie("Aaargh"));
		
		world.run();
	}
}
