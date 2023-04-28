package towerDefense;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import towerDefense.animations.AniManager;
import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.*;

public class TowerDefense {

    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 500;

    private CanvasWindow canvas;
    private Cat cat;
    private Path path;
    private Button startGame;
    private boolean running = false, canPlace = false;
    private GraphicsGroup inGameText, gameOverGroup;
    private GraphicsText roundText, lifeText, startText, gameOver, gameOverScore;
    private Rectangle roundBackground, gameOverRec, lifeBackground;
    private int round;
    private int life = 3;
    private Tower movingTower;
    private List<Tower> exampleTowers, towers;
    public ArrayList<Cat> enemyList;
    private AniManager aniManager;
    private Ellipse towerRange;

    public static void main(String[] args) {
        new TowerDefense();
    }

    public TowerDefense() {
        canvas = new CanvasWindow("Tower Defense!", CANVAS_WIDTH, CANVAS_HEIGHT);
        aniManager = new AniManager(canvas);
        towers = new ArrayList<>();
        exampleTowers = new ArrayList<>(4);
        path = new Path();
        canvas.add(path.getVisuals());
        cat = new Cat(path.getPoints().peek().getX() - 50, path.getPoints().peek().getY(), 5, path, aniManager);
        canvas.add(cat);
        inGameText = new GraphicsGroup();
        roundText = new GraphicsText();
        lifeText = new GraphicsText();
        startText = new GraphicsText();
        gameOver = new GraphicsText();
        gameOverScore = new GraphicsText();
        gameOverGroup = new GraphicsGroup();
        startGame();
        gameText();

        canvas.animate((dt) -> { // main frame behavior
            if (getLife() < 1) {
                gameOverDisplay();
                running = false;
            }
            if (running) {
                catBehavior();
                towerBehavior(dt);
            }
        });
        startGame.onClick(() -> { // button behavior
            running = true;
            raiseRound();
            enemyList = cat.createEnemies(path, round);
        });
        canvas.onMouseDown((handler) -> { // click on tower
            Point position = handler.getPosition();

            Iterator<Tower> itr = exampleTowers.iterator();
            while (itr.hasNext()) {
                Tower next = itr.next();
                if (next.getGroup().testHit(position.getX(), position.getY())) {
                    movingTower = next;

                    towerRange = new Ellipse(0, 0, movingTower.getRange() * 2, movingTower.getRange() * 2);
                    towerRange.setFillColor(Helpers.CLEAR_BLUE_SKIES);
                    towerRange.setCenter(movingTower.getGroup().getCenter());
                    canvas.add(towerRange);
                    canvas.add(movingTower.getGroup());
                    itr.remove();
                }
            }
        });
        canvas.onDrag((handler) -> { // drag and...
            if (movingTower != null) {
                boolean touchingTower = false;
                movingTower.getGroup().setCenter(handler.getPosition());
                towerRange.setCenter(handler.getPosition());
                for(Tower tower : towers) {
                    if(tower.getGroup().testHit(towerRange.getCenter().getX(), towerRange.getCenter().getY())) {
                        touchingTower = true;
                        break;
                    }
                }
                if(touchingTower || path.getVisuals().testHit(towerRange.getCenter().getX(), towerRange.getCenter().getY())) {
                    towerRange.setFillColor(Helpers.ERROR_CODE_RED);
                    canPlace = false;
                } else {
                    towerRange.setFillColor(Helpers.CLEAR_BLUE_SKIES);
                    canPlace = true;
                }
            }
        });
        canvas.onMouseUp((handler) -> { // drop!
            if(movingTower != null) {
                if(canPlace) {
                    towers.add(movingTower);
                } else {
                    canvas.remove(movingTower.getGroup());
                    // error text?
                }
                canvas.remove(towerRange);
                movingTower = null;
                createSampleTowers();
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

        lifeText.setFont(FontStyle.BOLD, 30);
        lifeText.setText("Lives: " + life);
        lifeText.setPosition(570, 100);
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
        gameOverScore.setPosition(50, 260);
        gameOverScore.setFillColor(Color.white);

        gameOverGroup.add(gameOverRec);
        gameOverGroup.add(gameOver);
        gameOverGroup.add(gameOverScore);
        canvas.add(gameOverGroup);
    }

    private void raiseRound() {
        round++;
        roundText.setText("Round: " + round);
        if (round > 99) {
            roundBackground.setSize(190, 40);
        }
    }

    private void createSampleTowers() {
        exampleTowers.clear();
        Tower exampleBurst = new BurstTower(600, 200, aniManager);
        exampleTowers.add(exampleBurst);
        canvas.add(exampleBurst.getGroup());
    }

    private int getLife() {
        return life;
    }

    private void loselife() {
        life--;
        lifeText.setText("Lives: " + life);
    }

    private void catBehavior() {
        Iterator<Cat> iter = enemyList.iterator();
        while (iter.hasNext()) {
            Cat cat = iter.next();
            canvas.add(cat);
            cat.step(canvas);

            if (cat.getCenter().getX() >= 510) {
                loselife();
                iter.remove();
                canvas.remove(cat);
            }
            if (cat.isHit() && cat.getX() < 0) {
                iter.remove();
                canvas.remove(cat);
            }
        }
    }

    private void towerBehavior(double dt) {
        for(Tower tower : towers) {
            tower.step(dt, enemyList);
        }
    }
}
