package model;

public class MonsterFactory {

    public static Monster createOgre(final String theName, final int theHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit,
                                     final double theAttkSpd, final double theChanceToHeal, final int theMinHeal, final int theMaxHeal) {
        return new Ogre(theName, theHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theChanceToHeal, theMinHeal, theMaxHeal);
    }

    public static Monster createGremlin(final String theName, final int theHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit,
                                     final double theAttkSpd, final double theChanceToHeal, final int theMinHeal, final int theMaxHeal) {
        return new Gremlin(theName, theHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theChanceToHeal, theMinHeal, theMaxHeal);
    }

    public static Monster createSkeleton(final String theName, final int theHealth, final int theDmgMin, final int theDmgMax, final double theChanceToHit,
                                     final double theAttkSpd, final double theChanceToHeal, final int theMinHeal, final int theMaxHeal) {
        return new Skeleton(theName, theHealth, theDmgMin, theDmgMax, theChanceToHit, theAttkSpd, theChanceToHeal, theMinHeal, theMaxHeal);
    }

}
