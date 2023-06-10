package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests all the methods of the Room class
 *
 * @author Amrit Singh
 * @author Jay Phommakhot
 * @author Akil Turner-Richards
 * @version 09 June 2023
 */
class RoomTest {
    Room myRoom;

    @BeforeEach
    void setUpRoom(){
        myRoom = new Room(3,3);
        myRoom.setMyNorthDoor("^");
        myRoom.setMyEastDoor(">");
    }
    @Test
    void testSetIsVisited() {
        myRoom.setIsVisited(true);
        assertTrue(myRoom.getIsVisited());
    }
    @Test
    void testToString() {
        assertEquals("[ ^ >]",myRoom.toString());
    }
    @Test
    void testSetMyEncapsulationPillar() {
        myRoom.setMyEncapsulationPillar(true);
        assertTrue(myRoom.getMyEncapsulationPillar());
    }
    @Test
    void testSetMyAbstractionPillar() {
        myRoom.setMyAbstractionPillar(true);
        assertTrue(myRoom.getMyAbstractionPillar());
    }
    @Test
    void testSetMyInheritancePillar() {
        myRoom.setMyInheritancePillar(true);
        assertTrue(myRoom.getMyInheritancePillar());
    }
    @Test
    void testSetMyPolymorphismPillar() {
        myRoom.setMyPolymorphismPillar(true);
        assertTrue(myRoom.getMyPolymorphismPillar());
    }
    @Test
    void testSetMyHasPit() {
        myRoom.setMyHasPit(true);
        assertTrue(myRoom.getMyHasPit());
    }
    @Test
    void testSetHasPotion() {
        myRoom.setHasPotion(true);
        assertTrue(myRoom.getHasPotion());
    }
    @Test
    void testSetIsEnter() {
        myRoom.setIsEnter(true);
        assertTrue(myRoom.getIsEnter());
    }
    @Test
    void testSetIsExit() {
        myRoom.setIsExit(true);
        assertTrue(myRoom.getIsExit());
    }
    @Test
    void testHasNorthDoor() {
        assertTrue(myRoom.hasNorthDoor());
    }
    @Test
    void testHasSouthDoor() {
        assertFalse(myRoom.hasSouthDoor());
    }
    @Test
    void testHasEastDoor() {
        assertTrue(myRoom.hasEastDoor());
    }
    @Test
    void testHasWestDoor() {
        assertFalse(myRoom.hasWestDoor());
    }
}
