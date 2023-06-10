package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DungeonModelHeroMonsterTests {

    /**
     * This is a MockObject of a Warrior Object.
     */
    private Warrior myWarrior;

    /**
     * This is a MockObject of a Priestess Object.
     */
    private Priestess myPriestess;

    /**
     * This is a MockObject of a Thief Object.
     */
    private Thief myThief;

    /**
     * This is a MockObject of an Ogre Monster Object.
     */
    private Monster myOgre;

    /**
     * This is a MockObject of a Gremlin Monster Object.
     */
    private Monster myGremlin;

    /**
     * This is a MockObject of a Skeleton Monster Object.
     */
    private Monster mySkeleton;

    /**
     * This initializes all the MockObject variables used for testing.
     */
    @BeforeEach
    public void dungeonConstructor() {
        HashMap<String, Integer> myInventory = new HashMap<>();
        myInventory.put("Health Potion", 2);
        myInventory.put("Vision Potion", 0);
        myInventory.put("Poison Potion", 1);
        myWarrior = HeroFactory.createWarrior("Warrior", 125, 125, 35, 60,
                0.8, 4.0, true, 0.2, myInventory, 2.0);

        myPriestess = HeroFactory.createPriestess("Priestess", 75, 75, 25, 45, 0.7,
                                                    5.0, true, 0.3, myInventory, 2.0);

        myThief = HeroFactory.createThief("Thief", 75, 75, 20, 40, 0.8, 6.0, true,
                                            0.4, myInventory, 2.0);

        myOgre = new Monster("Ogre", 200, 200, 30, 60, 0.6,
                    2.0, true, 0.1, 30, 60);

        myGremlin = new Monster("Gremlin", 70, 70, 15, 30, 0.8,
                                5.0, true, 0.4, 20, 40);

        mySkeleton = new Monster("Skeleton", 100, 100, 30, 50, 0.8,
                                    3.0, true, 0.3, 30, 50);
    }

    /**
     * This tests for creating the heroes of the correct class.
     */
    @Test
    public void testHeroCreation() {
        assertEquals(Warrior.class, myWarrior.getClass());
        assertEquals(Priestess.class, myPriestess.getClass());
        assertEquals(Thief.class, myThief.getClass());

        assertEquals("Warrior", myWarrior.getMyName());
        assertEquals("Priestess", myPriestess.getMyName());
        assertEquals("Thief", myThief.getMyName());
    }

    /**
     * This test the regularAttack() method in the DungeonModel class.
     */
    @Test
    public void testRegularAttack() {
        myGremlin.setMyCurrentHealth(myPriestess.regularAttack(myGremlin.getMyCurrentHealth(), myPriestess.getMyChanceToHit()));
        assertNotEquals(myGremlin.getMyCurrentHealth(), myGremlin.getMyHealthPoints()); // test can fail if the attack missed
    }

    /**
     * This test if the setMyCurrentHealth() works correctly.
     */
    @Test
    public void testSetMyCurrentHealth() {
        myWarrior.setMyCurrentHealth(10);
        assertEquals(10, myWarrior.getMyCurrentHealth());
        assertNotEquals(myWarrior.getMyCurrentHealth(), myWarrior.getMyHealthPoints());
    }

    /**
     * This test the setMyDmgMin() method if it correctly updates the minimum damage variable.
     */
    @Test
    public void testSetMyDmgMin() {
        myPriestess.setMinDmg(35);
        assertEquals(35, myPriestess.getMyDmgMin());

        mySkeleton.setMinDmg(25);
        assertEquals(25, mySkeleton.getMyDmgMin());
    }

    /**
     * This tests the setMyDmgMax() method if it correctly updates the max damage variable.
     */
    @Test
    public void testSetMyDmgMax() {
        myPriestess.setMaxDmg(60);
        assertEquals(60, myPriestess.getMyDmgMax());

        myGremlin.setMaxDmg(100);
        assertEquals(100, myGremlin.getMyDmgMax());
    }

    /**
     * This tests the setChanceToHit() method if it correctly changes the model's chance to land an attack.
     */
    @Test
    public void testSetChanceToHit() {
        myThief.setChnceToHit(0.9);
        assertEquals(0.9, myThief.getMyChanceToHit());

        mySkeleton.setChnceToHit(0.2);
        assertEquals(0.2, mySkeleton.getMyChanceToHit());
    }

    /**
     * This tests if setting the model's attack speed updates correctly.
     */
    @Test
    public void testSetAttackSpd() {
        myWarrior.setAttkSpd(4.5);
        assertEquals(4.5, myWarrior.getMyAttkSpd());

        myOgre.setAttkSpd(1.0);
        assertEquals(1.0, myOgre.getMyAttkSpd());
    }

    /**
     * This tests if setting the model's dead/alive variable gets updated.
     */
    @Test
    public void testSetMyAlive() {
        myWarrior.setMyAlive(false);
        assertFalse(myWarrior.getMyAlive());
        assertTrue(myPriestess.getMyAlive());
    }

    /**
     * This tests getting the model's correct health.
     */
    @Test
    public void testGetHealth() {
        assertEquals(125, myWarrior.getMyHealthPoints());
        assertEquals(myWarrior.getMyHealthPoints(), myWarrior.getMyCurrentHealth());

        assertEquals(200, myOgre.getMyHealthPoints());
    }

    /**
     * This tests getting the model's min and max damage range correctly.
     */
    @Test
    public void testGetDmg() {
        assertEquals(20, myThief.getMyDmgMin());
        assertEquals(45, myPriestess.getMyDmgMax());

        assertEquals(30, myOgre.getMyDmgMin());
        assertEquals(50, mySkeleton.getMyDmgMax());
    }

    /**
     * This tests if getting the model's correct chance to hit.
     */
    @Test
    public void testGetChanceToHit() {
        assertEquals(0.8, myThief.getMyChanceToHit());
        assertEquals(0.6, myOgre.getMyChanceToHit());
    }


    /**
     * This tests getting the model's correct attack speed.
     */
    @Test
    public void testGetAttckSpd() {
        assertEquals(4.0, myWarrior.getMyAttkSpd());
        assertEquals(5.0, myPriestess.getMyAttkSpd());
        assertEquals(6.0, myThief.getMyAttkSpd());

        assertEquals(2.0, myOgre.getMyAttkSpd());
        assertEquals(5.0, myGremlin.getMyAttkSpd());
        assertEquals(3.0, mySkeleton.getMyAttkSpd());
    }

    /**
     * This tests getting the model's correct myAlive variable.
     */
    @Test
    public void testGetMyAlive() {
        assertTrue(myWarrior.getMyAlive());
        assertTrue(myOgre.getMyAlive());
    }

    /**
     * This tests getting the Hero's correct quantity of potions in inventory.
     */
    @Test
    public void testPotionQuantity() {
        myThief.setInventory("Health Potion", 2);
        myPriestess.setInventory("Poison Potion", 1);
        assertEquals(2, myThief.getMyInventoryValues("Health Potion"));
        assertEquals(0, myWarrior.getMyInventoryValues("Vision Potion"));
        assertEquals(1, myPriestess.getMyInventoryValues("Poison Potion"));
    }

    /**
     * This tests getting the Hero's correct chance to block.
     */
    @Test
    public void testSetChanceToBlock() {
        myThief.setChnceToBlock(0.49);
        assertEquals(0.49, myThief.getChanceToBlock());
        myThief.setChnceToBlock(0.78);
        assertEquals(0.78, myThief.getChanceToBlock());
    }

    /**
     * This tests getting the Hero's correct vision.
     */
    @Test
    public void testGetVision() {
        assertEquals(2.0, myPriestess.getMyVision());
    }

    /**
     * This tests the correct toString() for Hero's.
     */
    @Test
    public void testHeroToString() {
        final StringBuilder characterInfo = new StringBuilder();

        characterInfo.append("Name: ").append("Warrior");
        characterInfo.append(System.lineSeparator());
        characterInfo.append("Health: ").append(125).append("/").append(125);
        characterInfo.append(System.lineSeparator());
        characterInfo.append("Health Potions: ").append(myWarrior.getMyInventoryValues("Health Potion"));
        characterInfo.append(System.lineSeparator());
        characterInfo.append("Vision Potions: ").append(myWarrior.getMyInventoryValues("Vision Potion"));
        characterInfo.append(System.lineSeparator());
        characterInfo.append("Poison Potions: ").append(myWarrior.getMyInventoryValues("Poison Potion"));
        characterInfo.append(System.lineSeparator());
        characterInfo.append("Pillars Achieved: ").append(0);

        String info = characterInfo.toString();

        myWarrior.setAllies(new String[0]);

        assertEquals(info, myWarrior.toString());
    }

    /**
     * This tests the Warrior's special ability.
     */
    @Test
    public void testWarriorSpecial() {
        myOgre.setMyCurrentHealth(myWarrior.crushingBlow(myOgre.getMyCurrentHealth()));
        assertNotEquals(myOgre.getMyCurrentHealth(), myOgre.getMyHealthPoints()); // test can fail if move missed
    }

    /**
     * This tests the Thief's special ability.
     */
    @Test
    public void testThiefSpecial() {
        mySkeleton.setMyCurrentHealth(myThief.surpriseAttck(myOgre.getMyCurrentHealth()));
        assertNotEquals(mySkeleton.getMyCurrentHealth(), mySkeleton.getMyHealthPoints()); // test can fail if move missed
    }

    /**
     * This tests the Priestess' special ability.
     */
    @Test
    public void testPriestessSpecial() {
        myPriestess.setMyCurrentHealth(myOgre.regularAttack(myPriestess.getMyCurrentHealth(), myOgre.getMyChanceToHit()) * 2);
        assertNotEquals(myPriestess.getMyCurrentHealth(), myPriestess.getMyHealthPoints()); // can fail if move missed

        int healPoints = myPriestess.heal();
        myPriestess.setMyCurrentHealth(healPoints);
        assertEquals(healPoints, myPriestess.getMyCurrentHealth());
    }

    /**
     * This tests the correct Monster creations.
     */
    @Test
    public void testMonsterCreation() {
        assertEquals(Monster.class, myOgre.getClass());
        assertEquals(Monster.class, myGremlin.getClass());
        assertEquals(Monster.class, mySkeleton.getClass());

        assertEquals("Ogre", myOgre.getMyName());
        assertEquals("Gremlin", myGremlin.getMyName());
        assertEquals("Skeleton", mySkeleton.getMyName());
    }

    /**
     * This tests the getting the correct instance variables of the Monster's heal stats.
     */
    @Test
    public void testHealVariables() {
        assertEquals(0.3, mySkeleton.getMyChanceToHeal());
        assertEquals(30, mySkeleton.getMyMinHeal());
        assertEquals(50, mySkeleton.getMyMaxHeal());
    }

    @Test
    public void testTileManager() {

    }

}
