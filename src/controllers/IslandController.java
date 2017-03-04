package controllers;

import models.GameModel;
import models.IslandModel;
import models.PlayerBulletModel;
import models.PowerUpModel;
import utils.GameSetting;
import utils.Utils;
import views.GameView;
import views.IslandView;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by QuanT on 2/27/2017.
 */
public class IslandController extends GameController implements Colliable {
    private static final int SPEED = 1;
    public IslandController(IslandModel model, GameView view) {
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
            this.getModel().destroy();
        }
    }
}
