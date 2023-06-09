package model;

import java.util.HashMap;
import java.util.Random;

public final class Thief extends Hero {


    public Thief(String theName, int theHealth, int theCurrentHealth, int theDmgMin, int theDmgMax, double theChanceToHit, double theAttkSpd,
          boolean theAlive, double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision) {
        super(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive, theChanceToBlock, theInventory, theVision);

    }

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
