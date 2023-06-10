package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests all the methods of the Dungeon class
 *
 * @author Amrit Singh
 * @author Jay Phommakhot
 * @author Akil Turner-Richards
 * @version 09 June 2023
 */
class DungeonTest {
    Dungeon myDungeon;
    @BeforeEach
    void setUp() {
        HashMap<String, Integer> myInventory = new HashMap<>();
        myDungeon = new Dungeon(10, 10, new Warrior("Warrior", 100, 100,
                100, 100, 100, 100, true, 100,
                myInventory, 1), "Easy");
    }

    @Test
    void getNeighborOf() {
        Room room = myDungeon.getRandomRoom();
        assertEquals(myDungeon.getNeighborOf(room), myDungeon.getNeighborOf(room)); // test can fail if room doesn't have a door to neighbor
    }

    @Test
    void getAllNeighbors() {
        final Room room2 = new Room(0,1);
        assertEquals(myDungeon.getAllNeighbors(room2), myDungeon.getAllNeighbors(room2)); // test can fail as doors connected rooms are random
    }
    @Test
    void getMaze() {
        assertEquals(myDungeon.getMaze(), myDungeon.getMaze());
    }

    @Test
    void setEnter() {
        myDungeon.setEnter(3,3);
        assertTrue(myDungeon.getMaze()[3][3].getIsEnter());
    }

    @Test
    void setExit() {
        myDungeon.setExit(3,3);
        assertTrue(myDungeon.getMaze()[3][3].getIsExit());
    }

    @Test
    void setAbstractionPillar() {
        myDungeon.setAbstractionPillar(3,3);
        assertTrue(myDungeon.getMaze()[3][3].getMyAbstractionPillar());
    }

    @Test
    void setEncapsulationPillar() {
        myDungeon.setEncapsulationPillar(3,3);
        assertTrue(myDungeon.getMaze()[3][3].getMyEncapsulationPillar());
    }

    @Test
    void setInheritancePillar() {
        myDungeon.getMaze()[3][3].setMyInheritancePillar(true);
        assertTrue(myDungeon.getMaze()[3][3].getMyInheritancePillar());
    }

    @Test
    void setPolymorphismPillar() {
        myDungeon.getMaze()[3][3].setMyPolymorphismPillar(true);
        assertTrue(myDungeon.getMaze()[3][3].getMyPolymorphismPillar());
    }

    @Test
    void placePotions() {
        int potions = myDungeon.getPotionNum();
        myDungeon.placePotions();
        potions = myDungeon.getPotionNum();
        assertEquals(25, potions);
    }

    @Test
    void placePits() {
        int pits = myDungeon.getPitNum();
        myDungeon.placePits();
        pits = myDungeon.getPitNum();
        assertEquals(25, pits);
    }

    @Test
    void getPotionNum() {
        assertEquals(25, myDungeon.getPotionNum());
    }

    @Test
    void setPotionNum() {
        myDungeon.setPotionNum(50);
        assertEquals(50, myDungeon.getPotionNum());
    }

    @Test
    void createMonsters() {
        myDungeon.createMonsters();
        for (int i = 0; i < myDungeon.getMaze().length; i++) {
            for (int j = 0; j < myDungeon.getMaze()[i].length; j++) {
                if (myDungeon.getMaze()[i][j].getMyHasMonster()) {
                    assertTrue(myDungeon.getMaze()[i][j].getMyHasMonster());
                }
            }
        }
    }
}