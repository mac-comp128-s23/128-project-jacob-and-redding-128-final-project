import java.util.ArrayDeque;
import java.util.Deque;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.ui.Button;

public class towerDefense {

    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 500;

    private CanvasWindow canvas;
    private cat cat;
    private Path path;
    private Button startGame;
    private boolean animation = true;
    private Deque<Point> maybe;

    public static void main(String[] args) {
        new towerDefense();
    }

    public towerDefense(){
        canvas = new CanvasWindow("Tower Defense!", CANVAS_WIDTH, CANVAS_HEIGHT);
        cat = new cat(95,100,.10);
        path = new Path(canvas);
        cat.addToCanvas(canvas);
        startGame();

        maybe = new ArrayDeque<Point>(); //Just put this in to see movement of cat
        maybe.addFirst(new Point(100,100));
        
        
        //cat.addToCanvas(canvas);
    }

    public void startGame(){
        startGame = new Button("Start Round");
        startGame.setCenter(440, 20);
        canvas.add(startGame);
        startGame.onClick(()-> {
            canvas.animate(() -> {
                try {
                    //canvas.animate(cat.moveCat(canvas, path.makePath(), animation););
                    cat = new cat(10,10,10);
                    cat.moveCat(canvas, path.makePath(), animation);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
        });

    }
    
}
