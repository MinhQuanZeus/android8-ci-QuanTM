import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by QuanT on 2/19/2017.
 */
public class GameWindow extends Frame {

    private static final int SCREEN_WIDTH = 400;
    private static final int SCREEN_HIGHT = 600;
    private static final int SPEED = 6;


    private boolean isKeyUp = false;
    private boolean iskeyDown = false;
    private boolean isKeyLeft = false;
    private boolean isKeyRight = false;
    private boolean isSpace = false;


    public Plane plane;
    private Utilities util;
    private Ground ground;

    private BufferedImage backBufferImage;
    private Graphics backGraphics;

    Thread thread;
    int count = 0;
    private ArrayList<PlayerBullet> playerBullets = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<EnemyBullet> enemyBullets = new ArrayList<>();
    private ArrayList<Island> islands = new ArrayList<>();
    long lasttimepress = 0;


    public GameWindow() {
        setVisible(true);
        setSize(400, 600);
        setResizable(false);
        util = new Utilities();
        ground = new Ground();

        plane = new Plane();
        plane.x = (400 - 25) / 2;
        plane.y = 600 - 44;

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }

        });


        //1: load image
        plane.image = util.loadImageFromRes("plane4.png");

        //2: draw image
        //  update(getGraphics());
        //     repaint();


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
//                switch (e.getKeyCode()){
//                    case KeyEvent.VK_RIGHT:
//                        //TODO: move plane to right
//                        planeX+=SPEED;
//                        break;
//                    case KeyEvent.VK_LEFT:
//                        //TODO: move plane to Left
//                        planeX-=SPEED;
//                        break;
//                    case KeyEvent.VK_UP:
//                        //move plane to up
//                        planeY-=SPEED;
//                        break;
//                    case KeyEvent.VK_DOWN:
//                        planeY+=SPEED;
//                        break;
//
//                }

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        //press right key
                        isKeyRight = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        //press left key
                        isKeyLeft = true;
                        break;
                    case KeyEvent.VK_UP:
                        //press up key
                        isKeyUp = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        //press down key
                        iskeyDown = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        isSpace = true;
                        break;
                }
                //   movePlane();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        //Released Right key
                        isKeyRight = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        //Released Left key
                        isKeyLeft = false;
                        break;
                    case KeyEvent.VK_UP:
                        //Released Up key
                        isKeyUp = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        //Released Down key
                        iskeyDown = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        //Released Space
                        isSpace = false;
                        break;
                }
            }

        });


        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    count++;
                    movePlane();
                    isEnemyDie();
                    if (count % 150 == 0) {
                        Enemy enemy = new Enemy();
                        enemy.image = util.loadImageFromRes("plane1.png");
                        enemy.y = -30;
                        enemy.x = util.getRandom(350);
                        enemies.add(enemy);

                    }

                    if (count % 200 == 0) {
                        Island island = new Island();
                        switch (util.getRandom(2)) {
                            case 0:
                                island.image = util.loadImageFromRes("island.png");
                                break;
                            case 1:
                                island.image = util.loadImageFromRes("island-2.png");
                                break;
                        }
                        island.y = -30;
                        island.x = util.getRandom(350);
                        islands.add(island);
                    }

                    if (count % 80 == 0) {
                        for (Enemy enemy : enemies) {
                            if (enemy != null) {
                                EnemyBullet enemyBullet = new EnemyBullet();
                                enemyBullet.image = util.loadImageFromRes("enemy_bullet.png");
                                enemyBullet.x = enemy.x + enemy.image.getWidth(null) / 4;
                                enemyBullet.y = enemy.y;
                                enemyBullets.add(enemyBullet);
                            }
                        }
                    }


                    ground.Update();
                    for (Enemy enemy : enemies) {
                        enemy.y += 2;
                    }
                    for (EnemyBullet enemyBullet : enemyBullets) {
                        enemyBullet.y += 10;
                    }
                    for (PlayerBullet playerBullet : playerBullets) {
                        playerBullet.y -= 7;
                    }
                    for (Island island : islands) {
                        island.y += 1;
                    }

                    try {

                        Thread.sleep(17);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    removeObjectOutOfScreen();
                    repaint();
                    if (count == 3000) {
                        count = 0;
                    }
                }
            }
        });

        backBufferImage = new BufferedImage(SCREEN_WIDTH, SCREEN_HIGHT, BufferedImage.TYPE_INT_ARGB);
        backGraphics = backBufferImage.getGraphics();
        thread.start();
    }

    private void removeObjectOutOfScreen() {
        Iterator playerBulletIterator = playerBullets.iterator();
        while (playerBulletIterator.hasNext()) {
            PlayerBullet playerBullet = (PlayerBullet) playerBulletIterator.next();
            if (playerBullet.y < 0) {
                playerBulletIterator.remove();
            }
        }
        Iterator enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = (Enemy) enemyIterator.next();
            if (enemy.y > 600) {
                enemyIterator.remove();
            }
        }
        Iterator enemyBulletIterator = enemyBullets.iterator();
        while (enemyBulletIterator.hasNext()) {
            EnemyBullet enemyBullet = (EnemyBullet) enemyBulletIterator.next();
            if (enemyBullet.y > 600) {
                enemyBulletIterator.remove();
            }
        }
    }

    private void isEnemyDie() {
        Iterator enemyItr = enemies.iterator();
        Iterator playerBulletItr = playerBullets.iterator();
        while (enemyItr.hasNext()) {
            Enemy enemy = (Enemy) enemyItr.next();
            while (playerBulletItr.hasNext()) {
                PlayerBullet playerBullet = (PlayerBullet) playerBulletItr.next();
                if ((playerBullet.x > enemy.x && playerBullet.x < (enemy.x + enemy.image.getWidth(null))) && ((enemy.y + enemy.image.getHeight(null) / 2) > playerBullet.y)) {
                    playerBulletItr.remove();
                    enemyItr.remove();
                    break;
                }
            }
        }
    }


    private void movePlane() {
        //move plane to right
        if (isKeyRight && (plane.x + 10) < (SCREEN_WIDTH - 25)) {
            plane.x += SPEED;
        }
        //move plane to left
        if (isKeyLeft && (plane.x - 10) > -5) {
            plane.x -= SPEED;
        }
        //move plane to up
        if (isKeyUp && (plane.y - 10) > 20) {
            plane.y -= SPEED;
        }
        //move plane to down
        if (iskeyDown && (plane.y + 10) < (SCREEN_HIGHT - 35)) {
            plane.y += SPEED;
        }
        //shotting bullet
        if (isSpace && (getSystemTime() - lasttimepress > 200)) {
            PlayerBullet playerBullet = new PlayerBullet();
            playerBullet.image = util.loadImageFromRes("bullet.png");
            playerBullet.x = plane.x + plane.image.getWidth(null) / 4;
            playerBullet.y = plane.y;
            playerBullets.add(playerBullet);
            lasttimepress = getSystemTime();
        }

    }


    @Override
    public void update(Graphics g) {
        if (backBufferImage != null) {
            isEnemyDie();

            ground.Paint(backGraphics);
            for (Island island : islands) {
                backGraphics.drawImage(island.image, island.x, island.y, island.image.getWidth(null), island.image.getHeight(null), null);
            }
            backGraphics.drawImage(plane.image, plane.x, plane.y, plane.image.getWidth(null) / 2, plane.image.getHeight(null) / 2, null);
            for (PlayerBullet playerBullet : playerBullets) {
                backGraphics.drawImage(playerBullet.image, playerBullet.x, playerBullet.y, 10, 10, null);
            }
            for (Enemy enemy : enemies) {
                backGraphics.drawImage(enemy.image, enemy.x, enemy.y, enemy.image.getWidth(null), enemy.image.getHeight(null), null);

            }

            for (EnemyBullet enemyBullet : enemyBullets) {
                if (enemyBullet != null) {
                    backGraphics.drawImage(enemyBullet.image, enemyBullet.x, enemyBullet.y, enemyBullet.image.getWidth(null), enemyBullet.image.getHeight(null), null);
                }
            }

            g.drawImage(backBufferImage, 0, 0, null);
            isEnemyDie();
        }
    }

    private long getSystemTime() {
        return System.currentTimeMillis();
    }
}
