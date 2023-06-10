package model;

/**
 * This class is used to create an object that represents a Monster and it's behaviors.
 */
public final class Monster extends DungeonModel {

    /**
     * This instance variable represents the Monster's percentage chance to heal.
     */
    private final double myChanceToHeal;

    /**
     * This instance variable of the minimum number the Monster can heal.
     */
    private final int myMinHeal;

    /**
     * This instance variable of the maximum number the Monster can heal.
     */
    private final int myMaxHeal;

    /**
     * This instance variable keeps track of when it's the Monster's turn to attack.
     */
    public int fightCount = 1;

    /**
     * This constructor is used to create Monster objects.
     *
     * @param theName          the name of the Monster.
     * @param theHealth        the total health of the Monster.
     * @param theCurrentHealth the current health of the Monster.
     * @param theDmgMin        the minimum damage the Monster can do.
     * @param theDmgMax        the maximum damage the Monster can do.
     * @param theChanceToHit   the percentage the Monster's attack is successful.
     * @param theAttkSpd       the monster's attack speed.
     * @param theAlive         the state of the Monster's aliveness.
     * @param theChanceToHeal  the Monster's chance to heal.
     * @param theMinHeal       the minimum number the Monster can heal for.
     * @param theMaxHeal       the maximum number the Monster can heal.
     */
    public Monster(final String theName, final int theHealth, final int theCurrentHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit,
                   final double theAttkSpd, final boolean theAlive, final double theChanceToHeal, final int theMinHeal, final int theMaxHeal) {
        super(theName, theHealth, theCurrentHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theAlive);
        myChanceToHeal = theChanceToHeal;
        myMinHeal = theMinHeal;
        myMaxHeal = theMaxHeal;
    }

    /**
     * This method returns the Monster's chance to heal.
     *
     * @return the chance to heal.
     */
    public double getMyChanceToHeal() {
        return myChanceToHeal;
    }

    /**
     * This method returns the Monster's minimum number of healing.
     *
     * @return the minimum number of healing.
     */
    public int getMyMinHeal() {
        return myMinHeal;
    }

    /**
     * This method returns the max number the Monster can heal.
     *
     * @return max number of healing.
     */
    public int getMyMaxHeal() {
        return myMaxHeal;
    }

}
