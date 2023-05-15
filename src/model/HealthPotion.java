package model;

import java.util.Random;

public final class HealthPotion extends Potions {

    private String myPotionName;

    private int myQuantity;

    private final Random randGenerator = new Random();

    HealthPotion(final String thePotionName, final int theQuantity) {
        super(thePotionName, theQuantity);
    }

    public int addHealth(final int theHealth, final int theMax) {
        int healAmount = randGenerator.nextInt(theMax) + 1;
        return theHealth + healAmount;
    }
}
