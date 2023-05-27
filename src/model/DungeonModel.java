package model;

import java.util.Random;

public abstract class DungeonModel {

    private final String myName;

    private final int myHealthPoints;

    private int myCurrentHealth;

    private int myDmgMin;

    private int myDmgMax;

    private double myChanceToHit;

    private double myAttkSpd;

    private boolean myAlive;

    protected DungeonModel(final String theName, final int theHealthPoints, final int theCurrentHealth, final int theDmgMin,
                            final int theDmgMax, final double theChanceToHit, final double theAttckSpd, final boolean theAlive) {
        myName = theName;
        myHealthPoints = theHealthPoints;
        myCurrentHealth = theCurrentHealth;
        myDmgMin = theDmgMin;
        myDmgMax = theDmgMax;
        myChanceToHit = theChanceToHit;
        myAttkSpd = theAttckSpd;
        myAlive = theAlive;
    }

    public void regularAttack(int theHealth) {
        Random random = new Random();
        theHealth -= random.nextInt(myDmgMax + 1 - myDmgMin) + myDmgMin;
    }


    public void setMyCurrentHealth(final int theHealth) {
        myCurrentHealth = theHealth;
    }

    public void setMinDmg(final int theDmgMin) {
        myDmgMin = theDmgMin;
    }

    public void setMaxDmg(final int theDmgMax) {
        myDmgMax = theDmgMax;
    }

    public void setChnceToHit(final double theChnceToHit) {
        myChanceToHit = theChnceToHit;
    }

    public void setAttkSpd(final double theAttkSpd) {
        myAttkSpd = theAttkSpd;
    }

    public void setMyAlive(final boolean theAlive) {
        myAlive = theAlive;
    }

    public String getMyName() {
        return myName;
    }

    public int getMyHealthPoints() {
        return myHealthPoints;
    }

    public int getMyCurrentHealth() {
        return myCurrentHealth;
    }

    public int getMyDmgMin() {
        return myDmgMin;
    }

    public int getMyDmgMax() {
        return myDmgMax;
    }

    public double getMyChanceToHit() {
        return myChanceToHit;
    }

    public double getMyAttkSpd() {
        return myAttkSpd;
    }

    public boolean getMyAlive() {
        return myAlive;
    }

}
