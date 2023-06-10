package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serializable;

/**
 * The GameScreen class is the main class for the gameplay. It is responsible for
 * running the thread that controls the GUI and the tilemanager.
 *
 * @author Amrit Singh
 * @version 09 June 2023
 */
public class GameScreen extends JPanel implements Runnable, Serializable {
    // constants to capture screen dimensions
    /**
     * A ToolKit.
     */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    /**
     * A Factor for scaling the size of the GUI relative to the current screen size.
     */
    private static final int SCALE = 3;
    /**
     * Field for tile size
     */
    private final int originalTileSize = 16; // 16x16
    /**
     * Scaling up each tile
     */
    public final int tileSize = originalTileSize * SCALE; // 48x48 tiles
    /**
     * Setting max column dimension
     */
    public final int maxScreenCol = 16;
    /**
     * Field for max row dimension
     */
    public final int maxScreenRow = 12;
    /**
     * Field for screen width
     */
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    /**
     * Field for screen height
     */
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    /**
     * Field for player X position on map
     */
    public int worldX = 350;
    /**
     * Field for player Y position on map
     */
    public int worldY = 350;
    /**
     * Field for player location on screen
     */
    public int screenX;
    /**
     * Field for player location on screen
     */
    public int screenY;
    /**
     * Field for max columns on map
     */
    public int worldCol;
    /**
     * Field for max rows on map
     */
    public int worldRow;
    /**
     * Field for max map width
     */
    public int worldWidth;
    /**
     * Field for max map height
     */
    public int worldHeight;
    /**
     * Field for the dungeon
     */
    public Dungeon dungeon;
    /**
     * Field for the player's speed
     */
    private final int playerSpeed = 20;
    /**
     * Field for the thread
     */
    private transient Thread gameThread;
    /**
     * Field for the frame rate/update rate
     */
    final private int FPS = 60;
    /**
     * Field for tile manager
     */
    transient TileManager tiles;
    /**
     * Field for player hitbox
     */
    Rectangle solidArea;
    /**
     * Field for the hero
     */
    public Hero myHero;
    /**
     * Field for the player's pillar count
     */
    public int pillarCount = 0;
    /**
     * Field for the cards
     */
    JPanel myCards;
    /**
     * Field for the card layout
     */
    CardLayout myCardLayout;

