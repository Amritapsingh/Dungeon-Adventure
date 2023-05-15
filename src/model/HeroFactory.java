package model;

import java.util.HashMap;

public final class HeroFactory {

    public static Hero createWarrior(final String theName, final int theHealth, final int theDmgMin, final int theDmgMax,
                                     final double theChanceToHit, final double theAttkSpd, final double theChanceToBlock,
                                     final HashMap<String, Integer> theInventory, double theVision, final String[] theAllies) {
        return new Warrior(theName, theHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theChanceToBlock, theInventory,
                theVision, theAllies);
    }

    public static Hero createPriestess(final String theName, final int theHealth, final int theDmgMin, final int theDmgMax,
                                       final double theChanceToHit, final double theAttkSpd, final double theChanceToBlock,
                                       final HashMap<String, Integer> theInventory, double theVision, final String[] theAllies) {
        return new Priestess(theName, theHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theChanceToBlock, theInventory,
                theVision, theAllies);
    }

    public static Hero createThief(final String theName, final int theHealth, final int theDmgMin, final int theDmgMax,
                                       final double theChanceToHit, final double theAttkSpd, final double theChanceToBlock,
                                       final HashMap<String, Integer> theInventory, double theVision, final String[] theAllies) {
        return new Thief(theName, theHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theChanceToBlock, theInventory,
                theVision, theAllies);
    }

}
