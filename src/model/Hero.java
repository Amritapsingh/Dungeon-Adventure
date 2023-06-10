package model;

import view.GameScreen;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * This class is used to create an object of a Hero and its unique behaviors.
 */
public abstract class Hero extends DungeonModel {

    /**
     * This instance variable represents the Hero's chance to block an attack.
     */
    private double myChanceToBlock;

    /**
     * This is a hashmap representing the Hero's inventory of items.
     */
    public HashMap<String, Integer> myInventory;

    /**
     * This represents the Hero's vision ability to see.
     */
    private double myVision;

    /**
     * This represents the Hero's pillars captured.
     */
    private String[] myAllies;

    /**
     * This constructor is used to create an object of a Hero.
     *
     * @param theName          the name of the Hero.
     * @param theHealth        the total health of the hero.
     * @param theCurrentHealth the current health of the hero.
     * @param theDmgMin        the minimum number of damage that Hero can do.
     * @param theDmgMax        the maximum number of damage that Hero can do.
     * @param theChanceToHit   the Hero's chance to hit the opponent.
     * @param theAttkSpd       the Hero's attack speed.
     * @param theAlive         the Hero's state of aliveness.
     * @param theChanceToBlock the Hero's chance to block an attack.
     * @param theInventory     the Hero's inventory of items.
     * @param theVision        the Hero's vision.
     */
    protected Hero(final String theName, final int theHealth, final int theCurrentHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit,
                   final double theAttkSpd, final boolean theAlive, final double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision) {
        super(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive);

        myChanceToBlock = theChanceToBlock;
        myInventory = new HashMap<>(theInventory);
        myInventory.put("Health Potion", 0);
        myInventory.put("Vision Potion", 0);
        myInventory.put("Poison Potion", 0);
        setVision(theVision);
        //setAllies(theAllies);
    }

    /**
     * This method sets the Hero's chance to block.
     *
     * @param theChnceToBlock the new chance to block.
     */
    public void setChnceToBlock(double theChnceToBlock) {
        myChanceToBlock = theChnceToBlock;
    }

    /**
     * This method sets the Hero's inventory. If the inventory already has the item, replace the quantity.
     *
     * @param theItemName the name of the item.
     * @param theQuantity the quantity of the item.
     */
    public void setInventory(String theItemName, int theQuantity) {
        if (!myInventory.containsKey(theItemName)) {
            myInventory.put(theItemName, theQuantity);
        } else {
            myInventory.replace(theItemName, theQuantity);
        }
    }

    /**
     * This method sets the vision of the Hero.
     *
     * @param theVision the new vision.
     */
    public void setVision(double theVision) {
        myVision = theVision;
    }

    /**
     * This method sets the pillars collected.
     *
     * @param theAllies the pillar collected.
     */
    public void setAllies(String[] theAllies) {
        myAllies = theAllies;
    }

    /**
     * This method returns the Hero's chance to block.
     *
     * @return
     */
    public double getChanceToBlock() {
        return myChanceToBlock;
    }

    /**
     * This method returns the quantity of a specific item in inventory.
     *
     * @param theString the name of the item.
     * @return the quantity of the item.
     */
    public Integer getMyInventoryValues(final String theString) {
        return myInventory.get(theString);
    }

    /**
     * This method returns the Hero's vision.
     *
     * @return the current vision.
     */
    public double getMyVision() {
        return myVision;
    }

    /**
     * This method returns the array of pillars collected.
     *
     * @return the pillars.
     */
    public String[] getMyAllies() {
        return myAllies;
    }

    /**
     * This method allows the Hero to use a health potion to regain health.
     *
     * @return the healed current health.
     */
    public int usePotion() {
        Random rand = new Random();
        int heal = rand.nextInt(getMyHealthPoints());
        if (getMyCurrentHealth() + heal > 100) {
            heal = 100 - getMyCurrentHealth();
            setMyCurrentHealth(100);
        } else {
            setMyCurrentHealth(getMyCurrentHealth() + heal);
        }
        myInventory.replace("Health Potion", myInventory.get("Health Potion") - 1);
        return heal;
    }

    /**
     * This method returns the number of health potions the Hero has.
     *
     * @return the quantity of potion.
     */
    public int getPotionCount() {
        return myInventory.get("Health Potion");
    }

    /**
     * This method sets the quantity of the health potion.
     *
     * @param thePotionCount new health potion quantity.
     */
    public void setPotionCount(int thePotionCount) {
        myInventory.replace("Health Potion", thePotionCount);
    }

    /**
     * The string representation of the Hero's current stats.
     *
     * @return the string of the Hero's current stats.
     */
    @Override
    public String toString() {
        final StringBuilder characterInfo = new StringBuilder();

        characterInfo.append("Name: ").append(super.getMyName());
        characterInfo.append(System.lineSeparator());
        characterInfo.append("Health: ").append(super.getMyCurrentHealth()).append("/").append(super.getMyHealthPoints());
        characterInfo.append(System.lineSeparator());
        characterInfo.append("Health Potions: ").append(myInventory.get("Health Potion"));
        characterInfo.append(System.lineSeparator());
        characterInfo.append("Vision Potions: ").append(myInventory.get("Vision Potion"));
        characterInfo.append(System.lineSeparator());
        characterInfo.append("Poison Potions: ").append(myInventory.get("Poison Potion"));
        characterInfo.append(System.lineSeparator());
        characterInfo.append("Pillars Achieved: ").append(myAllies.length);

        return characterInfo.toString();
    }

}
