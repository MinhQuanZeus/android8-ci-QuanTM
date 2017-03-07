package controllers.BombController;

import controllers.Colliable;
import controllers.CollsionPool;
import controllers.GameController;
import controllers.PlaneController;
import models.GameModel;
import models.LockModel;
import views.GameView;

/**
 * Created by QuanT on 3/7/2017.
 */
public class LockController extends GameController implements Colliable {
    private static final int SPEED = 2;
    public LockController(GameModel lockModel, GameView gameDrawer) {
        super(lockModel, gameDrawer);
        vector.dy = SPEED;
        CollsionPool.instance.add(this);
    }
    public static LockController create(int x , int y){
        return new LockController(
                new LockModel(x,y), new GameView("Snowflake.png")
        );
    }


    @Override
    public GameModel getGameModel() {
        return model;
    }

    @Override
    public void onCollide(Colliable colliable) {
        if(colliable instanceof PlaneController) {
            Notification.instance.onFrezze(
                    getGameModel().getX(),
                    getGameModel().getY()
            );
            getGameModel().destroy();
        }
    }

}
