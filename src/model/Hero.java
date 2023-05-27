package model;

import view.GameScreen;

import java.util.HashMap;

public abstract class Hero extends DungeonModel {

    private double myChanceToBlock;

    private HashMap<String, Integer> myInventory;

    private double myVision;

    private String[] myAllies;

    private int x, y;
    private int speed;
    GameScreen myGameScreen;


    protected Hero(final String theName, final int theHealth, final int theCurrentHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit,
                   final double theAttkSpd, final boolean theAlive, final double theChanceToBlock, HashMap<String, Integer> theInventory, double theVision, final String[] theAllies) {
        super(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive);

        myChanceToBlock = theChanceToBlock;
        myInventory.put("Health Potion", 0);
        myInventory.put("Vision Potion", 0);
        myInventory.put("Poison Potion", 0);
        setVision(theVision);
        setAllies(theAllies);
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

}
