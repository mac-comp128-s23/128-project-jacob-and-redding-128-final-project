package towerDefense.animations;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import towerDefense.Helpers;

public class Projectile extends GraphicsGroup implements Animation {
    private final double movementRate = 5, radius = 5, upperXBound, upperYBound;
    private final Point destination;
    private final CanvasWindow canvas;

    public Projectile(Point origin, Point destination, double upperXBound, double upperYBound, CanvasWindow canvas) {
        this.destination = destination;
        this.upperXBound = upperXBound;
        this.upperYBound = upperYBound;
        this.canvas = canvas;
        
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
        if(!isInBounds() || Helpers.isInRange(getCenter(), destination, 5, 5)) { 
                                                                //warning: arbitrary range and radius
            canvas.remove(this);
            return true;
        }
        setCenter(Point.interpolate(getCenter(), destination, movementRate / getCenter().distance(destination)));
        return false;
    }
}
