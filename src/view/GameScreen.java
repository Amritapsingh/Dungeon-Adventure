package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameScreen extends JPanel implements Runnable {
    // constants to capture screen dimensions
    /**
     * A ToolKit.
     */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /**
     * A Factor for scaling the size of the GUI relative to the current screen size.
     */
    private static final int SCALE = 3;
    private final int originalTileSize = 16; // 16x16
    public final int tileSize = originalTileSize * SCALE; // 48x48 tiles
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    public int worldX = 350;
    public int worldY = 350;
    public int screenX;
    public int screenY;
    public int worldCol;
    public int worldRow;
    public int worldWidth;
    public int worldHeight;
    public Dungeon dungeon;
    private final int playerSpeed = 20;
    private Thread gameThread;
    private final int FPS = 60;

    TileManager tiles;
    Rectangle solidArea;
    Hero myHero;
    public int pillarCount = 0;
    JPanel myCards;
    CardLayout myCardLayout;


    public GameScreen(JPanel cards, CardLayout cardLayout, Hero theHero, int rows, int cols) {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.black);
        setDoubleBuffered(true);
        myCards = cards;
        myCardLayout = cardLayout;
        setVisible(true);

        JButton backButton = new JButton("Back");
        //add(backButton, BorderLayout.SOUTH);
        backButton.addActionListener(e -> cardLayout.show(cards, "StartingScreen"));
        setFocusable(true);
        screenX = screenWidth/2;
        screenY = screenHeight/2;
        dungeon = new Dungeon(rows, cols);
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

    public void startNewGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public TileManager getTileManager() {
        return tiles;
    }
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
                        System.out.println("Hero did not recover any health points");
                    } else {
                        System.out.println("Recovered " + healedValue + " health points");
                    }
                } else {
                    System.out.println("Hero has no potions");
                }
            }

        });
        //player.update();

    }

    public void checkCollisionNorth(Rectangle rectRight, Rectangle rectLeft) {
        if (rectRight.intersects(solidArea)) {
            worldY += playerSpeed;
        }
        if (rectLeft.intersects(solidArea)) {
            worldY += playerSpeed;

        }
    }
    public void checkCollisionSouth(Rectangle rectRight, Rectangle rectLeft) {
        if (rectRight.intersects(solidArea)) {
            worldY -= playerSpeed;
        }
        if (rectLeft.intersects(solidArea)) {
            worldY -= playerSpeed;

        }
    }
    public void checkCollisionEast(Rectangle rectRight, Rectangle rectLeft) {
        if (rectRight.intersects(solidArea)) {
            worldX -= playerSpeed;
        }
        if (rectLeft.intersects(solidArea)) {
            worldX -= playerSpeed;

        }
    }
    public void checkCollisionWest(Rectangle rectRight, Rectangle rectLeft) {
        if (rectRight.intersects(solidArea)) {
            worldX += playerSpeed;
        }
        if (rectLeft.intersects(solidArea)) {
            worldX += playerSpeed;
        }
    }
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
    public void potionCheck(Rectangle rectangle, int row, int col) {
        Room[][] maze = dungeon.getMaze();
        if (rectangle.intersects(solidArea)) {
            dungeon.setPotionNum(dungeon.getPotionNum() - 1);
            int potionCount = myHero.getPotionCount();
            myHero.setPotionCount(++potionCount);
            System.out.println("Collected a potion. Hero now has " + myHero.getPotionCount() + " potions");
            maze[row/12][col/16].setHasPotion(false);
        }
    }
}

