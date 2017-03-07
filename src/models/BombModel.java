package models;

/**
 * Created by QuanT on 3/6/2017.
 */
public class BombModel extends GameModel {
    public final static int WIDTH = 40;
    public final static int HEIGHT = 40;
    public BombModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public BombModel(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }
}
