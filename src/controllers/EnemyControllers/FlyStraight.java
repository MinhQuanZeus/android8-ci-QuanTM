package controllers.EnemyControllers;

/**
 * Created by QuanT on 3/4/2017.
 */
public class FlyStraight implements FlyBehavior {
    public FlyStraight() {

    }

    @Override
    public void fly(EnemyController enemyController) {
        enemyController.getVector().dy =enemyController.SPEED;
    }
}
