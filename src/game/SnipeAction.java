package game;

import edu.monash.fit2099.engine.*;

import java.util.Scanner;

/**
 * Class representing a snipe action
 * @author Huang Yaoying 31044891
 */
public class SnipeAction extends Action {
    private double probabilityOfHit = 0.75;
    private int damage = 40;
    private String targetName;

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setProbabilityOfHit(double probabilityOfHit) {
        this.probabilityOfHit = probabilityOfHit;
    }

    /**
     * fire a specific target or aim
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String what the actor does in this action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        Scanner obj = new Scanner(System.in);
        while (true) {
            if (player.getAimRound() == 0){
                System.out.println("Enter the name of the target");
                targetName = obj.nextLine();
            }
            else{
                targetName = player.getSnipeTarget();
            }
            for (int i = map.getXRange().min(); i <= map.getXRange().max(); i++) {
                for (int j = map.getYRange().min(); j <= map.getYRange().max(); j++) {
                    Location currentLocation = map.at(i, j);
                    if (currentLocation.containsAnActor() && currentLocation.getActor().toString().equals(targetName)) {
                        while (true) {
                            if (player.getAimRound() ==  2){
                                setAimRoundToZero(player);
                                return fireTarget(actor, map, i, j);
                            }
                            else {
                                System.out.println("Input 1 if you want to fire now");
                                System.out.println("Input 2 if you want to aim, you will have more chance to hit and get a higher damage");
                            }
                            Scanner obj2 = new Scanner(System.in);
                            int order = obj2.nextInt();
                            if (order == 1) {
                                if (Math.random() <= probabilityOfHit) {
                                    setAimRoundToZero(player);
                                    return fireTarget(actor, map, i,j);
                                }
                                else
                                    return actor.toString() + " miss the target";
                            } else if (order == 2) {
                                player.setSnipeTarget(targetName);
                                AimAction aimAction = new AimAction();
                                return aimAction.execute(actor, map);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * set the aimRound to 0
     * @param player player
     */
    public void setAimRoundToZero(Player player){
        player.setAimRound(0);
    }

    /**
     * create a new attackAction and attack the target
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @param x x value of actor's location
     * @param y y value of actor's location
     * @return a string shows the target has been attacked
     */
    public String fireTarget(Actor actor, GameMap map, int x, int y){
        Actor target = map.at(x, y).getActor();
        AttackAction attackAction = new AttackAction(target);
        attackAction.setDamage(damage);
        for (Item item: actor.getInventory()){
            if (item.getDisplayChar() == Ammunition.ammunition){
                actor.removeItemFromInventory(item);
                break;
            }
        }
        return (attackAction.execute(actor, map));
    }

    @Override
    public String menuDescription(Actor actor) {
        return "snipe the target";
    }
}
