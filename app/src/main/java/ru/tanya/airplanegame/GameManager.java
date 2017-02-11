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
        this.meteorite =  new Meteorite(bitmap.getWidth(), bitmap.getHeight(), bitmap);
    }

    private void initAirplane(Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane);
        this.airplane = new Airplane(width/2, height/2, bitmap);
    }

    private boolean checkCollisions() {
        // if the meteorite image intersects the plane image on the BOTTOM border
        if (meteorite.getY() + meteorite.getBitmapHeight() / 2>
                airplane.getY() - airplane.getBitmapHeight() / 2) {

            // if meteorite is in left part of display
            if (meteorite.getX() < width/2) {
                //If the meteorite image intersects the plane image on the RIGHT border
                if (meteorite.getX() + meteorite.getBitmapWidth() / 2  >
                        airplane.getX() - airplane.getBitmapWidth() / 2) {
                    meteorite.updateCoordinates();
                    return false;       //collision - stop game cycle
                }
            } else  {
                // if the meteorite image intersects the plane image on the LEFT border
                if (meteorite.getX() - meteorite.getBitmapWidth() / 2 <
                        airplane.getX() - airplane.getBitmapWidth() / 2) {
                    meteorite.updateCoordinates();
                    return false;       //collision - stop game cycle
                }
            }
        }

        // no collisions
        return true;
    }

    public boolean updateAndCheckCollisions() {
        meteorite.updateCoordinates();
        return checkCollisions();
    }
}
