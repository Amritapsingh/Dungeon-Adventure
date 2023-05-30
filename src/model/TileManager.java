package model;

import view.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GameScreen myGameScreen;
    Tiles[] tile;
    int[][] mapTiles;
    Dungeon dungeon;
    Room[][] myMaze;
    Image image;
    int[][] worldMap;


    public TileManager(GameScreen theGameScreen, Dungeon dungeon) {
        myGameScreen = theGameScreen;
        tile = new Tiles[10];
        mapTiles = new int[myGameScreen.worldRow][myGameScreen.worldCol];
        getTileImage();
        //loadMap("/assets/map2.txt");
        //createMap(dungeon.getMaze());
        myMaze = dungeon.getMaze();


    }
    // premake layouts
    // in room class pick a random layout
    // Boss rooms will be different
    // Feed entire maze into Tilemanager class
    // go through maze one room at a time
    // place items, monsters, pits etc
    //


//    public void loadMap(String filePath) {
//        try {
//            InputStream st = getClass().getResourceAsStream(filePath);
//            BufferedReader br = new BufferedReader(new InputStreamReader((st)));
//            int col = 0;
//            int row = 0;
//            while(col < myGameScreen.maxScreenCol && row < myGameScreen.maxScreenRow) {
//                String line = br.readLine();
//                while (col < myGameScreen.maxScreenCol) {
//                    String[] numbers = line.split(" ");
//                    int num = Integer.parseInt(numbers[col]);
//                    mapTiles[col][row] = num;
//                    col++;
//                }
//                if (col == myGameScreen.maxScreenCol) {
//                    col = 0;
//                    row++;
//                }
//            }
//            br.close();
//            } catch(Exception e){
//                e.printStackTrace();
//            }
//        }
    public void getTileImage() {
        try {
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/assets/grass00.png"));

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/assets/water01.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void createMap(Room[][] theMaze) {
        myMaze = theMaze;
        worldMap = new int[myGameScreen.worldCol][myGameScreen.worldRow];
        for (int i = 0; i < myMaze.length; i++) {
            for (int j = 0; j < myMaze[i].length; j++) {
                int rowIndex = i % 16;
                int colIndex = j % 12;
                myMaze[i][j].loadMap(myGameScreen);
                worldMap[i][j] = myMaze[i][j].getMapTiles(i,j);
            }
        }
//        for (Room[] room: myMaze) {
//            for (Room room1 : room) {
//                worldMap[myMaze.length] = room1.loadMap(myGameScreen);
//            }
//        }

    }
    public void draw(Graphics2D g2) {

        createMap(myMaze);
        int col = 0;
        int row = 0;
        //mapTiles = myMaze[0][0].loadMap(myGameScreen);
        int screenX;
        int screenY;
        while (col < myGameScreen.worldCol && row < myGameScreen.worldRow) {
            int worldX = col * myGameScreen.tileSize;
            int worldY = row * myGameScreen.tileSize;
            screenX = worldX - myGameScreen.worldX + myGameScreen.screenX;
            screenY = worldY - myGameScreen.worldY + myGameScreen.screenY;
            int tileNum = worldMap[col][row];
            g2.drawImage(tile[tileNum].image, screenX, screenY, myGameScreen.tileSize, myGameScreen.tileSize, null);
            col++;
            if (col == myGameScreen.worldCol) {
                col = 0;
                row++;
            }
        }
    }
//            }}
//        for (Room[] room: myMaze) {
//            for (Room room1: room) {
//                image = room1.getImage();
//                int worldCol = 0;
//                int worldRow = 0;
//                mapTiles = room1.loadMap(myGameScreen);
//                //g2.drawImage(image, 400, 200, myGameScreen.tileSize, myGameScreen.tileSize, null);
//                while(worldCol < myGameScreen.worldCol && worldRow < myGameScreen.worldRow) {
////                    mapTiles = room1.getMapTiles();
//                    int tileNum = mapTiles[myGameScreen.maxScreenCol - 1][myGameScreen.maxScreenRow - 1];
//                    int worldX = worldCol * myGameScreen.tileSize;
//                    int worldY = worldRow * myGameScreen.tileSize;
//                    int screenX = worldX - myGameScreen.worldX + myGameScreen.screenX;
//                    int screenY = worldY - myGameScreen.worldY + myGameScreen.screenY;
//                    g2.drawImage(tile[tileNum].image, screenX, screenY, myGameScreen.tileSize, myGameScreen.tileSize, null);
//                    worldCol++;
//                    if (worldCol == myGameScreen.worldCol) {
//                        worldCol = 0;
//                        worldRow++;
//                    }
//                }
//            }
//        }



//        for(int i = 0; i < myMaze.length; i++) {
//            for (int j = 0; j < myMaze[i].length; j++) {
//                int col = 0;
//                int row = 0;
//                int x = 0;
//                int y = 0;
//                while (col < myGameScreen.maxScreenCol && row < myGameScreen.maxScreenRow) {
//                    int tileNum = mapTiles[col][row];
//                    g2.drawImage(tile[tileNum].image, x, y, myGameScreen.tileSize, myGameScreen.tileSize, null);
//                    col++;
//                    x += myGameScreen.tileSize;
//                    if (col == myGameScreen.maxScreenCol) {
//                        col = 0;
//                        x = 0;
//                        row++;
//                        y += myGameScreen.tileSize;
//                    }
//                }
//            }
//        int col = 0;
//        int row = 0;
//        int x = 0;
//        int y = 0;
//        while(col < myGameScreen.maxScreenCol && row < myGameScreen.maxScreenRow) {
//            int tileNum = mapTiles[col][row];
//            g2.drawImage(tile[tileNum].image, x, y, myGameScreen.tileSize, myGameScreen.tileSize, null);
//            col++;
//            x += myGameScreen.tileSize;
//            if (col == myGameScreen.maxScreenCol) {
//                col = 0;
//                x = 0;
//                row++;
//                y += myGameScreen.tileSize;
//            }
//        }
//    }
}
