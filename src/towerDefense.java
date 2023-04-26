import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import animations.AniManager;
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
    private GraphicsGroup inGameText, gameOverGroup;
    private GraphicsText roundText, lifeText, startText, gameOver, gameOverScore;
    private Rectangle roundBackground, gameOverRec, lifeBackground;
    private int round;
    private int life = 3;
    private Tower movingTower;
    private List<Tower> exampleTowers, towers;
    public ArrayList<Cat> enemyList;
    private AniManager aniManager;
    
    public static void main(String[] args) {
        new towerDefense();
    }

    public towerDefense() {
        aniManager = new AniManager(canvas);
        towers = new ArrayList<>();
        exampleTowers = new ArrayList<>(4);
        canvas = new CanvasWindow("Tower Defense!", CANVAS_WIDTH, CANVAS_HEIGHT);
        path = new Path(canvas);
        cat = new Cat(path.getPoints().peek().getX() -50,path.getPoints().peek().getY(),5, path);
        cat.addToCanvas(canvas);
        inGameText = new GraphicsGroup();
        roundText = new GraphicsText();
        lifeText = new GraphicsText();
        startText = new GraphicsText();
        gameOver = new GraphicsText();
        gameOverScore = new GraphicsText();
        gameOverGroup = new GraphicsGroup();
        startGame();
        gameText();
        run();
        canvas.animate(()-> {
            if(getLife() < 1) {
                gameOverDisplay();
                running = false; 
            }
            if(running) {
                java.util.Iterator iter = (java.util.Iterator) enemyList.iterator();
                while(iter.hasNext()){
                    Cat cat = (Cat) iter.next();
                    canvas.add(cat);
                    if(running) {
                        cat.step(canvas); 
                    }
                    if(cat.getCenter().getX()>= 510){
                        loselife();
                        iter.remove();
                        canvas.remove(cat);
                    }
                }
            }
        });
    }

    private void run() {
        startGame.onClick(() -> {
            running = true;
            raiseRound();
            enemyList = cat.createEnemies(path, round); 
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
    }

    private void startGame() {
        startGame = new Button("Start Round");
        startGame.setCenter(440, 20);
        canvas.add(startGame);
        createSampleTowers();
    }

    private void gameText() {
        roundBackground = new Rectangle(560, 20, 175, 40);
        roundBackground.setFilled(true);
        roundBackground.setFillColor(Color.white);
        inGameText.add(roundBackground);
        roundText.setFont(FontStyle.BOLD, 30);
        roundText.setText("Round: " + round);
        roundText.setPosition(570, 50);
        inGameText.add(roundText);
        lifeBackground = new Rectangle(560, 70, 175, 40);
        lifeBackground.setFilled(true);
        lifeBackground.setFillColor(Color.white);
        inGameText.add(lifeBackground);
        lifeText.setFont(FontStyle.BOLD,30);
        lifeText.setText("Lives: " + life);
        lifeText.setPosition(570,100);
        inGameText.add(lifeText);
        startText.setFont(FontStyle.BOLD, 10);
        startText.setText("Start Here!");
        startText.setPosition(10, 220);
        inGameText.add(startText);
        canvas.add(inGameText);
    }

    private void gameOverDisplay() {
        gameOverRec = new Rectangle(0, 100, 500, 200);
        gameOverRec.setFilled(true);
        gameOverRec.setFillColor(Color.RED);
        gameOver.setFont(FontStyle.BOLD, 76);
        gameOver.setFillColor(Color.white);
        gameOver.setText("GAME OVER!");
        gameOver.setPosition(2, 200);
        gameOverScore.setFont(FontStyle.BOLD, 30);
        gameOverScore.setText("You made it to round: " + round + "!");
        gameOverScore.setPosition(50,260);
        gameOverScore.setFillColor(Color.white);
        gameOverGroup.add(gameOverRec);
        gameOverGroup.add(gameOver);
        gameOverGroup.add(gameOverScore);
        canvas.add(gameOverGroup);
    }

    private void raiseRound() {
        round++;
        roundText.setText("Round: " + round);
        if(round > 99){
            roundBackground.setSize(190, 40);
        }
    }
    private void createSampleTowers() {
        exampleTowers.clear();
        Tower exampleBurst = new BurstTower(600, 200, aniManager);
        exampleTowers.add(exampleBurst);
        canvas.add(exampleBurst);
    }

    private int getLife() {
        return life;
    }

    private void loselife() {
        life--;
        lifeText.setText("Lives: " + life);
    }
}
