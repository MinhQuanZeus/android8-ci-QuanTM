import controllers.*;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

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

    private PlaneController planeController;
    private Utils util;
    private GroundController groundController;

    private BufferedImage backBufferImage;
    private Graphics backGraphics;

    Thread thread;
    int count = 0;
    private Vector<PlayerBulletController> playerBullets = new Vector<>();
    private Vector<EnemyController> enemies = new Vector<>();
    private Vector<EnemyBulletController> enemyBullets = new Vector<>();
    private Vector<IslandController> islands = new Vector<>();
    long lasttimepress = 0;


    public GameWindow() {
        setVisible(true);
        setSize(400, 600);
        util = new Utils();
        groundController = new GroundController();


        planeController = new PlaneController((SCREEN_WIDTH-22)/2,SCREEN_HIGHT-70);

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

        //2: draw image

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
            public synchronized void run() {
                while (true) {
                    count++;
                    movePlane();
                    isEnemyDie();

                    if (count % 150 == 0) {
                        EnemyController enemy = new EnemyController(util.getRandom(350),-30);
                        enemies.add(enemy);

                    }

                    if (count % 200 == 0) {
                        IslandController island = new IslandController(util.getRandom(350),-30);
                        islands.add(island);
                    }

                    if (count % 80 == 0) {
                        for (EnemyController enemy : enemies) {
                            if (enemy != null) {
                                EnemyBulletController enemyBullet = new EnemyBulletController(enemy.getX() + enemy.getImage().getWidth(null) / 4,enemy.getY());
                                enemyBullets.add(enemyBullet);
                            }
                        }
                    }

              //      System.out.println("Enemies: "+enemies.size());
                  //  System.out.println("Bullets player: "+playerBullets.size());

                    groundController.Update();
                    for (EnemyController enemy : enemies) {
                        enemy.run();
                    }
                    for (EnemyBulletController enemyBullet : enemyBullets) {
                        enemyBullet.run();
                    }
                    for (PlayerBulletController playerBullet : playerBullets) {
                        playerBullet.run();
                    }
                    for (IslandController island : islands) {
                        island.run();
                    }
                    removeObjectOutOfScreen();
                    try {

                        Thread.sleep(17);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


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

    private synchronized void removeObjectOutOfScreen() {
        Iterator playerBulletIterator = playerBullets.iterator();
        while (playerBulletIterator.hasNext()) {
            PlayerBulletController playerBullet = (PlayerBulletController) playerBulletIterator.next();
            if (playerBullet.getY() < 0) {
                playerBulletIterator.remove();
            }
        }
        Iterator enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            EnemyController enemy = (EnemyController) enemyIterator.next();
            if (enemy.getY() > 600) {
                enemyIterator.remove();
            }
        }
        Iterator enemyBulletIterator = enemyBullets.iterator();
        while (enemyBulletIterator.hasNext()) {
            EnemyBulletController enemyBullet = (EnemyBulletController) enemyBulletIterator.next();
            if (enemyBullet.getY() > 600) {
                enemyBulletIterator.remove();
            }
        }
    }

    private synchronized void isEnemyDie() {
        Iterator enemyItr = enemies.iterator();
        Iterator playerBulletItr = playerBullets.iterator();
        while (enemyItr.hasNext()) {
            EnemyController enemy = (EnemyController) enemyItr.next();
            while (playerBulletItr.hasNext()) {
                PlayerBulletController playerBullet = (PlayerBulletController) playerBulletItr.next();
                if ((playerBullet.getX() > enemy.getX() && playerBullet.getX() < (enemy.getX() + enemy.getWidth())) && ((enemy.getY() + enemy.getHeight() / 2) > playerBullet.getY())) {
                    playerBulletItr.remove();
                    enemyItr.remove();
                    break;
                }
            }
        }
    }


    private synchronized void movePlane() {
        //move plane to right
        if (isKeyRight && (planeController.getX() + 10) < (SCREEN_WIDTH - 25)) {
         //   plane.x += SPEED;
            planeController.moveRight();
        }
        //move plane to left
        if (isKeyLeft && (planeController.getX() - 10) > -5) {
         ///   plane.x -= SPEED;
            planeController.moveLeft();
        }
        //move plane to up
        if (isKeyUp && (planeController.getY() - 10) > 20) {
         //   plane.y -= SPEED;
            planeController.moveUp();
        }
        //move plane to down
        if (iskeyDown && (planeController.getY() + 10) < (SCREEN_HIGHT - 35)) {
         //   plane.y += SPEED;
            planeController.moveDown();
        }
        //shotting bullet
        if (isSpace && (getSystemTime() - lasttimepress > 200)) {
            PlayerBulletController playerBulletController = new PlayerBulletController(planeController.getX() + planeController.getWidth() / 2,planeController.getY());
            playerBullets.add(playerBulletController);
            lasttimepress = getSystemTime();
        }

    }


    @Override
    public synchronized void update(Graphics g) {
        if (backBufferImage != null) {
            groundController.Paint(backGraphics);
            for (IslandController island : islands) {
                island.draw(backGraphics);
            }
            planeController.draw(backGraphics);
            for (PlayerBulletController playerBulletController : playerBullets) {

                playerBulletController.draw(backGraphics);
            }
            for (EnemyController enemy : enemies) {
                    enemy.draw(backGraphics);
            }
            for (EnemyBulletController enemyBullet : enemyBullets) {
                    enemyBullet.draw(backGraphics);
            }

            g.drawImage(backBufferImage, 0, 0, null);
        }
    }

    private synchronized long getSystemTime() {
        return System.currentTimeMillis();
    }
}
