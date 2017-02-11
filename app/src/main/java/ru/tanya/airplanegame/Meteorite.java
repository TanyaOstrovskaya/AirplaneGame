package ru.tanya.airplanegame;

import android.graphics.Bitmap;

public class Meteorite extends SimpleImageObject {

    private int speed = 5;

    public Meteorite(int x, int y, Bitmap bitmap) {
        super(x, y, bitmap);
    }

    private void checkDisplayBounds() {
        if ((y + getBitmapHeight()/2) > GameManager.getHeight()) {
            y = -getBitmapHeight()/2;
            x = GameManager.getWidth() - getBitmapWidth();
        }
    }

    public void updateCoordinates() {
        y += speed;
        checkDisplayBounds();
    }
}
