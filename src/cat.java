import java.util.ArrayDeque;
import java.util.Deque;

import edu.macalester.graphics.*;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;


public class cat extends Image {

    private Deque<Point> path;
    private Path path2;
    private double speed;
    private cat cat;
    private CanvasWindow canvas;

    public cat(double x, double y, double speed){
        super(x,y);
        this.speed = speed;
        path = new ArrayDeque<Point>();
        cat = new cat(x, y, speed);
        setImagePath("kittycatpurr.png");
        setMaxHeight(40);
        setMaxWidth(40);
        //moveCat(canvas);
    }

    public void addToCanvas(CanvasWindow canvas){
        canvas.add(this);
    }

    public Image getCat(){
        return this;
    }

    public Deque<Point> getPath(){
        path = path2.makePath();
        return path;
    }

    public void moveCat(CanvasWindow canvas, Deque<Point> path){
        makePath();
        canvas.add(cat);
        for(Point point : path){
            cat.moveBy(point.getX(), point.getY());
        }
    }

    public Deque<Point> makePath(){
        path.addFirst(new Point(480, 220));
        path.addFirst(new Point(440, 220));
        path.addFirst(new Point(400, 220));
        path.addFirst(new Point(400, 260));
        path.addFirst(new Point(400, 300));
        path.addFirst(new Point(400, 340));
        path.addFirst(new Point(400, 380));
        path.addFirst(new Point(360, 380));
        path.addFirst(new Point(320, 380));
        path.addFirst(new Point(280, 380));
        path.addFirst(new Point(280, 340));
        path.addFirst(new Point(280, 300)); //Annyoing that I did not realize 500 is not divisible by 40
        path.addFirst(new Point(280, 260)); //  later problem
        path.addFirst(new Point(280, 220));
        path.addFirst(new Point(280, 180));
        path.addFirst(new Point(280, 140));
        path.addFirst(new Point(280, 100));
        path.addFirst(new Point(240, 100));
        path.addFirst(new Point(200, 100));
        path.addFirst(new Point(160, 100));
        path.addFirst(new Point(120, 100));
        path.addFirst(new Point(120, 140));
        path.addFirst(new Point(120, 180));
        path.addFirst(new Point(120, 220));
        path.addFirst(new Point(120, 260));
        path.addFirst(new Point(80, 260));
        path.addFirst(new Point(40, 260));
        path.addFirst(new Point(0, 260));
        return path;
    }
    


}
