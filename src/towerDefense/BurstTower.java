package towerDefense;
import java.util.List;

import edu.macalester.graphics.*;
import towerDefense.animations.AniManager;
import towerDefense.animations.Projectile;

public class BurstTower extends Tower {
    private final double price = 500, radius = 25, range = 100;
    private final double fireRate = 0.2; //time between bursts
    private double timeElapsed = 0;
    private Image base = new Image("Tower.png");
    private Image gun = new Image("Cannon.png");
    private AniManager aniManager;

    public BurstTower(double x, double y, AniManager aniManager) {
        this.aniManager = aniManager;
        gun.setMaxHeight(radius * 3);
        base.setMaxHeight(radius * 2);
        gun.setCenter(0, 0);
        base.setCenter(0, 0);
        setCenter(x, y);
        add(base);
        add(gun);

    }

    public Cat step(double dt, List<Cat> cats) {
        if(getCanvas() == null) {
            return null;
        }
        timeElapsed += dt;
        if(timeElapsed >= fireRate) {
            timeElapsed = 0;
            Cat target = null;
            for(Cat cat : cats) { //TODO: order?
                if(Helpers.isInRange(getCenter(), cat.getCenter(), range, cat.getWidth() / 2));
                target = cat;
                break;
            }

            gun.setRotation(target.getCenter().subtract(getCenter()).angle());
            
            aniManager.add(new Projectile(getCenter(), target.getCenter(), 
                            getCanvas().getWidth(), getCanvas().getHeight(), getCanvas()));
            return target;
        }
        return null;
    }
}
