import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;

public class towerDefense {

    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 500;

    private CanvasWindow canvas;
    private Cat cat;
    private Path path;
    private Button startGame;
    private boolean running = false;
    private GraphicsGroup roundLabel;
    private GraphicsText roundText;
    private Rectangle roundBackground;
    private int round;
    

    public static void main(String[] args) {
        new towerDefense();
    }

    public towerDefense(){
        canvas = new CanvasWindow("Tower Defense!", CANVAS_WIDTH, CANVAS_HEIGHT);
        path = new Path(canvas);
        cat = new Cat(path.getPoints().peek().getX() -50,path.getPoints().peek().getY(),10, path);
        cat.addToCanvas(canvas);
        roundLabel = new GraphicsGroup();
        roundText = new GraphicsText();
        
        startGame();
        roundTracker();

       
        
        
        //cat.addToCanvas(canvas);
    }

    public void startGame(){
        startGame = new Button("Start Round");
        startGame.setCenter(440, 20);
        canvas.add(startGame);
        startGame.onClick(() -> {
            raiseRound();
            running = true;
            //cat.createEnemies(path,round);
        });
        canvas.animate(()->{
            if(running == true) {
                cat.step();
            
            }
        });
    }

    public void roundTracker(){
        roundBackground = new Rectangle(560, 20, 175, 40);
        roundBackground.setFilled(true);
        roundBackground.setFillColor(Color.white);
        roundLabel.add(roundBackground);
        roundText.setFont(FontStyle.BOLD, 30);
        roundText.setText("Round: " + round);
        roundText.setPosition(570, 50);
        roundLabel.add(roundText);
        canvas.add(roundLabel);
    }

    public void raiseRound(){
        round++;
        roundText.setText("Round:" + round);
        if(round > 99){
            roundBackground.setSize(190, 40);
        }
    }
    
}
