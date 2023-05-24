package model;

public abstract class Potions {

    private String myPotionName;

    private int myQuantity;

    protected Potions(final String thePotionName, final int theQuantity) {
        setMyPotionName(thePotionName);
        setMyQuantity(theQuantity);
    }

    private boolean hasPotion() {
        return myQuantity > 0;
    }

    private void setMyPotionName(final String thePotionName) {
        myPotionName = thePotionName;
    }

    private void setMyQuantity(final int theQuantity) {
        myQuantity = theQuantity;
    }

    private String getMyPotionName() {return myPotionName;}

    private int getMyQuantity() {return myQuantity;}
}
