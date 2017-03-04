package controllers.EnemyControllers;

/**
 * Created by QuanT on 3/4/2017.
 */
public class FlyZiczac implements FlyBehavior {
    private int count;
    public FlyZiczac(){
    }

    @Override
    public void fly(EnemyController enemyController) {
        count++;
        if(count == 1 ) {
            enemyController.getVector().dx = enemyController.SPEED;
            enemyController.getVector().dy = enemyController.SPEED;

        }
        if (enemyController.getModel().getX() >=600 || enemyController.getModel().getX() <= 0){
            enemyController.getVector().dx = -enemyController.getVector().dx;
        }
    }
}
