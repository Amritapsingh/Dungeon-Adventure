package controller;

import model.Dungeon;
import model.Hero;
import view.GameScreen;
import view.StartScreen;

import java.awt.*;
import java.io.IOException;

public class DungeonController {
    private Hero myHero;
    private GameScreen myGameScreen;


    public DungeonController() {
        Dungeon myDungeon = new Dungeon(5, 5);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            StartScreen app = new StartScreen();
            try {
                app.createAndShowUI();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            app.setVisible(true);
        });
    }
}
