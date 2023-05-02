package model;

import java.util.HashMap;

public class Warrior extends Hero {

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

    protected Warrior(String theName, int theHealth, int theDmgMin, int theDmgMax, double theChanceToHit, double theAttkSpd,
                      double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision, String[] theAllies) {
        super(theName, theHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theChanceToBlock, theInventory, theVision, theAllies);

        myName = setMyName();
    }

    @Override
    String setMyName() {
        return "Warrior";
    }

    @Override
    int setMyHealth() {
        return 0;
    }

    @Override
    public String getName() {
        return myName;
    }

    @Override
    public int getHealth() {
        return myHealthPoints;
    }

    @Override
    public double getAttkSpd() {
        return myAttkSpd;
    }

    @Override
    public double getChanceToHit() {
        return myChanceToHit;
    }

    @Override
    public int getDmgMin() {
        return myDmgMin;
    }

    @Override
    public int getDmgMax() {
        return myDmgMax;
    }

    @Override
    public void attck() {

    }
}
