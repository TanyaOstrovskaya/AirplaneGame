package ru.tanya.airplanegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameManager {

    private Airplane airplane;
    private Meteorite meteorite1;
    private Meteorite meteorite2;
    private MainGamePanel gamePanel;

    private static int width;
    private static int height;

    public GameManager(MainGamePanel gamePanel, int w, int h, Context context) {
        height = h;
        width = w;
        this.gamePanel = gamePanel;
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

    private void checkCollisions() {
        //если картинка метеорита зашла на картинку самолета по правой границе
        if (meteorite1.getX()+ meteorite1.getBitmapWidth()/2 >
                airplane.getX() - airplane.getBitmapWidth()/2) {
            //если картинка метеорита зашла на картинку самолета по нижней границе
            if (meteorite1.getY() + meteorite1.getBitmapHeight() / 2 >
                    airplane.getY() - airplane.getBitmapHeight() / 2) {
             //   gamePanel.showMessage("YOU LOSE");
            }
        }
    }

    public void update() {
        meteorite1.update();
        checkCollisions();
    }
}
