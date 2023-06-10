package model;

import java.util.HashMap;
import java.util.Random;

/**
 * This class is used to create the Thief object and special behaviors.
 */
public final class Thief extends Hero {


    /**
     * The constructor that creates an instance of a Thief.
     * @param theName the name of the Thief.
     * @param theHealth the total health of the Thief.
     * @param theCurrentHealth the current health of the Thief.
     * @param theDmgMin the min. damage the Thief can do.
     * @param theDmgMax the max damage the Thief can do.
     * @param theChanceToHit the Thief chance to hit.
     * @param theAttkSpd the Thief attack speed.
     * @param theAlive the state of aliveness.
     * @param theChanceToBlock the Thief chance to block an attack.
     * @param theInventory the Thief inventory of items.
     * @param theVision the Thief's vision.
     */
    public Thief(String theName, int theHealth, int theCurrentHealth, int theDmgMin, int theDmgMax, double theChanceToHit, double theAttkSpd,
          boolean theAlive, double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision) {
        super(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive, theChanceToBlock, theInventory, theVision);

    }

    /**
     * The Thief's special move that if successful does an extra turn while on its current turn. Does double damage to opponent.
     * @param theHealth the opponent's current health.
     * @return the new opponent current health.
     */
    public int surpriseAttck(int theHealth) {
        Random rand = new Random();
        final double successHit = rand.nextDouble();
        if (successHit < 0.4) {
            theHealth -= (super.regularAttack(theHealth, super.getMyChanceToHit())) * 2; // double damage
        } else if (successHit < 0.9) {
            theHealth -= super.regularAttack(theHealth, super.getMyChanceToHit());
        }
        return theHealth;
    }

}
