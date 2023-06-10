package model;

import view.GameScreen;

import java.io.*;
import java.util.ArrayList;

/**
 * This class represents a room in a dungeon
 *
 * @author Amrit Singh
 * @author Akil Turner-Richards
 * @author Jay Phommakhot
 * @version 09 June 2023
 */
public class Room implements Serializable {
    /**
     * Field to determine if room is the entrance
     */
    boolean myIsEnter;
    /**
     * Field to determine if room is the exit
     */
    boolean myIsExit;
    /**
     * Field to determine if room is visited
     */
    private boolean myIsVisited;
    /**
     * Field to determine if room has a monster
     */
    private boolean myHasMonster;
    /**
     * Field to determine if room has a pit
     */
    private boolean myHasPit;
    /**
     * Field to determine if room has an encapsulation pillar
     */
    private boolean myEncapsulationPillar;
    /**
     * Field to determine if room has an abstraction pillar
     */
    private boolean myAbstractionPillar;
    /**
     * Field to determine if room has an inheritance pillar
     */
    private boolean myInheritancePillar;
    /**
     * Field to determine if room has a polymorphism pillar
     */
    private boolean myPolymorphismPillar;
    /**
     * Field to determine if room has a potion
     */
    private boolean myHasPotion;
    /**
     * Field for the monster in the room
     */
    private Monster myMonster;
    /**
     * Field for the x coordinate
     */
    public int myX;
    /**
     * Field for the y coordinate
     */
    public int myY;
    /**
     * Field for the ArrayList of the room's neighbors
     */
    private ArrayList<Room> myRoomNeighbors;
    /**
     * Field for the amount of unvisited neighbors a room has
     */
    public int myNeighbors;
    /**
     * Field for the String for the north door
     */
    String myNorthDoor;
    /**
     * Field for the String for the south door
     */
    String mySouthDoor;
    /**
     * Field for the String for the east door
     */
    String myEastDoor;
    /**
     * Field for the String for the west door
     */
    String myWestDoor;
    /**
     * Field for the map tile for the room to be serialized
     */
    int[][] myMapTiles;
    /**
     * Field for the game screen to be serialized
     */
    GameScreen myGameScreen;
    /**
     * Field for the room map to be serialized
     */
    int[][] myRoomMap;

    /**
     * Constructor for the Room
     *
     * @param theX the x coordinate
     * @param theY the y coordinate
     */
    public Room(final int theX, final int theY) {
        myX = theX;
        myY = theY;
        myIsVisited = false;
        myIsEnter = false;
        myIsExit = false;
        myNorthDoor = " ";
        mySouthDoor = " ";
        myEastDoor = " ";
        myWestDoor = " ";
        myRoomNeighbors = new ArrayList<>();
        myNeighbors = 0;
        myMonster = null;

    }

