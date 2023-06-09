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
 * @author Akil Turner-Richars
 * @author Jay Phommakhot
 * @version 09 June 2023
 */
public class Dungeon implements Serializable {
    /**
     * Field for rows of maze
     */
    private int myRows;
    /**
     * Field for cols of maze
     */
    private int myCols;
    /**
     * Field the maze of rooms
     */
    private Room[][] myMaze;
    /**
     * Field for stack for DFS traversal
     */
    private Stack<Room> myRoomStack;
    /**
     * Field for number of rooms
     */
    private int myNumRooms;
    /**
     * Field for how many rooms have been visited
     */
    private int myVisitedRooms;
    /**
     * Field for percentage of rooms with potions and pits
     */
    private float myPotionPercentage;
    /**
     * Field for total num of potions
     */
    private int myPotionNum;
    /**
     * Field for total num of pits in dungeon
     */
    private int myPitNum;
    /**
     * Field for the hero
     */
    private Hero myHero;
    /**
     * Field for random generation
     */
    Random rand = new Random();

    /**
     * Constructor for dungeon
     *
     * @param theRows the rows of the maze
     * @param theCols the cols of the maze
     * @param theHero the hero of the maze
     */
    public Dungeon(int theRows, int theCols, Hero theHero) {
        myPotionPercentage = 0.25f;
        myRows = theRows;
        myCols = theCols;
        myMaze = new Room[theRows][theCols];
        myRoomStack =  new Stack<>();
        myNumRooms = theRows * theCols;
        myPotionNum = (int) (myPotionPercentage * myNumRooms);
        myPitNum = (int) (myPotionPercentage * myNumRooms);
        myHero = theHero;
        myVisitedRooms = 0;
        generateMaze();
    }

