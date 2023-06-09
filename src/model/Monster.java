package model;

public final class Monster extends DungeonModel {

    private final double myChanceToHeal;

    private final int myMinHeal;

    private final int myMaxHeal;
    public int fightCount = 1;

    public Monster(final String theName, final int theHealth, final int theCurrentHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit,
                   final double theAttkSpd, final boolean theAlive, final double theChanceToHeal, final int theMinHeal, final int theMaxHeal) {
        super(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive);
        myChanceToHeal = theChanceToHeal;
        myMinHeal = theMinHeal;
        myMaxHeal = theMaxHeal;
    }

    public double getMyChanceToHeal() {
        return myChanceToHeal;
    }

    public int getMyMinHeal() {
        return myMinHeal;
    }

    public int getMyMaxHeal() {
        return myMaxHeal;
    }

}
