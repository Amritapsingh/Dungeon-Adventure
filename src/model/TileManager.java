package model;

import view.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GameScreen myGameScreen;
    Tiles[] tile;
    int mapTiles[][];

    public TileManager(GameScreen myGameScreen) {
        this.myGameScreen = myGameScreen;
        tile = new Tiles[10];
        mapTiles = new int[myGameScreen.maxScreenCol][myGameScreen.maxScreenRow];
        getTileImage();
        loadMap("/assets/map2.txt");

    }
    public void loadMap(String filePath) {
        try {
            InputStream st = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader((st)));
            int col = 0;
            int row = 0;
            while(col < myGameScreen.maxScreenCol && row < myGameScreen.maxScreenRow) {
                String line = br.readLine();
                while (col < myGameScreen.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTiles[col][row] = num;
                    col++;
                    System.out.println(num);
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
        }
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
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < myGameScreen.maxScreenCol && row < myGameScreen.maxScreenRow) {
            int tileNum = mapTiles[col][row];
            g2.drawImage(tile[tileNum].image, x, y, myGameScreen.tileSize, myGameScreen.tileSize, null);
            col++;
            x += myGameScreen.tileSize;
            if (col == myGameScreen.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += myGameScreen.tileSize;
            }
        }
    }
}
