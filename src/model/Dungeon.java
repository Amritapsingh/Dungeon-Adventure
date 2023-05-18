package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dungeon {
    private int rows;
    private int cols;
    private Room[][] maze;

    public Dungeon(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maze = new Room[rows][cols];
        generateMaze();
    }

    private void generateMaze() {
        Random random = new Random();

        // Generate rooms
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = new Room(i, j);
                maze[i][j].generateItems();
            }
        }

        // Generate connections
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Room room = maze[i][j];
                if (i > 0) {
                    Room northRoom = maze[i - 1][j];
                    room.addDoor("N", northRoom);
                    northRoom.addDoor("S", room);
                }
                if (i < rows - 1) {
                    Room southRoom = maze[i + 1][j];
                    room.addDoor("S", southRoom);
                    southRoom.addDoor("N", room);
                }
                if (j > 0) {
                    Room westRoom = maze[i][j - 1];
                    room.addDoor("W", westRoom);
                    westRoom.addDoor("E", room);
                }
                if (j < cols - 1) {
                    Room eastRoom = maze[i][j + 1];
                    room.addDoor("E", eastRoom);
                    eastRoom.addDoor("W", room);
                }
            }
        }
    }
    public void setEntrance(final int col, final int row) {
        maze[col][row].setEntrance(true); // [col][row

    }
    public void setExit() {

    }
    public Room getRoom(int x, int y) {
        if (x >= 0 && x < rows && y >= 0 && y < cols) {
            return maze[x][y];
        }
        return null;
    }

    public Room getRandomRoom() {
        Random random = new Random();
        int x = random.nextInt(rows);
        int y = random.nextInt(cols);
        return maze[x][y];
    }

    public void printMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Room room = maze[i][j];
                room.display();
            }
        }
    }

    public static void main(String[] args) {
        int rows = 5;
        int cols = 5;
        Dungeon generator = new Dungeon(rows, cols);
        generator.printMaze();
    }
}
