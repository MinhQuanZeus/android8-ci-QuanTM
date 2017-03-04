package models;

/**
 * Created by QuanT on 2/27/2017.
 */
public class EnemyBulletModel extends GameModel {
    public static final int SIZE = 32;

    public EnemyBulletModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public EnemyBulletModel(int x, int y) {
        super(x, y, SIZE, SIZE);
    }
}
