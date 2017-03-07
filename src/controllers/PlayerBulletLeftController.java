package controllers;

import controllers.EnemyControllers.EnemyController;
import models.GameModel;
import models.GameModelWithHP;
import models.PlayerBulletModel;
import views.GameView;

import java.awt.*;

/**
 * Created by QuanT on 3/5/2017.
 */
public class PlayerBulletLeftController extends GameController implements Colliable {
    private static final int SPEED = 10;

    public PlayerBulletLeftController(PlayerBulletModel model, GameView view) {
        super(model, view);
        this.vector.dy = -SPEED;
        this.vector.dx = -(SPEED -4);
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
        if (model.getY() < 0 || model.getX() < 0) {
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
        //   this.getModel().destroy();
        if (colliable instanceof EnemyController) {
            PlayerBulletModel bullet = (PlayerBulletModel) model;
            ((GameModelWithHP) ((EnemyController) colliable).getModel()).decreaseHP(bullet.getDamage());
            this.getModel().destroy();
        }
        if (colliable instanceof IslandController) {
            PlayerBulletModel bullet = (PlayerBulletModel) model;
            ((GameModelWithHP) ((IslandController) colliable).getModel()).decreaseHP(bullet.getDamage());
            this.getModel().destroy();
        }
    }
}
