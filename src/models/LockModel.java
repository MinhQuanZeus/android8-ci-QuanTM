package models;

/**
 * Created by QuanT on 3/7/2017.
 */
public class LockModel extends GameModel {
    public final static int WIDTH = 32;
    public final static int HEIGHT = 32;

    public LockModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public LockModel(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }
}