    /**
     * Method to load the map to the game screen
     *
     * @param theGameScreen the game screen to serialize
     */
    public int[][] loadMap(final GameScreen theGameScreen) {
        myGameScreen = theGameScreen;
        myMapTiles = new int[myGameScreen.maxScreenRow][myGameScreen.maxScreenCol];
        String filePath = "/assets/map2.txt";
        try {
            InputStream st = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader((st)));
            int col = 0;
            int row = 0;
            while (col < myGameScreen.maxScreenCol && row < myGameScreen.maxScreenRow) {
                String line = br.readLine();
                while (col < myGameScreen.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    myMapTiles[row][col] = num;
                    col++;
                }
                if (col == myGameScreen.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myMapTiles;
    }

    /**
     * Method to determine the appropriate room layout
     *
     * @param theGameScreen the game screen to draw to
     */
    public void generateRoom(final GameScreen theGameScreen) {
        myGameScreen = theGameScreen;
        myRoomMap = new int[myGameScreen.dungeon.getMaze().length][myGameScreen.dungeon.getMaze()[0].length];
        for (int i = 0; i < myRoomMap.length; i++) {
            for (int j = 0; j < myRoomMap[0].length; j++) {
                if (hasNorthDoor()) {
                    myRoomMap[i][j] = 0;
                }
                if (hasSouthDoor()) {
                    myRoomMap[i][j] = 1;
                }
                if (hasEastDoor()) {
                    myRoomMap[i][j] = 2;
                }
                if (hasWestDoor()) {
                    myRoomMap[i][j] = 3;
                }
                if (hasNorthDoor() && hasSouthDoor()) {
                    myRoomMap[i][j] = 8;
                }
                if (hasNorthDoor() && hasEastDoor()) {
                    myRoomMap[i][j] = 4;
                }
                if (hasNorthDoor() && hasWestDoor()) {
                    myRoomMap[i][j] = 6;
                }
                if (hasSouthDoor() && hasEastDoor()) {
                    myRoomMap[i][j] = 13;
                }
                if (hasSouthDoor() && hasWestDoor()) {
                    myRoomMap[i][j] = 15;
                }
                if (hasEastDoor() && hasWestDoor()) {
                    myRoomMap[i][j] = 14;
                }
                if (hasEastDoor() && hasNorthDoor() && hasSouthDoor()) {
                    myRoomMap[i][j] = 11;
                }
                if (hasWestDoor() && hasNorthDoor() && hasSouthDoor() && hasEastDoor()) {
                    myRoomMap[i][j] = 10;
                }
                if (hasWestDoor() && hasNorthDoor() && hasSouthDoor()) {
                    myRoomMap[i][j] = 9;
                }
                if (hasEastDoor() && hasNorthDoor() && hasWestDoor()) {
                    myRoomMap[i][j] = 7;
                }
                if (hasEastDoor() && hasSouthDoor() && hasWestDoor()) {
                    myRoomMap[i][j] = 12;
                }
            }
        }

    }

    /**
     * Method to get the appropriate room tile index
     *
     * @param index1 the row
     * @param index2 the column
     * @return image index
     */
    public int getMapTiles(final int index1, final int index2) {
        return myRoomMap[index1][index2];
    }

    /**
     * Method to get if a room has been visited
     *
     * @return true or false
     */
    public boolean getIsVisited() {
        return myIsVisited;
    }

    /**
     * Method to set whether a room is visited
     */
    public void setIsVisited(final boolean theIsVisited) {
        myIsVisited = theIsVisited;
    }

    /**
     * Method to get the ArrayList of a room's neighbors
     *
     * @return ArrayList of rooms
     */
    public ArrayList<Room> getMyRoomNeighbors() {
        return myRoomNeighbors;
    }

    /**
     * Method to create String representation of a room
     *
     * @return String of a room
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(myWestDoor);
        sb.append(myNorthDoor);
        if (this.getMyInheritancePillar()) {
            sb.append("I");
        }
        if (this.getMyAbstractionPillar()) {
            sb.append("A");
        }
        if (this.getMyEncapsulationPillar()) {
            sb.append("E");
        }
        if (this.getMyPolymorphismPillar()) {
            sb.append("P");
        }
        if (this.getHasPotion()) {
            sb.append("p");
        }
        if (this.getIsEnter()) {
            sb.append("i");
        }
        if (this.getIsExit()) {
            sb.append("O");
        }
        if (this.getMyHasMonster()) {
            sb.append("M");
        }
        sb.append(mySouthDoor);
        sb.append(myEastDoor);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Method to get if a room has an encapsulation pillar
     *
     * @return true or false
     */
    public boolean getMyEncapsulationPillar() {
        return myEncapsulationPillar;
    }

    /**
     * Method to get if a room has an abstraction pillar
     *
     * @return true or false
     */
    public boolean getMyAbstractionPillar() {
        return myAbstractionPillar;
    }

    /**
     * Method to get if a room has an inheritance pillar
     *
     * @return true or false
     */
    public boolean getMyInheritancePillar() {
        return myInheritancePillar;
    }

    /**
     * Method to get if a room has a polymorphism pillar
     *
     * @return true or false
     */
    public boolean getMyPolymorphismPillar() {
        return myPolymorphismPillar;
    }

    /**
     * Method to set if a room has an encapsulation pillar
     */
    public void setMyEncapsulationPillar(final boolean theEncapsulationPillar) {
        myEncapsulationPillar = theEncapsulationPillar;
    }

    /**
     * Method to set if a room has an abstraction pillar
     */
    public void setMyAbstractionPillar(final boolean theAbstractionPillar) {
        myAbstractionPillar = theAbstractionPillar;
    }

    /**
     * Method to set if a room has an inheritance pillar
     */
    public void setMyInheritancePillar(final boolean theInheritancePillar) {
        myInheritancePillar = theInheritancePillar;
    }

    /**
     * Method to set if a room has a polymorphism pillar
     */
    public void setMyPolymorphismPillar(final boolean thePolymorphismPillar) {
        myPolymorphismPillar = thePolymorphismPillar;
    }

    /**
     * Method to get if a room has a monster
     *
     * @return true or false
     */
    public boolean getMyHasMonster() {
        return myHasMonster;
    }

    /**
     * Method to get if a room has a pit
     *
     * @return true or false
     */
    public boolean getMyHasPit() {
        return myHasPit;
    }

    /**
     * Method to set if a room has a pit
     */
    public void setMyHasPit(final boolean theHasPit) {
        myHasPit = theHasPit;
    }

    /**
     * Method to set if a room has a potion
     */
    public void setHasPotion(final boolean theHasPotion) {
        myHasPotion = theHasPotion;
    }

    /**
     * Method to get if a room has a potion
     *
     * @return true or false
     */
    public boolean getHasPotion() {
        return myHasPotion;
    }

    /**
     * Method to get if a room is the entrance
     *
     * @return true or false
     */
    public boolean getIsEnter() {
        return myIsEnter;
    }

    /**
     * Method to set if a room is the entrance
     */
    public void setIsEnter(final boolean theIsEnter) {
        myIsEnter = theIsEnter;
    }

    /**
     * Method to get if a room is the exit
     *
     * @return true or false
     */
    public boolean getIsExit() {
        return myIsExit;
    }

    /**
     * Method to set if a room is the exit
     */
    public void setIsExit(final boolean theIsExit) {
        myIsExit = theIsExit;
    }

    /**
     * Method to get if a room has a north door
     *
     * @return true or false
     */
    public boolean hasNorthDoor() {
        return myNorthDoor.equals("^");
    }

    /**
     * Method to set if a room has a north door
     */
    public void setMyNorthDoor(final String theNorthDoor) {
        myNorthDoor = theNorthDoor;
    }

    /**
     * Method to get if a room has a south door
     *
     * @return true or false
     */
    public boolean hasSouthDoor() {
        return mySouthDoor.equals("v");
    }

    /**
     * Method to set if a room has a south door
     */
    public void setMySouthDoor(final String theSouthDoor) {
        mySouthDoor = theSouthDoor;
    }

    /**
     * Method to get if a room has an east door
     *
     * @return true or false
     */
    public boolean hasEastDoor() {
        return myEastDoor.equals(">");
    }

    /**
     * Method to set if a room has an east door
     */
    public void setMyEastDoor(final String theEastDoor) {
        myEastDoor = theEastDoor;
    }

    /**
     * Method to get if a room has a west door
     *
     * @return true or false
     */
    public boolean hasWestDoor() {
        return myWestDoor.equals("<");
    }

    /**
     * Method to set if a room has a west door
     */
    public void setMyWestDoor(final String theWestDoor) {
        myWestDoor = theWestDoor;
    }

    /**
     * Method to set a room's monster
     */
    public void setMonster(final Monster theMonster) {
        myMonster = theMonster;
        myHasMonster = true;
        myMonster.setMyAlive(true);

    }

    /**
     * Method to get a room's monster
     *
     * @return a monster object
     */
    public Monster getMyMonster() {
        return myMonster;
    }

}
