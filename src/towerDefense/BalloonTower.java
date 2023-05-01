package towerDefense;

import java.util.List;

import towerDefense.animations.AniManager;
import towerDefense.animations.SplashProjectile;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;

/**
 * An area-of-effect type tower with a large range and a slow rate of fire.
 * @author ReddSaut
 * Images from https://opengameart.org/users/nido
 */
public class BalloonTower implements Tower {
    private final double price = 700, radius = 25, range = 300;
    private double fireRate = 1.5; // time between bursts
    private double timeElapsed = 0;
    private Image base = new Image("Tower.png");
    private Image gun = new Image("Missile_Launcher.png");
    private AniManager aniManager;
    private GraphicsGroup group;

    public BalloonTower(double x, double y, AniManager aniManager) {
        this.aniManager = aniManager;
        group = new GraphicsGroup();
        gun.setMaxHeight(radius * 3);
        base.setMaxHeight(radius * 2);
        gun.setCenter(0, 0);
        base.setCenter(0, 0);
        group.setCenter(x, y);
        group.add(base);
        group.add(gun);
    }

    @Override
    public void step(double dt, List<Cat> cats) {
        if (group.getCanvas() == null) {
            return;
        }
        timeElapsed += dt;
        if (timeElapsed >= fireRate) {
            timeElapsed = 0;
            Cat target = null;
            for (Cat cat : cats) {
                if (Helpers.isInRange(group.getCenter(), cat.getCenter(), range, cat.getRadius()) && !cat.isHit()) {
                    target = cat;
                    break;
                }
            }
            if (target == null) {
                return;
            }
            gun.setRotation(Math.toDegrees(target.getCenter().subtract(group.getCenter()).angle()) + 90);

            aniManager.add(new SplashProjectile(group.getCenter(), target.getCenter(), cats, group.getCanvas(), aniManager));
            return;
        }
        return;
    }

    @Override
    public double getRange() {
        return range;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public GraphicsGroup getGroup() {
        return group;
    }

    @Override
    public void upgrade() {
        throw new UnsupportedOperationException("Unimplemented method 'upgrade'");
    }
    
}
