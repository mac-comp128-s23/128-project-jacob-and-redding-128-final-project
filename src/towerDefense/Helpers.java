package towerDefense;

import edu.macalester.graphics.Point;
import java.awt.Color;

/**
 * Helpful helpers, to help you out
 * @author ReddSaut
 */
public class Helpers {

    /**
     * translucent cyan
     */
    public static final Color CLEAR_BLUE_SKIES = new Color(0, 255, 255, 100);

    /**
     * translucent red
     */
    public static final Color ERROR_CODE_RED = new Color(255, 0, 0, 100);
    
    public static boolean isInRange(Point origin, Point target, double range, double targetRadius) {
        if(origin.distance(target) <= range + targetRadius) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Divides a point by another point
     * @param p1
     * @param p2
     * @return
     */
    public static Point divide(Point p1, Point p2) {
        return new Point(p1.getX() / p2.getX(), p1.getY() / p2.getY());
    }

    /**
     * Divides a point by the point (p2, p2)
     * @param p1
     * @param p2
     * @return
     */
    public static Point divide(Point p1, double p2) {
        return divide(p1, new Point(p2, p2));
    }

    /**
     * Multiplies a point by another point
     * @param p1
     * @param p2
     * @return
     */
    public static Point multiply(Point p1, Point p2) {
        return new Point(p1.getX() * p2.getX(), p1.getY() * p2.getY());
    }

    /**
     * Multiplies a point by (p2, p2)
     * @param p1
     * @param p2
     * @return
     */
    public static Point multiply(Point p1, double p2) {
        return multiply(p1, new Point(p2, p2));
    }
}
