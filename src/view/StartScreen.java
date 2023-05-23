package view;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private void createAndShowUI() throws IOException {
        CardLayout cardLayout = new CardLayout();
        JPanel cards = new JPanel(cardLayout);

        JPanel startingScreen = new ImagePanel("/assets/image.jpeg");
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(newGameButton);
        buttonPanel.add(loadGameButton);
//        startingScreen.add(newGameButton);
//        startingScreen.add(loadGameButton);

        newGameButton.addActionListener(e -> {
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


    private class ImagePanel extends JPanel {
        private Image backgroundImage;

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