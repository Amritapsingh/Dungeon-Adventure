package model;

import view.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

public class TileManager implements Serializable {
    static GameScreen myGameScreen;
    static Tiles[] tile;
    static int[][] mapTiles;
    static Room[][] myMaze;
    static int[][] worldMap;


    public TileManager(GameScreen theGameScreen, Dungeon dungeon) {
        myGameScreen = theGameScreen;
        tile = new Tiles[20];
        mapTiles = new int[myGameScreen.worldRow][myGameScreen.worldCol];
        getTileImage();
        myMaze = dungeon.getMaze();
    }
    public void getTileImage() {
        try {
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/assets/north.png"));
            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/assets/south.png"));

            tile[2] = new Tiles();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/assets/east.png"));

            tile[3] = new Tiles();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/assets/west.png"));

            tile[4] = new Tiles();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/assets/north+east.png"));

            tile[5] = new Tiles();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/assets/east+south+west.png"));

            tile[6] = new Tiles();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/assets/north+west.png"));

            tile[7] = new Tiles();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/assets/north+east+west.png"));

            tile[8] = new Tiles();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/assets/north+south.png"));

            tile[9] = new Tiles();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/assets/north+west+south.png"));

            tile[10] = new Tiles();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/assets/north+east+west+south.png"));

            tile[11] = new Tiles();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/assets/north+east+south.png"));

            tile[12] = new Tiles();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/assets/east+south+west.png"));

            tile[13] = new Tiles();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/assets/east+south.png"));

            tile[14] = new Tiles();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/assets/east+west.png"));

            tile[15] = new Tiles();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/assets/south+west.png"));

            tile[16] = new Tiles();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/assets/playerSprite.png"));

            tile[17] = new Tiles();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/assets/impSprite.png"));


        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void createMap(Room[][] theMaze) {
        myMaze = theMaze;
        worldMap = new int[myMaze.length][myMaze[0].length];
        for (int i = 0; i < myMaze.length; i++) {
            for (int j = 0; j < myMaze[0].length; j++) {
                myMaze[i][j].generateRoom(myGameScreen);
                worldMap[i][j] = myMaze[i][j].getMapTiles(i, j);
            }
        }
    }
    public void draw(Graphics2D g2) {

        createMap(myMaze);
        int col = 0;
        int row = 0;
        int screenX;
        int screenY;
        int count = 0;
        while (col < myGameScreen.worldCol && row < myGameScreen.worldRow) {
            int worldX = col * myGameScreen.tileSize;
            int worldY = row * myGameScreen.tileSize;
            screenX = worldX - myGameScreen.worldX + myGameScreen.screenX;
            screenY = worldY - myGameScreen.worldY + myGameScreen.screenY;
            if (col / 16 < myMaze[0].length && row / 12 < myMaze.length) {
                int tileNum = worldMap[row /12][col/16];
                if (myMaze[row / 12][col/ 16].getHasMonster()) {
                    Monster myMonster = myMaze[row / 12][col/ 16].getMyMonster();
                    if (myMonster.getMyAlive()) {
                        g2.setColor(Color.red);
                        Rectangle hitBox = new Rectangle(screenX + 280, screenY + 120, myGameScreen.tileSize * 4, myGameScreen.tileSize * 4);
                        g2.draw(hitBox);
                        myGameScreen.combatCheck(hitBox, myMonster);
                    }
                }
                if (myMaze[row / 12][col/ 16].getHasPotion()) {
                    g2.setColor(Color.black);
                    Rectangle hitBox = new Rectangle(screenX + 20, screenY + 50, myGameScreen.tileSize * 16 - 50, myGameScreen.tileSize * 12 - 50);
                    g2.draw(hitBox);
                    myGameScreen.potionCheck(hitBox, row, col);
                }
                if (myMaze[row / 12][col/ 16].getHasPit()) {
                    g2.setColor(Color.black);
                    Rectangle hitBox = new Rectangle(screenX + 20, screenY + 50, myGameScreen.tileSize * 16 - 50, myGameScreen.tileSize * 12 - 50);
                    g2.draw(hitBox);
                    myGameScreen.pitCheck(hitBox, row, col);
                }
                if (myMaze[row / 12][col/ 16].hasNorthDoor()) {
                    g2.setColor(Color.blue);
                    Rectangle rect = new Rectangle(screenX + 50, screenY + 50, (myGameScreen.tileSize * 6) - 30, (myGameScreen.tileSize * 3) - 100);
                    g2.draw(rect);
                    Rectangle rect1 = new Rectangle(screenX + (myGameScreen.tileSize * 8) + 60, screenY + 50, (myGameScreen.tileSize * 6) - 30, (myGameScreen.tileSize * 3) - 100);
                    g2.draw(rect1);
                    myGameScreen.checkCollisionNorth(rect, rect1);
                }
                if (myMaze[row / 12][col/ 16].hasSouthDoor()) {
                    g2.setColor(Color.green);
                    Rectangle rect = new Rectangle(screenX + 50, screenY + (myGameScreen.tileSize * 12) - 90, (myGameScreen.tileSize * 6) - 30, (myGameScreen.tileSize * 3) - 100);
                    g2.draw(rect);
                    Rectangle rect1 = new Rectangle(screenX + (myGameScreen.tileSize * 8) + 60, screenY + (myGameScreen.tileSize * 12) - 90, (myGameScreen.tileSize * 6) - 30, (myGameScreen.tileSize * 3) - 100);
                    g2.draw(rect1);
                    myGameScreen.checkCollisionSouth(rect, rect1);
                }
                if (myMaze[row / 12][col/ 16].hasEastDoor()) {
                    g2.setColor(Color.yellow);
                    Rectangle rect = new Rectangle(screenX + (myGameScreen.tileSize * 16) - 110 , screenY + 50, (myGameScreen.tileSize * 3) - 90, (myGameScreen.tileSize * 6) - 110);
                    g2.draw(rect);
                    Rectangle rect1 = new Rectangle(screenX + (myGameScreen.tileSize * 16) - 110, screenY + (myGameScreen.tileSize * 8) - 40, (myGameScreen.tileSize * 3) - 90, (myGameScreen.tileSize * 6) - 100);
                    g2.draw(rect1);
                    myGameScreen.checkCollisionEast(rect, rect1);
                }
                if (myMaze[row / 12][col/ 16].hasWestDoor()) {
                    g2.setColor(Color.orange);
                    Rectangle rect = new Rectangle(screenX + 50, screenY + 50, (myGameScreen.tileSize * 3) - 100, (myGameScreen.tileSize * 6) - 110);
                    g2.draw(rect);
                    Rectangle rect1 = new Rectangle(screenX + 50, screenY + (myGameScreen.tileSize * 8) - 40, (myGameScreen.tileSize * 3) - 90, (myGameScreen.tileSize * 6) - 100);
                    g2.draw(rect1);
                    myGameScreen.checkCollisionWest(rect, rect1);
                }
                if (!myMaze[row / 12][col/ 16].hasNorthDoor()){
                    g2.setColor(Color.red);
                    Rectangle rect = new Rectangle(screenX + 50, screenY + 50, (myGameScreen.tileSize * 16) - 100, (myGameScreen.tileSize * 3) - 100);
                    g2.draw(rect);
                    myGameScreen.checkCollisionNorth(rect, rect);
                }
                if (!myMaze[row / 12][col/ 16].hasSouthDoor()){
                    g2.setColor(Color.red);
                    Rectangle rect = new Rectangle(screenX + 50, screenY + (myGameScreen.tileSize * 12) - 90, (myGameScreen.tileSize * 16) - 100, (myGameScreen.tileSize * 3) - 100);
                    g2.draw(rect);
                    myGameScreen.checkCollisionSouth(rect, rect);
                }
                if (!myMaze[row / 12][col/ 16].hasEastDoor()){
                    g2.setColor(Color.red);
                    Rectangle rect = new Rectangle(screenX + (myGameScreen.tileSize * 16) - 110 , screenY + 50, (myGameScreen.tileSize * 3) - 100, (myGameScreen.tileSize * 12) - 100);
                    g2.draw(rect);
                    myGameScreen.checkCollisionEast(rect, rect);
                }
                if (!myMaze[row / 12][col/ 16].hasWestDoor()){
                    g2.setColor(Color.red);
                    Rectangle rect = new Rectangle(screenX + 50, screenY + 50, (myGameScreen.tileSize * 3) - 100, (myGameScreen.tileSize * 12) - 100);
                    g2.draw(rect);
                    myGameScreen.checkCollisionWest(rect, rect);
                }
                g2.drawImage(tile[tileNum].image, screenX, screenY, myGameScreen.tileSize * 16 , myGameScreen.tileSize * 12, null);


                if (myMaze[row/12][col/16].getAbstractionPillar()) {
                    g2.setColor(Color.blue);
                    g2.fillRect(screenX + 350, screenY + 175, myGameScreen.tileSize, myGameScreen.tileSize);
                    g2.setColor(Color.white);
                    g2.drawString("Abstraction Pillar", screenX + 350, screenY + 220);
                    if(myMaze[row/12][col/16].getHasMonster()) {
                        Monster monster = myMaze[row / 12][col / 16].getMyMonster();
                        if (monster.getMyAlive()) {
                            g2.drawImage(tile[17].image, screenX + 340, screenY + 150, myGameScreen.tileSize * 3, myGameScreen.tileSize * 3, null);
                        }
                    }
                    Rectangle rect = new Rectangle(screenX + 350, screenY + 175, myGameScreen.tileSize, myGameScreen.tileSize);
                    myGameScreen.checkPillarCollision(rect, row/12, col/16);
                }
                if (myMaze[row/12][col/16].getEncapsulationPillar()) {
                    g2.setColor(Color.green);
                    g2.fillRect(screenX + 350, screenY + 175, myGameScreen.tileSize, myGameScreen.tileSize);
                    g2.setColor(Color.white);
                    g2.drawString("Encapsulation Pillar", screenX + 350, screenY + 220);
                    if(myMaze[row/12][col/16].getHasMonster()) {
                        Monster monster = myMaze[row / 12][col / 16].getMyMonster();
                        if (monster.getMyAlive()) {
                            g2.drawImage(tile[17].image, screenX + 340, screenY + 150, myGameScreen.tileSize * 3, myGameScreen.tileSize * 3, null);
                        }
                    }
                    Rectangle rect = new Rectangle(screenX + 350, screenY + 175, myGameScreen.tileSize, myGameScreen.tileSize);
                    myGameScreen.checkPillarCollision(rect, row/12, col/16);
                }
                if (myMaze[row/12][col/16].getInheritancePillar()) {
                    g2.setColor(Color.red);
                    g2.fillRect(screenX + 350, screenY + 175, myGameScreen.tileSize, myGameScreen.tileSize);
                    g2.setColor(Color.white);
                    g2.drawString("Inheritance Pillar", screenX + 350, screenY + 220);
                    if(myMaze[row/12][col/16].getHasMonster()) {
                        Monster monster = myMaze[row / 12][col / 16].getMyMonster();
                        if (monster.getMyAlive()) {
                            g2.drawImage(tile[17].image, screenX + 340, screenY + 150, myGameScreen.tileSize * 3, myGameScreen.tileSize * 3, null);
                        }
                    }
                    Rectangle rect = new Rectangle(screenX + 350, screenY + 175, myGameScreen.tileSize, myGameScreen.tileSize);
                    myGameScreen.checkPillarCollision(rect, row/12, col/16);
                }
                if (myMaze[row/12][col/16].getPolymorphismPillar()) {
                    g2.setColor(Color.white);
                    g2.fillRect(screenX + 350, screenY + 175, myGameScreen.tileSize, myGameScreen.tileSize);
                    g2.setColor(Color.green);
                    g2.drawString("Polymorphism Pillar", screenX + 350, screenY + 220);
                    if(myMaze[row/12][col/16].getHasMonster()) {
                        Monster monster = myMaze[row / 12][col / 16].getMyMonster();
                        if (monster.getMyAlive()) {
                            g2.drawImage(tile[17].image, screenX + 340, screenY + 150, myGameScreen.tileSize * 3, myGameScreen.tileSize * 3, null);
                        }
                    }
                    Rectangle rect = new Rectangle(screenX + 350, screenY + 175, myGameScreen.tileSize, myGameScreen.tileSize);
                    myGameScreen.checkPillarCollision(rect, row/12, col/16);
                }
                if (myGameScreen.pillarCount == 4 && myMaze[row/12][col/16].getIsExit()) {
                    g2.setColor(Color.white);
                    g2.drawString("Exit", screenX + 360, screenY + 210);
                    g2.setColor(Color.green);
                    Rectangle rect = new Rectangle(screenX + 350, screenY + 175, myGameScreen.tileSize, myGameScreen.tileSize);
                    g2.draw(rect);
                    myGameScreen.checkPillarCollision(rect, row / 12, col / 16);
                }

            }
            col+= 16;
            if (col == myGameScreen.worldCol) {
                col = 0;
                row+= 12;
            }
        }
        String myHealth = "Health: " + myGameScreen.myHero.getMyCurrentHealth();
        g2.setColor(Color.black);
        g2.fillRect(myGameScreen.screenX + 295, myGameScreen.screenY - 240, 80, 28);
        g2.setColor(Color.green);
        g2.drawString(myHealth, myGameScreen.screenX + 300, myGameScreen.screenY - 220);
        Rectangle rect = new Rectangle(myGameScreen.screenX + 300, myGameScreen.screenY - 200, myGameScreen.tileSize, myGameScreen.tileSize / 12 *myGameScreen.myHero.getMyCurrentHealth());
        g2.drawRect(myGameScreen.screenX + 300, myGameScreen.screenY - 200, myGameScreen.tileSize, myGameScreen.tileSize * 8);
        g2.fill(rect);
    }
    public Tiles[] getTile() {
        return tile;
    }
}
