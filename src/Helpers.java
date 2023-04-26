import edu.macalester.graphics.Point;

public class Helpers {

    public static boolean isInRange(Point origin, Point target, double range, double targetRadius) {
        if(origin.distance(target) <= range + targetRadius) {
            return true;
        } else {
            return false;
        }
    }
}
