import java.util.ArrayDeque;
import java.util.Deque;

import edu.macalester.graphics.*;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;


public class Cat extends Image {

    private Deque<Point> pathPoints;
    private Path path;
    private double speed;
    private CanvasWindow canvas;
    private double x;
    private double y;
    

    public Cat(double x, double y, double speed){
        super(x,y);
        this.x = x;
        this.y = y;
        this.speed = speed;
        pathPoints = path.
        setImagePath("orangeCat.png");
        setMaxHeight(50);
        setMaxWidth(50);
    }

    public void addToCanvas(CanvasWindow canvas){
        canvas.add(this);
    }

    public Image getCat(){
        return this;
    }

    public Deque<Point> getPath(){
        
        return pathPoints;
    }

    public void moveCat(CanvasWindow canvas, Deque<Point> path, boolean animation) throws InterruptedException{
        for(Point point : path){
            x = point.getX();
            y = point.getY();
            System.out.println("Im Moving");
            //this.moveBy(x , y );
            this.addToCanvas(canvas);
            this.setCenter(x,y);
            
            Thread.sleep(100, 0);
        }
    }
    


}
