import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.*;

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

    private Tower movingTower;
    private List<Tower> exampleTowers;
    private List<Tower> towers;
    

    public static void main(String[] args) {
        new towerDefense();
    }

    public towerDefense() {
        towers = new ArrayList<>();
        exampleTowers = new ArrayList<>(4);
        canvas = new CanvasWindow("Tower Defense!", CANVAS_WIDTH, CANVAS_HEIGHT);
        path = new Path(canvas);
        cat = new Cat(path.getPoints().peek().getX() -50,path.getPoints().peek().getY(),10, path);
        cat.addToCanvas(canvas);
        roundLabel = new GraphicsGroup();
        roundText = new GraphicsText();
        
        startGame();
        roundTracker();

        startGame.onClick(() -> {
            running = true;
        });

        canvas.onMouseDown((handler) -> {
            Point position = handler.getPosition();
            for(Tower example : exampleTowers) {
                if(example.testHit(position.getX(), position.getY())) {
                    System.out.println("clicked on a tower");
                    movingTower = example;
                    exampleTowers.remove(movingTower);
                }
            }
        });
        canvas.onMouseUp((handler) -> {
            towers.add(movingTower);
            movingTower = null;
            createSampleTowers();
        });
        canvas.onDrag((handler) -> {
            if(movingTower != null) {
                movingTower.setCenter(handler.getPosition());
            }
        });
        
        canvas.animate(()->{
            if(running == true) {
                //cat.step();
            }
        });
        
        //cat.addToCanvas(canvas);
    }

    public void startGame(){
        startGame = new Button("Start Round");
        startGame.setCenter(440, 20);
        canvas.add(startGame);
        startGame.onClick(() -> {
            raiseRound();
            running = true;
            cat.moveCats(path, round,canvas);
            //cat.createEnemies(path,round);
        });
        canvas.animate(()->{
            if(running == true) {
                cat.step(); 
            }
        });
        createSampleTowers();
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
    private void createSampleTowers() {
        exampleTowers.clear();
        Tower exampleBurst = new BurstTower(600, 200);
        exampleTowers.add(exampleBurst);
        canvas.add(exampleBurst);
    }
}
