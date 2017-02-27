package controllers;

import models.PlaneModel;
import utils.Utils;
import views.PlaneView;

import java.awt.*;

/**
 * Created by QuanT on 2/27/2017.
 */
public class PlaneController {
    private PlaneModel model;
    private PlaneView view;

    public PlaneController(PlaneModel model, PlaneView view) {
        this.model = model;
        this.view = view;
    }

    public PlaneController(int x, int y) {
        this(new PlaneModel(x, y, 50, 40),
                new PlaneView(Utils.loadImageFromRes("plane2.png")));
    }

    public void draw(Graphics g){
        view.draw(g,model);
    }

    public void moveLeft(){
        model.moveLeft();
    }
    public void moveDown(){
        model.moveDown();
    }
    public void moveUp(){
        model.moveUp();
    }
    public void moveRight(){
        model.moveRight();
    }

    public int getX(){
        return model.getX();
    }

    public int getY(){
        return model.getY();
    }

    public int getWidth(){
        return model.getWidth();
    }

    public int getHeight(){
        return model.getHeight();
    }
}
