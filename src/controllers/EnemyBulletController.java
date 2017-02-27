package controllers;

import models.EnemyBulletModel;
import utils.Utils;
import views.EnemyBulletView;

import java.awt.*;

/**
 * Created by QuanT on 2/27/2017.
 */
public class EnemyBulletController {
    private EnemyBulletModel model;
    private EnemyBulletView view;

    public EnemyBulletController(EnemyBulletModel model, EnemyBulletView view) {
        this.model = model;
        this.view = view;
    }

    public EnemyBulletController(int x, int y) {
        this(new EnemyBulletModel(x, y, 13, 30),
                new EnemyBulletView(Utils.loadImageFromRes("enemy_bullet.png")));
    }

    public void run() {
        model.fire();
    }

    public void draw(Graphics graphics) {
        view.draw(graphics, model);
    }

    public int getX(){
        return model.getX();
    }
    public int getY(){
        return model.getX();
    }
}
