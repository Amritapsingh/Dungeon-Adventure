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

    private JButton attackButton;
    private JButton defendButton;
    private JButton specialMoveButton;
    boolean playerTurn;
    JPanel cards;
    CardLayout cardLayout;


    public BattleScreen(Hero theHero, Monster theMonster, JPanel cards, CardLayout cardLayout) {
        setTitle("Dungeon Battle");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        myHero = theHero;
        myMonster = theMonster;
        this.cards = cards;
        this.cardLayout = cardLayout;
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playerHPLabel = new JLabel("Player HP: " + myHero.getMyCurrentHealth());
        enemyHPLabel = new JLabel("Monster HP: " + myMonster.getMyCurrentHealth());
        topPanel.add(playerHPLabel);
        topPanel.add(enemyHPLabel);
        panel.add(topPanel, BorderLayout.NORTH);

        battleLog = new JTextArea(10, 30);
        battleLog.setEditable(false);
        battleLog.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(battleLog);
        panel.add(scrollPane, BorderLayout.CENTER);
        attackButton = new JButton("Attack");
        attackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (playerTurn) {
                    attackEnemy();
                    playerTurn = false;
                    disablePlayerButtons();
                    updateMonsterHP();
                    performEnemyTurn();
                    updatePlayerHP();
                }
            }
        });

        defendButton = new JButton("Defend");
        defendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (playerTurn) {
                    defend();
                    playerTurn = false;
                    disablePlayerButtons();
                    updateMonsterHP();
                    performEnemyTurn();
                    updatePlayerHP();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(attackButton);
        buttonPanel.add(defendButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        pack();
        setLocationRelativeTo(null);

        playerTurn = true;
        specialMoveButton = new JButton();
        // implement later

    }

    private void updateMonsterHP() {
        enemyHPLabel.setText("Monster HP: " + myMonster.getMyCurrentHealth());
    }

    private void updatePlayerHP() {
        playerHPLabel.setText("Player HP: " + myHero.getMyCurrentHealth());
    }

    private void attackEnemy() {
        // Code for attacking the enemy
        addToBattleLog("Player attacks!");
        // Add more logic here

        // Check if the enemy has died
        int damageTaken = myMonster.getMyCurrentHealth() - myHero.regularAttack(myMonster.getMyCurrentHealth(), myHero.getMyChanceToHit());
        myMonster.setMyCurrentHealth(myMonster.getMyCurrentHealth() - damageTaken);
        addToBattleLog("Player does " + damageTaken + " damage!");
        if (myMonster.getMyCurrentHealth() <= 0) {
            addToBattleLog("Player defeats the enemy!");
            disablePlayerButtons();
            dispose();
            myMonster.setMyAlive(false);
        }
    }

    private void defend() {
        // Code for defending
        addToBattleLog("Player defends!");
        // Add more logic here

        // Enemy's turn
        // ...
    }

    private void performEnemyTurn() {
        // Perform enemy's turn logic here
        addToBattleLog("Monster attacks!");
        int damageTaken = myHero.getMyCurrentHealth() - myMonster.regularAttack(myHero.getMyCurrentHealth(), myMonster.getMyChanceToHit());
        myHero.setMyCurrentHealth(myHero.getMyCurrentHealth() - damageTaken);
        addToBattleLog("Monster does " + damageTaken + " damage!");
        if (myHero.getMyCurrentHealth() <= 0) {
            addToBattleLog("Player has been defeated!");
            disablePlayerButtons();
            dispose();
            DeathScreen  deathScreen = new DeathScreen(cards, cardLayout);
            deathScreen.setVisible(true);
        }

        // Player's turn
        playerTurn = true;
        enablePlayerButtons();
    }

    private void disablePlayerButtons() {
        attackButton.setEnabled(false);
        defendButton.setEnabled(false);
    }

    private void enablePlayerButtons() {
        attackButton.setEnabled(true);
        defendButton.setEnabled(true);
    }

    private void addToBattleLog(String message) {
        battleLog.append(message + "\n");
    }

}
