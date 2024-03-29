package towerDefense;

import java.util.List;

import edu.macalester.graphics.GraphicsGroup;

/**
 * Framework for denfense units
 * @author ReddSaut
 */
public interface Tower {
    /**
     * Per-frame behavior of a tower. Returns cats which can be removed from canvas.
     * @param dt
     * @param cats
     * @return
     */
    void step(double dt, List<Cat> cats);

    double getRange();

    double getPrice();

    /**
     * Returns the tower's visual information.
     * @return
     */
    GraphicsGroup getGroup();

    void upgrade();
}
