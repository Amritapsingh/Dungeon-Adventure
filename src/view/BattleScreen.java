package view;

import model.Hero;
import model.Monster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleScreen extends JFrame {
    private JTextArea battleLog;
    private JLabel playerHPLabel;
    private JLabel enemyHPLabel;
    private Hero myHero;
    private Monster myMonster;

    public BattleScreen(Hero theHero, Monster theMonster){
        setTitle("Dungeon Battle");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        myHero = theHero;
        myMonster = theMonster;

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playerHPLabel = new JLabel("Player HP: " + myHero.getMyCurrentHealth());
        enemyHPLabel = new JLabel("Enemy HP: " + myMonster.getMyCurrentHealth());
        topPanel.add(playerHPLabel);
        topPanel.add(enemyHPLabel);
        panel.add(topPanel, BorderLayout.NORTH);

        battleLog = new JTextArea(10, 30);
        battleLog.setEditable(false);
        battleLog.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(battleLog);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton attackButton = new JButton("Attack");
        attackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attackEnemy();
            }
        });

        JButton defendButton = new JButton("Defend");
        defendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                defend();
            }
        });

        JButton specialMoveButton = new JButton();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(attackButton);
        buttonPanel.add(defendButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void attackEnemy() {
        // Code for attacking the enemy
        addToBattleLog("Player attacks!");
        // Add more logic here
    }

    private void defend() {
        // Code for defending
        addToBattleLog("Player defends!");
        // Add more logic here
    }

    private void addToBattleLog(String message) {
        battleLog.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //new BattleScreen().setVisible(true);
            }
        });
    }
}
