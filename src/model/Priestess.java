package model;

import java.util.HashMap;
import java.util.Random;

public final class Priestess extends Hero {

    Priestess(String theName, int theHealth, int theCurrentHealth, int theDmgMin, int theDmgMax, double theChanceToHit, double theAttkSpd,
              boolean theAlive, double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision, String[] theAllies) {
        super(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive, theChanceToBlock, theInventory, theVision, theAllies);
    }

    public void heal() {
        Random rand = new Random();
        int heal = rand.nextInt(super.getMyHealthPoints());
        if (super.getMyCurrentHealth() + heal > super.getMyHealthPoints()) {
            super.setMyCurrentHealth(super.getMyHealthPoints());
        } else {
            super.setMyCurrentHealth(super.getMyCurrentHealth() + heal);
        }
    }

}
