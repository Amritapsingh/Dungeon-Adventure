package model;

import java.util.HashMap;

public class Thief extends Hero {
    protected Thief(String theName, int theHealth, int theDmgMin, int theDmgMax, double theChanceToHit, double theAttkSpd, double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision, String[] theAllies) {
        super(theName, theHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theChanceToBlock, theInventory, theVision, theAllies);
    }

    @Override
    public String getName() {
        return "Thief";
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public double getAttkSpd() {
        return 0;
    }

    @Override
    public double getChanceToHit() {
        return 0;
    }

    @Override
    public int getDmgMin() {
        return 0;
    }

    @Override
    public int getDmgMax() {
        return 0;
    }

    @Override
    public void attck() {

    }

    @Override
    String setMyName() {
        return "Thief";
    }

    @Override
    int setMyHealth() {
        return 0;
    }
}
