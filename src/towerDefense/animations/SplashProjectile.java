package towerDefense.animations;

import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import towerDefense.Cat;

/**
 * A projectile used by BalloonTower. Has no collision and is mostly visual.
 * @author ReddSaut
 */
public class SplashProjectile extends GraphicsGroup implements Animation {
    private final double movementRate = 7, radius = 10;
    private final List<Cat> targets;
    private final CanvasWindow canvas;
    private final Point destination;
    private final AniManager aniManager;

    public SplashProjectile(Point origin, Point destination, List<Cat> targets, CanvasWindow canvas, AniManager aniManager) {
        this.targets = targets;
        this.canvas = canvas;
        this.destination = destination;
        this.aniManager = aniManager;

        Image balloon = new Image("Red_Balloon.png");
        balloon.setMaxHeight(radius * 2);
        balloon.setRotation(Math.random() * 360);
        add(balloon);
        setCenter(origin);
        canvas.add(this);
    }

    @Override
    public boolean step(double dt) {
        if(getCenter().distance(destination) <= 50) {
            aniManager.add(new SplashEffect(getCenter(), targets, canvas));
            canvas.remove(this);
            return true;
        }
        setCenter(Point.interpolate(getCenter(), destination, movementRate / getCenter().distance(destination)));
        return false;
    }
}
