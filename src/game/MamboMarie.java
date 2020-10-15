package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

public class MamboMarie extends ZombieActor {

    public static final char MAMBO_MARIE = 'M';
    private int turnForChant = 0;
    private int numberOfChant = 0;

    public MamboMarie(String name, int hitPoints) {
        super(name, MAMBO_MARIE, hitPoints, ZombieCapability.UNDEAD);
    }

    Behaviour wanderBehaviour = new WanderBehaviour();

    public void setTurnForChant(int turnForChant) {
        this.turnForChant = turnForChant;
    }

    public int getTurnForChant() {
        return turnForChant;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        Action action = wanderBehaviour.getAction(this, map);
        setTurnForChant(turnForChant+1);
        // ===========================
        if (getTurnForChant() == 10){
//            if (getTurnForChant() == 3){
            //=======================
            setTurnForChant(0);
            ChantAction chantAction = new ChantAction();
            chantAction.setNumberOfZombies(5 * numberOfChant);
            numberOfChant += 1;
            return chantAction;
        }
        return action;
    }
}
