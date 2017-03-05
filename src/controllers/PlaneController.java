package controllers;

import models.GameModel;
import utils.GameSetting;
import models.PlaneModel;
import models.PlayerBulletModel;
import utils.Utils;
import views.GameView;
import views.PlayerBulletView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by QuanT on 2/27/2017.
 */
public class PlaneController extends GameController implements KeyListener, Colliable {
    public static final int SPEED = 10;
    public static final int ATK_SPEED = 7;
    private int count;

    private ControllerManager bulletManager;
    private KeyInputManage keyInputManage;
    private Image bulletImage = Utils.loadImageFromRes("bullet.png");
    private int bulletDamage = 1;
    private int planeLevel = 1;

    private PlaneController(PlaneModel plane, GameView view) {
        super(plane, view);
        this.bulletManager = new ControllerManager();
        this.keyInputManage = new KeyInputManage();
        CollsionPool.instance.add(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.keyInputManage.keyUp = true;
                break;
            case KeyEvent.VK_DOWN:
                this.keyInputManage.keyDown = true;
                break;
            case KeyEvent.VK_LEFT:
                this.keyInputManage.keyLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                this.keyInputManage.keyRight = true;
                break;
            case KeyEvent.VK_SPACE:
                this.keyInputManage.keySpace = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.keyInputManage.keyUp = false;

                break;
            case KeyEvent.VK_DOWN:
                this.keyInputManage.keyDown = false;
                break;
            case KeyEvent.VK_LEFT:
                this.keyInputManage.keyLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                this.keyInputManage.keyRight = false;
                break;
            case KeyEvent.VK_SPACE:
                this.keyInputManage.keySpace = false;
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        bulletManager.draw(g);
        super.draw(g);

    }

    @Override
    public void run() {
        count++;
        this.vector.dx = 0;
        this.vector.dy = 0;

        if (keyInputManage.keyDown && !keyInputManage.keyUp && ((model.getY() + SPEED) < (GameSetting.SCREEN_HIGHT - model.getHeight()))) {
            this.vector.dy = SPEED;
        } else if (!keyInputManage.keyDown && keyInputManage.keyUp && ((model.getY() - SPEED) > model.getHeight() / 2)) {
            this.vector.dy = -SPEED;
        }

        if (keyInputManage.keyLeft && !keyInputManage.keyRight && ((model.getX() - SPEED) > 0)) {
            this.vector.dx = -SPEED;
        } else if (!keyInputManage.keyLeft && keyInputManage.keyRight && ((model.getX() + SPEED) < GameSetting.SCREEN_WIDTH - model.getWidth())) {
            this.vector.dx = SPEED;
        }

        if (keyInputManage.keySpace) {
            if (count > ATK_SPEED) {
                switch (planeLevel){
                    case 1:
                        PlayerBulletController bulletController = new PlayerBulletController(
                                new PlayerBulletModel(this.model.getMiddleX() - PlayerBulletModel.WIDTH / 2, this.model.getY(),bulletDamage),
                                new PlayerBulletView(bulletImage)
                        );
                        bulletManager.add(bulletController);
                        break;
                    case 2:
                        PlayerBulletController bulletController1 = new PlayerBulletController(
                                new PlayerBulletModel(this.model.getMiddleX() - PlayerBulletModel.WIDTH / 2, this.model.getY(),bulletDamage),
                                new PlayerBulletView(bulletImage)
                        );
                        bulletManager.add(bulletController1);
                        break;
                    case 3:
                        PlayerBulletController bulletController2 = new PlayerBulletController(
                                new PlayerBulletModel(this.model.getMiddleX() - PlayerBulletModel.WIDTH / 2, this.model.getY(),bulletDamage),
                                new PlayerBulletView(bulletImage)
                        );
                        PlayerBulletLeftController bulletController3 = new PlayerBulletLeftController(
                                new PlayerBulletModel(this.model.getMiddleX() - PlayerBulletModel.WIDTH / 2, this.model.getY(),bulletDamage),
                                new PlayerBulletView(Utils.loadImageFromRes("bullet-right.png"))
                        );
                        PlayerBulletRightController bulletController4 = new PlayerBulletRightController(
                                new PlayerBulletModel(this.model.getMiddleX() - PlayerBulletModel.WIDTH / 2, this.model.getY(),bulletDamage),
                                new PlayerBulletView(Utils.loadImageFromRes("bullet-left.png"))
                        );
                        bulletManager.add(bulletController2);
                        bulletManager.add(bulletController3);
                        bulletManager.add(bulletController4);
                        break;
                }


                count = 0;
            }
        }

        super.run();
        bulletManager.run();

    }

    public final static PlaneController instance = new PlaneController(
            new PlaneModel(GameSetting.SCREEN_WIDTH/2-PlaneModel.WIDTH/2, GameSetting.SCREEN_HIGHT-PlaneModel.HEIGHT),
            new GameView("plane2.png")
    );

    @Override
    public GameModel getGameModel() {
        return model;
    }

    @Override
    public void onCollide(Colliable colliable) {

    }

    public void setBulletImage(Image bulletImage) {
        this.bulletImage = bulletImage;
    }

    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    public void setPlaneLevel(int planeLevel) {
        this.planeLevel = planeLevel;
    }

    public void planeLevelUp(){
        if(planeLevel<3){
            planeLevel++;
            bulletDamage +=2;
        }
    }
}
