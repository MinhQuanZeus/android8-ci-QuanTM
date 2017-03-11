package controllers;

import models.IslandModel;
import utils.GameSetting;
import utils.Utils;
import views.GameView;

/**
 * Created by QuanT on 3/4/2017.
 */
public class IslanManager extends ControllerManager {
    private int count;
    private static final int RESPAWN = 200;

    private IslanManager() {
        super();
    }

    @Override
    public void run() {
        super.run();
        count++;
        if (count == RESPAWN) {
            count = 0;
            switch (Utils.getRandom(1)) {
                case 0:
                    IslandController islandController = new IslandController(new IslandModel(Utils.getRandom(GameSetting.SCREEN_WIDTH), -10, 5), new GameView("island.png"));
                    this.add(islandController);
                case 1:
                    IslandController islandController1 = new IslandController(new IslandModel(Utils.getRandom(GameSetting.SCREEN_WIDTH), -10, 5), new GameView("island-2.png"));
                    this.add(islandController1);
            }
        }
    }

    public final static IslanManager instance = new IslanManager();
}
