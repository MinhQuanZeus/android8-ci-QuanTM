package controllers;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by QuanT on 3/4/2017.
 */
public class ControllerManager implements BaseController {
    private Vector<GameController> gameControllerVector;

    public ControllerManager() {
        gameControllerVector = new Vector<GameController>();
    }

    public void add(GameController gameController) {
        this.gameControllerVector.add(gameController);
    }

    @Override
    public void draw(Graphics g) {
        synchronized (this.gameControllerVector) {
            Iterator<GameController> gameControllerIterator =
                    this.gameControllerVector.iterator();
            while (gameControllerIterator.hasNext()) {
                GameController gameController = gameControllerIterator.next();
                if (gameController.getModel().isAlive()) {
                    gameController.draw(g);
                }
            }
        }
    }

    @Override
    public void run() {
        synchronized (this.gameControllerVector) {
            Iterator<GameController> gameControllerIterator =
                    this.gameControllerVector.iterator();
            while (gameControllerIterator.hasNext()) {
                GameController singleController = gameControllerIterator.next();
                if (!singleController.getModel().isAlive()) {
                    gameControllerIterator.remove();
                } else {
                    singleController.run();
                }
            }
        }
    }
}
