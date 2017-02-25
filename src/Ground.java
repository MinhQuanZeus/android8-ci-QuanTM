import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by QuanT on 2/25/2017.
 */
public class Ground {
    private Image groundImage;
    private Image islandImage;

    private int x1, y1, x2, y2;
    Utilities util;

    public Ground() {
        util = new Utilities();
            groundImage = util.loadImageFromRes("background.png");
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = y1-600;
    }


    public void Update() {
        y1+=1;
        y2+=1;
        if (y2 >600) {
            y2 = y1 - 600;
        }
        if (y1>600) {
            y1 = y2 -600;
        }
    }

    public void Paint(Graphics graphics) {
        graphics.drawImage(groundImage, x1, y1,400,600, null);
        graphics.drawImage(groundImage, x2, y2,400,600, null);
    }
    public int getXGround(){
        return x1;
    }


}
