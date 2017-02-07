package ru.tanya.airplanegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameManager {

    private Airplane airplane;
    private Meteorite meteorite1;
    private Meteorite meteorite2;
    private CanvasView canvasView;

    private static int width;
    private static int height;

    public GameManager(CanvasView canvasView, int w, int h, Context context) {
        height = h;
        width = w;
        this.canvasView = canvasView;
        initAirplane(context);
        initMeteorites(context);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public Meteorite getMeteorite1() {
        return meteorite1;
    }

    private void initMeteorites(Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteorite1);
        this.meteorite1 =  new Meteorite(width/4 - bitmap.getWidth()/2, height/4 - bitmap.getHeight()/2, bitmap);
    }

    private void initAirplane(Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane);
        this.airplane = new Airplane(width/2, height/2, bitmap);
    }

    public void onDraw() {
        canvasView.drawAirplane (airplane);
        canvasView.drawMeteorite (meteorite1);
    }

    public void move() {
        meteorite1.moveOneStep();
    }
}
