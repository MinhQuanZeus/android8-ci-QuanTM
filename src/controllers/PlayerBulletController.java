package controllers;

import models.PlayerBulletModel;
import utils.Utils;
import views.PlayerBulletView;
import java.awt.*;

/**
 * Created by QuanT on 2/26/2017.
 */
public class PlayerBulletController {

    private PlayerBulletModel model;
    private PlayerBulletView view;

    public PlayerBulletController(PlayerBulletModel model, PlayerBulletView view) {
        this.model = model;
        this.view = view;
    }

    public PlayerBulletController(int x, int y) {
        this(new PlayerBulletModel(x, y, 13, 30),
                new PlayerBulletView(Utils.loadImageFromRes("bullet.png")));
    }

    public void run() {
        model.fly();
    }

    public void draw(Graphics graphics) {
        view.draw(graphics, model);
    }

    public int getX(){
        return model.getX();
    }
    public int getY(){
        return model.getY();
    }
}
