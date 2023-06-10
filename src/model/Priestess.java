package model;

import java.util.HashMap;
import java.util.Random;

/**
 * This class is used to create a Priestess along with Priestess special behaviors.
 */
public final class Priestess extends Hero {

    /**
     * The constructor that creates an instance of a Priestess.
     * @param theName the name of the Priestess.
     * @param theHealth the total health of the Priestess.
     * @param theCurrentHealth the current health of the Priestess.
     * @param theDmgMin the min. damage the Priestess can do.
     * @param theDmgMax the max damage the Priestess can do.
     * @param theChanceToHit the Priestess chance to hit.
     * @param theAttkSpd the Priestess attack speed.
     * @param theAlive the state of aliveness.
     * @param theChanceToBlock the Priestess chance to block an attack.
     * @param theInventory the Priestess inventory of items.
     * @param theVision the Priestess vision.
     */
    public Priestess(String theName, int theHealth, int theCurrentHealth, int theDmgMin, int theDmgMax, double theChanceToHit, double theAttkSpd,
                     boolean theAlive, double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision) {
        super(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive, theChanceToBlock, theInventory, theVision);
    }

    /**
     * The Priestess special move of healing themselves for a random amount.
     * @return the new healed health.
     */
    public int heal() {
        Random rand = new Random();
        int heal = rand.nextInt(super.getMyHealthPoints());
        if (super.getMyCurrentHealth() + heal > super.getMyHealthPoints()) {
            super.setMyCurrentHealth(super.getMyHealthPoints());
        } else {
            super.setMyCurrentHealth(super.getMyCurrentHealth() + heal);
        }
        return heal;
    }

}
