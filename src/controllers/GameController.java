package controllers;

import models.GameModel;
import views.GameView;

import java.awt.*;

/**
 * Created by QuanT on 2/28/2017.
 */
public class GameController implements BaseController {
    protected GameModel model;
    protected GameView view;
    protected GameVector vector;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.vector = new GameVector();
    }

    public GameModel getModel() {
        return model;
    }

    public GameVector getVector() {
        return vector;
    }


    @Override
    public void draw(Graphics g) {
        view.draw(g, model);
    }

    public void run() {
        model.move(this.vector);
    }

}
