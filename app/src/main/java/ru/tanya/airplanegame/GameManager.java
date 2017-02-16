package ru.tanya.airplanegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameManager {

    private Airplane airplane;
<<<<<<< HEAD
    private Meteorite meteorite1;
    private Meteorite meteorite2;
=======
    private Meteorite meteorite;
>>>>>>> 35506752259261a91817083084fe9ebbb4d124e3
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

<<<<<<< HEAD
    public Meteorite getMeteorite1() { return meteorite1;  }

    public Meteorite getMeteorite2() { return meteorite2; }
=======
    public Meteorite getMeteorite() {
        return meteorite;
    }
>>>>>>> 35506752259261a91817083084fe9ebbb4d124e3

    public Cloud getCloud1() {
        return cloud1;
    }
    public Cloud getCloud2() {
        return cloud2;
    }

    private void initMeteorites(Context context) {
<<<<<<< HEAD
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteorite1);
        this.meteorite1 =  new Meteorite(width/4, -bitmap1.getHeight(), bitmap1);

        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteorite2);
        this.meteorite2 =  new Meteorite(width/4*3, -height/2 - bitmap2.getHeight(), bitmap2);
=======
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteorite2);
        this.meteorite =  new Meteorite(width/4, -bitmap.getHeight(), bitmap);
>>>>>>> 35506752259261a91817083084fe9ebbb4d124e3
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

<<<<<<< HEAD
    private boolean checkCollisions(Meteorite meteorite) {
=======
    private boolean checkCollisions() {
>>>>>>> 35506752259261a91817083084fe9ebbb4d124e3
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



//    private boolean checkCollisions() {
<<<<<<< HEAD
//        // if the meteorite1 image intersects the plane image on the BOTTOM border
//        if (meteorite1.getY() + meteorite1.getBitmapHeight() / 2 >
//                airplane.getY() - airplane.getBitmapHeight() / 4) {
//
//            if (meteorite1.getX() < width/2) {
//                // if meteorite1 is in LEFT PART of display
//                // if the meteorite1 image intersects the plane image on the RIGHT border
//                if (meteorite1.getX() + meteorite1.getBitmapWidth() / 2  >
//                        airplane.getX() - airplane.getBitmapWidth() / 2) {
//                    meteorite1.updateCoordinates();
=======
//        // if the meteorite image intersects the plane image on the BOTTOM border
//        if (meteorite.getY() + meteorite.getBitmapHeight() / 2 >
//                airplane.getY() - airplane.getBitmapHeight() / 4) {
//
//            if (meteorite.getX() < width/2) {
//                // if meteorite is in LEFT PART of display
//                // if the meteorite image intersects the plane image on the RIGHT border
//                if (meteorite.getX() + meteorite.getBitmapWidth() / 2  >
//                        airplane.getX() - airplane.getBitmapWidth() / 2) {
//                    meteorite.updateCoordinates();
>>>>>>> 35506752259261a91817083084fe9ebbb4d124e3
//                    airplane.updateCoordinates();
//                    return false;       //collision - stop game cycle
//                }
//            } else  {
<<<<<<< HEAD
//                // if meteorite1 is in RIGHT PART of display
//                // if the meteorite1 image intersects the plane image on the LEFT border
//                if (meteorite1.getX() - meteorite1.getBitmapWidth() / 2 <
//                        airplane.getX() - airplane.getBitmapWidth() / 2) {
//                    meteorite1.updateCoordinates();
=======
//                // if meteorite is in RIGHT PART of display
//                // if the meteorite image intersects the plane image on the LEFT border
//                if (meteorite.getX() - meteorite.getBitmapWidth() / 2 <
//                        airplane.getX() - airplane.getBitmapWidth() / 2) {
//                    meteorite.updateCoordinates();
>>>>>>> 35506752259261a91817083084fe9ebbb4d124e3
//                    airplane.updateCoordinates();
//                    return false;       //collision - stop game cycle
//                }
//            }
//
//        }
//        return true;        // no collisions
//    }

    public boolean updateAndCheckCollisions() {
        cloud1.updateCoordinates();
        cloud2.updateCoordinates();
        airplane.updateCoordinates();
<<<<<<< HEAD
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
=======
        meteorite.updateCoordinates();
        updateScore();

        return checkCollisions();
    }

    private void updateScore() {
        if (meteorite.isChangedScore()) {
            ++score;
        }
>>>>>>> 35506752259261a91817083084fe9ebbb4d124e3
    }
}
