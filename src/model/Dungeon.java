package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dungeon {
    private final int myRows;
    private final int myCols;
    private final Room[][] myMaze;

    public Dungeon(int rows, int cols) {
        myRows = rows;
        myCols = cols;
        myMaze = new Room[rows][cols];
        generateMaze();
    }

    private void generateMaze() {
        Random random = new Random();

        // Generate rooms
        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                myMaze[i][j] = new Room(i, j);
                myMaze[i][j].generateItems();
            }
        }

        // Generate connections
        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                Room room = myMaze[i][j];
                if (i > 0 && random.nextDouble() < 0.5) { // Randomly add north door
                    Room northRoom = myMaze[i - 1][j];
                    room.addDoor("N", northRoom);
                    northRoom.addDoor("S", room);
                }
                if (i < myRows - 1 && random.nextDouble() < 0.5) { // Randomly add south door
                    Room southRoom = myMaze[i + 1][j];
                    room.addDoor("S", southRoom);
                    southRoom.addDoor("N", room);
                }
                if (j > 0 && random.nextDouble() < 0.5) { // Randomly add west door
                    Room westRoom = myMaze[i][j - 1];
                    room.addDoor("W", westRoom);
                    westRoom.addDoor("E", room);
                }
                if (j < myCols - 1 && random.nextDouble() < 0.5) { // Randomly add east door
                    Room eastRoom = myMaze[i][j + 1];
                    room.addDoor("E", eastRoom);
                    eastRoom.addDoor("W", room);
                }
            }
        }
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
                room.display();
            }
        }
    }

    public static void main(String[] args) {
        int rows = 6;
        int cols = 4;
        Dungeon generator = new Dungeon(rows, cols);
        generator.printMaze();
    }
}
