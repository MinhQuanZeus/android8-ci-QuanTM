import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by QuanT on 2/25/2017.
 */
public class Utilities {
    public Image loadImageFromRes(String url) {
        try {
            Image image = ImageIO.read(new File("resources/" + url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
    public int getRandom(int max) {
        Random random = new Random();
        int a;
        a = random.nextInt(max);
        return a;
    }
}
