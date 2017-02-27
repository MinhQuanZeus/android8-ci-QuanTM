package views;

import models.PlaneModel;

import java.awt.*;

/**
 * Created by QuanT on 2/27/2017.
 */
public class PlaneView {
    private Image image;

    public PlaneView(Image image) {
        this.image = image;
    }

    public void draw(Graphics graphics, PlaneModel planeModel) {
        graphics.drawImage(image, planeModel.getX(), planeModel.getY(),
                planeModel.getWidth(), planeModel.getHeight(), null);

    }

    public Image getImage() {
        return image;
    }
}
