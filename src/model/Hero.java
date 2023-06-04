package model;

import view.GameScreen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public abstract class Hero extends DungeonModel {

    private double myChanceToBlock;

    private HashMap<String, Integer> myInventory;

    private double myVision;

    private String[] myAllies;

    private int x, y;
    private int speed;
    GameScreen myGameScreen;


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

//    public Hero(GameScreen theGameScreen) {
//        myGameScreen = theGameScreen;
//    }

    public void setChnceToBlock(double theChnceToBlock) {
        myChanceToBlock = theChnceToBlock;
    }

    public void setInventory(String theItemName, int theQuantity) {
        myInventory.replace(theItemName, theQuantity);
    }

    public void setVision(double theVision) {
        myVision = theVision;
    }

    public void setAllies(String[] theAllies) {
        myAllies = theAllies;
    }

    public double getChanceToBlock() {
        return myChanceToBlock;
    }

    public Set<String> getMyInventoryKeys() {
        return myInventory.keySet();
    }

    public Integer getMyInventoryValues() {
        return myInventory.get(myInventory);
    }

    public double getMyVision() {
        return myVision;
    }

    public String[] getMyAllies() {
        return myAllies;
    }

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
        characterInfo.append("Pillars Achieved: ").append(Arrays.toString(myAllies));

        return characterInfo.toString();
    }

}
