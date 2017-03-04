package models;

import controllers.GameVector;

import java.awt.*;

/**
 * Created by QuanT on 2/28/2017.
 */
public abstract class GameModel {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected boolean isAlive;

    public GameModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isAlive = true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getMiddleX() {
        return this.x + this.width / 2;
    }

    public int getBottom() {
        return  this.y + this.height;
    }


    public void move(GameVector gameVector) {
        this.x += gameVector.dx;
        this.y += gameVector.dy;
    }

    public boolean overlap(GameModel gameObject) {
        Rectangle rect1 = this.getRect();
        Rectangle rect2 = gameObject.getRect();
        return rect1.intersects(rect2);
    }

    private Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public void destroy() {
        this.isAlive = false;
    }


}
