package controllers.EnemyControllers;

import controllers.PlaneController;
import models.EnemyBulletModel;
import models.GameModel;
import views.GameView;

/**
 * Created by QuanT on 3/4/2017.
 */
public class NormalShotBehavior implements ShotBehavior {
    private final static int BULLET_SPEED = 4;
    private final static int SHOT_PERIOD = 100;
    private int count;
    @Override
    public void doShot(EnemyController enemyController) {
        count++;
        if(count>= SHOT_PERIOD){
            count=0;
            GameModel model = enemyController.getModel();
            EnemyBulletController enemyBulletController =
                    new EnemyBulletController(
                            new EnemyBulletModel(
                                    model.getMiddleX() - EnemyBulletModel.SIZE / 2,
                                    model.getBottom()),
                            new GameView("enemy_bullet.png")
                    );
                enemyBulletController.getVector().dy = BULLET_SPEED;
                EnemyBulletManage.instance.add(enemyBulletController);
        }
    }
}
