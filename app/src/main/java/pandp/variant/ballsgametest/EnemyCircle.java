package pandp.variant.ballsgametest;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by FilGardFoil.
 */
public class EnemyCircle extends SimpleCircle {
    public static final int FROM_RADIUS = 10;
    public static final int TO_RADIUS = 80;
    public static final int RUNDOM_SPEED = 10;
    public static final int ENEMY_COLOR = Color.rgb(156, 6, 6);
    public static final int FOOD_COLOR = Color.rgb(137, 249, 76);
    private int dx, dy;

    public EnemyCircle(int x, int y, int radius, int dx, int dy){
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }

    public static EnemyCircle getRandomCircle() {
        Random random = new Random();
        int x = random.nextInt(GameManager.getWidth());
        int y = random.nextInt(GameManager.getHeight());
        int dx = 1 + random.nextInt(RUNDOM_SPEED);
        int dy = 1 + random.nextInt(RUNDOM_SPEED);
        int radius = FROM_RADIUS + random.nextInt(TO_RADIUS - FROM_RADIUS);
        EnemyCircle enemyCircle = new EnemyCircle(x, y, radius, dx, dy);
        enemyCircle.setColor(ENEMY_COLOR);
        return enemyCircle;
    }

    public void setEnemyOrFoodDependsOn(MainCircle mainCircle) {
        if (isSmallerThan(mainCircle)){
            setColor(FOOD_COLOR);
        }else{
            setColor(ENEMY_COLOR);
        }
    }

    public boolean isSmallerThan(SimpleCircle circle){
        if (radius < circle.radius){
            return true;
        }
        return false;
    }

    public void moveOneStap() {
        x += dx;
        y += dy;
        checkBounds();
    }

    private void checkBounds() {
        if (x > GameManager.getWidth() || x < 0){
            dx = -dx;
        }
        if (y > GameManager.getHeight() || y < 0){
            dy = -dy;
        }
    }
}
