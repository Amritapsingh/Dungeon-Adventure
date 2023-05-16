package model;

import java.util.Random;

public final class VisionPotion extends Potions {

    private String myPotionName;

    private int myQuantity;

    private final Random randGenerator = new Random();

    VisionPotion(final String thePotionName, final int theQuantity) {
        super(thePotionName, theQuantity);
    }

    public double addVision(final double theVision) {
        return theVision + randGenerator.nextDouble();
    }

}