    /**
     * Constructor for the game screen panel.
     * @param cards the cards
     * @param cardLayout the layout
     * @param theHero the hero
     * @param rows the dungeon rows
     * @param cols the dungeon cols
     * @param theDifficulty the selected difficulty
     */
    public GameScreen(JPanel cards, CardLayout cardLayout, Hero theHero, int rows, int cols, String theDifficulty) {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.black);
        setDoubleBuffered(true);
        myCards = cards;
        myCardLayout = cardLayout;
        JButton backButton = new JButton("Back");
        //add(backButton, BorderLayout.SOUTH);
        backButton.addActionListener(e -> cardLayout.show(cards, "StartingScreen"));
        setFocusable(true);
        screenX = screenWidth/2;
        screenY = screenHeight/2;
        dungeon = new Dungeon(rows, cols, theHero, theDifficulty);
        dungeon.printMaze();
        tiles = new TileManager(this, dungeon);
        worldCol = dungeon.getMaze()[0].length * 16;
        worldRow = dungeon.getMaze().length * 16;
        worldWidth = worldCol * tileSize;
        worldHeight = worldRow * tileSize;
        setStart();
        solidArea = new Rectangle( screenX - 30, screenY - 20, tileSize , tileSize);
        myHero = theHero;
    }

    /**
     * Method to start new thread
     */
    public void startNewGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Method to get tilemanager
     * @return the tilemanager
     */
    public TileManager getTileManager() {
        return tiles;
    }

    /**
     * Method to run the game
     */
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        long lastTime = System.nanoTime();
        long currentTime;
        double delta = 0;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }

    }

    /**
     * Field for setting the player starting position
     * in the entrance of the dungeon
     */
    public void setStart() {
        for (int i = 0; i < dungeon.getMaze().length; i++) {
            for (int j = 0; j < dungeon.getMaze()[i].length; j++) {
                int y = i * 12;
                int x = j * 16;
                if (dungeon.getMaze()[i][j].getIsEnter()) {
                    worldX = x * tileSize + screenWidth/2;
                    worldY = y * tileSize + +screenHeight/2;
                }
            }
        }
    }

    /**
     * Method to repaint the screen
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tiles.draw(g2d);
        g2d.setColor(Color.red);
        g2d.drawImage(tiles.getTile()[16].image, screenX - 30, screenY - 20, tileSize , tileSize , null);
        //g2d.draw(rect);
        //g2d.fill(rect);
        g2d.dispose();
    }

    /**
     * Method to update movement and monitor keybinds
     */
    public void update() {
        setInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, getInputMap());
        KeyStroke keyW = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyW, "pressedW");
        getActionMap().put("pressedW", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                worldY -= playerSpeed;
            }
        });
        KeyStroke keyS = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyS, "pressedS");
        getActionMap().put("pressedS", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                worldY += playerSpeed;
            }
        });
        KeyStroke keyA = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyA, "pressedA");
        getActionMap().put("pressedA", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                worldX -= playerSpeed;
            }
        });
        KeyStroke keyD = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyD, "pressedD");
        getActionMap().put("pressedD", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                worldX += playerSpeed;
            }
        });
        KeyStroke keyP = KeyStroke.getKeyStroke(KeyEvent.VK_P, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyP, "pressedP");
        getActionMap().put("pressedP", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                int healedValue = 0;
                if (myHero.getPotionCount() > 0) {
                    healedValue = myHero.usePotion();
                    if (healedValue == 0) {
                        System.out.println(myHero.getMyName() + " did not recover any health points");
                    } else {
                        System.out.println("Recovered " + healedValue + " health points");
                        System.out.println(myHero.getMyName() + " has " + myHero.getMyCurrentHealth() + " health points");
                    }
                } else {
                    System.out.println(myHero.getMyName() + " has no potions");
                }
            }
        });
        KeyStroke keyG = KeyStroke.getKeyStroke(KeyEvent.VK_G, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyG, "pressedG");
        getActionMap().put("pressedG", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                SaveLoad.saveGame("save", dungeon);
            }
        });
        KeyStroke keyL = KeyStroke.getKeyStroke(KeyEvent.VK_L, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyL, "pressedL");
        getActionMap().put("pressedL", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                Dungeon theDungeon = SaveLoad.loadGame("save");
                dungeon = theDungeon;
                assert theDungeon != null;
            }
        });
    }

    /**
     * Method to check collision with the north walls
     * @param rectRight the rectangle to check
     * @param rectLeft the rectangle to check
     */
    public void checkCollisionNorth(Rectangle rectRight, Rectangle rectLeft) {
        if (rectRight.intersects(solidArea)) {
            worldY += playerSpeed;
        }
        if (rectLeft.intersects(solidArea)) {
            worldY += playerSpeed;

        }
    }
    /**
     * Method to check collision with the south walls
     * @param rectRight the rectangle to check
     * @param rectLeft the rectangle to check
     */
    public void checkCollisionSouth(Rectangle rectRight, Rectangle rectLeft) {
        if (rectRight.intersects(solidArea)) {
            worldY -= playerSpeed;
        }
        if (rectLeft.intersects(solidArea)) {
            worldY -= playerSpeed;
        }
    }
    /**
     * Method to check collision with the east walls
     * @param rectRight the rectangle to check
     * @param rectLeft the rectangle to check
     */
    public void checkCollisionEast(Rectangle rectRight, Rectangle rectLeft) {
        if (rectRight.intersects(solidArea)) {
            worldX -= playerSpeed;
        }
        if (rectLeft.intersects(solidArea)) {
            worldX -= playerSpeed;
        }
    }
    /**
     * Method to check collision with the west walls
     * @param rectRight the rectangle to check
     * @param rectLeft the rectangle to check
     */
    public void checkCollisionWest(Rectangle rectRight, Rectangle rectLeft) {
        if (rectRight.intersects(solidArea)) {
            worldX += playerSpeed;
        }
        if (rectLeft.intersects(solidArea)) {
            worldX += playerSpeed;
        }
    }
    /**
     * Method to check collision with the monster hitbox
     * @param rectangle the hitbox to check
     * @param theMonster the monster to check
     */
    public void combatCheck(Rectangle rectangle, Monster theMonster) {
        if (rectangle.intersects(solidArea) && theMonster.fightCount == 1) {
            BattleScreen  battleScreen = new BattleScreen(myHero, theMonster, myCards, myCardLayout);
            System.out.println("Trial by combat");
            battleScreen.setVisible(true);
            theMonster.fightCount--;
        }
        if (myHero.getMyCurrentHealth() <= 0) {
            myHero.setMyAlive(false);
            this.setVisible(false);
        }
    }
    /**
     * Method to check collision with the pillar hitboxes
     * @param rect the rectangle to check
     * @param rows
     * @param cols
     */
    public void checkPillarCollision(Rectangle rect, int rows, int cols) {
        if (rect.intersects(solidArea)) {
            if (dungeon.getMaze()[rows][cols].getAbstractionPillar()) {
                //add abstract pillar
                dungeon.getMaze()[rows][cols].setAbstractionPillar(false);
                pillarCount++;
            }
            if (dungeon.getMaze()[rows][cols].getPolymorphismPillar()) {
                //add concrete pillar
                dungeon.getMaze()[rows][cols].setPolymorphismPillar(false);
                pillarCount++;

            }
            if (dungeon.getMaze()[rows][cols].getEncapsulationPillar()) {
                //add encapsulation pillar
                dungeon.getMaze()[rows][cols].setEncapsulationPillar(false);
                pillarCount++;

            }
            if (dungeon.getMaze()[rows][cols].getInheritancePillar()) {
                //add inheritance pillar
                dungeon.getMaze()[rows][cols].setInheritancePillar(false);
                pillarCount++;
            }
            if (dungeon.getMaze()[rows][cols].getIsExit()) {
                this.setVisible(false);
                myCardLayout.show(myCards, "StartingScreen");
                pillarCount = 0;
            }
        }
    }

    /**
     * Method to check collision with the potion hitbox
     * @param rectangle hitbox of potion
     * @param row row of potion room
     * @param col of potion room
     */
    public void potionCheck(Rectangle rectangle, int row, int col) {
        Room[][] maze = dungeon.getMaze();
        if (rectangle.intersects(solidArea)) {
            dungeon.setPotionNum(dungeon.getPotionNum() - 1);
            int potionCount = myHero.getPotionCount();
            myHero.setPotionCount(++potionCount);
            System.out.println("Collected a potion. " + myHero.getMyName() + " now has " + myHero.getPotionCount() + " potions");
            maze[row/12][col/16].setHasPotion(false);
        }
    }

    /**
     * Method to check collision with the pit hitbox
     * @param rectangle the pit hitbox
     * @param row row of pit
     * @param col col of the pit
     */
    public void pitCheck(Rectangle rectangle, int row, int col) {
        Room[][] maze = dungeon.getMaze();
        if (rectangle.intersects(solidArea)) {
            System.out.println("You fell into a pit");
            maze[row / 12][col / 16].setHasPit(false);
            myHero.setMyCurrentHealth(myHero.getMyCurrentHealth() - 20);
        }
    }
}

