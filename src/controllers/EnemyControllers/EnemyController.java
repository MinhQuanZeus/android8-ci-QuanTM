package controllers.EnemyControllers;

import controllers.Colliable;
import controllers.CollsionPool;
import controllers.GameController;
import models.EnemyModel;
import models.GameModel;
import utils.Utils;
import views.EnemyView;
import views.GameView;

import java.awt.*;

/**
 * Created by QuanT on 2/27/2017.
 */
public class EnemyController extends GameController implements Colliable {
    public static final int SPEED = 2;
    private ShotBehavior shotBehavior;
    private FlyBehavior flyBehavior;

    public EnemyController(EnemyModel gameObject, GameView gameDrawer, FlyBehavior flyBehavior, ShotBehavior shotBehavior) {
        super(gameObject, gameDrawer);
        CollsionPool.instance.add(this);
        this.flyBehavior = flyBehavior;
        this.shotBehavior = shotBehavior;
    }

    @Override
    public GameModel getGameModel() {
        return model;
    }

    @Override
    public void onCollide(Colliable colliable) {

    }

    public static EnemyController create(int x, int y, EnemyPlaneType type) {
        EnemyController enemyController = null;

        switch (type) {
            case YELLOW:
                enemyController = new EnemyController(
                        new EnemyModel(x, y),
                        new GameView("enemy_plane_yellow_1.png"),
                        new FlyStraight(),
                        new NormalShotBehavior());

                break;
            case WHITE:
                enemyController = new EnemyController(
                        new EnemyModel(x, y),
                        new GameView("enemy_plane_white_1.png"),
                        new FlyZiczac(),
                        new FollowShotBehavior());
                break;
        }
        return enemyController;
    }

    @Override
    public void run() {
        super.run();
        if (flyBehavior != null) {
            flyBehavior.fly(this);
        }
        if (shotBehavior != null)
            shotBehavior.doShot(this);
    }

}
