package controllers.EnemyControllers;

import controllers.ControllerManager;
import utils.GameSetting;
import utils.Utils;

/**
 * Created by QuanT on 3/4/2017.
 */
public class EnemyManage extends ControllerManager {
    private int count;
    private static final int RESPAWN_TYPE1 = 25;
    private static final int RESPAWN_TYPE2 = 150;

    private EnemyManage() {
        super();
    }
    @Override
    public void run() {
        super.run();
        count++;
        int enemyX = 5;
        int enemyY = 5;
        if(count == RESPAWN_TYPE2) {
            count = 0;
            for (int i = 0; i < 4; i++) {
                EnemyController enemyController = EnemyController.create(enemyX,enemyY, EnemyPlaneType.WHITE);
                this.add(enemyController);
                enemyX += 100;
            }
        }
        else if (count == RESPAWN_TYPE1) {
            for (int i = 0; i < 4; i++) {
                EnemyController enemyController = EnemyController.create(enemyX,enemyY,EnemyPlaneType.YELLOW);
                this.add(enemyController);
                enemyX += 150;
            }
        }
    }
    public final static EnemyManage instance = new EnemyManage();
}
