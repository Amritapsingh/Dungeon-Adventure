package model;

import java.util.*;

public class Room {

    private int myX;
    private int myY;
    boolean myNorthDoor;
    boolean mySouthDoor;
    boolean myEastDoor;
    boolean myWestDoor;
    boolean myIsEnter;
    boolean myIsExit;
    private final List<String> myItems;
    private final HashMap<String, Boolean> myDoors;
    private boolean myIsVisited;
    private static final String[] DIRECTIONS = {"N", "S", "E", "W"};

    public Room(final int theX, final int theY) {
        Random theRandom = new Random();
        myX = theX;
        myY = theY;
        myIsVisited = false;
        myNorthDoor = theRandom.nextBoolean();
        mySouthDoor = theRandom.nextBoolean();
        myEastDoor = theRandom.nextBoolean();
        myWestDoor = theRandom.nextBoolean();
        myItems = new ArrayList<>();
        myDoors = new HashMap<>();
        myDoors.put("N", false);
        myDoors.put("S", false);
        myDoors.put("E", false);
        myDoors.put("W", false);
        myIsEnter = false;
        myIsExit = false;
    }


    public boolean getIsVisited() {
        return myIsVisited;
    }
    public void setIsVisited(final boolean isVisited) {
        myIsVisited = isVisited;
    }

    public void generateItems() {
        double random = Math.random();
        myItems.add("Health Potion");
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
