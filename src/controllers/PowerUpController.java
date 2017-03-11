package controllers;

import models.GameModel;
import models.PowerUpModel;
import utils.GameSetting;
import utils.Utils;
import views.GameView;

import java.awt.*;

/**
 * Created by QuanT on 3/4/2017.
 */
public class PowerUpController extends GameController implements Colliable {
    private static final int SPEED = 2;

    public PowerUpController(PowerUpModel model, GameView view) {
        super(model, view);
        this.vector.dy = +SPEED;
        CollsionPool.instance.add(this);
    }

    public int getX() {
        return model.getX();
    }

    public int getY() {
        return model.getY();
    }

    @Override
    public void run() {
        super.run();
        if (model.getY() > GameSetting.SCREEN_HIGHT) {
            model.destroy();
        }
    }

    public void draw(Graphics graphics) {
        view.draw(graphics, model);
    }

    @Override
    public GameModel getGameModel() {
        return model;
    }

    @Override
    public void onCollide(Colliable colliable) {
        if (colliable instanceof PlaneController) {
            ((PlaneController) colliable).setBulletImage(Utils.loadImageFromRes("bullet-double.png"));
            ((PlaneController) colliable).planeLevelUp();
            this.getModel().destroy();
        }
    }
}
