package game;

import edu.monash.fit2099.engine.*;

import java.util.Scanner;
/**
 * Class representing an shot action
 * @author Huang Yaoying 31044891
 */
public class ShotAction extends Action {

    /**
     * shot actors in a specific range of location
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string shows attacked zombies or fail to fire
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String north = "north";
        String northeast = "northeast";
        String east = "east";
        String southeast = "southeast";
        String south = "south";
        String southwest = "southwest";
        String west = "west";
        String northwest = "northwest";

        Location locationOfPlayer = map.locationOf(actor);
        int x = locationOfPlayer.x();
        int y = locationOfPlayer.y();

        Scanner obj = new Scanner(System.in);   //create a scanner object
        System.out.println("Enter the direction");
        String direction = obj.nextLine();  //read user input

        //============================
        //if (Math.random() >= 0.75){
        //============================
        if (Math.random() <= 0.9999){
            while (!direction.equals(north) && !direction.equals(northeast) && !direction.equals(east)
                    && !direction.equals(southeast) && !direction.equals(south) && !direction.equals(southwest)
                    && !direction.equals(west) && !direction.equals(northwest)) {
                System.out.println("Direction can only be either " + north + ",  " + northeast + ",  12" + east
                 + ",  " + southeast + ", " + south + ",  " + southwest + ", " + west + " or " + northwest);
                System.out.println("Enter the direction");
                direction = obj.nextLine();
            }
            if (direction.equals(north)){
                fireNorth(actor, map, x, y);
            }
            else if (direction.equals(northeast)){
                fireNortheast(actor, map, x, y);
            }
            else if (direction.equals(east)){
                fireEast(actor, map, x, y);
            }
            else if (direction.equals(southeast)){
                fireSoutheast(actor, map, x, y);
            }
            else if (direction.equals(south)){
                fireSouth(actor, map, x, y);
            }
            else if (direction.equals(southwest)){
                fireSouthwest(actor, map, x, y);
            }
            else if (direction.equals(west)){
                fireWest(actor, map, x, y);
            }
            else {
                fireNorthWest(actor, map, x, y);
            }
            removeAmmunition(actor);
            return actor.toString() + " shots to " + direction;
        }
        return actor.toString() + "fails to shot";
    }

    /**
     * remove ammunition from actor's inventory
     * @param actor the actor performing this method
     */
    public void removeAmmunition(Actor actor){
        for (Item item: actor.getInventory()){
            if (item.getDisplayChar() == Ammunition.ammunition){
                actor.removeItemFromInventory(item);
                break;
            }
        }
    }

    /**
     * fire actors in the north
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @param x x value of actor's location
     * @param y y value of actor's location
     */
    public void fireNorth(Actor actor, GameMap map, int x, int y){
        for (int j = y - 1; j >= y - 3; j--) {
            int difference = y - j;
            int i = x - difference;
            while (i <= x + difference){
                if (i <= map.getXRange().max() && j <= map.getYRange().max()){
                    fire(actor, map, i, j);
                }
                i++;
            }
        }
    }

    /**
     * fire actors in northeast
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @param x x value of actor's location
     * @param y y value of actor's location
     */
    public void fireNortheast(Actor actor, GameMap map, int x, int y){
        for (int i = x; i <= x + 3; i++){
            for (int j = y; j >= y - 3; j--){
                if (!(i == x && j == y)){
                    if (i <= map.getXRange().max() && j <= map.getYRange().max()){
                        fire(actor, map, i, j);
                    }
                }
            }
        }
    }

    /**
     * fire actors in east
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @param x x value of actor's location
     * @param y y value of actor's location
     */
    public void fireEast(Actor actor, GameMap map, int x, int y){
        for (int i = x + 1; i <= x + 3; i++){
            int difference = i - x;
            int j = y - difference;
            while (j <= y + difference){
                if (i <= map.getXRange().max() && j <= map.getYRange().max()){
                    fire(actor, map, i, j);
                }
                j++;
            }
        }
    }

    /**
     * fire actors in southeast
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @param x x value of actor's location
     * @param y y value of actor's location
     */
    public void fireSoutheast(Actor actor, GameMap map, int x, int y){
        for (int i = x; i <= x + 3; i++){
            for (int j = y; j <= y + 3; j++){
                if (!(i == x && j == y)){
                    if (i <= map.getXRange().max() && j <= map.getYRange().max()){
                        fire(actor, map, i, j);
                    }
                }
            }
        }
    }

    /**
     * fire actors in south
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @param x x value of actor's location
     * @param y y value of actor's location
     */
    public void fireSouth(Actor actor, GameMap map, int x, int y){
        for (int j = y + 1; j <= y + 3; j++){
            int difference = j - y;
            int i = x - difference;
            while (i <= x + difference){
                if (i <= map.getXRange().max() && j <= map.getYRange().max()){
                    fire(actor, map, i, j);
                }
                i++;
            }
        }
    }

    /**
     * fire actors in southwest
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @param x x value of actor's location
     * @param y y value of actor's location
     */
    public void fireSouthwest(Actor actor, GameMap map, int x, int y){
        for (int i = x; i >= x - 3; i--){
            for (int j = y; j<= y + 3; j++){
                if (!(i == x && j == y)){
                    if (i <= map.getXRange().max() && j <= map.getYRange().max()){
                        fire(actor, map, i, j);
                    }
                }
            }
        }
    }

    /**
     * fire actors in west
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @param x x value of actor's location
     * @param y y value of actor's location
     */
    public void fireWest(Actor actor, GameMap map, int x, int y){
        for (int i = x - 1; i >= x - 3; i--){
            int difference = x - i;
            int j = y - difference;
            while (j <= y + difference){
                if (i <= map.getXRange().max() && j <= map.getYRange().max()){
                    fire(actor, map, i, j);
                }
                j++;
            }
        }
    }

    /**
     * fire actors in northwest
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @param x x value of actor's location
     * @param y y value of actor's location
     */
    public void fireNorthWest(Actor actor, GameMap map, int x, int y){
        for (int i = x; i >= x - 3; i--){
            for(int j = y; j>= y - 3; j--){
                if (!(i == x && j == y)){
                    if (i <= map.getXRange().max() && j <= map.getYRange().max()){
                        fire(actor, map, i, j);
                    }
                }
            }
        }
    }


     /**
     * fire a specific actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @param x x value of actor's location
     * @param y y value of actor's location
     */
    public void fire(Actor actor, GameMap map, int x,  int y){
        Location fireLocation = map.at(x, y);
        if (map.isAnActorAt(fireLocation)) {
            AttackAction attackAction = new AttackAction(map.getActorAt(fireLocation));
            attackAction.setDamage(50);
            // attackAction.setDamage(100);
            System.out.println(attackAction.execute(actor, map));
        }
    }



    @Override
    public String menuDescription(Actor actor) {
        return actor + " use the shotgun";
    }
}
