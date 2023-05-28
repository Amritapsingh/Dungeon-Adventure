package model;

import java.util.Random;

public final class PoisonPotion extends Potions {

    private String myPotionName;

    private int myQuantity;

    private final Random randGenerator = new Random();

    PoisonPotion(final String thePotionName, final int theQuantity) {
        super(thePotionName, theQuantity);
    }

    private int takeDmg(final int theHealth) {
        super.setMyQuantity(super.getMyQuantity() - 1);
        return theHealth - randGenerator.nextInt(theHealth);
    }

}
