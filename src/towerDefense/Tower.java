package towerDefense;

import java.util.List;

import edu.macalester.graphics.*;

public abstract class Tower extends GraphicsGroup {
    abstract Cat step(double dt, List<Cat> cats);
    abstract double getRange();
}
