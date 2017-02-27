package models;

/**
 * Created by QuanT on 2/27/2017.
 */
public class PlaneModel {
    private static final int SPEED = 6;
    private int x;
    private int y;
    private int width;
    private int height;

    public PlaneModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void moveLeft(){
        this.x-=SPEED;
    }

    public void moveRight(){
        this.x+=SPEED;
    }

    public void moveUp(){
        this.y-=SPEED;
    }

    public void moveDown(){
        this.y+=SPEED;
    }
}
