package pandp.variant.ballsgametest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by FilGardFoil.
 */
public class CanvasView extends View implements ICanvasView{

    private static int width, height;
    private GameManager gameManager;
    private Paint paint;
    private Canvas canvas;
    private Toast toast;

    public CanvasView(Context context, AttributeSet attrs){
        super(context, attrs);
        initWidthAndHeight();
        initPaint();
        gameManager = new GameManager(this, width, height);
    }

    private void initWidthAndHeight() {

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);   //непонятно зачем он это создает
        this.canvas = canvas;
        gameManager.onDraw();
    }

    @Override
    public void drawCircle(SimpleCircle circle){
        paint.setColor(circle.getColor());
        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);
    }

    @Override
    public void reDraw() {
        invalidate();
    }

    @Override
    public void showMessage(String text) {
        if (toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_MOVE){
            gameManager.onTouchEvent(x, y);
        }
        invalidate();
        return true;
    }



}
