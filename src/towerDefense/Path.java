package towerDefense;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import edu.macalester.graphics.*;

/**
 * A path cats follow and towers politely cannot be placed on
 * @author Jacob Hellenbrand
 */
public class Path {
    private GraphicsGroup visuals;
    private Rectangle pathRectangle;
    private Rectangle dragRectangle;
    private Deque<Point> points;

    public Path(){
        visuals = new GraphicsGroup();
        points = new ArrayDeque<Point>();
        dragRectangle = new Rectangle(500, 0,300,500);
        dragRectangle.setFillColor(Color.cyan.darker());
        visuals.add(dragRectangle);
        makePath();
        makeVisuals(points);
    }

    public Deque<Point> makePath(){
        points.addFirst(new Point(480, 220));
        points.addFirst(new Point(440, 220));
        points.addFirst(new Point(400, 220));
        points.addFirst(new Point(400, 260));
        points.addFirst(new Point(400, 300));
        points.addFirst(new Point(400, 340));
        points.addFirst(new Point(400, 380));
        points.addFirst(new Point(360, 380));
        points.addFirst(new Point(320, 380));
        points.addFirst(new Point(280, 380));
        points.addFirst(new Point(280, 340));
        points.addFirst(new Point(280, 300)); 
        points.addFirst(new Point(280, 260)); 
        points.addFirst(new Point(280, 220));
        points.addFirst(new Point(280, 180));
        points.addFirst(new Point(280, 140));
        points.addFirst(new Point(280, 100));
        points.addFirst(new Point(240, 100));
        points.addFirst(new Point(200, 100));
        points.addFirst(new Point(160, 100));
        points.addFirst(new Point(120, 100));
        points.addFirst(new Point(120, 140));
        points.addFirst(new Point(120, 180));
        points.addFirst(new Point(120, 220));
        points.addFirst(new Point(120, 260));
        points.addFirst(new Point(80, 260));
        points.addFirst(new Point(40, 260));
        points.addFirst(new Point(0, 260));
        return points;
    }

    public Deque<Point> getPoints() {
        return points;
    }

    public void makeVisuals(Deque<Point> pathFollow){
        for(Point point : pathFollow){
            pathRectangle = new Rectangle(0, 0, 40, 40);
            pathRectangle.setFillColor(Color.gray);
            pathRectangle.setCenter(point);
            visuals.add(pathRectangle);
        }
    }
    public GraphicsGroup getVisuals() {
        return visuals;
    }
}
