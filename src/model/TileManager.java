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
        tile = new Tiles[16];
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
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/assets/north.png"));
            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/assets/south.png"));
            // scale these up so they are an entire room instead of just one tile
            // implement battle screen?
            tile[1].isSolid = true;
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
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/assets/north+east+south.png"));

            tile[10] = new Tiles();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/assets/north+east+west+south.png"));

            tile[11] = new Tiles();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/assets/north+west+south.png"));

            tile[12] = new Tiles();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/assets/east+south+west.png"));

            tile[13] = new Tiles();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/assets/east+south.png"));

            tile[14] = new Tiles();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/assets/east+west.png"));

            tile[15] = new Tiles();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/assets/south+west.png"));


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
//        for (int k = 0; k < myMaze.length; k++) {
//            for (int l = 0; l < myMaze[0].length; l++) {
//                myMaze[k][l].loadMap(myGameScreen);
//                for (int i = 0; i < worldMap.length; i+= 12) {
//                    for (int j = 0; j < worldMap[0].length; j+=16) {
//                        worldMap[i][j] = myMaze[k][l].getMapTiles(i %12, j %16);
//                    }
//                }
//            }
//        }

    }
    public void draw(Graphics2D g2) {

        createMap(myMaze);
        int col = 0;
        int row = 0;
        int screenX;
        int screenY;
        while (col < myGameScreen.worldCol && row < myGameScreen.worldRow) {
            int worldX = col * myGameScreen.tileSize;
            int worldY = row * myGameScreen.tileSize;
            screenX = worldX - myGameScreen.worldX + myGameScreen.screenX;
            screenY = worldY - myGameScreen.worldY + myGameScreen.screenY;
            if (col /16 < myMaze[0].length && row / 12 < myMaze.length) {
            int tileNum = worldMap[row /12][col/16];
            //System.out.println(tileNum);
            g2.drawImage(tile[tileNum].image, screenX, screenY, myGameScreen.tileSize * 16 , myGameScreen.tileSize * 12, null); }
            col+= 16;
            if (col == myGameScreen.worldCol) {
                col = 0;
                row+= 12;
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
