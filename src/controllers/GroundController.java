package controllers;

import models.GroundModel;
import utils.GameSetting;
import utils.Utils;
import views.GroundView;

import java.awt.*;

/**
 * Created by QuanT on 2/25/2017.
 */
public class GroundController {
    private Image groundImage;

    private int x1, y1, x2, y2;
    private Utils util;


    public GroundController() {
        util = new Utils();
        groundImage = util.loadImageFromRes("background.png");
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = y1 - GameSetting.SCREEN_HIGHT;
    }


    public void Update() {
        y1 += 1;
        y2 += 1;
        if (y2 > GameSetting.SCREEN_HIGHT) {
            y2 = y1 - GameSetting.SCREEN_HIGHT;
        }
        if (y1 > GameSetting.SCREEN_HIGHT) {
            y1 = y2 - GameSetting.SCREEN_HIGHT;
        }
    }

    public void Paint(Graphics graphics) {
        graphics.drawImage(groundImage, x1, y1, GameSetting.SCREEN_WIDTH, GameSetting.SCREEN_HIGHT, null);
        graphics.drawImage(groundImage, x2, y2, GameSetting.SCREEN_WIDTH, GameSetting.SCREEN_HIGHT, null);
    }

    public int getXGround() {
        return x1;
    }


}
