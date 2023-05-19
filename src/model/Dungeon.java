package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dungeon {
    private final int myRows;
    private final int myCols;
    private final Room[][] myMaze;
    Random random = new Random();

    public Dungeon(int rows, int cols) {
        myRows = rows;
        myCols = cols;
        myMaze = new Room[rows][cols];
        generateMaze();
    }

    private void generateMaze() {
        int myStartingRow = random.nextInt(myRows);
        int myStartingCol = random.nextInt(myCols);
        int rooms = 1;
        // Generate rooms
        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                myMaze[i][j] = new Room(i, j);
                myMaze[i][j].generateItems();
            }
        }
        setEntrance(myStartingCol, myStartingRow);
        int currentRow = myStartingRow;
        int currentCol = myStartingCol;
        int nextRow = myStartingRow;
        int nextCol = myStartingCol;
        myMaze[currentRow][currentCol].setIsVisited(true);
        String[] directions = {"NORTH", "SOUTH", "EAST", "WEST"};

        while (rooms < myRows * myCols) {
            String direction = directions[random.nextInt(directions.length)];
            if(currentRow < myRows - 1 && direction.equals("SOUTH")) {
                nextRow = currentRow + 1;
                myMaze[currentRow][currentCol].setMySouthRoom(myMaze[nextRow][nextCol]);
                myMaze[nextRow][nextCol].setMyNorthRoom(myMaze[currentRow][currentCol]);
                currentRow = nextRow;
                rooms += checkRoom(myMaze[currentRow][currentCol]);
            }
            else if(currentRow > 0 && direction.equals("NORTH")) {
                nextRow = currentRow - 1;
                myMaze[currentRow][currentCol].setMyNorthRoom(myMaze[nextRow][nextCol]);
                myMaze[nextRow][nextCol].setMySouthRoom(myMaze[currentRow][currentCol]);
                currentRow = nextRow;
                rooms += checkRoom(myMaze[currentRow][currentCol]);
            }
            else if(currentCol < myRows - 1 && direction.equals("EAST")) {
                nextCol = currentCol + 1;
                myMaze[currentRow][currentCol].setMyEastRoom(myMaze[nextRow][nextCol]);
                myMaze[nextRow][nextCol].setMyWestRoom(myMaze[currentRow][currentCol]);
                currentCol = nextCol;
                rooms += checkRoom(myMaze[currentRow][currentCol]);
            }
            else if(currentCol > 0 && direction.equals("WEST")) {
                nextCol = currentCol - 1;
                myMaze[currentRow][currentCol].setMyWestRoom(myMaze[nextRow][nextCol]);
                myMaze[nextRow][nextCol].setMyEastRoom(myMaze[currentRow][currentCol]);
                currentCol = nextCol;
                rooms += checkRoom(myMaze[currentRow][currentCol]);
            }
        }
        setExit(currentCol, currentRow);


        // Generate connections
//        for (int i = 0; i < myRows; i++) {
//            for (int j = 0; j < myCols; j++) {
//                Room room = myMaze[i][j];
//                if (i > 0 && random.nextDouble() < 0.5) { // Randomly add north door
//                    Room northRoom = myMaze[i - 1][j];
//                    room.addDoor("N", northRoom);
//                    northRoom.addDoor("S", room);
//                }
//                if (i < myRows - 1 && random.nextDouble() < 0.5) { // Randomly add south door
//                    Room southRoom = myMaze[i + 1][j];
//                    room.addDoor("S", southRoom);
//                    southRoom.addDoor("N", room);
//                }
//                if (j > 0 && random.nextDouble() < 0.5) { // Randomly add west door
//                    Room westRoom = myMaze[i][j - 1];
//                    room.addDoor("W", westRoom);
//                    westRoom.addDoor("E", room);
//                }
//                if (j < myCols - 1 && random.nextDouble() < 0.5) { // Randomly add east door
//                    Room eastRoom = myMaze[i][j + 1];
//                    room.addDoor("E", eastRoom);
//                    eastRoom.addDoor("W", room);
//                }
//            }
//        }
    }

    private void setExit(int currentCol, int currentRow) {
        myMaze[currentRow][currentCol].setExit(true); // [col][row
    }

    private int checkRoom(Room room) {
        if (room.getIsBuilt()) {
            return 0;
        }
        room.setIsBuilt(true);
        return 1;
    }

    public void setEntrance(final int col, final int row) {
        myMaze[row][col].setEntrance(true); // [col][row

    }
    public void setExit() {

    }
    public Room getRoom(int x, int y) {
        if (x >= 0 && x < myRows && y >= 0 && y < myCols) {
            return myMaze[x][y];
        }
        return null;
    }

    public Room getRandomRoom() {
        Random random = new Random();
        int x = random.nextInt(myRows);
        int y = random.nextInt(myCols);
        return myMaze[x][y];
    }

    public void printMaze() {
        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                Room room = myMaze[i][j];
//                room.display();
                System.out.println(myMaze[i][j].toString());

            }
        }
        System.out.println("-----------------------------");
    }

    public static void main(String[] args) {
        int rows = 5;
        int cols = 5;
        Dungeon generator = new Dungeon(rows, cols);
        generator.printMaze();
    }
}
