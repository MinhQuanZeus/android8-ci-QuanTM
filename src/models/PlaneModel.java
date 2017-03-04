package models;

/**
 * Created by QuanT on 2/27/2017.
 */
public class PlaneModel extends GameModel {
    public static final int WIDTH = 70;
    public static final int HEIGHT = 50;

    public PlaneModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public PlaneModel(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }
}
