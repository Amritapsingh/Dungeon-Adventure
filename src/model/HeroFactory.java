package model;

import java.util.HashMap;

/**
 * This class is a factory class used to create different objects of Heroes.
 */
public final class HeroFactory {

    /**
     * This method creates a Warrior Object.
     *
     * @param theName          the name of the Warrior
     * @param theHealth        the total health of the Warrior.
     * @param theCurrentHealth the current health of the warrior.
     * @param theDmgMin        the minimum damage the warrior can bestow.
     * @param theDmgMax        the maximum damage the warrior can do.
     * @param theChanceToHit   the warrior's chance to hit an attack.
     * @param theAttkSpd       the warriors attack speed.
     * @param theAlive         the aliveness of the Warrior.
     * @param theChanceToBlock the warrior's chance to block stat.
     * @param theInventory     the warrior's inventory.
     * @param theVision        the warrior's vision.
     * @return an object of a Warrior.
     */
    public static Warrior createWarrior(final String theName, final int theHealth, final int theCurrentHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit, final double theAttkSpd,
                                        final boolean theAlive, final double theChanceToBlock, final HashMap<String, Integer> theInventory, final double theVision) {
        return new Warrior(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive, theChanceToBlock, theInventory, theVision);
    }

    /**
     * This method creates a Priestess Object.
     *
     * @param theName          the name of the Priestess.
     * @param theHealth        the total health of the Priestess.
     * @param theCurrentHealth the current health of the Priestess.
     * @param theDmgMin        the minimum damage the Priestess can bestow.
     * @param theDmgMax        the maximum damage the Priestess can do.
     * @param theChanceToHit   the Priestess's chance to hit an attack.
     * @param theAttkSpd       the Priestess attack speed.
     * @param theAlive         the aliveness of the Priestess.
     * @param theChanceToBlock the Priestess's chance to block stat.
     * @param theInventory     the Priestess's inventory.
     * @param theVision        the Priestess's vision.
     * @return an object of a Priestess.
     */
    public static Priestess createPriestess(final String theName, final int theHealth, final int theCurrentHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit, final double theAttkSpd,
                                            final boolean theAlive, final double theChanceToBlock, final HashMap<String, Integer> theInventory, final double theVision) {
        return new Priestess(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd,
                theAlive, theChanceToBlock, theInventory, theVision);
    }

    /**
     * This method creates a Thief Object.
     *
     * @param theName          the name of the Thief.
     * @param theHealth        the total health of the Thief.
     * @param theCurrentHealth the current health of the Thief.
     * @param theDmgMin        the minimum damage the Thief can bestow.
     * @param theDmgMax        the maximum damage the Thief can do.
     * @param theChanceToHit   the Thief's chance to hit an attack.
     * @param theAttkSpd       the Thief attack speed.
     * @param theAlive         the aliveness of the Thief.
     * @param theChanceToBlock the Thief's chance to block stat.
     * @param theInventory     the Thief's inventory.
     * @param theVision        the Thief's vision.
     * @return an object of a Thief.
     */
    public static Thief createThief(final String theName, final int theHealth, final int theCurrentHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit, final double theAttkSpd,
                                    final boolean theAlive, final double theChanceToBlock, final HashMap<String, Integer> theInventory, final double theVision) {
        return new Thief(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd,
                theAlive, theChanceToBlock, theInventory, theVision);
    }

}
