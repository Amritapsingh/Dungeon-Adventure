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

    public void setMyPotionName(final String thePotionName) {
        myPotionName = thePotionName;
    }

    public void setMyQuantity(final int theQuantity) {
        myQuantity = theQuantity;
    }

    public String getMyPotionName() {return myPotionName;}

    public int getMyQuantity() {return myQuantity;}
}
