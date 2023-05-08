package model;

public class Gremlin extends Monster {

    private String myName;

    private int myHealthPoints;

    private int myDmgMin;

    private int myDmgMax;

    private double myChanceToHit;

    private double myAttkSpd;

    private double myChnceToHeal;

    private int myMinHeal;

    private int myMaxHeal;

    protected Gremlin(String theName, int theHealth, int theDmgMin, int theDmgMax, double theChanceToHit, double theAttkSpd, double theChanceToHeal, int theMinHeal, int theMaxHeal) {
        super(theName, theHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theChanceToHeal, theMinHeal, theMaxHeal);
    }

    @Override
    public String getName() {
        return myName;
    }

    @Override
    public int getHealth() {
        return myHealthPoints;
    }

    @Override
    public double getAttkSpd() {
        return myAttkSpd;
    }

    @Override
    public double getChanceToHit() {
        return myChanceToHit;
    }

    @Override
    public int getDmgMin() {
        return myDmgMin;
    }

    @Override
    public int getDmgMax() {
        return myDmgMax;
    }

    public double getChanceToHeal() {
        return myChnceToHeal;
    }

    public int getMyMinHeal() {
        return myMinHeal;
    }

    public int getMyMaxHeal() {
        return myMaxHeal;
    }

    @Override
    public void attck() {

    }

    @Override
    void setMyName(String theName) {
        myName = theName;
    }

    @Override
    void setMyHealth(int theHealth) {
        myHealthPoints = theHealth;
    }

    @Override
    void setMinDmg(int theDmgMin) {
        myDmgMin = theDmgMin;
    }

    @Override
    void setMaxDmg(int theDmgMax) {
        myDmgMax = theDmgMax;
    }

    @Override
    void setChnceToHit(double theChnceToHit) {
        myChanceToHit = theChnceToHit;
    }

    @Override
    void setAttkSpd(double theAttkSpd) {
        myAttkSpd = theAttkSpd;
    }

    @Override
    void setMyChanceToHeal(double theChanceToHeal) {
        myChnceToHeal = theChanceToHeal;
    }

    @Override
    void setMyMinHeal(int theMinHeal) {
        myMinHeal = theMinHeal;
    }

    @Override
    void setMyMaxHeal(int theMaxHeal) {
        myMaxHeal = theMaxHeal;
    }
}
