package towerDefense.animations;

import java.util.ArrayDeque;
import java.util.Deque;

import edu.macalester.graphics.CanvasWindow;

/**
 * Runs animation objects. Internally uses an 
 * ArrayDeque as a per-frame animation queue
 * @author ReddSaut
 */
public class AniManager {
    Deque<Animation> animations;

    public AniManager(CanvasWindow canvas) {
        animations = new ArrayDeque<>();

        canvas.animate((dt) -> {
            int size = animations.size();

            for(int i=0; i<size; i++) {
                Animation running = animations.poll();
                if(!running.step(dt)) { //if the animation has not finished,
                    animations.offer(running); //add back to queue
                }
            }
        });
    }

    /**
     * adds the given animation object to the rear of the queue
     * @param a
     */
    public void add(Animation a) {
        if(a == null) {
            return;
        }
        animations.offer(a);
    }

    public boolean contains(Animation a) {
        return animations.contains(a);
    }

    public void clear() {
        animations.clear();
    }
}
