package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Dungeon implements Serializable {
    private int myRows;
    private int myCols;
    private Room[][] myMaze;
    private Stack<Room> myRoomStack;
    private int myNumRooms;
    private int myVisitedRooms;
    private float myPotionPercentage;
    private int myPotionNum;
    private int myPitNum;
    private Hero myHero;

    Random rand = new Random();

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


    public Room getRandomRoom() {
        Random random = new Random();
        int x = random.nextInt(myRows);
        int y = random.nextInt(myCols);
        return myMaze[x][y];
    }

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
    public Room[][] getMaze() {
        return myMaze;
    }
    public void setEnter(final int row, final int col) {
        myMaze[row][col].setIsEnter(true);
    }
    public void setExit(final int row, final int col) {
        if (myMaze[row][col].getIsEnter()) {
            setExit(rand.nextInt(myRows), rand.nextInt(myCols));
        } else {
            myMaze[row][col].setIsExit(true);
        }
    }
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
    public int getPotionNum() {
        return myPotionNum;
    }
    public void setPotionNum(final int thePotionNum) {
        myPotionNum = thePotionNum;
    }
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

    public Hero getMyHero() {
        return myHero;
    }
}
