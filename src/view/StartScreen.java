package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class StartScreen {
    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;
    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;
    /** A Factor for scaling the size of the GUI relative to the current screen size. */
    private static final int SCALE = 3;
    private JFrame myFrame = new JFrame("Dungeon Adventure");
    private JPanel buttonPanel = new JPanel();
    private GameScreen gameScreen = new GameScreen();
    private LoadScreen loadScreen = new LoadScreen();
    private JButton buttonOne = new JButton("Load saved game");
    private JButton buttonSecond = new JButton("New Game");
    private CardLayout cl = new CardLayout();

    public StartScreen() {
        buttonPanel.setLayout(cl);
        gameScreen.add(buttonOne);
        gameScreen.add(buttonSecond);
        gameScreen.setBackground(Color.BLUE);
        //loadScreen.setBackground(Color.GREEN);
        buttonPanel.add(gameScreen, "Game");
        buttonPanel.add(loadScreen, "Load");
        //buttonPanel.add(loadScreen, "2");
        //cl.show(buttonPanel, "Game");

        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(buttonPanel, "Game");
            }
        });

        buttonSecond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(buttonPanel, "Load");
                //gameScreen.newGame();
            }
        });
        myFrame.pack();
        myFrame.setSize(SCREEN_WIDTH / SCALE, SCREEN_HEIGHT / SCALE);
        myFrame.setLocation(SCREEN_WIDTH / 2 - myFrame.getWidth() / 2,
                SCREEN_HEIGHT / 2 - myFrame.getHeight() / 2);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.add(buttonPanel);
        myFrame.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartScreen();
            }
        });
    }

}