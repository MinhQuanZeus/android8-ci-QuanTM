package controllers.EnemyControllers;

import controllers.Colliable;
import controllers.GameController;
import controllers.PlaneController;
import models.EnemyBulletModel;
import models.GameModel;
import utils.Utils;
import views.EnemyBulletView;
import views.GameView;

import java.awt.*;

/**
 * Created by QuanT on 2/27/2017.
 */
public class EnemyBulletController extends GameController implements Colliable {
    public EnemyBulletController(EnemyBulletModel model, GameView view) {
        super(model, view);
    }

    @Override
    public GameModel getGameModel() {
        return model;
    }

    @Override
    public void onCollide(Colliable colliable) {
        if (colliable instanceof PlaneController) {
            ((PlaneController) colliable).getModel().destroy();
        }
    }
}
