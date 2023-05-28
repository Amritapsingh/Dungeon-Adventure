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
        super.setMyQuantity(super.getMyQuantity() - 1);
        return theVision + randGenerator.nextDouble();
    }

}
