package model;

import java.util.HashMap;

public final class Warrior extends Hero {

    public Warrior(String theName, int theHealth, int theCurrentHealth, int theDmgMin, int theDmgMax, double theChanceToHit, double theAttkSpd,
                   boolean theAlive, double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision) {
        super(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive, theChanceToBlock, theInventory, theVision);
    }

    public int crushingBlow(final int theHealth, final int theSpecialDmg) {
        // enemy health subtract hit
        return theHealth - theSpecialDmg;
    }

}
