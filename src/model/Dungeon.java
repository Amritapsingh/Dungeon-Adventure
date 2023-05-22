package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Dungeon {
    private int rows;
    private int cols;
    private Room[][] maze;
    private Stack<Room> roomStack;
    private int numRooms;
    private int visitedRooms;

    public Dungeon(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maze = new Room[rows][cols];
        this.roomStack =  new Stack<>();
        this.numRooms = rows * cols;
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
        }
        Room room = getRandomRoom();
        System.out.println();
        roomStack.push(room);
        room.setIsVisited(true);
        visitedRooms++;
        do {
            room = roomStack.peek();
            Room neighbor = getNeighborOf(room);
            if (neighbor == null) {
                roomStack.pop();
            } else {
                visitedRooms++;
                neighbor.setIsVisited(true);
                roomStack.push(neighbor);
            }
        } while (visitedRooms != numRooms);
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

    public Room getNeighborOf(Room room) {
        Random random = new Random();
        getAllNeighbors(room);
        if (room.neighbors > 0) {
            ArrayList<Room> roomNeighbors = room.getRoomNeighbors();
            return roomNeighbors.get(random.nextInt(room.neighbors));
        } else {
            return null;
        }
    }

    public void getAllNeighbors(Room room) {
        ArrayList<Room> neighbors = room.getRoomNeighbors();
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
    public void printMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Room room = maze[i][j];
                room.display();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rows = 5;
        int cols = 8;
        Dungeon generator = new Dungeon(rows, cols);

        generator.printMaze();
    }
}
