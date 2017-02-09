package ru.tanya.airplanegame;



import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();

    private MainThread thread;
    private Airplane airplane;

    public MainGamePanel(Context context) {
        super(context);
        // Ñîîáùàåì, ÷òî îáðàáîò÷èê ñîáûòèé îò ïîâåðõíîñòè áóäåò ðåàëèçîâàí
        // â ýòîì êëàññå.
        getHolder().addCallback(this);

        // ñîçäàåì ïîòîê äëÿ èãðîâîãî öèêëà
        airplane = new Airplane(50, 50, BitmapFactory.decodeResource(getResources(), R.drawable.plane));

        // create the game loop thread
        thread = new MainThread(getHolder(), this);

        // äåëàåì GamePanel ñïîñîáíîé îáðàáàòûâàòü ôîêóñ è ñîáûòèÿ
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Â ýòîé òî÷êå ïîâåðõíîñòü óæå ñîçäàíà è ìû ìîæåì
        // áåçîïàñíî çàïóñòèòü èãðîâîé öèêë â ïîòîêå
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "Surface is being destroyed");
        //ïîñûëàåì ïîòîêó êîìàíäó íà çàêðûòèå è äîæèäàåìñÿ,
        //ïîêà ïîòîê íå áóäåò çàêðûò.
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // ïûòàåìñÿ ñíîâà îñòàíîâèòü ïîòîê thread
            }
        }
        Log.d(TAG, "Thread was shut down cleanly");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // âûçûâàåì ìåòîä handleActionDown, êóäà ïåðåäàåì êîîðäèíàòû êàñàíèÿ
            airplane.handleActionDown((int)event.getX(), (int)event.getY());

            // åñëè ùåë÷îê ïî íèæíåé îáëàñòè ýêðàíà, òî âûõîäèì
            if (event.getY() > getHeight() - 50) {
                thread.setRunning(false);
                ((Activity)getContext()).finish();
            } else {
                Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
            }
        } if (event.getAction() == MotionEvent.ACTION_MOVE) {
            // ïåðåìåùåíèå
            if (airplane.isTouched()) {
                // ðîáîò íàõîäèòñÿ â ñîñòîÿíèè ïåðåòàñêèâàíèÿ,
                // ïîýòîìó èçìåíÿåì åãî êîîðäèíàòû
                airplane.setX((int)event.getX());
                airplane.setY((int)event.getY());
            }
        } if (event.getAction() == MotionEvent.ACTION_UP) {
            // Îáðàáàòûâàåì îòïóñêàíèå
            if (airplane.isTouched()) {
                airplane.setTouched(false);
            }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Çàëèâàåì canvas ÷åðíûì öâåòîì
        canvas.drawColor(Color.BLACK);
        // Âûçûâàåì ìåòîä, êîòîðûé âûâîäèò ðèñóíîê ðîáîòà
        airplane.draw(canvas);
    }

    public void update() {

        airplane.update();
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
