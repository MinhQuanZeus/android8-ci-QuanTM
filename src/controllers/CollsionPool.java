package controllers;

import models.GameModel;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by QuanT on 3/2/2017.
 */
public class CollsionPool implements BaseController {
    private Vector<Colliable> colliableVector;

    private CollsionPool() {
        colliableVector = new Vector<Colliable>();
    }

    public void add(Colliable colliable) {
        this.colliableVector.add(colliable);
    }


    @Override
    public void run() {
        for (int i = 0; i < colliableVector.size() - 1; i++) {
            for (int j = i + 1; j < colliableVector.size(); j++) {
                Colliable ci = colliableVector.get(i);
                Colliable cj = colliableVector.get(j);
                GameModel gi = ci.getGameModel();
                GameModel gj = cj.getGameModel();
                if (gi.overlap(gj)) {
                    ci.onCollide(cj);
                    cj.onCollide(ci);
                }
            }
        }

        Iterator<Colliable> colliableIterator = colliableVector.iterator();
        while (colliableIterator.hasNext()) {
            Colliable colliable = colliableIterator.next();
            if (!colliable.getGameModel().isAlive()) {
                colliableIterator.remove();
            }
        }
    }

    @Override
    public void draw(Graphics g) {

    }

    public static final CollsionPool instance = new CollsionPool();
}
