package towerDefense;

import java.util.List;

import edu.macalester.graphics.*;
import towerDefense.animations.AniManager;
import towerDefense.animations.Projectile;

/**
 * A basic defense unit which supports a single upgrade.
 * @author ReddSaut, Jacob Hellenbrand
 * Images from https://opengameart.org/users/nido
 */
public class BurstTower implements Tower {
    private final double price = 500, radius = 25, range = 100;
    private double fireRate = 0.5; // time between bursts
    private double timeElapsed = 0;
    private Image base = new Image("Tower.png");
    private Image gun = new Image("Cannon.png");
    private AniManager aniManager;
    private GraphicsGroup group;

    public BurstTower(double x, double y, AniManager aniManager) {
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

            aniManager.add(new Projectile(group.getCenter(), target.getCenter(), cats, group.getCanvas()));
            return;
        }
        return;
    }

    public double getRange() {
        return range;
    }
    public GraphicsGroup getGroup() {
        return group;
    }

    public double getPrice() {
        return price;
    }

    public void upgrade() {
        fireRate = 0.2;
        gun.setImagePath("Cannon2.png");
    }
}
