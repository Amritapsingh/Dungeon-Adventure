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
        mapTiles = new int[myGameScreen.worldCol][myGameScreen.maxScreenRow];
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
                    mapTiles[col][row] = num;
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
    public int getMapTiles(int index1, int index2) {
        return mapTiles[index1][index2];
    }
    public boolean getIsVisited() {
        return myIsVisited;
    }
    public void setIsVisited(final boolean isVisited) {
        myIsVisited = isVisited;
    }
    public Image getImage() {
        return image;
    }


    public ArrayList<Room> getRoomNeighbors() { return roomNeighbors; }
    public void display() {
        //StringBuilder graphicalRepr = new StringBuilder();
        System.out.print("["  + myWestDoor + myNorthDoor + mySouthDoor + myEastDoor + "]");
    }
    public String toString() {
        return "[" + myWestDoor + myNorthDoor + mySouthDoor + myEastDoor + "]";
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

}
