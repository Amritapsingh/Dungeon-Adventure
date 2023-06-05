package view;

import model.Hero;
import model.Warrior;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class StartScreen extends JFrame {
    Hero myHero;
    CardLayout cardLayout = new CardLayout();
    JPanel cards = new JPanel(cardLayout);
    public StartScreen() {
        setTitle("Dungeon Adventure");
        setSize(768, 576);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(Color.black);
    }

    public void createAndShowUI() throws IOException {


        JPanel startingScreen = new ImagePanel("/assets/image.jpeg");
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(newGameButton);
        buttonPanel.add(loadGameButton);

        newGameButton.addActionListener(e -> {
            JFrame charSelect;
            charSelect = charSelection();
            charSelect.setVisible(true);
        });
        loadGameButton.addActionListener(e -> {
            LoadScreen loadScreen = new LoadScreen(cards, cardLayout);
            cards.add(loadScreen, "LoadScreen");
            cardLayout.show(cards, "LoadScreen");
        });
        // Add the screens to the cards panel
        startingScreen.add(buttonPanel);
        cards.add(startingScreen, "StartingScreen");

        cardLayout.show(cards, "StartingScreen");
        getContentPane().add(cards);
    }

    public JFrame charSelection() {
        JFrame charSelect = new JFrame("Character Selection");
        charSelect.setSize(768, 576);
        charSelect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        charSelect.setLocationRelativeTo(null);
        charSelect.setResizable(false);
        charSelect.setBackground(Color.black);
        JPanel charSelectPanel = new JPanel();
        charSelectPanel.setLayout(new BoxLayout(charSelectPanel, BoxLayout.Y_AXIS));
        JButton warriorButton = new JButton("Warrior");
        warriorButton.addActionListener(e -> {
            HashMap<String, Integer> myInventory;
            myInventory = new HashMap<>();
            setHero(new Warrior("Warrior", 100, 100, 10, 90, 0.8, 2, true, 0.5, myInventory, 0.5));

            });
        JButton thiefButton = new JButton("Thief");
        JButton priestessButton = new JButton("Priestess");
        JButton easyButton = new JButton("Easy");
        easyButton.addActionListener(e -> {
            HashMap<String, Integer> myInventory;
            myInventory = new HashMap<>();
            setHero(new Warrior("Warrior", 100, 100, 10, 90, 0.8, 2, true, 0.5, myInventory, 0.5));
            charSelect.dispose();
            GameScreen gameScreen = new GameScreen(cards, cardLayout, getmyHero());
            cards.add(gameScreen, "GameScreen");
            cardLayout.show(cards, "GameScreen");
            gameScreen.startNewGameThread();
            });

        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.Y_AXIS));
        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);
        charSelectPanel.add(difficultyPanel);

        charSelectPanel.add(warriorButton);
        charSelectPanel.add(thiefButton);
        charSelectPanel.add(priestessButton);
        charSelect.add(charSelectPanel);
        charSelect.setVisible(true);

        return charSelect;
    }
    public void setHero(Hero theHero) {
        myHero = theHero;
    }
    public Hero getmyHero() {
        return myHero;
    }




    private static class ImagePanel extends JPanel {
        private final Image backgroundImage;

        public ImagePanel(String filename) throws IOException {
            backgroundImage = ImageIO.read(getClass().getResource(filename));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image scaledImage = backgroundImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, this);        }
    }
}