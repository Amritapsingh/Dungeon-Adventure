package model;

import java.util.Random;

public class Room {
    boolean northDoor;
    boolean southDoor;
    boolean eastDoor;
    boolean westDoor;
    boolean isEnter;
    boolean isExit;
    private boolean isVisited;

    public Room() {
        Random theRandom = new Random();
        this.isVisited = false;
        this.northDoor = theRandom.nextBoolean();
        this.southDoor = theRandom.nextBoolean();
        this.eastDoor = theRandom.nextBoolean();
        this.westDoor = theRandom.nextBoolean();
        this.isEnter = false;
        this.isExit = false;
    }

    public Room(int i, int j) {
    }

    public boolean getIsVisited() {
        return isVisited;
    }
    public void setIsVisited(final boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void generateItems() {
    }

    public void addDoor(String n, Room northRoom) {
    }

    public void display() {
    }
}
