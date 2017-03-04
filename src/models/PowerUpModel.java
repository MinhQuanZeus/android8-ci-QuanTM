package models;

/**
 * Created by QuanT on 3/4/2017.
 */
public class PowerUpModel extends GameModel {
    public static final int SIZE = 35;
    public PowerUpModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public PowerUpModel(int x, int y) {
        super(x, y, SIZE, SIZE);
    }
}
