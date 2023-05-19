package controller;

import model.Dungeon;
import model.Hero;
import view.GameScreen;

public class DungeonController {
    private Hero myHero;
    private GameScreen myGameScreen;


    public DungeonController() {
        //myGameScreen =
        Dungeon myDungeon = new Dungeon(myGameScreen.tileSize, myGameScreen.tileSize);
    }
    public void startGame() {

       // myHero = new Hero();
    }
}
