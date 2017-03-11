package controllers.EnemyControllers;

import controllers.BombController.BombSubcriber;
import controllers.BombController.FreezzeSubcriber;
import controllers.BombController.Notification;
import controllers.Colliable;
import controllers.CollsionPool;
import controllers.GameController;
import models.EnemyModel;
import models.GameModel;
import utils.SoundPlayer;
import views.EnemyView;

/**
 * Created by QuanT on 2/27/2017.
 */
public class EnemyController extends GameController implements Colliable, BombSubcriber, FreezzeSubcriber {
    public static final int SPEED = 2;
    private ShotBehavior shotBehavior;
    private FlyBehavior flyBehavior;
    private FreezeBehavior freezeBehavior;
    private EnemyState enemyState;
    private int frezzeCount;
    private boolean isPlaySound = true;

    public EnemyController(EnemyModel model, EnemyView view, FreezeBehavior freezeBehavior, FlyBehavior flyBehavior, ShotBehavior shotBehavior) {
        super(model, view);
        CollsionPool.instance.add(this);
        Notification.instance.subsribe(this);
        enemyState = EnemyState.NORMAL;
        Notification.instance.subsribeFrezze(this);
        this.freezeBehavior = freezeBehavior;
        this.flyBehavior = flyBehavior;
        this.shotBehavior = shotBehavior;
    }

    @Override
    public void onBombExplode(int x, int y) {
      //  model.destroy();
        model.explosive();
        if (model.isExplosive()) {
            if (!((EnemyView) view).explode()) {
                model.destroy();
            }
        }

    }

    @Override
    public void onFrezze(int x, int y) {
        enemyState = EnemyState.FREZEED;
        frezzeCount = 0;
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
                        new EnemyView("enemy_plane_yellow_1.png"),
                        new FreezeBehavior(200),
                        new FlyStraight(),
                        new NormalShotBehavior());

                break;
            case WHITE:
                enemyController = new EnemyController(
                        new EnemyModel(x, y),
                        new EnemyView("enemy_plane_white_1.png"),
                        new FreezeBehavior(250),
                        new FlyZiczac(),
                        new FollowShotBehavior());
                break;
        }
        return enemyController;
    }

    public EnemyState getEnemyState() {
        return enemyState;
    }

    public void setEnemyState(EnemyState enemyState) {
        this.enemyState = enemyState;
    }

    @Override
    public void run() {

        switch (this.enemyState) {
            case NORMAL:
                super.run();
                break;
            case FREZEED:
                break;
        }
        if (model.isExplosive()) {
            if (!((EnemyView) view).explode()) {
                model.destroy();
            }
        }
        if (freezeBehavior != null) {
            freezeBehavior.run(this);
        }
        if (flyBehavior != null) {
            flyBehavior.fly(this);
        }
        if (shotBehavior != null)
            shotBehavior.doShot(this);
    }

}
