package towerDefense.animations;
/**
 * @author ReddSaut
 */
public interface Animation {

    /**
     * Per-frame behavior of an animation.
     * @param dt passed from Canvaswindow.animate(DoubleConsumer)
     * @return true when the animation can stop running (final frame)
     */
    boolean step(double dt);
}
