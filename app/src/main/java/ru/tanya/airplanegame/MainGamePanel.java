package ru.tanya.airplanegame;



import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();

    private MainThread thread;
    private GameManager gameManager;
    private Toast toast;

    private int width;
    private int height;

    public MainGamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        initWidthAndHeigth(context);
        thread = new MainThread(getHolder(), this);
        gameManager = new GameManager(this, width, height, context);
        setFocusable(true);
    }

    private void initWidthAndHeigth(Context context) {
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);                 //point содержит координаты правой нижней точки экрана
        width = point.x;
        height = point.y;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        gameManager.getAirplane().draw(canvas);
        gameManager.getMeteorite1().draw(canvas);
    }

    public void update() {
        gameManager.update();
    }

    public void showMessage(String text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}



//import android.content.Context;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Point;
//import android.util.AttributeSet;
//import android.view.Display;
//import android.view.Gravity;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.Toast;

//public class MainGamePanel extends View implements ICanvasView, Runnable {
//
//    private GameManager gameManager;
//    private static int width;
//    private static int height;
//    private Canvas canvas;
//    private Toast toast;        //хранит текущий тост, который отображается на экране
//    private boolean isRunning;
//
//    public MainGamePanel(Context context) {
//        super(context);
//        initWidthAndHeight(context);
//        gameManager = new GameManager(this, width, height, context);
//        isRunning = true;
//        new Thread(this).start();
//    }
//
//    private void initWidthAndHeight(Context context) {
//        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
//        Point point = new Point();
//        display.getSize(point);                 //point содержит координаты правой нижней точки экрана
//        width = point.x;
//        height = point.y;
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        this.canvas = canvas;
//        gameManager.onDraw();
//    }
//
//    @Override
//    public void redraw() {
//        invalidate();
//    }
//
//    @Override
//    public void showMessage(String text) {
//        if (toast != null) {
//            toast.cancel();
//        }
//        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.show();
//    }
//
//    public void drawAirplane(Airplane airplane) {
//        canvas.drawBitmap(airplane.getBitmap(), airplane.getX(), airplane.getY(), null);
//    }
//
//    public void drawMeteorite(Meteorite meteorite1) {
//        canvas.drawBitmap(meteorite1.getBitmap(), meteorite1.getX(), meteorite1.getY(), null);
//    }
//
//
//    @Override
//    public void run() {
//        synchronized (this) {
//            while (isRunning) {
//                gameManager.startGame();
//                invalidate();
//            }
//        }
//
//    }
//}
