package controllers;

/**
 * Created by QuanT on 3/2/2017.
 */
public class GameVector {
    public int dx;
    public int dy;

    public GameVector() {
        this(0, 0);
    }

    public GameVector(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
