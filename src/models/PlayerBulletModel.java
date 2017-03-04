package models;

/**
 * Created by QuanT on 2/26/2017.
 */
public class PlayerBulletModel extends GameModel {
    private static final int SPEED = 15;
    public final static int WIDTH = 13;
    public final static int HEIGHT = 30;
    private int damage;

    public PlayerBulletModel(int x, int y) {
        this(x, y, 2);
    }

    public PlayerBulletModel(int x, int y, int damage) {
        super(x, y, WIDTH, HEIGHT);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
