package towerDefense.animations;

import edu.macalester.graphics.GraphicsObject;

public class RotateTo implements Animation {
    private final double startAngle, endAngle, totalAngle, maxRuntime = 5; //hardcoded runtime param
    private final GraphicsObject context;
    private double runtime, angle, stepSize;

    public RotateTo(GraphicsObject context, double endAngle) {
        this.endAngle = endAngle;
        this.context = context;
        this.startAngle = context.getRotation();

        runtime = 0;
        angle = startAngle;
        totalAngle = Math.min(Math.abs(endAngle - startAngle), Math.abs(startAngle - endAngle)) * endAngle < startAngle ? -1 : 1;
        //minimum distance btwn angles, negative if endAngle is less than startAngle.
    }

    public boolean step(double dt) {
        stepSize = totalAngle * dt / maxRuntime;
        runtime += dt;
        angle += stepSize;

        if( (totalAngle < 0 ? angle <= endAngle : angle >= endAngle) || runtime >= maxRuntime) {
            context.setRotation(endAngle);
            return true;
        } else {
            context.rotateBy(stepSize);
            return false;
        }
    }
}
