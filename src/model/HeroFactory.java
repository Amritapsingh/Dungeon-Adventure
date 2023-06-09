package model;

import java.util.HashMap;

public final class HeroFactory {

    public static Hero createWarrior(final String theName, final int theHealth, final int theCurrentHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit, final double theAttkSpd,
                                     final boolean theAlive, final double theChanceToBlock, final HashMap<String, Integer> theInventory, final double theVision) {
        return new Warrior(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive, theChanceToBlock, theInventory, theVision);
    }

    public static Hero createPriestess(final String theName, final int theHealth, final int theCurrentHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit, final double theAttkSpd,
                                       final boolean theAlive, final double theChanceToBlock, final HashMap<String, Integer> theInventory, final double theVision) {
        return new Priestess(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd,
                theAlive, theChanceToBlock, theInventory, theVision);
    }

    public static Hero createThief(final String theName, final int theHealth, final int theCurrentHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit, final double theAttkSpd,
                                   final boolean theAlive, final double theChanceToBlock, final HashMap<String, Integer> theInventory, final double theVision) {
        return new Thief(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd,
                theAlive, theChanceToBlock, theInventory, theVision);
    }

}
