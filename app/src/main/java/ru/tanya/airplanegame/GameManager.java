package ru.tanya.airplanegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameManager {

    private Airplane airplane;

    private Meteorite meteorite1;
    private Meteorite meteorite2;

    private Cloud cloud1;
    private Cloud cloud2;
    private static int width;
    private static int height;
    private int score;

    private int currentAirplaneAngle = 0;

    public void setCurrentAirplaneAngle(int currentAirplaneAngle) {
        airplane.setAngle(currentAirplaneAngle);
    }

    public GameManager(int w, int h, Context context) {
        height = h;
        width = w;
        initAirplane(context);
        initMeteorites(context);
        initClouds(context);
    }

    public int getScore() {
        return score;
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


    public Meteorite getMeteorite1() { return meteorite1;  }

    public Meteorite getMeteorite2() { return meteorite2; }


    public Cloud getCloud1() {
        return cloud1;
    }
    public Cloud getCloud2() {
        return cloud2;
    }

    private void initMeteorites(Context context) {
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteorite1);
        this.meteorite1 =  new Meteorite(width/4, -bitmap1.getHeight(), bitmap1);

        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteorite2);
        this.meteorite2 =  new Meteorite(width/4*3, -height/2 - bitmap2.getHeight(), bitmap2);
    }

    private void initAirplane(Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane);
        this.airplane = new Airplane(width/2, height/2, bitmap);
    }

    private void initClouds(Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud);
        this.cloud1 = new Cloud(width / 4 * 3, -height/3, bitmap);
        this.cloud2 = new Cloud(width / 4, -height*5/6, bitmap);
    }


    private boolean checkCollisions(Meteorite meteorite) {

        int airplaneX = airplane.getX();
        int airplaneY = airplane.getY();
        int meteoriteX = meteorite.getX();
        int meteoriteY = meteorite.getY();

        int deltaX = meteoriteX - airplaneX;
        int deltaY = meteoriteY - airplaneY;

        // if metheorite is away from airplane
        if ((Math.abs(deltaX) > airplane.getBitmapWidth() / 2 + meteorite.getBitmapWidth()/2)
                || (Math.abs(deltaY) > airplane.getBitmapHeight() / 2 + meteorite.getBitmapHeight()/2)) {
            return true;    // no collisions

        } else {
            // if meteorite is in lower part of airplane
            if (deltaY > 0) {
                if (Math.abs(deltaX) < 0.18 * airplane.getBitmapWidth()) {
                    return false;
                } else {
                    // check collision under airplane wings
                    if ((deltaY < meteorite.getBitmapHeight()/2) &&
                            (Math.abs(deltaX) < 0.18 * airplane.getBitmapWidth() + meteorite.getBitmapWidth()/2))
                        return false;
                    else
                        return true;
                }

            // if meteorite is in upper part of airplane
            } else {
                // if meteorite intersect airplane top
                if (Math.abs(deltaX)< 0.08 *airplane.getBitmapWidth())
                    return false;

                // if meteorite intersect airplane wing from the right/left border
                if (Math.abs(deltaY) < airplane.getBitmapHeight()/4) {
                    return false;
                } else {
                    if (Math.abs(deltaY) < airplane.getBitmapHeight()/4 + meteorite.getBitmapHeight()/2)
                        return false;
                    else
                        return true;
                }
            }
        }
    }




    public boolean updateAndCheckCollisions() {
        cloud1.updateCoordinates();
        cloud2.updateCoordinates();
        airplane.updateCoordinates();

        meteorite1.updateCoordinates();
        meteorite2.updateCoordinates();
        updateScore();
        return checkCollisions(meteorite1) && checkCollisions(meteorite2);
    }

    private void updateScore() {
        if (meteorite1.isChangedScore())
            ++score;
        if (meteorite2.isChangedScore())
            ++score;
    }

}
