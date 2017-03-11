package controllers;

import models.GameModel;

/**
 * Created by QuanT on 3/2/2017.
 */
public interface Colliable {
    GameModel getGameModel();

    void onCollide(Colliable colliable);
}
