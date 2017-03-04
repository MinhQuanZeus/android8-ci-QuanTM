package controllers.EnemyControllers;

import controllers.PlaneController;
import models.EnemyBulletModel;
import models.GameModel;
import views.GameView;

/**
 * Created by QuanT on 3/4/2017.
 */
public class FollowShotBehavior implements ShotBehavior {
    private final static int BULLET_SPEED = 4;
    private final static int SHOT_PERIOD = 150;
    private int count;
    @Override
    public void doShot(EnemyController enemyController) {
        count++;
        if(count>= SHOT_PERIOD){
            count=0;
            GameModel gameObject = enemyController.getModel();
            EnemyBulletController enemyBulletController =
                    new EnemyBulletController(
                            new EnemyBulletModel(
                                    gameObject.getMiddleX() - EnemyBulletModel.SIZE / 2,
                                    gameObject.getBottom()),
                            new GameView("enemy_bullet.png")
                    );
            int dx = PlaneController.instance.getModel().getX() -
                    gameObject.getX();
            int dy = PlaneController.instance.getModel().getY() -
                    gameObject.getY();

            if (dy > 0) {
                double ratio = Math.sqrt(dx * dx + dy * dy) / BULLET_SPEED;

                enemyBulletController.getVector().dy = (int) (dy / ratio);
                enemyBulletController.getVector().dx = (int) (dx / ratio);

                EnemyBulletManage.instance.add(enemyBulletController);
            }
        }
    }
}
