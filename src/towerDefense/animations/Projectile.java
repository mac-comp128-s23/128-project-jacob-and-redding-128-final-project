package towerDefense.animations;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import towerDefense.*;

/**
 * A projectile used exclusively by the BurstTower class
 * @author ReddSaut
 */
public class Projectile extends GraphicsGroup implements Animation {
    private final double movementRate = 5, radius = 5, upperXBound, upperYBound;
    private final Point delta;
    private final List<Cat> targets;
    private final CanvasWindow canvas;

    /**
     * This will remove up to one target from the passed list on collision
     * @param origin
     * @param destination
     * @param targets
     * @param canvas
     */
    public Projectile(Point origin, Point destination, List<Cat> targets, CanvasWindow canvas) {
        this.targets = targets;
        this.upperXBound = canvas.getWidth();
        this.upperYBound = canvas.getHeight();
        this.canvas = canvas;

        Point vector = destination.subtract(origin);
        vector = Helpers.divide(vector, vector.magnitude());
        delta = Helpers.multiply(vector, movementRate);
        
        Ellipse shape = new Ellipse(0, 0, radius * 2, radius * 2);
        shape.setStroked(false);
        shape.setFillColor(Color.BLUE);
        add(shape);
        setCenter(origin);
        canvas.add(this);
    }

    private boolean isInBounds() {
        return getX() >= 0 && getX() <= upperXBound && getY() >= 0 && getY() <= upperYBound;
    }
    @Override
    public boolean step(double dt) {
        if(!isInBounds()) {
            canvas.remove(this);
            return true;
        }
        Iterator<Cat> itr = targets.iterator();
        while(itr.hasNext()) {
            Cat cat = itr.next();
            if(cat.getCenter().distance(getCenter()) < cat.getRadius() + 10) {
                cat.hit();
                canvas.remove(this);
                return true;
            }
        }
        moveBy(delta);
        return false;
    }
}
