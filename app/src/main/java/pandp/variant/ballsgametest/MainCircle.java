package pandp.variant.ballsgametest;

import android.graphics.Color;

/**
 * Created by FilGardFoil.
 */
public class MainCircle extends SimpleCircle {
    public static final int INIT_RADIUS = 50;
    public static final int MAIN_SPEED = 30;
    public static final int OUR_COLOR = Color.rgb(252, 102, 20);

    public MainCircle(int x, int y){
        super(x, y, INIT_RADIUS);
        setColor(OUR_COLOR);
    }

    public void moveMainCircleWhenTouchAt(int x, int y){
        int dx = (x - this.x) * MAIN_SPEED / GameManager.getWidth();
        int dy = (y - this.y) * MAIN_SPEED / GameManager.getHeight();
        this.x += dx;
        this.y += dy;
    }

    public void initRadius() {
        radius = INIT_RADIUS;
    }

    public void growRadius(SimpleCircle circle) {
        radius = (int) Math.sqrt(Math.pow(radius, 2) + Math.pow(circle.radius, 2));
    }
}
