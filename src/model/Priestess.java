package model;

import java.util.HashMap;

public class Priestess extends Hero {

    private String myName;

    private int myHealthPoints;

    private int myDmgMin;

    private int myDmgMax;

    private double myChanceToHit;

    private double myAttkSpd;

    private double myChanceToBlock;

    private HashMap<String, Integer> myInventory;

    private double myVision;

    private String[] myAllies;

    protected Priestess(String theName, int theHealth, int theDmgMin, int theDmgMax, double theChanceToHit, double theAttkSpd, double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision, String[] theAllies) {
        super(theName, theHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theChanceToBlock, theInventory, theVision, theAllies);
    }

    @Override
    public String getName() {
        return null;
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
        return "Priestess";
    }

    @Override
    int setMyHealth() {
        return 0;
    }
}
