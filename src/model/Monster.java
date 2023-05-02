package model;

public abstract class Monster implements  DungeonModels{

    private final String myName;

    private final int myHealthPoints;

    private final int myDmgMin;

    private final int myDmgMax;

    private final double myChanceToHit;

    private final double myAttkSpd;

    private final double myChanceToHeal;

    private final int myMinHeal;

    private final int myMaxHeal;

    protected Monster(final int theDmgMin, final int theDmgMax, final double theChanceToHit,
                      final double theAttkSpd, final double theChanceToHeal, final int theMinHeal, final int theMaxHeal) {
        myName = setMyName();
        myHealthPoints = setMyHealth();
        myDmgMin = theDmgMin;
        myDmgMax = theDmgMax;
        myChanceToHit = theChanceToHit;
        myAttkSpd = theAttkSpd;
        myChanceToHeal = theChanceToHeal;
        myMinHeal = theMinHeal;
        myMaxHeal = theMaxHeal;
    }

    abstract String setMyName();

    abstract int setMyHealth();

}
