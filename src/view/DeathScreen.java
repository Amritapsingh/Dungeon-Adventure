package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DeathScreen extends JFrame {
    private JLabel deathLabel;

    public DeathScreen(JFrame parent) {
        setTitle("Death Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        deathLabel = new JLabel("You Died");
        deathLabel.setFont(new Font("Arial", Font.BOLD, 36));
        deathLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(deathLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                parent.dispose();
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