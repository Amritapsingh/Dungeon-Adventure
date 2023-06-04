package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StartScreen extends JFrame {
    public StartScreen() {
        setTitle("Dungeon Adventure");
        setSize(768, 576);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(Color.black);
    }

    public void createAndShowUI() throws IOException {
        CardLayout cardLayout = new CardLayout();
        JPanel cards = new JPanel(cardLayout);

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
            GameScreen gameScreen = new GameScreen(cards, cardLayout);
            cards.add(gameScreen, "GameScreen");
            cardLayout.show(cards, "GameScreen");
            gameScreen.startNewGameThread();
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
        JButton mageButton = new JButton("Thief");
        JButton rogueButton = new JButton("Priestess");
        charSelectPanel.add(warriorButton);
        charSelectPanel.add(mageButton);
        charSelectPanel.add(rogueButton);
        charSelect.add(charSelectPanel);
        charSelect.setVisible(true);
        return charSelect;
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