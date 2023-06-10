package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class contains the JFrame of the game over screen.
 */
class DeathScreen extends JFrame {
    /**
     * Field for the game over message
     */
    final private JLabel deathLabel;

    /**
     * Constructor for deathscreen class. Initialize all fields
     * and setup the display screen.
     * @param cards
     * @param cardLayout
     */
    public DeathScreen(JPanel cards, CardLayout cardLayout) {
        setTitle("Game Over");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        deathLabel = new JLabel("You Died.");
        deathLabel.setFont(new Font("Arial", Font.BOLD, 36));
        deathLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(deathLabel, BorderLayout.CENTER);
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }
}