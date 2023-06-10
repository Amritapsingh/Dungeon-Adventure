package model;

import java.util.HashMap;
import java.util.Random;

/**
 * This class is used to create an object of a Warrior that the user can play as.
 */
public final class Warrior extends Hero {

    /**
     * The constructor creates the Warrior object.
     *
     * @param theName          name of the warrior.
     * @param theHealth        the total health of the warrior.
     * @param theCurrentHealth the current health of the warrior.
     * @param theDmgMin        the warrior's min. damage.
     * @param theDmgMax        the warrior's max damage.
     * @param theChanceToHit   the warrior's chance to hit.
     * @param theAttkSpd       the warrior's attack speed.
     * @param theAlive         the warrior's state of aliveness.
     * @param theChanceToBlock the warrior's chance to block.
     * @param theInventory     the warrior's inventory.
     * @param theVision        the warrior's vision.
     */
    public Warrior(String theName, int theHealth, int theCurrentHealth, int theDmgMin, int theDmgMax, double theChanceToHit, double theAttkSpd,
                   boolean theAlive, double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision) {
        super(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive, theChanceToBlock, theInventory, theVision);
    }

    /**
     * This method is the warrior's special move that deals more damage than a regular damage.
     *
     * @param theHealth the opponent's health.
     * @return the new opponent's health.
     */
    public int crushingBlow(int theHealth) {
        Random rand = new Random();
        double hitSucceed = rand.nextDouble();
        if (hitSucceed < 0.4) {
            theHealth -= rand.nextInt(101) + 75; // special attack from 75 to 175 damage
        }
        return theHealth;
    }

}
