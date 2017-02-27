package views;


import models.IslandModel;

import java.awt.*;

/**
 * Created by QuanT on 2/27/2017.
 */
public class IslandView {
    private Image image;

    public IslandView(Image image) {
        this.image = image;
    }

    public void draw(Graphics graphics, IslandModel islandModel) {
        graphics.drawImage(image, islandModel.getX(), islandModel.getY(),
                islandModel.getWidth(), islandModel.getHeight(), null);

    }
}
