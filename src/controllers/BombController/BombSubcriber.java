package controllers.BombController;

import models.GameModel;

/**
 * Created by QuanT on 3/6/2017.
 */
public interface BombSubcriber {
    public void onBombExplode(int x, int y);

    public GameModel getGameModel();
}
