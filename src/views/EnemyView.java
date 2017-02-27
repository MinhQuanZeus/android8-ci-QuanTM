package views;

import models.EnemyModel;
import models.IslandModel;

import java.awt.*;

/**
 * Created by QuanT on 2/27/2017.
 */
public class EnemyView {
    private Image image;

    public EnemyView(Image image) {
        this.image = image;
    }

    public void draw(Graphics graphics, EnemyModel model) {
        graphics.drawImage(image, model.getX(), model.getY(),
                model.getWidth(), model.getHeight(), null);

    }

    public Image getImage() {
        return image;
    }
}
