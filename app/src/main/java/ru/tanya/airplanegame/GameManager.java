package ru.tanya.airplanegame;

import android.content.Context;
import android.graphics.BitmapFactory;

public class GameManager {

    private Airplane airplane;
    private Meteorite meteorite1;
    private Meteorite meteorite2;
    private CanvasView canvasView;

    private static int width;
    private static int height;

    public Airplane getAirplane() {
        return airplane;
    }

    public Meteorite getMeteorite1() {
        return meteorite1;
    }

    public GameManager(CanvasView canvasView, int w, int h, Context context) {
        height = h;
        width = w;
        this.canvasView = canvasView;
        initAirplane(context);
        initMeteorites(context);
    }

    private void initMeteorites(Context context) {
        this.meteorite1 =  new Meteorite(width/4, height/4, BitmapFactory.decodeResource(context.getResources(), R.drawable.meteorite1));
    }

    private void initAirplane(Context context) {
        this.airplane = new Airplane(width/2, height/2, BitmapFactory.decodeResource(context.getResources(), R.drawable.plane));
    }

    public void onDraw() {
        canvasView.drawAirplane (airplane);
        canvasView.drawMeteorite (meteorite1);
    }
}
