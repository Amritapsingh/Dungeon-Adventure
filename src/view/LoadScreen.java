package view;

import javax.swing.*;
import javax.swing.JLabel;
import java.awt.*;

public class LoadScreen extends JPanel {
    public LoadScreen(JPanel cards, CardLayout cardLayout) {
        JLabel label = new JLabel("Loading...");
        add(label);
        setVisible(true);
        setSize(100, 100);
        setLocation(0, 0);
        label.setSize(100, 100);
        label.setLocation(0, 0);
        label.setVisible(true);
        setVisible(true);
        repaint();
        revalidate();
        JButton backButton = new JButton("Back");
        add(backButton, BorderLayout.SOUTH);
        backButton.addActionListener(e -> cardLayout.show(cards, "StartingScreen"));
    }
}