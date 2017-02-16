package ru.tanya.airplanegame;

import android.content.Context;
import android.content.Intent;
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
    private Context context;

    private int width;
    private int height;

    public MainGamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);

        initWidthAndHeigth(context);
        initGameManager(context);
        initMainThread();
        this.context = context;

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
        canvas.drawColor(Color.rgb(175, 237, 248));
        canvas.drawBitmap(gameManager.getCloud1().getBitmap(),
                gameManager.getCloud1().getX() - gameManager.getCloud1().getBitmapWidth()/2,
                gameManager.getCloud1().getY() - gameManager.getCloud1().getBitmapHeight()/2,
                null );
        canvas.drawBitmap(gameManager.getCloud2().getBitmap(),
                gameManager.getCloud2().getX() - gameManager.getCloud2().getBitmapWidth()/2,
                gameManager.getCloud2().getY() - gameManager.getCloud2().getBitmapHeight()/2,
                null );
        canvas.drawBitmap(gameManager.getAirplane().getBitmap(),
                gameManager.getAirplane().getX() - gameManager.getAirplane().getBitmapWidth()/2,
                gameManager.getAirplane().getY() - gameManager.getAirplane().getBitmapHeight()/2,
                null );
<<<<<<< HEAD
        canvas.drawBitmap(gameManager.getMeteorite1().getBitmap(),
                gameManager.getMeteorite1().getX() - gameManager.getMeteorite1().getBitmapWidth()/2,
                gameManager.getMeteorite1().getY() - gameManager.getMeteorite1().getBitmapHeight()/2,
                null );

        canvas.drawBitmap(gameManager.getMeteorite2().getBitmap(),
                gameManager.getMeteorite2().getX() - gameManager.getMeteorite2().getBitmapWidth()/2,
                gameManager.getMeteorite2().getY() - gameManager.getMeteorite2().getBitmapHeight()/2,
                null );
        Paint paint = new Paint();
        paint.setTextSize(70);
        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setColor(Color.BLUE);
        canvas.drawText("SCORE: " + gameManager.getScore(), gameManager.getWidth()*2/3, 80, paint);
=======
        canvas.drawBitmap(gameManager.getMeteorite().getBitmap(),
                gameManager.getMeteorite().getX() - gameManager.getMeteorite().getBitmapWidth()/2,
                gameManager.getMeteorite().getY() - gameManager.getMeteorite().getBitmapHeight()/2,
                null );

        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setColor(Color.BLUE);
        canvas.drawText("SCORE: " + gameManager.getScore(), gameManager.getWidth()*2/3, 60, paint);
>>>>>>> 35506752259261a91817083084fe9ebbb4d124e3

        if (!thread.isRunning()) {
            Intent intent = new Intent(context, FinishActivity.class);
            context.startActivity(intent);
<<<<<<< HEAD
            
=======


//            canvas.drawColor(Color.WHITE);
//            Paint paint = new Paint();
//            paint.setTextSize(40);
//            paint.setTextAlign(Paint.Align.CENTER);
//            canvas.drawText("YOU LOSE", gameManager.getWidth()/2, gameManager.getHeight()/2, paint);

>>>>>>> 35506752259261a91817083084fe9ebbb4d124e3
        }
    }

    public boolean updateAndCheckCollisions() {
        return gameManager.updateAndCheckCollisions();
    }

    public GameManager getGameManager() {
        return gameManager;
    }

}




