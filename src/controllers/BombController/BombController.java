package controllers.BombController;

import controllers.Colliable;
import controllers.CollsionPool;
import controllers.GameController;
import controllers.PlaneController;
import models.BombModel;
import models.GameModel;
import views.GameView;

/**
 * Created by QuanT on 3/6/2017.
 */
public class BombController extends GameController implements Colliable {
    private static final int SPEED = 2;
    public BombController(GameModel model, GameView view) {
        super(model, view);
        vector.dy = SPEED;
        CollsionPool.instance.add(this);
    }
    public static BombController create(int x, int y) {
        return new BombController(
                new BombModel(x, y),
                new GameView("bomb.png"));
    }

    @Override
    public GameModel getGameModel() {
        return model;
    }

    @Override
    public void onCollide(Colliable colliable) {
        if(colliable instanceof PlaneController) {
            Notification.instance.onBomExpode(model.getX(), model.getY());
            model.destroy();
        }
    }
}
