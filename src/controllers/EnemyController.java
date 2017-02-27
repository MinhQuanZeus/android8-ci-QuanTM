package controllers;

import models.EnemyModel;
import utils.Utils;
import views.EnemyView;

import java.awt.*;

/**
 * Created by QuanT on 2/27/2017.
 */
public class EnemyController {
    private EnemyModel model;
    private EnemyView view;

    public EnemyController(EnemyModel model, EnemyView view) {
        this.model = model;
        this.view = view;
    }

    public EnemyController(int x, int y) {
        this(new EnemyModel(x, y, 50, 40),
                new EnemyView(Utils.loadImageFromRes("plane1.png")));
    }

    public void run() {
        model.run();
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
    public Image getImage(){
        return view.getImage();
    }
    public int getWidth(){
        return model.getWidth();
    }
    public int getHeight(){
        return model.getHeight();
    }
}
