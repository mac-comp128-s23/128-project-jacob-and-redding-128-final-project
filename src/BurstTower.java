import edu.macalester.graphics.*;

public class BurstTower extends GraphicsGroup implements Tower {
    private double price = 500, radius = 25;
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
        
        System.out.println("Gun anchor: " + gun.getAnchor());
        System.out.println("Gun center: " + gun.getCenter());

    }

    public Runnable getRunningAnimation() {
        return (()-> {
            gun.rotateBy(5);
        });
    }
}
