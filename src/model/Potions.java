package model;

import java.util.Random;

public abstract class Potions {

    private String myPotionName;

    private int myQuantity;

    private boolean hasPotion = false;

    protected Potions(final String thePotionName, final int theQuantity) {
        setMyPotionName(thePotionName);
        setMyQuantity(theQuantity);
    }

    private boolean hasPotion() {
        if (myQuantity > 0) {
            hasPotion = true;
        }
        return hasPotion;
    }

    private void setMyPotionName(final String thePotionName) {
        myPotionName = thePotionName;
    }

    private void setMyQuantity(final int theQuantity) {
        myQuantity = theQuantity;
    }
}
