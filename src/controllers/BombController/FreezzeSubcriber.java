package controllers.BombController;

import models.GameModel;

/**
 * Created by QuanT on 3/7/2017.
 */
public interface FreezzeSubcriber {
    void onFrezze(int x, int y);

    GameModel getGameModel();
}
