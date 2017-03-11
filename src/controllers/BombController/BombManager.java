package controllers.BombController;

import controllers.ControllerManager;
import utils.GameSetting;
import utils.Utils;

/**
 * Created by QuanT on 3/6/2017.
 */
public class BombManager extends ControllerManager {
    private int count;
    private static final int BOMB_PERIOD = 500;
    private static final int LOCK_PERIOD = 450;

    private BombManager() {
        super();
    }

    @Override
    public void run() {
        super.run();
        count++;
        if (count >= BOMB_PERIOD) {
            count = 0;
            BombController bombController = BombController.create(Utils.getRandom(GameSetting.SCREEN_WIDTH), -10);
            this.add(bombController);
        } else if (count == LOCK_PERIOD) {
            LockController lockController = LockController.create(Utils.getRandom(GameSetting.SCREEN_WIDTH), -10);
            this.add(lockController);
        }
    }

    public final static BombManager instance = new BombManager();
}
