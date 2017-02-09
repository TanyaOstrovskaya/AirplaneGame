package ru.tanya.airplanegame;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private static final String TAG = MainThread.class.getSimpleName();

    private SurfaceHolder surfaceHolder;
    private MainGamePanel gamePanel;

    private boolean running;
    public void setRunning(boolean running) {
        this.running = running;
    }

    public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        Canvas canvas;
        Log.d(TAG, "Starting game loop");
        while (running) {
            canvas = null;
            // ïûòàåìñÿ çàáëîêèðîâàòü canvas
            // äëÿ èçìåíåíèå êàðòèíêè íà ïîâåðõíîñòè
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    // îáíîâëÿåì ñîñòîÿíèå
                    this.gamePanel.update();
                    // ôîðìèðóåì íîâûé êàäð
                    this.gamePanel.onDraw(canvas); //Âûçûâàåì ìåòîä äëÿ ðèñîâàíèÿ
                }
            } finally {
                // â ñëó÷àå îøèáêè, ïëîñêîñòü íå ïåðåøëà â
                //òðåáóåìîå ñîñòîÿíèå
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}