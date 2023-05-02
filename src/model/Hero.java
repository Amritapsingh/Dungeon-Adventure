package model;

import java.util.HashMap;

public abstract class Hero implements DungeonModels {

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

    protected Hero(final String theName, final int theHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit,
                    final double theAttkSpd, final double theChanceToBlock, final HashMap<String, Integer> theInventory, double theVision, final String[] theAllies) {

        myName = setMyName();
        myHealthPoints = setMyHealth();
        myDmgMin = theDmgMin;
        myDmgMax = theDmgMax;
        myChanceToHit = theChanceToHit;
        myAttkSpd = theAttkSpd;
        myChanceToBlock = theChanceToBlock;
        myInventory = theInventory;
        myVision = theVision;
        myAllies = theAllies;
    }

    abstract String setMyName();

    abstract int setMyHealth();

//    @Override
//    final public String toString() {
//
//    }

}
