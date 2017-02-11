package ru.tanya.airplanegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameManager {

    private Airplane airplane;
    private Meteorite meteorite;
    private static int width;
    private static int height;


    public GameManager(int w, int h, Context context) {
        height = h;
        width = w;
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

    public Meteorite getMeteorite() {
        return meteorite;
    }

    private void initMeteorites(Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteorite1);
        this.meteorite =  new Meteorite(width/10, height/10, bitmap);
    }

    private void initAirplane(Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane);
        this.airplane = new Airplane(width/2, height/2, bitmap);
    }

    private boolean checkCollisions() {
        //If the meteorite image intersects the plane image on the bottom border
        if (meteorite.getY() + meteorite.getBitmapHeight() / 2 >
                airplane.getY() - airplane.getBitmapHeight() / 2) {
            //If the meteorite image intersects the plane image on the right border
            if (meteorite.getX() + meteorite.getBitmapWidth() / 2 >
                    airplane.getX() - airplane.getBitmapWidth() / 2) {
                return false;       //collision - stop game cycle
            }
        }
        return true;        //no collisions
    }

    public boolean updateAndCheckCollisions() {
        meteorite.updateCoordinates();
        return checkCollisions();
    }
}
