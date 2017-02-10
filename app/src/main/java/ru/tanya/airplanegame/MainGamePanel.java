package ru.tanya.airplanegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
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
        if (!thread.isRunning())
            System.exit(0);
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
        canvas.drawColor(Color.WHITE);
        if (gameManager.isGameRunning()) {
            canvas.drawBitmap(gameManager.getAirplane().getBitmap(), gameManager.getAirplane().getX(), gameManager.getAirplane().getY(), null );
            canvas.drawBitmap(gameManager.getMeteorite1().getBitmap(), gameManager.getMeteorite1().getX(), gameManager.getMeteorite1().getY(), null );
        } else {
            Paint paint = new Paint();
            paint.setTextSize(40);
            canvas.drawText("YOU LOSE", gameManager.getWidth()/2, gameManager.getHeight()/2, paint );
        }
    }

    public boolean updateAndCheckCollisions() {
        boolean isGameEnded =  gameManager.updateAndCheckCollisions();
        gameManager.setGameRunning(!isGameEnded);
        return isGameEnded;
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




