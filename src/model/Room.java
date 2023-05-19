package model;

import java.util.*;

public class Room {

    private int myX;
    private int myY;
    Room myNorthRoom;
    Room mySouthRoom;
    Room myEastRoom;
    Room myWestRoom;
    boolean myIsEnter;
    boolean myIsExit;
    private final List<String> myItems;
    private final HashMap<String, Boolean> myDoors;
    private boolean myIsVisited;
    private static final String[] DIRECTIONS = {"N", "S", "E", "W"};

    public boolean myIsBuilt;

    public Room(final int theX, final int theY) {
        Random theRandom = new Random();
        myX = theX;
        myY = theY;
        myIsVisited = false;
//        myNorthDoor = theRandom.nextBoolean();
//        mySouthDoor = theRandom.nextBoolean();
//        myEastDoor = theRandom.nextBoolean();
//        myWestDoor = theRandom.nextBoolean();
        myItems = new ArrayList<>();
        myDoors = new HashMap<>();
        myDoors.put("N", false);
        myDoors.put("S", false);
        myDoors.put("E", false);
        myDoors.put("W", false);
        myIsEnter = false;
        myIsExit = false;
    }

    public Room getMyNorthRoom() {
        return myNorthRoom;
    }
    public void setMyNorthRoom(final Room northRoom) {
        myNorthRoom = northRoom;
    }
    public Room getMySouthRoom() {
        return mySouthRoom;
    }
    public void setMySouthRoom(final Room southRoom) {
        mySouthRoom = southRoom;
    }
    public Room getMyEastRoom() {
        return myEastRoom;
    }
    public void setMyEastRoom(final Room eastRoom) {
        myEastRoom = eastRoom;
    }
    public Room getMyWestRoom() {
        return myWestRoom;
    }
    public void setMyWestRoom(final Room westRoom) {
        myWestRoom = westRoom;
    }
    public boolean getIsVisited() {
        return myIsVisited;
    }
    public void setIsVisited(final boolean isVisited) {
        myIsVisited = isVisited;
    }
    public boolean getIsBuilt() {
        return myIsBuilt;
    }
    public void setIsBuilt(final boolean isBuilt) {
        myIsBuilt = isBuilt;
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
    public String toString() {
        String roomString = "";
        if (myIsEnter) {
            roomString += "E";
        }
        else if (myIsExit) {
            roomString += "X";
        }
        if (myNorthRoom != null) {
            roomString += "*-*\n";
        } else {
            roomString += "***\n";
        }

        if (myWestRoom != null) {
            roomString += "|";
        } else{
            roomString += "*";
        }

//        roomString += myStringToken;

        if (myEastRoom != null) {
            roomString += "|\n";
        }
        else {
            roomString += "*\n";
        }

        if (mySouthRoom != null) {
            roomString += "*-*\n";
        }
        else {
            roomString += "***\n";
        }

        return roomString;
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

    /**
     * Is entrance boolean.
     *
     * @return the boolean
     */
    public boolean isEntrance() {
        return myIsEnter;
    }

    /**
     * Sets entrance.
     *
     * @param entrance the entrance
     */
    public void setEntrance(boolean entrance) {
        myIsEnter = entrance;
    }

    /**
     * Is exit boolean.
     *
     * @return the boolean
     */
    public boolean getMyIsExit() {
        return myIsExit;
    }

    /**
     * Sets exit.
     *
     * @param exit the exit
     */
    public void setExit(boolean exit) {
        myIsExit = exit;
    }
}
