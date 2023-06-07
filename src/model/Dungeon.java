package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Dungeon {
    private int rows;
    private int cols;
    private Room[][] maze;
    private Stack<Room> roomStack;
    private int numRooms;
    private int visitedRooms;
    private float myPotionPercentage;
    private int myPotionNum;
    Random rand = new Random();

    public Dungeon(int rows, int cols) {
        this.myPotionPercentage = 0.25f;
        this.rows = rows;
        this.cols = cols;
        this.maze = new Room[rows][cols];
        this.roomStack =  new Stack<>();
        this.numRooms = rows * cols;
        this.myPotionNum = (int) (this.myPotionPercentage * this.numRooms);
        this.visitedRooms = 0;
        generateMaze();
    }

    private void generateMaze() {
        Random random = new Random();
        // Generate rooms
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = new Room(j, i);
            }
            System.out.println();
        }
        Room room = getRandomRoom();
        System.out.println(room.x +"," + room.y);
        roomStack.push(room);
        room.setIsVisited(true);
        room.myIsEnter = true;
        visitedRooms = 1;
        do {
            room = roomStack.peek();
            Room neighbor = getNeighborOf(room);
            if (neighbor == null) {
                roomStack.pop();
            } else {
                visitedRooms++;
                neighbor.setIsVisited(true);
                connectRooms(room,neighbor);
                roomStack.push(neighbor);
            }
        } while (visitedRooms != numRooms);
        setExit(rand.nextInt(rows), rand.nextInt(cols));
        setInheritancePillar(rand.nextInt(rows), rand.nextInt(cols));
        setAbstractionPillar(rand.nextInt(rows), rand.nextInt(cols));
        setPolymorphismPillar(rand.nextInt(rows), rand.nextInt(cols));
        setEncapsulationPillar(rand.nextInt(rows), rand.nextInt(cols));
        placePotions();
        createMonsters();
    }


    public Room getRandomRoom() {
        Random random = new Random();
        int x = random.nextInt(rows);
        int y = random.nextInt(cols);
        return maze[x][y];
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
        if (room.y > 0 && !maze[room.y - 1][room.x].getIsVisited()) {
            neighbors.add(maze[room.y - 1][room.x]);
            room.neighbors++;
        }
        if (room.y < rows - 1 && !maze[room.y + 1][room.x].getIsVisited()) {
            neighbors.add(maze[room.y + 1][room.x]);
            room.neighbors++;
        }
        if (room.x > 0 && !maze[room.y][room.x - 1].getIsVisited()) {
            neighbors.add(maze[room.y][room.x - 1]);
            room.neighbors++;
        }
        if (room.x < cols - 1 && !maze[room.y][room.x + 1].getIsVisited()) {
            neighbors.add(maze[room.y][room.x + 1]);
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
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Room room = maze[i][j];
//                room.display();
                System.out.print(maze[i][j].toString());

            }
            System.out.println();
        }
    }
    public Room[][] getMaze() {
        return maze;
    }
    public static void main(String[] args) {
        int rows = 4;
        int cols = 4;
        Dungeon generator = new Dungeon(rows, cols);

        generator.printMaze();
    }
    public void setEnter(final int row, final int col) {
        maze[row][col].setIsEnter(true);
    }
    public void setExit(final int row, final int col) {
        if (maze[row][col].getIsEnter()) {
            setExit(rand.nextInt(rows), rand.nextInt(cols));
        } else {
            maze[row][col].setIsExit(true);
        }
    }
    public void setAbstractionPillar(final int row, final int col) {
        if(maze[row][col].getIsEnter() || maze[row][col].getIsExit() ||
                maze[row][col].getEncapsulationPillar() || maze[row][col].getInheritancePillar() ||
                maze[row][col].getPolymorphismPillar()) {
            if (rows <= 2 && col <= 2) {
                maze[row][col].setAbstractionPillar(true);
            } else {
                setAbstractionPillar(rand.nextInt(rows), rand.nextInt(cols));
            }
        } else {
            maze[row][col].setAbstractionPillar(true);
        }
    }
    public void setEncapsulationPillar(final int row, final int col) {
        if (maze[row][col].getIsEnter() || maze[row][col].getIsExit() ||
                maze[row][col].getAbstractionPillar() || maze[row][col].getInheritancePillar() ||
                maze[row][col].getPolymorphismPillar()) {
            if (rows <= 2 && col <= 2) {
                maze[row][col].setEncapsulationPillar(true);
            } else {
                setEncapsulationPillar(rand.nextInt(rows), rand.nextInt(cols));
            }
        } else {
            maze[row][col].setEncapsulationPillar(true);
        }
    }
    public void setInheritancePillar(final int row, final int col) {
        if (maze[row][col].getIsEnter() || maze[row][col].getIsExit() ||
                maze[row][col].getAbstractionPillar() || maze[row][col].getEncapsulationPillar() ||
                maze[row][col].getPolymorphismPillar()) {
            if (rows <= 2 && col <= 2) {
                maze[row][col].setInheritancePillar(true);
            } else {
                setInheritancePillar(rand.nextInt(rows), rand.nextInt(cols));
            }
        } else {
            maze[row][col].setInheritancePillar(true);
        }
    }
    public void setPolymorphismPillar(final int row, final int col) {
        if (maze[row][col].getIsEnter() || maze[row][col].getIsExit() ||
                maze[row][col].getAbstractionPillar() || maze[row][col].getEncapsulationPillar() ||
                maze[row][col].getInheritancePillar()) {
            if (rows <= 2 && col <= 2) {
                maze[row][col].setPolymorphismPillar(true);
            } else {
                setPolymorphismPillar(rand.nextInt(rows), rand.nextInt(cols));
            }
        } else {
            maze[row][col].setPolymorphismPillar(true);
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
    public int getPotionNum() {
        return myPotionNum;
    }
    public void setPotionNum(final int thePotionNum) {
        myPotionNum = thePotionNum;
    }
    public void createMonsters() {
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j].getAbstractionPillar() || maze[i][j].getEncapsulationPillar() ||
                        maze[i][j].getInheritancePillar() || maze[i][j].getPolymorphismPillar()) {
                    Monster monster = null;
                    final DungeonSQLite database = new DungeonSQLite();
                    database.testConnection();
                    database.createMonsterTable();
                    database.addMonstersToTable();
                    monsters = database.fetchMonsters();
                    monster = monsters.get(rand.nextInt(monsters.size()));
                    maze[i][j].setMonster(monster);
                }
            }
        }
    }

}
