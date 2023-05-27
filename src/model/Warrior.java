package model;

import java.util.HashMap;

public final class Warrior extends Hero {

    Warrior(String theName, int theHealth, int theCurrentHealth, int theDmgMin, int theDmgMax, double theChanceToHit, double theAttkSpd,
                      boolean theAlive, double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision, String[] theAllies) {
        super(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive, theChanceToBlock, theInventory, theVision, theAllies);
    }

    public int crushingBlow(final int theHealth, final int theSpecialDmg) {
        // enemy health subtract hit
        return theHealth - theSpecialDmg;
    }

    @Override
    public String toString() {
        return null;
    }
}
