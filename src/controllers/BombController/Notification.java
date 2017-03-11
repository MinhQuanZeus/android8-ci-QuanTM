package controllers.BombController;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by QuanT on 3/6/2017.
 */
public class Notification {
    private Vector<BombSubcriber> subscribers;
    private Vector<FreezzeSubcriber> freezzeSubcribers;

    public Notification() {
        subscribers = new Vector<BombSubcriber>();
        freezzeSubcribers = new Vector<FreezzeSubcriber>();
    }

    public void subsribe(BombSubcriber bombSubscriber) {
        subscribers.add(bombSubscriber);
    }

    public void subsribeFrezze(FreezzeSubcriber bombSubscriber) {
        freezzeSubcribers.add(bombSubscriber);
    }

    public void onBomExpode(int x, int y) {
        Iterator<BombSubcriber> bombSubscriberIterator = subscribers.iterator();
        while (bombSubscriberIterator.hasNext()) {
            BombSubcriber bombSubscriber = bombSubscriberIterator.next();
            if (!bombSubscriber.getGameModel().isAlive()) {
                bombSubscriberIterator.remove();
            } else {
                bombSubscriber.onBombExplode(x, y);
            }
        }
    }

    public void onFrezze(int x, int y) {
        Iterator<FreezzeSubcriber> bombSubscriberIterator = freezzeSubcribers.iterator();
        while (bombSubscriberIterator.hasNext()) {
            FreezzeSubcriber freezzeSubcriber = bombSubscriberIterator.next();
            if (!freezzeSubcriber.getGameModel().isAlive()) {
                bombSubscriberIterator.remove();
            } else {
                freezzeSubcriber.onFrezze(x, y);
            }
        }
    }

    public static final Notification instance = new Notification();
}
