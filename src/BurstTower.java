import java.util.List;

import edu.macalester.graphics.*;

public class BurstTower extends Tower {
    private final double price = 500, radius = 25, range = 100;
    private final double fireRate = 0.2; //time between bursts
    private double timeElapsed = 0;
    private Image base = new Image("Tower.png");
    private Image gun = new Image("Cannon.png");

    public BurstTower(double x, double y) {
        gun.setMaxHeight(radius * 3);
        base.setMaxHeight(radius * 2);
        gun.setCenter(0, 0);
        base.setCenter(0, 0);
        setCenter(x, y);
        add(base);
        add(gun);

    }
    public BurstTower(Tower copy) {
        gun.setMaxHeight(radius * 3);
        base.setMaxHeight(radius * 2);
        gun.setCenter(0, 0);
        base.setCenter(0, 0);
        setCenter(copy.getCenter());
        add(base);
        add(gun);
    }

    public Cat step(double dt, List<Cat> cats) {
        timeElapsed += dt;
        if(timeElapsed >= fireRate) {
            timeElapsed = 0;
            Cat target = null;
            for(Cat cat : cats) { //TODO: order?
                if(Helpers.isInRange(getCenter(), cat.getCenter(), range, cat.getWidth() / 2));
                target = cat;
                break;
            }
            //rotate to target
            //spawn projectile
            return target;
        }
        return null;
    }
}
