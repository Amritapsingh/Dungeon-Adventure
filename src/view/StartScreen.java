package view;

import model.Hero;
import model.Priestess;
import model.Thief;
import model.Warrior;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class represents the starting screen of the game.
 */
public class StartScreen extends JFrame {
    /**
     * Field for the hero selected by the user
     */
    Hero myHero;
    /**
     * Field for the cardlayout
     */
    CardLayout myCardLayout = new CardLayout();
    /**
     * Field for the cards to switch betwen
     */
    JPanel myCards = new JPanel(myCardLayout);

    /**
     * Constructor to setup screen and initialize all fields.
     */
    public StartScreen() {
        setTitle("Dungeon Adventure");
        setSize(768, 576);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(Color.black);
        HashMap<String, Integer> myInventory;
        myInventory = new HashMap<>();
        // if no character is selected, default to warrior
        setHero(new Warrior("Warrior", 100, 100, 10, 90, 0.8, 2, true, 0.5, myInventory, 0.5));
    }

    /**
     * Method to create and setup the entire UI for the
     * starting screen.
     * @throws IOException if image cannot be found
     */
    public void createAndShowUI() throws IOException {
        JPanel startingScreen = new ImagePanel("/assets/image.jpeg");
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton helpButton = new JButton("Help");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(newGameButton);
        buttonPanel.add(loadGameButton);
        buttonPanel.add(helpButton);
        // add help button
        helpButton.addActionListener(e -> {
            String theInstructions = "The objective of the game is to find all four pillars of OO and escape the maze.\n";
            theInstructions += "Use WASD to move around the maze and P to use potions collected. You can view the number of potions you've" +
                    " collected in the console.";
            JOptionPane.showMessageDialog(this, theInstructions);

        });
        // add new game button
        newGameButton.addActionListener(e -> {
            JFrame charSelect;
            charSelect = charSelection();
            charSelect.setVisible(true);
        });
        // add load game button
        loadGameButton.addActionListener(e -> {
            LoadScreen loadScreen = new LoadScreen(myCards, myCardLayout);
            myCards.add(loadScreen, "LoadScreen");
            myCardLayout.show(myCards, "LoadScreen");
        });
        // Add the screens to the myCards panel
        startingScreen.add(buttonPanel);
        myCards.add(startingScreen, "StartingScreen");

        myCardLayout.show(myCards, "StartingScreen");
        getContentPane().add(myCards);
    }

    /**
     * JFrame for user character selection and difficulty selection
     * @return the JFrame
     */
    public JFrame charSelection() {
        JFrame charSelect = new JFrame("Character Selection");
        charSelect.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        charSelect.setLocationRelativeTo(null);
        charSelect.setResizable(false);
        charSelect.setBackground(Color.black);
        JPanel charSelectPanel = new JPanel();
        charSelectPanel.setLayout(new FlowLayout());
        JButton warriorButton = new JButton("Warrior");
        warriorButton.addActionListener(e -> {
            HashMap<String, Integer> myInventory;
            myInventory = new HashMap<>();
            setHero(new Warrior("Warrior", 100, 100, 10, 90, 0.8, 2, true, 0.5, myInventory, 0.5));
            });
        JButton thiefButton = new JButton("Thief");
        thiefButton.addActionListener(e -> {
            HashMap<String, Integer> myInventory;
            myInventory = new HashMap<>();
            setHero(new Thief("Thief", 100, 100, 10, 90, 0.8, 2, true, 0.5, myInventory, 0.5));
        });
        JButton priestessButton = new JButton("Priestess");
        priestessButton.addActionListener(e -> {
            HashMap<String, Integer> myInventory;
            myInventory = new HashMap<>();
            setHero(new Priestess("Priestess", 100, 100, 70, 90, 0.95, 2, true, 0.5, myInventory, 0.5));
        });
        JButton easyButton = new JButton("Easy");
        easyButton.addActionListener(e -> {
            HashMap<String, Integer> myInventory;
            myInventory = new HashMap<>();
            charSelect.dispose();
            GameScreen gameScreen = new GameScreen(myCards, myCardLayout, getMyHero(), 3, 3, "Easy");
            myCards.add(gameScreen, "GameScreen");
            myCardLayout.show(myCards, "GameScreen");
            gameScreen.startNewGameThread();
            });

        JButton mediumButton = new JButton("Medium");
        mediumButton.addActionListener(e -> {
            HashMap<String, Integer> myInventory;
            myInventory = new HashMap<>();
            charSelect.dispose();
            GameScreen gameScreen = new GameScreen(myCards, myCardLayout, getMyHero(), 5, 5, "Medium");
            myCards.add(gameScreen, "GameScreen");
            myCardLayout.show(myCards, "GameScreen");
            gameScreen.startNewGameThread();
        });
        JButton hardButton = new JButton("Hard");
        hardButton.addActionListener(e -> {
            HashMap<String, Integer> myInventory;
            myInventory = new HashMap<>();
            charSelect.dispose();
            GameScreen gameScreen = new GameScreen(myCards, myCardLayout, getMyHero(), 8, 8, "Hard");
            myCards.add(gameScreen, "GameScreen");
            myCardLayout.show(myCards, "GameScreen");
            gameScreen.startNewGameThread();
        });
        JLabel charSelection = new JLabel("Please select a character and difficulty level");
        charSelection.setHorizontalAlignment(JLabel.CENTER);
        charSelect.add(charSelection, BorderLayout.NORTH);
        charSelectPanel.add(warriorButton);
        charSelectPanel.add(thiefButton);
        charSelectPanel.add(priestessButton);
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new FlowLayout());
        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);
        charSelectPanel.add(difficultyPanel);

        charSelect.add(charSelectPanel);
        charSelect.setVisible(true);
        charSelect.pack();

        return charSelect;
    }

    /**
     * Field for setting the hero
     * @param theHero the hero
     */
    public void setHero(Hero theHero) {
        myHero = theHero;
    }

    /**
     * Field for getting the hero
     * @return the hero
     */
    public Hero getMyHero() {
        return myHero;
    }

    /**
     * Private inner class for the start screen background
     */
    private static class ImagePanel extends JPanel {
        /**
         * Field for background image
         */
        private final Image backgroundImage;

        /**
         * Constructor to load image into field.
         * @param filename the filename
         * @throws IOException exception for IO
         */
        public ImagePanel(String filename) throws IOException {
            backgroundImage = ImageIO.read(getClass().getResource(filename));
        }

        /**
         * Method to draw image and scale it to the screen
         * @param g the <code>Graphics</code> object to protect
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image scaledImage = backgroundImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, this);        }
    }
}