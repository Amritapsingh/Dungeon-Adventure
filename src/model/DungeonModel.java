package model;

import java.io.Serializable;
import java.util.Random;

/**
 * This is an abstract class that can be used when creating Monsters and Heroes.
 */

public abstract class DungeonModel implements Serializable {

    /**
     * This is an instance variable of the name of the DungeonModel.
     */
    private final String myName;

    /**
     * This is an instance variable of the total health.
     */
    private final int myHealthPoints;

    /**
     * This is an instance variable of the current health of the model at that given state.
     */
    private int myCurrentHealth;

    /**
     * This represents the model's minimum damage it can do.
     */
    private int myDmgMin;

    /**
     * this represents the model's max damage it can do.
     */
    private int myDmgMax;

    /**
     * This represents the percentage that an attack will succeed
     */
    private double myChanceToHit;

    /**
     * This represents the model's attack speed.
     */
    private double myAttkSpd;

    /**
     * This represents the boolean of the model's state of alive/dead.
     */
    private boolean myAlive;

    /**
     * The constructor is used to instantiate an object of the Dungeon Model
     *
     * @param theName          is the name of the model as a String.
     * @param theHealthPoints  is the total health of the model as an int.
     * @param theCurrentHealth the current health of the model as an int.
     * @param theDmgMin        the minimum damage the model can possibly do.
     * @param theDmgMax        the maximum damage the model can possibly do.
     * @param theChanceToHit   the percentage of the model's attack being successful.
     * @param theAttckSpd      the double that represents the model's attack speed.
     * @param theAlive         the boolean that says if the model is alive or not.
     */
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

    /**
     * This method does a random amount of damage to the opposing side.
     *
     * @param theHealth      the current health of the opposing side.
     * @param theChanceToHit the model's chance to hit used to see if the attack is successful.
     * @return the opponent's health.
     */
    public int regularAttack(int theHealth, final double theChanceToHit) {
        Random random = new Random();
        double chanceToHitChecker = random.nextDouble();
        if (chanceToHitChecker <= theChanceToHit) {
            theHealth -= random.nextInt(myDmgMax + 1 - myDmgMin) + myDmgMin;
        }
        return theHealth;
    }


    /**
     * This method is used to set the model's current health
     *
     * @param theHealth the health that will replace the current
     */
    public void setMyCurrentHealth(final int theHealth) {
        myCurrentHealth = theHealth;
    }

    /**
     * This method sets the model's minimum damage.
     *
     * @param theDmgMin the model's new minimum damage.
     */
    public void setMinDmg(final int theDmgMin) {
        myDmgMin = theDmgMin;
    }

    /**
     * This method sets the model's max damage.
     *
     * @param theDmgMax the model's new max damage.
     */
    public void setMaxDmg(final int theDmgMax) {
        myDmgMax = theDmgMax;
    }

    /**
     * This method sets the model's chance to hit.
     *
     * @param theChnceToHit the model's new chance to hit.
     */
    public void setChnceToHit(final double theChnceToHit) {
        myChanceToHit = theChnceToHit;
    }

    /**
     * This method sets the model's attack speed.
     *
     * @param theAttkSpd the model's new attack speed.
     */
    public void setAttkSpd(final double theAttkSpd) {
        myAttkSpd = theAttkSpd;
    }

    /**
     * This method sets if the model is dead or alive as a boolean.
     *
     * @param theAlive the new state of the model.
     */
    public void setMyAlive(final boolean theAlive) {
        myAlive = theAlive;
    }

    /**
     * This method returns the name of the model.
     *
     * @return the String name of the model.
     */
    public String getMyName() {
        return myName;
    }

    /**
     * This method returns the model's total health as an int.
     *
     * @return the total health of the model.
     */
    public int getMyHealthPoints() {
        return myHealthPoints;
    }

    /**
     * This method returns the model's current health as an int.
     *
     * @return the current health of the model.
     */
    public int getMyCurrentHealth() {
        return myCurrentHealth;
    }

    /**
     * This method returns the model's minimum damage as an int.
     *
     * @return the minimum damage
     */
    public int getMyDmgMin() {
        return myDmgMin;
    }

    /**
     * This method returns the model's max damage as an int.
     *
     * @return the max damage.
     */
    public int getMyDmgMax() {
        return myDmgMax;
    }

    /**
     * This method returns the model's percentage chance to hit as a double.
     *
     * @return the double representing the model's chance to hit.
     */
    public double getMyChanceToHit() {
        return myChanceToHit;
    }

    /**
     * This method returns the model's current attack speed.
     *
     * @return the attack speed as a double.
     */
    public double getMyAttkSpd() {
        return myAttkSpd;
    }

    /**
     * This method returns if the model is alive or not.
     *
     * @return the boolean representing state of aliveness.
     */
    public boolean getMyAlive() {
        return myAlive;
    }

}
