import java.util.Deque;
import edu.macalester.graphics.*;

public class Cat extends Image {

    private Deque<Point> pathPoints;
    private double stepSize = 5;
    

    public Cat(double x, double y, double stepSize, Path path){
        super(x,y);
        this.stepSize = stepSize;
        pathPoints = path.getPoints();
        setImagePath("orangeCat.png");
        setMaxHeight(50);
        setMaxWidth(50);
    }

    public void addToCanvas(CanvasWindow canvas){
        canvas.add(this);
    }

    public Deque<Point> getPath(){
        return pathPoints;
    }

    public boolean isAtGoal() {
        return pathPoints.isEmpty();
    }

    public void step() { // stop when there are no more points
        Point center = getCenter();
        Point target = pathPoints.peek();

        if(!pathPoints.isEmpty() && center.distance(target) <= stepSize) {
            pathPoints.pop();
            target = pathPoints.peek();
        }
        setCenter(Point.interpolate(center, target, stepSize / center.distance(target)));
    }
}
