package pandp.variant.ballsgametest;

import java.util.ArrayList;

/**
 * Created by FilGardFoil.
 */
public class GameManager {

    private CanvasView canvasView;
    private static int width, height;
    private MainCircle mainCircle;
    private ArrayList<EnemyCircle> circles;
    public static final int ENEMY_COUNT = 10;
    public static final String FAIL_TEXT = "Наткнулся не на то!";
    public static final String WIN_TEXT = "Красавчик!";


    public GameManager(CanvasView canvasView, int width, int height){
        this.canvasView = canvasView;
        this.width = width;
        this.height = height;
        initializeMain();
        initializeEnemy();
    }

    public void initializeEnemy(){
        SimpleCircle mainCircleArea = mainCircle.getCircleArea();
        circles = new ArrayList<EnemyCircle>();
        for (int i = 0; i < ENEMY_COUNT; i++){
            EnemyCircle circle;
            do {
                circle = EnemyCircle.getRandomCircle();
            }while(circle.isIntersect(mainCircleArea));
            circles.add(circle);
        }
        setCirclesColor();
    }

    private void setCirclesColor(){
        for (EnemyCircle circle : circles){
            circle.setEnemyOrFoodDependsOn(mainCircle);
        }
    }

    private void initializeMain() {
        mainCircle = new MainCircle(width / 2, height / 2);
    }

    public void onDraw(){
        canvasView.drawCircle(mainCircle);
        for (EnemyCircle circle : circles){
            canvasView.drawCircle(circle);
        }
    }

    public void onTouchEvent(int x, int y){
        mainCircle.moveMainCircleWhenTouchAt(x, y);
        checkCollision();
        moveCircles();
    }

    private void checkCollision() {
        SimpleCircle circleForDel = null;
        for (EnemyCircle circle : circles){
            if (mainCircle.isIntersect(circle)){
                if (circle.isSmallerThan(mainCircle)){
                    mainCircle.growRadius(circle);
                    circleForDel = circle;
                    setCirclesColor();
                    break;
                }else {
                    gameEnd(FAIL_TEXT);
                    return;
                }
            }
        }
        if (circleForDel != null){
            circles.remove(circleForDel);
        }
        if (circles.isEmpty()){
            gameEnd(WIN_TEXT);
        }
    }


    private void gameEnd(String text) {
        canvasView.showMessage(text);
        mainCircle.initRadius();
        initializeEnemy();
        canvasView.reDraw();
    }

    public void moveCircles(){
        for(EnemyCircle circle : circles){
            circle.moveOneStap();
        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
