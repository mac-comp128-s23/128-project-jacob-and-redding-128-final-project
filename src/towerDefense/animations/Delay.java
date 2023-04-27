package towerDefense.animations;

public class Delay implements Animation {
    private final Runnable context;
    private final double delay;
    private double timeElapsed = 0;

    public Delay(Runnable context, double delay) {
        this.context = context;
        this.delay = delay;
    }

    @Override
    public boolean step(double dt) {
        timeElapsed += dt;

        if(timeElapsed >= delay) {
            context.run();
            return true;
        } else {
            return false;
        }
    }
    
}
