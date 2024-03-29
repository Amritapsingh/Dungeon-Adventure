package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * This class represents a dungeon containing our maze and
 * all its components.
 *
 * @author Amrit Singh
 * @author Akil Turner-Richards
 * @author Jay Phommakhot
 * @version 09 June 2023
 */
public class Dungeon implements Serializable {
    /**
     * Field for rows of maze
     */
    final private int myRows;
    /**
     * Field for cols of maze
     */
    final private int myCols;
    /**
     * Field the maze of rooms
     */
    final private Room[][] myMaze;
    /**
     * Field for stack for DFS traversal
     */
    final private Stack<Room> myRoomStack;
    /**
     * Field for number of rooms
     */
    final private int myNumRooms;
    /**
     * Field for how many rooms have been visited
     */
    private int myVisitedRooms;
    /**
     * Field for percentage of rooms with potions and pits
     */
    final private float myPotionPercentage;
    /**
     * Field for total num of potions
     */
    private int myPotionNum;
    /**
     * Field for total num of pits in dungeon
     */
    final private int myPitNum;

    /**
     * Field for random generation
     */
    Random rand = new Random();

    /**
     * String for the difficulty
     */
    String myDifficulty;

    /**
     * Constructor for dungeon
     *
     * @param theRows the rows of the maze
     * @param theCols the cols of the maze
     * @param theHero the hero of the maze
     */
    public Dungeon(int theRows, int theCols, Hero theHero, String theDifficulty) {
        myDifficulty = theDifficulty;
        myPotionPercentage = 0.25f;
        myRows = theRows;
        myCols = theCols;
        myMaze = new Room[theRows][theCols];
        myRoomStack =  new Stack<>();
        myNumRooms = theRows * theCols;
        myPotionNum = (int) (myPotionPercentage * myNumRooms);
        myPitNum = (int) (myPotionPercentage * myNumRooms);
        myVisitedRooms = 0;
        generateMaze();
    }

