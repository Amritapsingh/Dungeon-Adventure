package view;

import javax.swing.*;
import javax.swing.JLabel;

public class LoadScreen extends JPanel {
    public LoadScreen() {
        JLabel label = new JLabel("Loading...");
        add(label);
        setVisible(true);
        setLayout(null);
        setSize(100, 100);
        setLocation(0, 0);
        label.setSize(100, 100);
        label.setLocation(0, 0);
        label.setVisible(true);
        setVisible(true);
        repaint();
        revalidate();
    }
}