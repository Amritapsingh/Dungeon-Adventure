package controller;

import model.Hero;
import view.GameScreen;
import view.StartScreen;

import java.awt.*;
import java.io.IOException;

public class DungeonController {
    private Hero myHero;
    private GameScreen myGameScreen;


    private DungeonController() {
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
