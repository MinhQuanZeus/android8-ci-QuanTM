package controllers.EnemyControllers;

/**
 * Created by QuanT on 3/7/2017.
 */
public class FreezeBehavior {
    private int count;
    private int frezzePeriod;

    public FreezeBehavior(int frezzePeriod) {
        this.frezzePeriod = frezzePeriod;
        this.count = 0;
    }

    public void run(EnemyController enemyController) {
        switch (enemyController.getEnemyState()) {
            case NORMAL:
                break;
            case FREZEED:
                count++;
                System.out.println("Count: "+count);
                if(count > frezzePeriod) {
                    count = 0;
                    enemyController.setEnemyState(EnemyState.NORMAL);
                }
                break;
        }
    }
}
