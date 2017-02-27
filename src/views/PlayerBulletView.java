package views;

import models.PlayerBulletModel;

import java.awt.*;

/**
 * Created by QuanT on 2/26/2017.
 */
public class PlayerBulletView {
    private Image image;

    public PlayerBulletView(Image image) {
        this.image = image;
    }

    public void draw(Graphics graphics, PlayerBulletModel playerBulletModel){
        graphics.drawImage(image,playerBulletModel.getX(),playerBulletModel.getY(),
                playerBulletModel.getWidth(),playerBulletModel.getHeight(),null);

    }
}
