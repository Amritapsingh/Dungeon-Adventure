package model;

import java.util.HashMap;

public final class Warrior extends Hero {

    private String myName;

    private int myHealthPoints;

    private int myCurrentHealth;

    private int myDmgMin;

    private int myDmgMax;

    private double myChanceToHit;

    private double myAttkSpd;

    private double myChanceToBlock;

    private HashMap<String, Integer> myInventory;

    private double myVision;

    private String[] myAllies;

    Warrior(String theName, int theHealth, int theDmgMin, int theDmgMax, double theChanceToHit, double theAttkSpd,
                      double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision, String[] theAllies) {
        super(theName, theHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theChanceToBlock, theInventory, theVision, theAllies);

    }

    @Override
    void setMyName(String theName) {
        myName = theName;
    }

    @Override
    void setMyHealth(int theHealth) {
        myHealthPoints = theHealth;
    }

    @Override
    void setMyCurrentHealth(int theHealth) {
        myCurrentHealth = theHealth;
    }

    @Override
    void setMinDmg(int theDmgMin) {
        myDmgMin = theDmgMin;
    }

    @Override
    void setMaxDmg(int theDmgMax) {
        myDmgMax = theDmgMax;
    }

    @Override
    void setChnceToHit(double theChnceToHit) {
        myChanceToHit = theChnceToHit;
    }

    @Override
    void setAttkSpd(double theAttkSpd) {
        myAttkSpd = theAttkSpd;
    }

    @Override
    void setChnceToBlock(double theChnceToBlock) {
        myChanceToBlock = theChnceToBlock;
    }

    @Override
    void setInventory(String theItemName, int theQuantity) {
        myInventory.put(theItemName, theQuantity);
    }

    @Override
    void setVision(double theVision) {
        myVision = theVision;
    }

    @Override
    void setAllies(String[] theAllies) {
        myAllies = theAllies;
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

    public int crushingBlow(final int theHealth, final int theSpecialDmg) {
        // enemy health subtract hit
        return theHealth - theSpecialDmg;
    }
}
