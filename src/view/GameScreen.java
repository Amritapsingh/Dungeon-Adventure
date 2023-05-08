package view;

import javax.swing.*;
import java.awt.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameScreen extends JPanel {
    // constants to capture screen dimensions
    /**
     * A ToolKit.
     */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    /**
     * The Dimension of the screen.
     */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    /**
     * The width of the screen.
     */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;
    /**
     * The height of the screen.
     */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;
    /**
     * A Factor for scaling the size of the GUI relative to the current screen size.
     */
    private static final int SCALE = 3;
//    /**
//     * The JFrame for the program.
//     */
//    private final JFrame myFrame;
    /**
     * Replaces the default JFrame icon.
     */
    private final ImageIcon myLogo = new ImageIcon("");

    public GameScreen() {
        super();
        setBackground(Color.red);
        setVisible(true);

    }

    public void newGame() {
    }
}

