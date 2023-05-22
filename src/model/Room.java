package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Room {
    boolean myIsEnter;
    boolean myIsExit;
    private boolean myIsVisited;
    public int x;
    public int y;
    private ArrayList<Room> roomNeighbors;
    public int neighbors;
    boolean myNorthDoor;
    boolean mySouthDoor;
    boolean myEastDoor;
    boolean myWestDoor;
    private static final String[] DIRECTIONS = {"N", "S", "E", "W"};

    public Room(int x, int y) {
        this.x = x;
        this.y = y;
        myIsVisited = false;
        myIsEnter = false;
        myIsExit = false;
        myNorthDoor = false;
        mySouthDoor = false;
        myEastDoor = false;
        myWestDoor = false;
        this.roomNeighbors = new ArrayList<>();
        neighbors = 0;
    }

    public boolean getIsVisited() {
        return myIsVisited;
    }
    public void setIsVisited(final boolean isVisited) {
        myIsVisited = isVisited;
    }

    public ArrayList<Room> getRoomNeighbors() { return roomNeighbors; }


    public void display() {
        //StringBuilder graphicalRepr = new StringBuilder();
        System.out.print("[" + x + "," + y + "]");

    }

}
