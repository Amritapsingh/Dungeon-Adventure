package model;

import java.awt.*;

public class Dungeon {
    private Room[][] myDungeon;
    private int numRooms;
    private final Point entryPoint;
    private final Point exitPoint;
    public Dungeon(int numRooms) {
        this.numRooms = numRooms;
        entryPoint = new Point(0, 0);
        exitPoint = new Point(0, 0);
    }
    public Room[][] setDungeon() {
        myDungeon = new Room[numRooms][numRooms];
        for (int i = 0; i < numRooms; i++) {
            for (int j = 0; j < numRooms; j++) {
                myDungeon[i][j] = new Room();
            }
        }
        return myDungeon;
    }
    public Room getRoom(int x, int y) {
        if (x < 0 || x >= numRooms || y < 0 || y >= numRooms) {
            return null; // throw outofbounds exception later
        }
        return myDungeon[x][y];
    }
    public Point getEntryPoint() {
        return entryPoint;
    }
    public Point getExitPoint() {
        return exitPoint;
    }

}
