package towerDefense;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import edu.macalester.graphics.*;
import towerDefense.animations.AniManager;

/**
 * A basic enemy unit
 * @author Jacob Hellenbrand
 */
public class Cat extends Image {

    private Deque<Point> pathPoints;
    private double stepSize = 5;
    private boolean isHit = false;
    public ArrayList<Cat> enemies;
    private AniManager aniManager;
    
    public Cat(double x, double y, double stepSize, Path path, AniManager aniManager) {
        super(x,y);
        this.aniManager = aniManager;
        this.stepSize = stepSize;
        pathPoints = new ArrayDeque<Point>(path.getPoints());
        setImagePath("orangeCat.png");
        setMaxHeight(50);
        setMaxWidth(50);
    }

    public Deque<Point> getPath() {
        return pathPoints;
    }

    public boolean isAtGoal() {
        return pathPoints.isEmpty();
    }

    /**
     * Per-frame behavior of a cat object
     * @param canvas
     */
    public void step(CanvasWindow canvas) { 
        Point center = getCenter();
        Point target = pathPoints.peek();
        pathPoints.addLast(new Point (560,220));
        if(!pathPoints.isEmpty() && center.distance(target) <= stepSize) {
            pathPoints.pop();
            target = pathPoints.peek();
        }
        setCenter(Point.interpolate(center, target, stepSize / center.distance(target)));
    }

    /**
     * Creates a full list of Cat enemies. The lead cat will call their friends to join the fight!
     * @param path
     * @param round
     * @return
     */
    public ArrayList<Cat> createEnemies(Path path, int round) {
        double numCats = round * round;
        enemies = new ArrayList<Cat>();
        while(numCats >= enemies.size()) {
            enemies.add(new Cat(-50, pathPoints.getFirst().getY(), stepSize, path, aniManager));
        }
        addEnemiesToCanvas(getCanvas(), enemies);
        return enemies;
    }

    private void addEnemiesToCanvas(CanvasWindow canvas, ArrayList<Cat> catList ) {
        int spacer = 1;
        for(Cat cat : catList){
            canvas.add(cat, -50-70*spacer,260);
            spacer++;
        }
    }

    /**
     * A cat will run away when it is hit
     */
    public void hit() {
        isHit = true;
        pathPoints.clear();
        pathPoints.offer(getCenter().withX(-300));
        setScale(-1, 1);
    }

    public boolean isHit() {
        return isHit;
    }

    public double getRadius() {
        return 25;
    }
}
