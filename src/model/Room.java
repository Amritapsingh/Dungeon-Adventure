package model;

import view.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Room {
    boolean myIsEnter;
    boolean myIsExit;
    private boolean myIsVisited;
    private boolean hasMonster;
    private boolean hasPit;
    private boolean encapsulationPillar;
    private boolean abstractionPillar;
    private boolean inheritancePillar;
    private boolean polymorphismPillar;
    private boolean myHasPotion;
    private Monster myMonster;

    public int x;
    public int y;
    private ArrayList<Room> roomNeighbors;
    public int neighbors;
    String myNorthDoor;
    String mySouthDoor;
    String myEastDoor;
    String myWestDoor;
    private static final String[] DIRECTIONS = {"N", "S", "E", "W"};
    Image image;
    Random random = new Random();
    int[][] mapTiles;
    GameScreen myGameScreen;
    int[][] roomMap;
    int roomIndex;

    public Room(int x, int y) {
        this.x = x;
        this.y = y;
        myIsVisited = false;
        myIsEnter = false;
        myIsExit = false;
        myNorthDoor = " ";
        mySouthDoor = " ";
        myEastDoor = " ";
        myWestDoor = " ";
        this.roomNeighbors = new ArrayList<>();
        neighbors = 0;
        myMonster = null;
        try {
            int rand = random.nextInt(2);
            String filePath;
            if (rand != 1) {
                filePath = "/assets/water01.png";
            } else {
                filePath = "/assets/grass00.png";
            }
            this.image = ImageIO.read(getClass().getResourceAsStream(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public int[][] loadMap(GameScreen theGameScreen) {
        myGameScreen = theGameScreen;
        mapTiles = new int[myGameScreen.maxScreenRow][myGameScreen.maxScreenCol];
        String filePath = "/assets/map2.txt";
        try {
            InputStream st = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader((st)));
            int col = 0;
            int row = 0;
            while(col < myGameScreen.maxScreenCol && row < myGameScreen.maxScreenRow) {
                String line = br.readLine();
                while (col < myGameScreen.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTiles[row][col] = num;
                    col++;
                }
                if (col == myGameScreen.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return mapTiles;
    }
    public int[][] generateRoom(GameScreen theGameScreen) {
        myGameScreen = theGameScreen;
        roomMap = new int[myGameScreen.dungeon.getMaze().length][myGameScreen.dungeon.getMaze()[0].length];
        for (int i = 0; i < roomMap.length; i++) {
            for (int j = 0; j < roomMap[0].length; j++) {
                if (hasNorthDoor()) {
                    roomMap[i][j] = 0;
                }
                if (hasSouthDoor()) {
                    roomMap[i][j] = 1;
                }
                if (hasEastDoor()) {
                    roomMap[i][j] = 2;
                }
                if (hasWestDoor()) {
                    roomMap[i][j] = 3;
                }
                if (hasNorthDoor() && hasSouthDoor()) {
                    roomMap[i][j] = 8;
                }
                if (hasNorthDoor() && hasEastDoor()) {
                    roomMap[i][j] = 4;
                }
                if (hasNorthDoor() && hasWestDoor()) {
                    roomMap[i][j] = 6;
                }
                if (hasSouthDoor() && hasEastDoor()) {
                    roomMap[i][j] = 13;
                }
                if (hasSouthDoor() && hasWestDoor()) {
                    roomMap[i][j] = 15;
                }
                if (hasEastDoor() && hasWestDoor()) {
                    roomMap[i][j] = 14;
                }
                if (hasEastDoor() && hasNorthDoor()  && hasSouthDoor()) {
                    roomMap[i][j] = 11;
                }
                if (hasWestDoor() && hasNorthDoor()  && hasSouthDoor() && hasEastDoor()) {
                    roomMap[i][j] = 10;
                }
                if (hasWestDoor() && hasNorthDoor()  && hasSouthDoor()) {
                    roomMap[i][j] = 9;
                }
                if (hasEastDoor() && hasNorthDoor()  && hasWestDoor()) {
                    roomMap[i][j] = 7;
                }
                if (hasEastDoor()  && hasSouthDoor()  && hasWestDoor()) {
                    roomMap[i][j] = 12;
                }
            }
        }
        //roomIndex = 1;
        return roomMap;

    }
    public int getMapTiles(int index1, int index2) {
        return roomMap[index1][index2];
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
        System.out.print("["  + myWestDoor + myNorthDoor + mySouthDoor + myEastDoor + "]");
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(myWestDoor);
        sb.append(myNorthDoor);
        if (this.getInheritancePillar()) {
            sb.append("I");
        }
        if (this.getAbstractionPillar()) {
            sb.append("A");
        }
        if (this.getEncapsulationPillar()) {
            sb.append("E");
        }
        if (this.getPolymorphismPillar()) {
            sb.append("P");
        }
        if (this.getHasPotion()) {
            sb.append("p");
        }
        if (this.getIsEnter()) {
            sb.append("i");
        }
        if (this.getIsExit()) {
            sb.append("O");
        }
        if(this.getHasMonster()) {
            sb.append("M");
        }
        sb.append(mySouthDoor);
        sb.append(myEastDoor);
        sb.append("]");
        return sb.toString();
    }
    public boolean getEncapsulationPillar() {
        return encapsulationPillar;
    }
    public boolean getAbstractionPillar() {
        return abstractionPillar;
    }
    public boolean getInheritancePillar() {
        return inheritancePillar;
    }
    public boolean getPolymorphismPillar() {
        return polymorphismPillar;
    }
    public void setEncapsulationPillar(boolean encapsulationPillar) {
        this.encapsulationPillar = encapsulationPillar;
    }
    public void setAbstractionPillar(boolean abstractionPillar) {
        this.abstractionPillar = abstractionPillar;
    }
    public void setInheritancePillar(boolean inheritancePillar) {
        this.inheritancePillar = inheritancePillar;
    }
    public void setPolymorphismPillar(boolean polymorphismPillar) {

        this.polymorphismPillar = polymorphismPillar;
    }
    public boolean getHasMonster() {
        return hasMonster;
    }
    public void setHasMonster(boolean hasMonster) {
        this.hasMonster = hasMonster;
    }
    public boolean getHasPit() {
        return hasPit;
    }
    public void setHasPit(boolean hasPit) {
        this.hasPit = hasPit;
    }
    public void setHasPotion(boolean theHasPotion) {
        myHasPotion = theHasPotion;
    }
    public boolean getHasPotion() {
        return myHasPotion;
    }
    public boolean getIsEnter() {
        return myIsEnter;
    }
    public void setIsEnter(boolean myIsEnter) {
        this.myIsEnter = myIsEnter;
    }
    public boolean getIsExit() {
        return myIsExit;
    }
    public void setIsExit(boolean myIsExit) {
        this.myIsExit = myIsExit;
    }
    public String getMyNorthDoor() {
        return myNorthDoor;
    }
    public boolean hasNorthDoor() {
        if (!myNorthDoor.equals("^")) {
            return false;
        }
        return true;
    }
    public void setMyNorthDoor(String myNorthDoor) {
        this.myNorthDoor = myNorthDoor;
    }
    public String getMySouthDoor() {
        return mySouthDoor;
    }
    public boolean hasSouthDoor() {
        if (!mySouthDoor.equals("v")) {
            return false;
        }
        return true;
    }
    public void setMySouthDoor(String mySouthDoor) {
        this.mySouthDoor = mySouthDoor;
    }
    public String getMyEastDoor() {
        return myEastDoor;
    }
    public boolean hasEastDoor() {
        if (!myEastDoor.equals(">")) {
            return false;
        }
        return true;
    }
    public void setMyEastDoor(String myEastDoor) {
        this.myEastDoor = myEastDoor;
    }
    public String getMyWestDoor() {
        return myWestDoor;
    }
    public boolean hasWestDoor() {
        if (!myWestDoor.equals("<")) {
            return false;
        }
        return true;
    }
    public void setMyWestDoor(String myWestDoor) {
        this.myWestDoor = myWestDoor;
    }

    public void setMonster(Monster theMonster) {
        myMonster = theMonster;
        hasMonster = true;
        myMonster.setMyAlive(true);

    }
    public Monster getMyMonster() {
        return myMonster;
    }


}
