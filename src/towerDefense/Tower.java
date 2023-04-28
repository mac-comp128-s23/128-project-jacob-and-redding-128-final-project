package towerDefense;

import java.util.List;

import edu.macalester.graphics.GraphicsGroup;

public interface Tower {
    /**
     * Per-frame behavior of a tower. Returns cats which can be removed from canvas.
     * @param dt
     * @param cats
     * @return
     */
    void step(double dt, List<Cat> cats);

    double getRange();

    /**
     * Returns the tower's visual information.
     * @return
     */
    GraphicsGroup getGroup();

    void upgrade();
}
