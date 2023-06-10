package controller;

import model.Hero;
import view.GameScreen;
import view.StartScreen;

import java.awt.*;
import java.io.IOException;

/**
 * Controller class to start program and run the game.
 *
 * @author Amrit Singh
 * @author Akil Turner-Richars
 * @author Jay Phommakhot
 * @version 09 June 2023
 */
public class DungeonController {
    /**
     * Hero object to be used in the game.
     */
    private Hero myHero;
    /**
     * GameScreen object to be used in the game.
     */
    private GameScreen myGameScreen;

    /**
     * Constructor for DungeonController class.
     */
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
