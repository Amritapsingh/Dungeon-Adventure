package view;

import model.Dungeon;
import model.TileManager;

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
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    public int worldX = 380;
    public int worldY = 300;
    public final int screenX;
    public final int screenY;
    public int worldCol;
    public int worldRow;
    public int worldWidth;
    public int worldHeight;
    Dungeon dungeon;
    private final int playerSpeed = 20;
    private Thread gameThread;
    private final int FPS = 60;

    private final ImageIcon myLogo = new ImageIcon("");

    private final ImageIcon enemyLogo = new ImageIcon("");
    TileManager tiles;

    public GameScreen(JPanel cards, CardLayout cardLayout) {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.white);
        setDoubleBuffered(true);

        setVisible(true);

        JButton backButton = new JButton("Back");
        //add(backButton, BorderLayout.SOUTH);
        backButton.addActionListener(e -> cardLayout.show(cards, "StartingScreen"));
        setFocusable(true);
        screenX = screenWidth/2;
        screenY = screenHeight/2;
        dungeon = new Dungeon(5,5);
        tiles = new TileManager(this, dungeon);
        worldCol = dungeon.getMaze().length * 16;
        worldRow = dungeon.getMaze()[0].length * 12;
        worldWidth = worldCol * tileSize;
        worldHeight = worldRow * tileSize;



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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tiles.draw(g2d);
        g2d.setColor(Color.black);
        g2d.fillRect(screenX, screenY, tileSize, tileSize);
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

    }
}