    /**
     * Creates a maze of empty rooms and populates them with our maze
     */
    private void generateMaze() {
        // Generate rooms
        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                myMaze[i][j] = new Room(j, i);
            }
            System.out.println();
        }
        Room room = getRandomRoom();
        System.out.println(room.myX +"," + room.myY);
        myRoomStack.push(room);
        room.setIsVisited(true);
        room.myIsEnter = true;
        myVisitedRooms = 1;
        do {
            room = myRoomStack.peek();
            Room neighbor = getNeighborOf(room);
            if (neighbor == null) {
                myRoomStack.pop();
            } else {
                myVisitedRooms++;
                neighbor.setIsVisited(true);
                connectRooms(room,neighbor);
                myRoomStack.push(neighbor);
            }
        } while (myVisitedRooms != myNumRooms);
        setExit(rand.nextInt(myRows), rand.nextInt(myCols));
        setInheritancePillar(rand.nextInt(myRows), rand.nextInt(myCols));
        setAbstractionPillar(rand.nextInt(myRows), rand.nextInt(myCols));
        setPolymorphismPillar(rand.nextInt(myRows), rand.nextInt(myCols));
        setEncapsulationPillar(rand.nextInt(myRows), rand.nextInt(myCols));
        placePotions();
        placePits();
        createMonsters();
    }

    /**
     * Method to return a random room
     * @return random room
     */
    public Room getRandomRoom() {
        Random random = new Random();
        int x = random.nextInt(myRows);
        int y = random.nextInt(myCols);
        return myMaze[x][y];
    }

    /**
     * Returns neighbor of specified room
     * @param room room to check neighbor of
     * @return neighbor of room
     */
    public Room getNeighborOf(final Room room) {
        getAllNeighbors(room);
        if (room.myNeighbors > 0) {
            ArrayList<Room> roomNeighbors = room.getMyRoomNeighbors();
            Random random = new Random();
            return roomNeighbors.get(random.nextInt(room.myNeighbors));
        } else {
            return null;
        }
    }

    /**
     * Returns all neighbors of specified room
     * @param room room to check neighbors of
     */
    public ArrayList<Room> getAllNeighbors(final Room room) {
        ArrayList<Room> neighbors = room.getMyRoomNeighbors();
        room.myNeighbors = 0;
        room.getMyRoomNeighbors().clear();
        if (room.myY > 0 && !myMaze[room.myY - 1][room.myX].getIsVisited()) {
            neighbors.add(myMaze[room.myY - 1][room.myX]);
            room.myNeighbors++;
        }
        if (room.myY < myRows - 1 && !myMaze[room.myY + 1][room.myX].getIsVisited()) {
            neighbors.add(myMaze[room.myY + 1][room.myX]);
            room.myNeighbors++;
        }
        if (room.myX > 0 && !myMaze[room.myY][room.myX - 1].getIsVisited()) {
            neighbors.add(myMaze[room.myY][room.myX - 1]);
            room.myNeighbors++;
        }
        if (room.myX < myCols - 1 && !myMaze[room.myY][room.myX + 1].getIsVisited()) {
            neighbors.add(myMaze[room.myY][room.myX + 1]);
            room.myNeighbors++;
        }
        return neighbors;
    }

    /**
     * Method to connect and link rooms together
     * @param room1 room to connect
     * @param room2 room to connect
     */
    private void connectRooms(final Room room1, final Room room2) {
        if (room1.myX > room2.myX) {
            //room1.myWestDoor = "<";
            room1.setMyWestDoor("<");
            //room2.myEastDoor = ">";
            room2.setMyEastDoor(">");
        } else if(room1.myX < room2.myX) {
            //room2.myWestDoor = "<";
            room2.setMyWestDoor("<");
            //room1.myEastDoor = ">";
            room1.setMyEastDoor(">");
        }
        if (room1.myY > room2.myY) {
            //room1.myNorthDoor = "^";
            room1.setMyNorthDoor("^");
            //room2.mySouthDoor = "v";
            room2.setMySouthDoor("v");
        } else if (room1.myY < room2.myY) {
            //room2.myNorthDoor = "^";
            room2.setMyNorthDoor("^");
            //room1.mySouthDoor = "v";
            room1.setMySouthDoor("v");
        }
    }

    /**
     * Method to display the maze to the console
     */
    public void printMaze() {
        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                System.out.print(myMaze[i][j].toString());
            }
            System.out.println();
        }
    }

    /**
     * Method to get the current maze
     * @return maze
     */
    public Room[][] getMaze() {
        return myMaze;
    }

    /**
     * Method to set the entrance to the maze
     * @param row row of entrance
     * @param col column of entrance
     */
    public void setEnter(final int row, final int col) {
        myMaze[row][col].setIsEnter(true);
    }

    /**
     * Method to set the exit of the maze
     * @param row row of exit
     * @param col column of exit
     */
    public void setExit(final int row, final int col) {
        if (myMaze[row][col].getIsEnter()) {
            setExit(rand.nextInt(myRows), rand.nextInt(myCols));
        } else {
            myMaze[row][col].setIsExit(true);
        }
    }

    /**
     * Method to set location of the pillar of abstraction
     * @param row row of pillar
     * @param col col of pillar
     */
    public void setAbstractionPillar(final int row, final int col) {
        if(myMaze[row][col].getIsEnter() || myMaze[row][col].getIsExit() ||
                myMaze[row][col].getMyEncapsulationPillar() || myMaze[row][col].getMyInheritancePillar() ||
                myMaze[row][col].getMyPolymorphismPillar()) {
            if (myRows <= 2 && myCols <= 2) {
                myMaze[row][col].setMyAbstractionPillar(true);
            } else {
                setAbstractionPillar(rand.nextInt(myRows), rand.nextInt(myCols));
            }
        } else {
            myMaze[row][col].setMyAbstractionPillar(true);
        }
    }

    /**
     * Method to set location of the pillar of encapsulation
     * @param row   row of pillar
     * @param col   col of pillar
     */
    public void setEncapsulationPillar(final int row, final int col) {
        if (myMaze[row][col].getIsEnter() || myMaze[row][col].getIsExit() ||
                myMaze[row][col].getMyAbstractionPillar() || myMaze[row][col].getMyInheritancePillar() ||
                myMaze[row][col].getMyPolymorphismPillar()) {
            if (myRows <= 2 && myCols <= 2) {
                myMaze[row][col].setMyEncapsulationPillar(true);
            } else {
                setEncapsulationPillar(rand.nextInt(myRows), rand.nextInt(myCols));
            }
        } else {
            myMaze[row][col].setMyEncapsulationPillar(true);
        }
    }

    /**
     * Method to set location of the pillar of inheritance
     * @param row  row of pillar
     * @param col  col of pillar
     */
    public void setInheritancePillar(final int row, final int col) {
        if (myMaze[row][col].getIsEnter() || myMaze[row][col].getIsExit() ||
                myMaze[row][col].getMyAbstractionPillar() || myMaze[row][col].getMyEncapsulationPillar() ||
                myMaze[row][col].getMyPolymorphismPillar()) {
            if (myRows <= 2 && myCols <= 2) {
                myMaze[row][col].setMyInheritancePillar(true);
            } else {
                setInheritancePillar(rand.nextInt(myRows), rand.nextInt(myCols));
            }
        } else {
            myMaze[row][col].setMyInheritancePillar(true);
        }
    }

    /**
     * Method to set location of the pillar of polymorphism
     * @param row  row of pillar
     * @param col  col of pillar
     */
    public void setPolymorphismPillar(final int row, final int col) {
        if (myMaze[row][col].getIsEnter() || myMaze[row][col].getIsExit() ||
                myMaze[row][col].getMyAbstractionPillar() || myMaze[row][col].getMyEncapsulationPillar() ||
                myMaze[row][col].getMyInheritancePillar()) {
            if (myRows <= 2 && myCols <= 2) {
                myMaze[row][col].setMyPolymorphismPillar(true);
            } else {
                setPolymorphismPillar(rand.nextInt(myRows), rand.nextInt(myCols));
            }
        } else {
            myMaze[row][col].setMyPolymorphismPillar(true);
        }
    }

    /**
     * Method to place potions randomly on map
     */
    public void placePotions() {
        int potionNum = myPotionNum;
        while (potionNum != 0) {
            Room room = getRandomRoom();
            if (!room.getHasPotion()) {
                room.setHasPotion(true);
                potionNum--;
            }
        }
    }

    /**
     * Method to place pits randomly on map
     */
    public void placePits() {
        int pitNum = myPitNum;
        while (pitNum != 0) {
            Room room = getRandomRoom();
            if (!room.getMyHasPit()) {
                room.setMyHasPit(true);
                pitNum--;
            }
        }
    }

    /**
     * Method to get the number of potions
     * @return number of potions
     */
    public int getPotionNum() {
        return myPotionNum;
    }

    /**
     * Method to set number of potions
     * @param thePotionNum number of potions
     */
    public void setPotionNum(final int thePotionNum) {
        myPotionNum = thePotionNum;
    }

    /**
     * Method to create and place monsters on map
     */
    public void createMonsters() {
        List<Monster> monsters;
        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                if (myMaze[i][j].getMyAbstractionPillar() || myMaze[i][j].getMyEncapsulationPillar() ||
                        myMaze[i][j].getMyInheritancePillar() || myMaze[i][j].getMyPolymorphismPillar()) {
                    Monster monster;
                    final DungeonSQLite database = new DungeonSQLite();
                    database.testConnection();
                    // methods to populate database
//                    database.createMonsterTable();
//                    database.addMonstersToTable();
                    monsters = database.fetchMonsters(myDifficulty);
                    monster = monsters.get(rand.nextInt(monsters.size()));
                    myMaze[i][j].setMonster(monster);
                }
            }
        }
    }

    /**
     * Method to get the number of pits
     * @return int for number of pits
     */
    public int getPitNum() {
        return myPitNum;
    }

}
