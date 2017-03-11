import controllers.BombController.BombManager;
import controllers.*;
import controllers.EnemyControllers.EnemyBulletManage;
import controllers.EnemyControllers.EnemyManage;
import utils.GameSetting;
import utils.SoundPlayer;
import utils.Utils;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by QuanT on 2/19/2017.
 */
public class GameWindow extends Frame {
    Thread thread;
    private Utils util;
    private GroundController groundController;
    private BufferedImage backBufferImage;
    private Graphics backGraphics;
    SoundPlayer soundPlayer;

    public GameWindow() {
        setVisible(true);
        setSize(GameSetting.SCREEN_WIDTH, GameSetting.SCREEN_HIGHT);
        util = new Utils();
        groundController = new GroundController();
        this.addKeyListener(PlaneController.instance);
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
        soundPlayer = new SoundPlayer("Strikers_1945_II.wav");
        soundPlayer.playLoop();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    PlaneController.instance.run();
                    EnemyBulletManage.instance.run();
                    EnemyManage.instance.run();
                    PowerUpManager.instance.run();
                    BombManager.instance.run();
                    IslanManager.instance.run();
                    CollsionPool.instance.run();
                    groundController.Update();
                    try {

                        Thread.sleep(17);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    repaint();
                }
            }
        });

        backBufferImage = new BufferedImage(GameSetting.SCREEN_WIDTH, GameSetting.SCREEN_HIGHT, BufferedImage.TYPE_INT_ARGB);
        backGraphics = backBufferImage.getGraphics();
        thread.start();
    }


    @Override
    public synchronized void update(Graphics g) {
        if (backBufferImage != null) {
            groundController.Paint(backGraphics);
            IslanManager.instance.draw(backGraphics);
            PlaneController.instance.draw(backGraphics);
            EnemyManage.instance.draw(backGraphics);
            EnemyBulletManage.instance.draw(backGraphics);
            PowerUpManager.instance.draw(backGraphics);
            BombManager.instance.draw(backGraphics);
            g.drawImage(backBufferImage, 0, 0, null);
        }
    }
}
