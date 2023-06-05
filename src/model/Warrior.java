package model;

import java.util.HashMap;
import java.util.Random;

public final class Warrior extends Hero {

    public Warrior(String theName, int theHealth, int theCurrentHealth, int theDmgMin, int theDmgMax, double theChanceToHit, double theAttkSpd,
                   boolean theAlive, double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision) {
        super(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive, theChanceToBlock, theInventory, theVision);
    }

    public int crushingBlow(int theHealth) {
        Random rand = new Random();
        double hitSucceed = rand.nextDouble();
        if (hitSucceed < 0.4) {
            theHealth -= rand.nextInt(101) + 75; // special attack from 75 to 175 damage
        }
        return theHealth;
    }

}
