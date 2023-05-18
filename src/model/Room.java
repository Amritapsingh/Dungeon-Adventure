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
    }
    public boolean  getIsVisited() {
        return isVisited;
    }
    public void setIsVisited(final boolean isVisited) {
        this.isVisited = isVisited;
    }
}
