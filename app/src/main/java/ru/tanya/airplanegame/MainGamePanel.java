package ru.tanya.airplanegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();

    private MainThread thread;
    private GameManager gameManager;
    private Toast toast;                //for messages

    private int width;
    private int height;

    public MainGamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);

        initWidthAndHeigth(context);
        initGameManager(context);
        initMainThread();

        setFocusable(true);
    }

    private void initMainThread() {
        thread = new MainThread(getHolder(), this);
    }

    private void initGameManager(Context context) {
        gameManager = new GameManager(width, height, context);
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
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(gameManager.getAirplane().getBitmap(),
                gameManager.getAirplane().getX() - gameManager.getAirplane().getBitmapWidth()/2,
                gameManager.getAirplane().getY() - gameManager.getAirplane().getBitmapHeight()/2,
                null );
        canvas.drawBitmap(gameManager.getMeteorite().getBitmap(),
                gameManager.getMeteorite().getX() - gameManager.getMeteorite().getBitmapWidth()/2,
                gameManager.getMeteorite().getY() - gameManager.getMeteorite().getBitmapHeight()/2,
                null );

        if (!thread.isRunning()) {
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setTextSize(40);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("YOU LOSE", gameManager.getWidth()/2, gameManager.getHeight()/2, paint);
        }
    }

    public boolean updateAndCheckCollisions() {
        return gameManager.updateAndCheckCollisions();
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    //    public void showMessage(String text) {
//        if (toast != null) {
//            toast.cancel();
//        }
//        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.show();
//    }
}




