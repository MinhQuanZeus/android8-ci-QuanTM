package views;

import models.GroundModel;

import java.awt.*;

/**
 * Created by QuanT on 2/27/2017.
 */
public class GroundView {
    private Image image;

    public GroundView(Image image) {
        this.image = image;
    }

    public void draw(Graphics graphics, GroundModel model) {
        graphics.drawImage(image, model.getX(), model.getY(),
                model.getWidth(), model.getHeight(), null);

    }
}
