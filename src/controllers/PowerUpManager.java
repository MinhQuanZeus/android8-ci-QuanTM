package controllers;

import models.PowerUpModel;
import utils.GameSetting;
import utils.Utils;
import views.GameView;

/**
 * Created by QuanT on 3/4/2017.
 */
public class PowerUpManager extends ControllerManager {
    private int count;
    private static final int RESPAWN = 300;

    private PowerUpManager() {
        super();
    }

    @Override
    public void run() {
        super.run();
        count++;
        if (count == RESPAWN) {
            count = 0;
            PowerUpController powerUpController = new PowerUpController(new PowerUpModel(Utils.getRandom(GameSetting.SCREEN_WIDTH), -4), new GameView("power-up.png"));
            this.add(powerUpController);
        }
    }

    public final static PowerUpManager instance = new PowerUpManager();
}
