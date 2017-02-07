package ru.tanya.airplanegame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class CanvasView extends View implements ICanvasView {

    private GameManager gameManager;
    private static int width;
    private static int height;
    private Canvas canvas;
    private Toast toast;        //хранит текущий тост, который отображается на экране

    public CanvasView(Context context) {
        super(context);
        initWidthAndHeight(context);
        gameManager = new GameManager(this, width, height, context);
    }

    private void initWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);                 //point содержит координаты правой нижней точки экрана
        width = point.x;
        height = point.y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        gameManager.onDraw();
    }

    @Override
    public void redraw() {
        invalidate();
    }

    @Override
    public void showMessage(String text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void drawAirplane(Airplane airplane) {
        canvas.drawBitmap(airplane.getBitmap(), airplane.getX(), airplane.getY(), null);
    }

    public void drawMeteorite(Meteorite meteorite1) {
        canvas.drawBitmap(meteorite1.getBitmap(), meteorite1.getX(), meteorite1.getY(), null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gameManager.move();
        invalidate();
        return true;

    }
}
