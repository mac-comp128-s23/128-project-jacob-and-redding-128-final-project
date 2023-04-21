import java.awt.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import edu.macalester.graphics.*;

public class Cat extends Image {

    private Deque<Point> pathPoints;
    private double stepSize = 5;
    Boolean running = true;
    

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
        pathPoints.addLast(new Point (560,220));
        Point last = pathPoints.getLast();
        if(!pathPoints.isEmpty() && center.distance(target) <= stepSize) {
            pathPoints.pop();
            target = pathPoints.peek();     
        }
        setCenter(Point.interpolate(center, target, stepSize / center.distance(target)));
        if(pathPoints.size() == 2){
            setCenter(last);
        }
    }

    public ArrayList<Cat> createEnemies(Path path, int round){
        double numCats = round * round ;
        ArrayList<Cat> enemies = new ArrayList<Cat>();
        while(numCats >= enemies.size()){
            enemies.add(new Cat(-50, pathPoints.getFirst().getY(), stepSize, path));
        }
        return enemies;
    }

    public void moveCats(Path path, int round, CanvasWindow canvas){
        System.out.println("Running");
        
        ArrayList<Cat> kitties = createEnemies(path, round);
        for(Cat cat : kitties){
            System.out.println("new kitty");
            canvas.add(cat);
            canvas.animate(()->{
                if(running == true) {
                    cat.step(); 
                    //System.out.println("Cat stepping");
                }
            });
        }
        if(kitties.isEmpty()){
            running = false;
        }
    }
}
