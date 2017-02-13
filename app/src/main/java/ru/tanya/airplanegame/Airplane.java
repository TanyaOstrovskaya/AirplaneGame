package ru.tanya.airplanegame;

import android.graphics.Bitmap;

public class Airplane extends SimpleImageObject{

    public static final int SPEED_COEFFICIENT = 1; //speed on OX
    private int angle;

    public Airplane(int x, int y, Bitmap bitmap) {
        super(x, y, bitmap);
    }

    private boolean checkBounds() {
        // check if airplane if on display
        if ((x + getBitmapWidth()/2 >= GameManager.getWidth()) ||
        (x - getBitmapWidth()/2) <= 0)
            return false;
        else
            return true;
    }

    @Override
    public void updateCoordinates() {
        if (checkBounds()) {
            x += SPEED_COEFFICIENT*angle;
        }
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
