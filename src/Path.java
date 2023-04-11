// package towerDefense;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;

import edu.macalester.graphics.*;

public class Path {
    
    private  Rectangle pathRectangle;
    private Rectangle dragRectangle;
    private  Deque<Point> travel;
    private  CanvasWindow canvas;
    private cat cat;


    public static void main(String[] args) {
        Path squa = new Path();
        
    }

    public Path(){
        travel = new ArrayDeque<Point>();
        canvas = new CanvasWindow("Title", 800, 500);
        //This is the rectangle I envisioned the water defense towers being on for user
        // to drag them off of. 
        dragRectangle = new Rectangle(500, 0,300,500);
        dragRectangle.setFillColor(Color.BLUE);
        canvas.add(dragRectangle);
        path(travel);
        cat = new cat(100, 100,10);
        cat.addToCanvas(canvas);
        cat.moveCat(canvas, travel);
    }

    public Deque<Point> makePath(){
        travel.addFirst(new Point(480, 220));
        travel.addFirst(new Point(440, 220));
        travel.addFirst(new Point(400, 220));
        travel.addFirst(new Point(400, 260));
        travel.addFirst(new Point(400, 300));
        travel.addFirst(new Point(400, 340));
        travel.addFirst(new Point(400, 380));
        travel.addFirst(new Point(360, 380));
        travel.addFirst(new Point(320, 380));
        travel.addFirst(new Point(280, 380));
        travel.addFirst(new Point(280, 340));
        travel.addFirst(new Point(280, 300)); //Annyoing that I did not realize 500 is not divisible by 40
        travel.addFirst(new Point(280, 260)); //  later problem
        travel.addFirst(new Point(280, 220));
        travel.addFirst(new Point(280, 180));
        travel.addFirst(new Point(280, 140));
        travel.addFirst(new Point(280, 100));
        travel.addFirst(new Point(240, 100));
        travel.addFirst(new Point(200, 100));
        travel.addFirst(new Point(160, 100));
        travel.addFirst(new Point(120, 100));
        travel.addFirst(new Point(120, 140));
        travel.addFirst(new Point(120, 180));
        travel.addFirst(new Point(120, 220));
        travel.addFirst(new Point(120, 260));
        travel.addFirst(new Point(80, 260));
        travel.addFirst(new Point(40, 260));
        travel.addFirst(new Point(0, 260));
        return travel;
    }


    public void path(Deque<Point> pathFollow){
        //For later implementation we can have an if statement here depending on user
        //selection on what path will be used, possibly new method for it
        
        makePath();
        for(Point point : pathFollow){
            pathRectangle = new Rectangle(0, 0, 40, 40);
            pathRectangle.setFillColor(Color.gray);
            pathRectangle.setCenter(point);
            canvas.add(pathRectangle);
        }

    }


}
