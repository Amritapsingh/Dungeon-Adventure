package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame {
    public StartScreen() {
        setTitle("Dungeon Adventure");
        setSize(768, 576);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    private void createAndShowUI() {
        // Create the CardLayout and the JPanel that will hold the cards
        CardLayout cardLayout = new CardLayout();
        JPanel cards = new JPanel(cardLayout);

        // Create the starting screen panel
        JPanel startingScreen = new JPanel();
        startingScreen.add(new JLabel("Game Starting Screen"));
        // Create buttons for the starting screen
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");

        // Add buttons to the starting screen panel
        startingScreen.add(newGameButton);
        startingScreen.add(loadGameButton);

        // Add action listeners to the buttons
        newGameButton.addActionListener(e -> {
            GameScreen gameScreen = new GameScreen(cards, cardLayout);
            // Show the new game view panel
            cards.add(gameScreen, "GameScreen");
            cardLayout.show(cards, "GameScreen");
            gameScreen.startNewGameThread();
        });
        loadGameButton.addActionListener(e -> {
            LoadScreen loadScreen = new LoadScreen(cards, cardLayout);
            // Show the load game view panel
            cards.add(loadScreen, "LoadScreen");
            cardLayout.show(cards, "LoadScreen");
        });

        // Add the panels to the cards panel
        cards.add(startingScreen, "StartingScreen");

        // Show the starting screen by default
        cardLayout.show(cards, "StartingScreen");

        // Add the cards panel to the JFrame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(cards, BorderLayout.CENTER);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            StartScreen app = new StartScreen();
            app.createAndShowUI();
            app.setVisible(true);
        });
    }


}