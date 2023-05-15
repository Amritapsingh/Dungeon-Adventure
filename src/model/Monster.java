package model;

public abstract class Monster implements  DungeonModels{

    private String myName;

    private int myHealthPoints;

    private int myDmgMin;

    private int myDmgMax;

    private double myChanceToHit;

    private double myAttkSpd;

    private double myChanceToHeal;

    private int myMinHeal;

    private int myMaxHeal;

    protected Monster(final String theName, final int theHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit,
                      final double theAttkSpd, final double theChanceToHeal, final int theMinHeal, final int theMaxHeal) {

        setMyName(theName);
        setMyHealth(theHealth);
        setMinDmg(theDmgMin);
        setMaxDmg(theDmgMax);
        setChnceToHit(theChanceToHit);
        setAttkSpd(theAttkSpd);
        setMyChanceToHeal(theChanceToHeal);
        setMyMinHeal(theMinHeal);
        setMyMaxHeal(theMaxHeal);
    }

    abstract void setMyName(String theName);

    abstract void setMyHealth(int theHealth);

    abstract void setMinDmg(int theDmgMin);

    abstract void setMaxDmg(int theDmgMax);

    abstract void setChnceToHit(double theChnceToHit);

    abstract void setAttkSpd(double theAttkSpd);

    abstract void setMyChanceToHeal(double theChanceToHeal);

    abstract void setMyMinHeal(int theMinHeal);

    abstract void setMyMaxHeal(int theMaxHeal);

}
