package models;

/**
 * Created by QuanT on 2/27/2017.
 */
public class EnemyModel extends GameModelWithHP {
    public static final int DEFAULT_WIDTH = 45;
    public static final int DEFAULT_HEIGHT = 30;

    public EnemyModel(int x, int y, int width, int height) {
        this(x, y, width, height, 3);
    }

    public EnemyModel(int x, int y, int width, int height, int maxHP) {
        super(x, y, width, height, maxHP);
    }

    public EnemyModel(int x, int y, int maxHP) {
        this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, maxHP);
    }

    public EnemyModel(int x, int y) {
        this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
