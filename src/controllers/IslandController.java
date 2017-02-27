package controllers;

import models.IslandModel;
import models.PlayerBulletModel;
import utils.Utils;
import views.IslandView;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by QuanT on 2/27/2017.
 */
public class IslandController {
    private IslandModel model;
    private IslandView view;

    public IslandController(IslandModel model, IslandView view) {
        this.model = model;
        this.view = view;
    }

    public IslandController(int x, int y) {
        this(new IslandModel(x, y, 30, 35),
                new IslandView(Utils.loadImageFromRes("island.png")));
    }

    public void run() {
        model.run();
    }

    public void draw(Graphics graphics) {
        view.draw(graphics, model);
    }
}
