package model;

import view.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Graphics2D;
import java.io.IOException;

public class TileManager {
    GameScreen myGameScreen;
    Tiles[] tile;

    public TileManager(GameScreen myGameScreen) {
        this.myGameScreen = myGameScreen;
        tile = new Tiles[10];
        getTileImage();

    }
    public void getTileImage() {
        try {
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("tile1.png"));

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("tile2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(tile[0].image, 0, 0, myGameScreen.tileSize, myGameScreen.tileSize, null);
    }
}
