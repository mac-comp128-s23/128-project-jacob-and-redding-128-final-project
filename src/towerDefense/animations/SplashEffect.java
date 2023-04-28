package towerDefense.animations;

import java.awt.Color;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import towerDefense.Cat;

public class SplashEffect extends GraphicsGroup implements Animation {
    private Ellipse shape;
    private final CanvasWindow canvas;
    private final List<Cat> targets;
    private final Point position;
    private double radius = 7, scale = 1, runtime = 0;
    private int alpha = 255;
    private final double MAX_RUNTIME = 0.25;

    public SplashEffect(Point position, List<Cat> targets, CanvasWindow canvas) {
        this.canvas = canvas;
        this.targets = targets;
        this.position = position;

        shape = new Ellipse(position.getX(), position.getY(), radius * 2, radius * 2);
        shape.setStroked(false);
        shape.setFillColor(Color.CYAN);
        add(shape);
        canvas.add(this);
        setCenter(position);
    }

    @Override
    public boolean step(double dt) {
        runtime += dt;
        if(runtime >= MAX_RUNTIME) {
            canvas.remove(this);
            return true;
        }
        scale += 0.5;
        if(alpha > 10) {
            alpha -=10;
        } else {
            alpha = 10;
        }
        
        setScale(scale);
        setCenter(position);
        shape.setFillColor(new Color(0, 255, 255, alpha));
        for(Cat cat : targets) {
            if(cat.getCenter().distance(getCenter()) < cat.getRadius() + radius * scale) {
                cat.hit();
            }
        }
        return false;
    }
}
