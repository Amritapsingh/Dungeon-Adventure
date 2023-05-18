package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Room {
    boolean myNorthDoor;
    boolean mySouthDoor;
    boolean myEastDoor;
    boolean myWestDoor;
    boolean myIsEnter;
    boolean myIsExit;
    private List<String> myItems;
    private Map<String, Boolean> myDoors;
    private boolean myIsVisited;
    private static final String[] DIRECTIONS = {"N", "S", "E", "W"};

    public Room() {
        Random theRandom = new Random();
        myIsVisited = false;
        myNorthDoor = theRandom.nextBoolean();
        mySouthDoor = theRandom.nextBoolean();
        myEastDoor = theRandom.nextBoolean();
        myWestDoor = theRandom.nextBoolean();
        myItems = new ArrayList<>();
        myDoors = Map.of("N", false, "S", false, "E", false, "W", false);
        myIsEnter = false;
        myIsExit = false;
    }

    public Room(int i, int j) {
    }

    public boolean getIsVisited() {
        return myIsVisited;
    }
    public void setIsVisited(final boolean isVisited) {
        myIsVisited = isVisited;
    }

    public void generateItems() {
        double random = Math.random();
        if (random < 0.1) {
            if (random < 0.033) {
                myItems.add("Healing Potion");
            }
            if (random < 0.066) {
                myItems.add("Vision Potion");
            }
            if (random < 0.1) {
                myItems.add("Pit");
            }
        }
    }

    public void display() {
        StringBuilder graphicalRepr = new StringBuilder();
        if (!myDoors.get("N")) {
            graphicalRepr.append("***\n");
        } else {
            graphicalRepr.append("*-*\n");
        }
        System.out.print(graphicalRepr.toString());
    }

    public void addDoor(String direction, Room adjacentRoom) {
        if (adjacentRoom != null && containsDirection(direction)) {
            myDoors.put(direction, true);
            adjacentRoom.myDoors.put(getOppositeDirection(direction), true);
        }
    }

    private boolean containsDirection(String direction) {
        for (String dir : DIRECTIONS) {
            if (dir.equals(direction)) {
                return true;
            }
        }
        return false;
    }

    private String getOppositeDirection(String direction) {
        switch (direction) {
            case "N":
                return "S";
            case "S":
                return "N";
            case "E":
                return "W";
            case "W":
                return "E";
            default:
                return "";
        }
    }
}
