package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;
import java.util.Random;

/**
 * This class contains the JFrame for displaying
 * the battle functions during combat.
 *
 * @author Amrit Singh
 * @version 09 June 2023
 */
public class BattleScreen extends JFrame {
    /**
     * Field for battlelog
     */
    final private JTextArea battleLog;
    /**
     * Field for playerhp
     */
    final private JLabel playerHPLabel;
    /**
     * Field for enemyHP
     */
    final private JLabel enemyHPLabel;
    /**
     * Field for the Hero
     */
    final private Hero myHero;
    /**
     * Field for the monster
     */
    final private Monster myMonster;
    /**
     * Field for the attack button on the GUI
     */

    final private JButton attackButton;
    /**
     * Field for the defend button
     */
    final private JButton defendButton;
    /**
     * Field for the potion button
     */
    final private JButton potionButton;
    /**
     * Field for the special move button
     */
    final private JButton specialMoveButton;
    /**
     * Field to track turns
     */
    boolean playerTurn;
    /**
     * Field to track if player is defending
     */
    boolean playerDefending;
    /**
     * Field for the cards
     */
    JPanel cards;
    /**
     * Field for the card layout
     */
    CardLayout cardLayout;

    /**
     * Constructor for the battle screen. Initialize fields
     * @param theHero passes the hero
     * @param theMonster passes the monster
     * @param cards passes the cards
     * @param cardLayout passes the layout
     */
    public BattleScreen(Hero theHero, Monster theMonster, JPanel cards, CardLayout cardLayout) {
        setTitle("Dungeon Battle");
        this.addWindowListener(new WindowAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                myMonster.fightCount++;
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        setResizable(false);
        myHero = theHero;
        myMonster = theMonster;
        this.cards = cards;
        this.cardLayout = cardLayout;
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        // set up hp labels
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playerHPLabel = new JLabel("Player HP: " + myHero.getMyCurrentHealth());
        enemyHPLabel = new JLabel(myMonster.getMyName() + " HP: " + myMonster.getMyCurrentHealth());
        topPanel.add(playerHPLabel);
        topPanel.add(enemyHPLabel);
        panel.add(topPanel, BorderLayout.NORTH);
        // setup battle log
        battleLog = new JTextArea(10, 30);
        battleLog.setEditable(false);
        battleLog.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(battleLog);
        panel.add(scrollPane, BorderLayout.CENTER);
        // add attack button
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
        // add defend button
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
        // add potion button
        potionButton = new JButton("Potion");
        potionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (playerTurn) {
                    usePotion();
                    playerTurn = false;
                    disablePlayerButtons();
                    updateMonsterHP();
                    performEnemyTurn();
                    updatePlayerHP();
                }
            }
        });
        // add special move
        specialMoveButton = new JButton("Special Move");
        specialMoveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (playerTurn) {
                    specialMove();
                    playerTurn = false;
                    disablePlayerButtons();
                    updateMonsterHP();
                    performEnemyTurn();
                    updatePlayerHP();
                }
            }});
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(attackButton);
        buttonPanel.add(defendButton);
        buttonPanel.add(specialMoveButton);
        buttonPanel.add(potionButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        playerTurn = true;
    }

    /**
     * Method to use the potion button
     */
    private void usePotion() {
        if (myHero.getPotionCount() > 0) {
            addToBattleLog("Player uses a potion!");
            if (myHero.getMyCurrentHealth() == 100) {
                addToBattleLog("Player is already at full health!");
            }

            int healedValue = myHero.usePotion();
            addToBattleLog("Player heals " + healedValue + " health!");
        } else {
            addToBattleLog("Player has no potions left!");
        }
        if (myMonster.getMyCurrentHealth() <= 0) {
            addToBattleLog("Player defeats the enemy!");
            disablePlayerButtons();
            dispose();
            myMonster.setMyAlive(false);
        }
    }

    /**
     * Method to use the hero's special move
     */
    private void specialMove() {
        // Add more logic here
        if (Objects.equals(myHero.getMyName(), "Warrior")) {
            addToBattleLog("Warrior special move!");
            int damageTaken = myMonster.getMyCurrentHealth() - ((Warrior) myHero).crushingBlow(myMonster.getMyCurrentHealth());
            if (damageTaken == 0) {
                addToBattleLog("Warrior missed!");
            } else {
                addToBattleLog("Warrior does " + damageTaken + " damage!");
            }
            myMonster.setMyCurrentHealth(myMonster.getMyCurrentHealth() - damageTaken);
            if (myMonster.getMyCurrentHealth() <= 0) {
                addToBattleLog("Warrior defeats the enemy!");
                disablePlayerButtons();
                dispose();
                myMonster.setMyAlive(false);
            }
        }
        if (Objects.equals(myHero.getMyName(), "Thief")) {
            addToBattleLog("Thief special move!");
            int damageTaken = myMonster.getMyCurrentHealth() - ((Thief) myHero).surpriseAttck(myMonster.getMyCurrentHealth());
            addToBattleLog("Thief does " + damageTaken + " damage!");
            myMonster.setMyCurrentHealth(myMonster.getMyCurrentHealth() - damageTaken);
            if (myMonster.getMyCurrentHealth() <= 0) {
                addToBattleLog("Thief defeats the enemy!");
                disablePlayerButtons();
                dispose();
                myMonster.setMyAlive(false);
            }
        }
        if (Objects.equals(myHero.getMyName(), "Priestess")) {
            addToBattleLog("Priestess special move!");
            int healedValue = ((Priestess) myHero).heal();
            addToBattleLog("Priestess recovers " + healedValue + " health!");
            if (myMonster.getMyCurrentHealth() <= 0) {
                addToBattleLog("Priestess defeats the enemy!");
                disablePlayerButtons();
                dispose();
                myMonster.setMyAlive(false);
            }
        }

    }

    /**
     * Method to update enemy hp label
     */
    private void updateMonsterHP() {
        enemyHPLabel.setText(myMonster.getMyName() + " HP: " + myMonster.getMyCurrentHealth());
    }

    /**
     * Method to update player hp label
     */
    private void updatePlayerHP() {
        playerHPLabel.setText(myHero.getMyName() +" HP: " + myHero.getMyCurrentHealth());
    }

    /**
     * Method to facilitate the attack button
     */
    private void attackEnemy() {
        // Code for attacking the enemy
        addToBattleLog("Player attacks!");
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

    /**
     * Method to facilitate the defend button
     */
    private void defend() {
        addToBattleLog("Player defends!");
        playerDefending = true;
    }

    /**
     * Method to facilitate the enemy's turn
     */
    private void performEnemyTurn() {
        // Perform enemy's turn logic here
        if (myMonster.getMyCurrentHealth() <= 0) {
            disablePlayerButtons();
            dispose();
            return;
        }
        addToBattleLog("Monster attacks!");
        int damageTaken = 0;
        // if player is defending
        if (playerDefending) {
            Random random = new Random();
            double chanceToBlockChecker = random.nextDouble();
            if (chanceToBlockChecker <= myHero.getChanceToBlock()) {
                addToBattleLog("Successfully Defended!");
            } else {
                addToBattleLog("Failed to Defend!");
                damageTaken = myHero.getMyCurrentHealth() - myMonster.regularAttack(myHero.getMyCurrentHealth(), myMonster.getMyChanceToHit());
            }
        } else {
            damageTaken = myHero.getMyCurrentHealth() - myMonster.regularAttack(myHero.getMyCurrentHealth(), myMonster.getMyChanceToHit());
        }
        // do damage to player
        myHero.setMyCurrentHealth(myHero.getMyCurrentHealth() - damageTaken);
        addToBattleLog("Monster does " + damageTaken + " damage!");
        playerDefending = false;
        // check if player dead
        if (myHero.getMyCurrentHealth() <= 0) {
            addToBattleLog("Player has been defeated!");
            disablePlayerButtons();
            dispose();
            DeathScreen deathScreen = new DeathScreen(cards, cardLayout);
            deathScreen.setVisible(true);
        }

        // Player's turn
        playerTurn = true;
        enablePlayerButtons();
    }

    /**
     * Method to disable buttons
     */
    private void disablePlayerButtons() {
        attackButton.setEnabled(false);
        defendButton.setEnabled(false);
    }

    /**
     * Method to enable buttons
     */
    private void enablePlayerButtons() {
        attackButton.setEnabled(true);
        defendButton.setEnabled(true);
    }

    /**
     * Method to update battle log
     * @param message to be added to the battle log
     */
    private void addToBattleLog(String message) {
        battleLog.append(message + "\n");
    }
}
