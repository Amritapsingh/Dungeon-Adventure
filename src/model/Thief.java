package model;

import java.util.HashMap;

public final class Thief extends Hero {

    Thief(String theName, int theHealth, int theCurrentHealth, int theDmgMin, int theDmgMax, double theChanceToHit, double theAttkSpd,
          boolean theAlive, double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision, String[] theAllies) {
        super(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive, theChanceToBlock, theInventory, theVision);
    }

    public int surpriseAttck(final int theHealth, final double theChanceToHit) {
        if (theChanceToHit <= 0.4) {
            return 0; // make the hit again. Maybe double dmg?
        }
        return theHealth;
    }

}