    /**
     * Creates a maze of empty rooms and populates them with our maze
     */
    private void generateMaze() {
        Random random = new Random();
        // Generate rooms
        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                myMaze[i][j] = new Room(j, i);
            }
            System.out.println();
        }
        Room room = getRandomRoom();
        System.out.println(room.x +"," + room.y);
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
        if (room.neighbors > 0) {
            ArrayList<Room> roomNeighbors = room.getRoomNeighbors();
            Random random = new Random();
            return roomNeighbors.get(random.nextInt(room.neighbors));
        } else {
            return null;
        }
    }

    /**
     * Returns all neighbors of specified room
     * @param room room to check neighbors of
     */
    public void getAllNeighbors(final Room room) {
        ArrayList<Room> neighbors = room.getRoomNeighbors();
        room.neighbors = 0;
        room.getRoomNeighbors().clear();
        if (room.y > 0 && !myMaze[room.y - 1][room.x].getIsVisited()) {
            neighbors.add(myMaze[room.y - 1][room.x]);
            room.neighbors++;
        }
        if (room.y < myRows - 1 && !myMaze[room.y + 1][room.x].getIsVisited()) {
            neighbors.add(myMaze[room.y + 1][room.x]);
            room.neighbors++;
        }
        if (room.x > 0 && !myMaze[room.y][room.x - 1].getIsVisited()) {
            neighbors.add(myMaze[room.y][room.x - 1]);
            room.neighbors++;
        }
        if (room.x < myCols - 1 && !myMaze[room.y][room.x + 1].getIsVisited()) {
            neighbors.add(myMaze[room.y][room.x + 1]);
            room.neighbors++;
        }
    }

    /**
     * Method to connect and link rooms together
     * @param room1 room to connect
     * @param room2 room to connect
     */
    private void connectRooms(final Room room1, final Room room2) {
        if (room1.x > room2.x) {
            //room1.myWestDoor = "<";
            room1.setMyWestDoor("<");
            //room2.myEastDoor = ">";
            room2.setMyEastDoor(">");
        } else if(room1.x < room2.x) {
            //room2.myWestDoor = "<";
            room2.setMyWestDoor("<");
            //room1.myEastDoor = ">";
            room1.setMyEastDoor(">");
        }
        if (room1.y > room2.y) {
            //room1.myNorthDoor = "^";
            room1.setMyNorthDoor("^");
            //room2.mySouthDoor = "v";
            room2.setMySouthDoor("v");
        } else if (room1.y < room2.y) {
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
                Room room = myMaze[i][j];
//                room.display();
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
                myMaze[row][col].getEncapsulationPillar() || myMaze[row][col].getInheritancePillar() ||
                myMaze[row][col].getPolymorphismPillar()) {
            if (myRows <= 2 && col <= 2) {
                myMaze[row][col].setAbstractionPillar(true);
            } else {
                setAbstractionPillar(rand.nextInt(myRows), rand.nextInt(myCols));
            }
        } else {
            myMaze[row][col].setAbstractionPillar(true);
        }
    }

    /**
     * Method to set location of the pillar of encapsulation
     * @param row   row of pillar
     * @param col   col of pillar
     */
    public void setEncapsulationPillar(final int row, final int col) {
        if (myMaze[row][col].getIsEnter() || myMaze[row][col].getIsExit() ||
                myMaze[row][col].getAbstractionPillar() || myMaze[row][col].getInheritancePillar() ||
                myMaze[row][col].getPolymorphismPillar()) {
            if (myRows <= 2 && col <= 2) {
                myMaze[row][col].setEncapsulationPillar(true);
            } else {
                setEncapsulationPillar(rand.nextInt(myRows), rand.nextInt(myCols));
            }
        } else {
            myMaze[row][col].setEncapsulationPillar(true);
        }
    }

    /**
     * Method to set location of the pillar of inheritance
     * @param row  row of pillar
     * @param col  col of pillar
     */
    public void setInheritancePillar(final int row, final int col) {
        if (myMaze[row][col].getIsEnter() || myMaze[row][col].getIsExit() ||
                myMaze[row][col].getAbstractionPillar() || myMaze[row][col].getEncapsulationPillar() ||
                myMaze[row][col].getPolymorphismPillar()) {
            if (myRows <= 2 && col <= 2) {
                myMaze[row][col].setInheritancePillar(true);
            } else {
                setInheritancePillar(rand.nextInt(myRows), rand.nextInt(myCols));
            }
        } else {
            myMaze[row][col].setInheritancePillar(true);
        }
    }

    /**
     * Method to set location of the pillar of polymorphism
     * @param row  row of pillar
     * @param col  col of pillar
     */
    public void setPolymorphismPillar(final int row, final int col) {
        if (myMaze[row][col].getIsEnter() || myMaze[row][col].getIsExit() ||
                myMaze[row][col].getAbstractionPillar() || myMaze[row][col].getEncapsulationPillar() ||
                myMaze[row][col].getInheritancePillar()) {
            if (myRows <= 2 && col <= 2) {
                myMaze[row][col].setPolymorphismPillar(true);
            } else {
                setPolymorphismPillar(rand.nextInt(myRows), rand.nextInt(myCols));
            }
        } else {
            myMaze[row][col].setPolymorphismPillar(true);
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
                //room.setPotionValue(new Potion(rand.nextInt(10) + 1));
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
            if (!room.getHasPit()) {
                room.setHasPit(true);
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
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                if (myMaze[i][j].getAbstractionPillar() || myMaze[i][j].getEncapsulationPillar() ||
                        myMaze[i][j].getInheritancePillar() || myMaze[i][j].getPolymorphismPillar()) {
                    Monster monster = null;
                    final DungeonSQLite database = new DungeonSQLite();
                    database.testConnection();
                    //database.createMonsterTable();
                    //database.addMonstersToTable();
                    monsters = database.fetchMonsters();
                    monster = monsters.get(rand.nextInt(monsters.size()));
                    myMaze[i][j].setMonster(monster);
                }
            }
        }
    }

}
