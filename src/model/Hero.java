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

        setMyName(theName);
        setMyHealth(theHealth);
        setMinDmg(theDmgMin);
        setMaxDmg(theDmgMax);
        setChnceToHit(theChanceToHit);
        setAttkSpd(theAttkSpd);
        setChnceToBlock(theChanceToBlock);
        setInventory("Health Potion", 0);
        setVision(theVision);
        setAllies(theAllies);
    }

    abstract void setMyName(String theName);

    abstract void setMyHealth(int theHealth);

    abstract void setMinDmg(int theDmgMin);

    abstract void setMaxDmg(int theDmgMax);

    abstract void setChnceToHit(double theChnceToHit);

    abstract void setAttkSpd(double theAttkSpd);

    abstract void setChnceToBlock(double theChnceToBlock);

    abstract void setInventory(String theItemName, int theQuantity);

    abstract void setVision(double theVision);

    abstract void setAllies(String[] theAllies);

//    @Override
//    final public String toString() {
//
//    }
}
