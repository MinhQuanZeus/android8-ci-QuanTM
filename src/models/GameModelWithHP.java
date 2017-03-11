package models;

import utils.SoundPlayer;

/**
 * Created by QuanT on 3/4/2017.
 */
public class GameModelWithHP extends GameModel {
    private int hp;
    private int maxHP;
    private boolean isPlaysound = true;

    public GameModelWithHP(int x, int y, int width, int height, int maxHP) {
        super(x, y, width, height);
        this.hp = maxHP;
        this.maxHP = maxHP;
    }

    public int getHp() {
        return hp;
    }

    public void increaseHP(int amount) {
        this.hp += amount;
        if (this.hp > maxHP)
            this.hp = maxHP;
    }


    public void decreaseHP(int amount) {
        this.hp -= amount;
        if (this.hp < 0) {
            explosive();
            if(isPlaysound) {
                SoundPlayer shotSound = new SoundPlayer("explosive.wav");
                shotSound.play();
                isPlaysound = false;
            }
        }
    }

}
