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
        //System.out.println("-----------------------------");
    }

    private void connectRooms(final Room room1, final Room room2) {
        if (room1.x > room2.x) {
            room1.myWestDoor = "<";
            room2.myEastDoor = ">";
        } else if(room1.x < room2.x) {
            room2.myWestDoor = "<";
            room1.myEastDoor = ">";
        }
        if (room1.y > room2.y) {
            room1.myNorthDoor = "^";
            room2.mySouthDoor = "v";
        } else if (room1.y < room2.y) {
            room2.myNorthDoor = "^";
            room1.mySouthDoor = "v";
        }
    }
    public void printMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Room room = maze[i][j];
//                room.display();
                System.out.println(maze[i][j].toString());

            }
            System.out.println();
        }
    }
    public Room[][] getMaze() {
        return maze;
    }
    public static void main(String[] args) {
        int rows = 7;
        int cols = 10;
        Dungeon generator = new Dungeon(rows, cols);

        generator.printMaze();
    }
}
